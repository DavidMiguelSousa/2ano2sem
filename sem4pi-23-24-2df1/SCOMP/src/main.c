#include "asm.h"

Config project_config = {0}; //initialize a Config structure with zero values
sem_t *sem_worker, *sem_report, *sem_exit; //declare semaphore pointers for worker, report, and exit
shared_data_t *shm_1 = {0}, **shm_2 = {0}; //initialize a shared_data_t pointer with zero values
struct stat initial_stat, current_stat; //declare stat structures for file status
volatile sig_atomic_t keep_running = 1; //declare a volatile sig_atomic_t variable to keep track of running state

void handle_signal(int signal) {
    keep_running = 0; //signal handler to stop the program when a signal is received
}

void report(const char *directory, int file_count, char file_names[][MAX_FILENAME_LENGTH]) {
    char report_filename[512]; //buffer to hold the report file name
    snprintf(report_filename, sizeof(report_filename), "%s/files-report.txt", directory); //construct the report file name

    FILE *report_file = fopen(report_filename, "w"); //open the report file in write mode
    if (report_file == NULL) {
        perror("Failed to create report file"); //print error if the report file can't be created
        return;
    }

    fprintf(report_file, "Directory name: %s\n", directory); //write the directory name to the report file
    fprintf(report_file, "Number of files copied to this directory: %d\n", file_count); //write the number of files added to the report file
    for (int i = 0; i < file_count; i++) {
        fprintf(report_file, "File %d: %s\n", i+1, file_names[i]); //write each file name to the report file
    }

    fclose(report_file); //close the report file
}

void parent_report() {
	struct stat st = {0};
	char directory[1024] = {0};

	for (int i = 0; i < shm_1->number_newfiles; i++) {
		memset(directory, 0, sizeof(directory));
		char newfile[MAX_FILENAME_LENGTH]; //declare a new file name buffer
        strncpy(newfile, shm_1->name_newfiles[i], sizeof(newfile) - 1); //copy the new file name
        newfile[sizeof(newfile) - 1] = '\0'; //null-terminate the new file name

		if (strstr(newfile, "-candidate-data.txt") == NULL) {
			continue;  // if it isn't a candidate data file, skip it
		}

        char candidate_num[16]; //declare a candidate number buffer
        sscanf(newfile, "%15[^-]", candidate_num); //extract the candidate number from the new file name

        char data_filename[512]; //declare a data file name buffer
        snprintf(data_filename, sizeof(data_filename), "%s/%s-candidate-data.txt", project_config.inputDirectory, candidate_num); //construct the data file name

		FILE *data_file = fopen(data_filename, "r"); //open the data file in read mode
        if (data_file == NULL) {
            perror("Failed to open candidate data file"); //print error if data file can't be opened
            continue; //continue to the next file
        }

        char first_line[MAX_FILENAME_LENGTH], second_line[MAX_FILENAME_LENGTH];
        fgets(first_line, sizeof(first_line), data_file);
        fgets(second_line, sizeof(second_line), data_file);
        fclose(data_file);

        first_line[strcspn(first_line, "\n")] = 0; //remove the newline character from the first line
        second_line[strcspn(second_line, "\n")] = 0; //remove the newline character from the second line

        snprintf(directory, sizeof(directory), "%s/%s/%s", project_config.outputDirectory, first_line, second_line); //construct the output path

		if (stat(directory, &st) == -1) {
			mkdir(directory, 0777);
		}
	}

	report(directory, shm_1->number_newfiles, shm_1->name_newfiles);
}

int count_files(const char *dir_path) {
    DIR *dir; //declare a directory pointer
    struct dirent *entry; //declare a directory entry pointer
    int file_count = 0; //initialize file count to 0

    dir = opendir(dir_path); //open the directory
    if (dir == NULL) {
        perror("Failed to open directory"); //print error if directory can't be opened
        return -1;
    }

    while ((entry = readdir(dir)) != NULL) { //read each entry in the directory
        if (entry->d_type == DT_REG) { //check if the entry is a regular file
            file_count++; //increment file count
        }
    }

    closedir(dir); //close the directory
    return file_count; //return the file count
}

int count_lines(const char *file) {
    FILE *fp; //declare a file pointer
    int count = 0; //initialize line count to 0
    char c; //declare a character variable

    fp = fopen(file, "r"); //open the file in read mode
    if (fp == NULL) {
        perror("Failed to open file"); //print error if file can't be opened
        return -1;
    }

    for (c = getc(fp); c != EOF; c = getc(fp)) { //read each character in the file
        if (c == '\n') { //check if the character is a newline
            count++; //increment line count
        }
    }

    fclose(fp); //close the file

    if (c != '\n' && count != 0) { //check if the last character is not a newline and count is not zero
        count++; //increment line count
    }

    return count; //return the line count
}

void worker(int id) {
    sem_wait(sem_worker); //wait on the worker semaphore

    int i, files_added = 0;

    if (sem_trywait(sem_exit) == 0) { //try to wait on the exit semaphore
        return;
    }

    char directory[512];
    char added_files[MAX_FILES][MAX_FILENAME_LENGTH]; //buffer to store the names of added files

    for (i = 0; i < shm_2[id]->number_newfiles; i++) {
        char newfile[MAX_FILENAME_LENGTH]; //declare a new file name buffer
        strncpy(newfile, shm_2[id]->name_newfiles[i], sizeof(newfile) - 1); //copy the new file name
        newfile[sizeof(newfile) - 1] = '\0'; //null-terminate the new file name

        char candidate_num[16]; //declare a candidate number buffer
        sscanf(newfile, "%15[^-]", candidate_num); //extract the candidate number from the new file name

        char data_filename[512]; //declare a data file name buffer
        snprintf(data_filename, sizeof(data_filename), "%s/%s-candidate-data.txt", project_config.inputDirectory, candidate_num); //construct the data file name

        FILE *data_file = fopen(data_filename, "r"); //open the data file in read mode
        if (data_file == NULL) {
            perror("Failed to open candidate data file"); //print error if data file can't be opened
            continue; //continue to the next file
        }

        char first_line[MAX_FILENAME_LENGTH], second_line[MAX_FILENAME_LENGTH]; //declare buffers for the first and second lines
        fgets(first_line, sizeof(first_line), data_file); //read the first line
        fgets(second_line, sizeof(second_line), data_file); //read the second line

        first_line[strcspn(first_line, "\n")] = 0; //remove the newline character from the first line
        second_line[strcspn(second_line, "\n")] = 0; //remove the newline character from the second line

        fclose(data_file); //close the data file

        char output_path[512]; //declare an output path buffer
        snprintf(output_path, sizeof(output_path), "%s/%s/%s", project_config.outputDirectory, first_line, second_line); //construct the output path

        char mkdir_cmd[512]; //declare a mkdir command buffer
        snprintf(mkdir_cmd, sizeof(mkdir_cmd), "mkdir -p \"%s\"", output_path); //construct the mkdir command
        system(mkdir_cmd); //execute the mkdir command

        char src_path[512], dst_path[512]; //declare source and destination path buffers
        snprintf(src_path, sizeof(src_path), "%s/%s", project_config.inputDirectory, newfile); //construct the source path
        snprintf(dst_path, sizeof(dst_path), "%s/%s", output_path, newfile); //construct the destination path

        char cp_cmd[1024]; //declare a copy command buffer
        snprintf(cp_cmd, sizeof(cp_cmd), "cp \"%s\" \"%s\"", src_path, dst_path); //construct the copy command
        system(cp_cmd); //execute the copy command

        printf("Worker %d copied file: %s\n", id, newfile); //print file copied message

        //add file name to the report buffer
        strncpy(added_files[files_added], newfile, sizeof(added_files[files_added]) - 1);
        added_files[files_added][sizeof(added_files[files_added]) - 1] = '\0';
        files_added++;

        strncpy(directory, output_path, sizeof(directory) - 1);
		directory[sizeof(directory) - 1] = '\0';
    }

    //const char *directory, int file_count, char file_names[][MAX_FILENAME_LENGTH]

    report(directory, files_added, added_files);

    sem_post(sem_report); //post to the report semaphore
}

void trim_last_empty_line(FILE *file) {
    fseek(file, 0, SEEK_END); //move to the end of the file
    long fileSize = ftell(file); //get the file size
    if (fileSize == 0) return; //return if the file is empty

    long pos = fileSize - 1; //set the position to the last character
    fseek(file, pos, SEEK_SET); //move to the position
    char ch = fgetc(file); //get the character at the position

    while ((ch == '\n' || ch == '\r') && pos > 0) { //check if the character is a newline or carriage return
        fseek(file, --pos, SEEK_SET); //move to the previous character
        ch = fgetc(file); //get the character at the position
    }

    if (ch == '\n' || ch == '\r') {
        ftruncate(fileno(file), pos); //truncate the file if the character is a newline or carriage return
    } else {
        ftruncate(fileno(file), pos + 1); //truncate the file to the position
    }
}

void watcher() {
    int count_filesdb, count_inputdirectory, i;
    DIR *dir_inputdirectory; //declare a directory pointer
    FILE *file; //declare a file pointer
    struct dirent *entry_input; //declare a directory entry pointer
    char line[1024]; //declare a line buffer

    i = 0;
    printf("db files: %s\n", project_config.files_db); //print the files database path

    file = fopen(project_config.files_db, "r+"); //open the files database in read/write mode
    if (file == NULL) {
        perror("Failed to open files data base"); //print error if the files database can't be opened
        exit(1);
    }

    dir_inputdirectory = opendir(project_config.inputDirectory); //open the input directory
    if (dir_inputdirectory == NULL) {
        perror("Failed to open input dir"); //print error if the input directory can't be opened
        exit(1);
    }

    do {
        count_inputdirectory = count_files(project_config.inputDirectory); //count the files in the input directory
        printf("count files file bot: %d\n", count_inputdirectory); //print the file count

        count_filesdb = count_lines(project_config.files_db); //count the lines in the files database
        printf("count files db: %d\n", count_filesdb); //print the line count

        if (count_inputdirectory == 0 || count_inputdirectory == count_filesdb) {
            printf("searching...\n"); //print searching message
            sleep(project_config.checkInterval); //sleep for the check interval
        }
    } while (count_inputdirectory == 0 || count_inputdirectory == count_filesdb); //loop while there are no new files

    if (count_inputdirectory < 0 || count_filesdb < 0) {
        printf("Failed to count files!\n"); //print error if file counting failed
        fclose(file); //close the file
        closedir(dir_inputdirectory); //close the directory
        exit(EXIT_FAILURE);

    } else if (count_filesdb < count_inputdirectory) {
        rewinddir(dir_inputdirectory); //rewind the input directory
        printf("while input dir start\n"); //print message

        while ((entry_input = readdir(dir_inputdirectory)) != NULL) {
            if (entry_input->d_type == DT_REG) {
                int found = 0;

                rewind(file); //rewind the file

                printf("while db dir start\n"); //print message

                while (fgets(line, sizeof(line), file) != NULL) {
                    line[strcspn(line, "\n")] = 0; //remove newline from the line

                    if (strcmp(entry_input->d_name, line) == 0) {
                        found = 1; //set found if the file name matches
                        break;
                    }
                }

                if (!found) {
                    fseek(file, 0, SEEK_END); //seek to the end of the file
                    fprintf(file, "%s\n", entry_input->d_name); //append the new file name to the database

                    if (shm_1->number_newfiles < MAX_FILES) {
                        strncpy(shm_1->name_newfiles[i], entry_input->d_name, sizeof(shm_1->name_newfiles[i]) - 1); //copy the new file name to shared memory
                        shm_1->name_newfiles[i][sizeof(shm_1->name_newfiles[i]) - 1] = '\0'; //null-terminate the new file name

                        shm_1->number_newfiles++; //increment the new files count

                        printf("Appending new file: %s\n", entry_input->d_name); //print message
                        printf("shm->number_newfiles = %d\n", shm_1->number_newfiles); //print the new files count
                        printf("shm->name_newfiles[%d] = %s\n", i, shm_1->name_newfiles[i]); //print the new file name

                        i++;
                    } else {
                        printf("Error: Maximum number of new files reached.\n"); //print error if maximum files reached
                        break;
                    }
                }
            }
        }
        printf("while db dir end\n"); //print message

    } else if (count_filesdb > count_inputdirectory) {
        printf("\nFiles inconsistent! Follow these steps:\n1- Delete the data base's file content\n2- Rerun your codec.\n\n"); //print error if files are inconsistent
    } else {
        printf("No more new file!\n"); //print message if no more new files
    }

    printf("while input dir end\n"); //print message

    trim_last_empty_line(file); //trim the last empty line from the file

    fclose(file); //close the file
    closedir(dir_inputdirectory); //close the directory
}

void setup_signal_handler() {
    struct sigaction sa;
    sa.sa_handler = handle_signal; //set the signal handler function
    sa.sa_flags = 0;
    sigemptyset(&sa.sa_mask); //initialize the signal mask
    sigaction(SIGINT, &sa, NULL); //set the signal action for SIGINT
}

void analyze_config(char *filename, Config *cfg) {
    FILE *file = fopen(filename, "r"); //open the config file in read mode
    char line[512]; //declare a line buffer

    if (!file) {
        perror("Failed to open config file"); //print error if config file can't be opened
        exit(1);
    }

    while (fgets(line, sizeof(line), file)) {
        line[strcspn(line, "\n")] = 0; //remove newline from the line

        char *key = strtok(line, "="); //tokenize the line to get the key
        char *value = strtok(NULL, ""); //tokenize the line to get the value

        if (!key || !value) {
            printf("Warning: Null key or value found, skipping line.\n"); //print warning if key or value is null
            continue;
        }

        while (*value == ' ') value++; //skip leading spaces in the value

        if (strcmp(key, "input_directory") == 0) {
            strncpy(cfg->inputDirectory, value, sizeof(cfg->inputDirectory) - 1); //copy the input directory path to config
            cfg->inputDirectory[sizeof(cfg->inputDirectory) - 1] = '\0'; //null-terminate the input directory path
        } else if (strcmp(key, "output_directory") == 0) {
            strncpy(cfg->outputDirectory, value, sizeof(cfg->outputDirectory) - 1); //copy the output directory path to config
            cfg->outputDirectory[sizeof(cfg->outputDirectory) - 1] = '\0'; //null-terminate the output directory path
        } else if (strcmp(key, "files_db") == 0) {
            strncpy(cfg->files_db, value, sizeof(cfg->files_db) - 1); //copy the files database path to config
            cfg->files_db[sizeof(cfg->files_db) - 1] = '\0'; //null-terminate the files database path
        } else if (strcmp(key, "number_of_workers") == 0) {
            cfg->workerCount = atoi(value); //convert the number of workers to integer and assign to config
        } else if (strcmp(key, "check_interval") == 0) {
            cfg->checkInterval = atoi(value); //convert the check interval to integer and assign to config
        }
    }

    fclose(file); //close the config file
}

void splitFilesForWorkers() {
    int i, j;

    for (i = 0; i < project_config.workerCount; i++) {
		shm_2[i]->number_newfiles = 0;
		printf("Initialized shm_2[%d] with zero files.\n", i);
	}

    for (i = 0; i < shm_1->number_newfiles; i++) {
        char newfile[MAX_FILENAME_LENGTH]; //declare a new file name buffer
        strncpy(newfile, shm_1->name_newfiles[i], sizeof(newfile) - 1); //copy the new file name
        newfile[sizeof(newfile) - 1] = '\0'; //null-terminate the new file name

        char index[16]; //declare a candidate number buffer
        sscanf(newfile, "%15[^-]", index); //extract the candidate number from the new file name
        int worker_id = atoi(index) % project_config.workerCount; //determine the worker id
		int file_repeated = 0;

        if (worker_id < 0 || worker_id > project_config.workerCount){
			printf("%d is an invalid worker.\n", worker_id);
			continue;
		}

        if (shm_2[worker_id]->number_newfiles < MAX_FILES) {
			for (j = 0; j < shm_2[worker_id]->number_newfiles; j++){
				if (strcmp(shm_2[worker_id]->name_newfiles[j], newfile) == 0) {
					printf("repeated file found\n");
					file_repeated = 1;
					break;
				}
			}

			if (file_repeated == 0) {
				printf("Adding new file at index %d to shm_2[%d]\n", shm_2[worker_id]->number_newfiles, worker_id);
				strncpy(shm_2[worker_id]->name_newfiles[shm_2[worker_id]->number_newfiles], newfile, MAX_FILENAME_LENGTH - 1);
				shm_2[worker_id]->name_newfiles[shm_2[worker_id]->number_newfiles][MAX_FILENAME_LENGTH - 1] = '\0';
				shm_2[worker_id]->number_newfiles++;
				printf("shm_2[%d] now has %d new files.\n", worker_id, shm_2[worker_id]->number_newfiles);
			}

        } else {
            fprintf(stderr, "Maximum file limit reached for worker %d!\n", worker_id);
        }
    }
}

int main() {
    printf("inicio main\n"); //print start message

    int i, fd1, fd2;
    struct sigaction act;

    printf("chamar analyze\n"); //print analyze message
    analyze_config("config.txt", &project_config); //call analyze_config function
    printf("chamou analyze\n"); //print analyzed message

    printf("Worker count: %d\n", project_config.workerCount); //print worker count
    printf("Input directory: %s\n", project_config.inputDirectory); //print input directory
    printf("FILES DB directory: %s\n", project_config.files_db); //print files database directory

    pid_t pid_workers[project_config.workerCount], pid_watcher; //declare workers and watcher process IDs

    printf("Attempting to stat directory: %s\n", project_config.inputDirectory); //print stat directory message
    if (stat(project_config.inputDirectory, &initial_stat) == -1) {
        perror("stat."); //print error if stat fails
        exit(2);
    }

    setup_signal_handler(); //call setup_signal_handler function

    memset(&act, 0, sizeof(struct sigaction)); //initialize sigaction structure with zeros

    printf("blocking signals\n"); //print blocking signals message

    if (sigfillset(&act.sa_mask) != 0)
        perror("sigfillset"); //print error if sigfillset fails

    act.sa_handler = handle_signal; //set the signal handler function
    sigaction(SIGINT, &act, NULL); //set the signal action for SIGINT

    printf("creat shm_1\n"); //print create shared memory message

    if ((fd1 = shm_open(SHARED_MEMORY_1, O_CREAT | O_RDWR, S_IRUSR | S_IWUSR)) == -1) {
        perror("shmopen"); //print error if shm_open fails
        exit(1);
    }

    printf("define shm_1\n"); //print define shared memory message

    if (ftruncate(fd1, sizeof(shared_data_t)) == -1) {
        perror("ftruncate"); //print error if ftruncate fails
        exit(2);
    }

    printf("map shm_1\n"); //print map shared memory message

    printf("shm_1\n");

    if ((shm_1 = (shared_data_t *) mmap(0, sizeof(shared_data_t), PROT_READ | PROT_WRITE, MAP_SHARED, fd1, 0)) == MAP_FAILED) {
        perror("mmap"); //print error if mmap fails
        exit(3);
    }

    shm_1->number_newfiles = 0; //initialize number of new files to 0
    memset(shm_1->name_newfiles, 0, sizeof(shm_1->name_newfiles)); //initialize new file names with zeros

    printf("shm_2\n");

    shm_2 = malloc(project_config.workerCount * sizeof(shared_data_t *)); //allocating pointer to shm_2
    if (!shm_2) {
        perror("malloc failed.");
        exit(2);
    }

    printf("creat shm_2\n"); //print create shared memory message

    if ((fd2 = shm_open(SHARED_MEMORY_2, O_CREAT | O_RDWR, S_IRUSR | S_IWUSR)) == -1) {
        perror("shmopen"); //print error if shm_open fails
        exit(1);
    }

    printf("define shm_2\n"); //print define shared memory message

    if (ftruncate(fd2, sizeof(shared_data_t)) == -1) {
        perror("ftruncate"); //print error if ftruncate fails
        exit(2);
    }

    for (i = 0; i < project_config.workerCount; i++) {
        printf("map shm_2[%d]\n", i); //print map shared memory message

        if ((shm_2[i] = (shared_data_t *) mmap(0, sizeof(shared_data_t), PROT_READ | PROT_WRITE, MAP_SHARED, fd2, 0)) == MAP_FAILED) {
            perror("mmap"); //print error if mmap fails
            exit(3);
        }

        shm_2[i]->number_newfiles = 0; //initialize number of new files to 0
        memset(shm_2[i]->name_newfiles, 0, sizeof(shm_2[i]->name_newfiles)); //initialize new file names with zeros
    }

    printf("create sem\n"); //print create semaphore message

    if ((sem_worker = sem_open(SEMAPHORE_WORKER, O_CREAT, 0644, 0)) == SEM_FAILED) {
        perror("sem_open"); //print error if sem_open fails for worker semaphore
        exit(4);
    }

    if ((sem_report = sem_open(SEMAPHORE_REPORT, O_CREAT, 0644, 0)) == SEM_FAILED) {
        perror("sem_open"); //print error if sem_open fails for report semaphore
        exit(4);
    }

    if ((sem_exit = sem_open(SEMAPHORE_EXIT, O_CREAT, 0644, 0)) == SEM_FAILED) {
        perror("sem_open"); //print error if sem_open fails for exit semaphore
        exit(4);
    }

    pid_watcher = fork(); //create a new watcher proccess
    if (pid_watcher < 0) {
        perror("fork."); //print error if fork fails
        exit(3);
    } else if (pid_watcher == 0) {
        printf("watcher called\n"); //print watcher called message
        watcher(); //call watcher function
        printf("watcher finished\n"); //print watcher finished message

        splitFilesForWorkers(); //distribute files to workers

        for (i = 0; i < project_config.workerCount; i++) {
            sem_post(sem_worker); //post to worker semaphore for each worker
        }

        exit(EXIT_SUCCESS);
    } else {
        waitpid(pid_watcher, NULL, 0); //wait for the watcher process to finish

        for (i = 0; i < project_config.workerCount; i++) {
            pid_workers[i] = fork(); //create a new worker process

            if (pid_workers[i] < 0) {
                perror("fork."); //print error if fork fails
                exit(3);
            } else if (pid_workers[i] == 0) {
                printf("worker %d called\n", i); //print worker called message
                worker(i); //call worker function
                printf("worker %d finished\n", i); //print worker finished message
                exit(EXIT_SUCCESS);
            }
        }

        for (i = 0; i < project_config.workerCount; i++) {
            sem_wait(sem_report); //wait on the report semaphore for each worker
        }

        for (i = 0; i < project_config.workerCount; i++) {
            sem_post(sem_exit); //post to the exit semaphore for each worker
        }
    }

	//parent_report();

    munmap(shm_1, sizeof(shared_data_t)); //unmap the shared memory 1
    for (i = 0; i < project_config.workerCount; i++) {
        munmap(shm_2[i], sizeof(shared_data_t)); //unmap the shared memory 2
    }
    close(fd1); //close the shared memory file descriptor 1
    close(fd2); //close the shared memory file descriptor 2

    shm_unlink(SHARED_MEMORY_1); //unlink the shared memory 1
    shm_unlink(SHARED_MEMORY_2); //unlink the shared memory 2
    sem_close(sem_worker); //close the worker semaphore
    sem_close(sem_report); //close the report semaphore
    sem_close(sem_exit); //close the exit semaphore
    sem_unlink(SEMAPHORE_WORKER); //unlink the worker semaphore
    sem_unlink(SEMAPHORE_REPORT); //unlink the report semaphore
    sem_unlink(SEMAPHORE_EXIT); //unlink the exit semaphore

    kill(getpid(), SIGINT); //send SIGINT to the current process

    return 0;
}
