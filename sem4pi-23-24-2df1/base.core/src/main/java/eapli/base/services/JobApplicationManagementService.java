package eapli.base.services;

import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.clientusermanagement.domain.ApprovalStatus;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplicationBuilder;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplicationReference;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.domain.rank.Rank;
import eapli.base.clientusermanagement.domain.rank.RankStatus;
import eapli.base.clientusermanagement.repositories.JobApplicationRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class JobApplicationManagementService {
    private final JobApplicationRepository jobApplicationRepo;

    public JobApplicationManagementService(JobApplicationRepository jobApplicationRepo) {
        this.jobApplicationRepo = jobApplicationRepo;
    }

    public JobApplication registerNewJobApplication(JobApplicationReference jobApplicationReference, JobOpening jobOpening, Candidate candidate, ApprovalStatus approvalStatus) {
        final var jobApplicationBuilder = new JobApplicationBuilder();
        jobApplicationBuilder.with(jobApplicationReference, jobOpening, candidate).withJobApplicationStatus(approvalStatus).build();
        final var newJobOpening = jobApplicationBuilder.build();
        return jobApplicationRepo.save(newJobOpening);
    }

    public JobApplication registerNewJobApplication(JobApplicationReference jobApplicationReference, JobOpening jobOpening, Candidate candidate) {
        final var jobApplicationBuilder = new JobApplicationBuilder();
        jobApplicationBuilder.with(jobApplicationReference, jobOpening, candidate).withJobApplicationStatus(ApprovalStatus.PENDING).build();
        final var newJobOpening = jobApplicationBuilder.build();
        return jobApplicationRepo.save(newJobOpening);
    }

    public Iterable<JobApplication> applicationsByCandidate(Candidate candidate) {
        return jobApplicationRepo.applicationsByCandidate(candidate);
    }

    public Iterable<JobApplication> jobApplicationsOf(JobOpening jobOpening) {
        return jobApplicationRepo.applicationsByJobOpening(jobOpening);
    }

    public Iterable<JobApplication> jobApplications() {
        return jobApplicationRepo.findAll();
    }

    public ApprovalStatus jobApplicationStatus(JobApplicationReference jobApplicationReference) {
        if (jobApplicationRepo.ofIdentity(jobApplicationReference).isPresent()) {
            return jobApplicationRepo.ofIdentity(jobApplicationReference).get().status();
        } else {
            return ApprovalStatus.REFUSED;
        }
    }

    public void saveJobApplicationRank(JobApplication jobApplication, Rank rank) {
        jobApplication.defineRank(rank);

        jobApplicationRepo.save(jobApplication);
    }

    public int numberOfApplications(JobOpening jobOpening) {
        Iterator<JobApplication> iterator = jobApplicationRepo.applicationsByJobOpening(jobOpening).iterator();
        List<JobApplication> jobApplications = new ArrayList<>();
        iterator.forEachRemaining(jobApplications::add);
        return jobApplications.size();
    }

    public List<File> files(JobApplication jobApplication, JobOpening jobOpening) {
        try {
            File directory = new File("SCOMP/files/fileBotFiles/" + jobOpening.jobReference() + "/" + jobApplication.candidate().identity());

            List<File> filesList = new ArrayList<>();

            if (directory.exists() && directory.isDirectory()) {
                File[] files = directory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile()) {
                            filesList.add(file);
                        }
                    }
                }
            }

            if (filesList.isEmpty()) filesList = new ArrayList<>();

            return filesList;

        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void saveGrades(List<JobApplication> grades) {
        for (JobApplication jobApplication : grades) {
            jobApplicationRepo.save(jobApplication);
        }
    }

    public void saveRequirements(List<JobApplication> grades) {
        for (JobApplication jobApplication : grades) {
            jobApplicationRepo.save(jobApplication);
        }
    }

    public List<JobApplication> filterJobApplicationsWithInterview(List<JobApplication> jobApplications) {
        return jobApplications.stream().filter(jobApplication -> jobApplication.interview().isPresent()).collect(Collectors.toList());
    }

    public Optional<Candidate> iscandidateAcceptedForJobOpening(Candidate candidate, JobOpening jobOpening) {

        Iterable<JobApplication> jobApplications = jobApplicationRepo.applicationsByCandidate(candidate);

        for (JobApplication jobApplication : jobApplications) {
            if (jobApplication.jobOpening().equals(jobOpening) && jobApplication.status() == ApprovalStatus.ACCEPTED) {
                if (jobApplication.rank().status().equals(RankStatus.RANKED)) {
                    int rankPosition = jobApplication.rank().rank();
                    int numberOfVacancies = jobOpening.numberOfVacancies().value();
                    if (rankPosition <= numberOfVacancies) {
                        return Optional.of(candidate);
                    }
                }
            }
        }
        return Optional.empty();
    }

    public JobApplication recordInterviewTimeStamp(JobApplication jobApplication, Calendar timestamp) {
        jobApplication.recordInterviewTimestamp(timestamp);
        return jobApplicationRepo.save(jobApplication);
    }

    public void displayApplicationData(JobApplication jobApplication) {
        String path = "SCOMP/files/fileBotFiles/";
        String jobApplicationID = jobApplication.identity().toString();
        String candidateEmail = jobApplication.candidate().toString();
        String fullPath = path + jobApplicationID + "/" + candidateEmail + "/";

        String curriculumPath = jobApplication.curriculumVitae().cvFileName();
        List<String> candidateData = jobApplication.additionalData().files();

        List<String> allPaths = new ArrayList<>();

        for (String data : candidateData) {
            allPaths.add(fullPath + data);
        }

        allPaths.add(fullPath + curriculumPath);

        System.out.println("Displaying all data related to job application: " + jobApplicationID + "\n\n");

        for (String singlePath : allPaths) {
            System.out.println("----------------------------------------------------\n");
            readFileAndDisplay(singlePath);
            System.out.println("----------------------------------------------------\n");
        }

    }

    public static void readFileAndDisplay(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            System.out.println(content);
        } catch (IOException e) {
            // Handle the exception if the file is not found or can't be read
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}
