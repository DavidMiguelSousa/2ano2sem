package eapli.base.usermanagement.application;

import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.repositories.JobApplicationRepository;
import eapli.base.clientusermanagement.repositories.JobOpeningRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.services.JobApplicationManagementService;
import eapli.base.services.JobOpeningManagementService;

import java.util.List;

public class ListJobOpeningsInterviewGradesController {
    private final JobOpeningRepository jobOpeningRepository = PersistenceContext.repositories().jobOpenings();
    private final JobApplicationRepository jobApplicationRepository = PersistenceContext.repositories().jobApplications();
    private final JobOpeningManagementService jobOpeningManagementService = new JobOpeningManagementService(jobOpeningRepository);
    private final JobApplicationManagementService jobApplicationManagementService = new JobApplicationManagementService(jobApplicationRepository);

    public Iterable<JobOpening> jobOpeningsWithPhaseInterviewCompleted() {
        return jobOpeningManagementService.jobOpeningsWithPhaseInterviewCompleted();
    }

    public Iterable<JobApplication> jobApplicationsOf(JobOpening jobOpening) {
        return jobApplicationManagementService.jobApplicationsOf(jobOpening);
    }

    public List<JobApplication> filterJobApplicationsWithInterview(List<JobApplication> jobApplications) {
        return jobApplicationManagementService.filterJobApplicationsWithInterview(jobApplications);
    }
}
