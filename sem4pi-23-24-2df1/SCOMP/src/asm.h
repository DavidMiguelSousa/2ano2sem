#ifndef ASM_H
#define ASM_H

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <dirent.h>
#include <errno.h>
#include <semaphore.h>
#include <fcntl.h>
#include <regex.h>

#include <sys/stat.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/mman.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/sem.h>

#define SHARED_MEMORY_1 "/shm_filebot_1"
#define SHARED_MEMORY_2 "/shm_filebot_2"
#define SEMAPHORE_WORKER "/sem_filebot_worker"
#define SEMAPHORE_REPORT "/sem_filebot_report"
#define SEMAPHORE_EXIT "/sem_filebot_exit"

#define MAX_FILES 1024
#define MAX_FILENAME_LENGTH 256

typedef struct {
    char inputDirectory[MAX_FILENAME_LENGTH];
    char outputDirectory[MAX_FILENAME_LENGTH];
    char files_db[MAX_FILENAME_LENGTH];
    int workerCount;
    int checkInterval;
} Config;

typedef struct {
    char name_newfiles[MAX_FILES][MAX_FILENAME_LENGTH];
    int number_newfiles;
} shared_data_t;

extern struct stat initial_stat, current_stat;
extern Config project_config;
extern shared_data_t *shm_1, **shm_2;

#endif // ASM_H
