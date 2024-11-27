package eapli.base.usermanagement.application;

import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.domain.jobopening.Phase;
import eapli.base.clientusermanagement.repositories.JobApplicationRepository;
import eapli.base.clientusermanagement.repositories.JobOpeningRepository;
import eapli.base.clientusermanagement.repositories.JobRequirementsRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.interviewmanagement.repositories.InterviewModelRepository;
import eapli.base.services.InterviewManagementService;
import eapli.base.services.JobApplicationManagementService;
import eapli.base.services.JobOpeningManagementService;
import eapli.base.services.JobRequirementsManagementService;

import java.util.List;

public class VerifyJobOpeningsRequirementsController {
    private final JobApplicationRepository jobApplicationRepository = PersistenceContext.repositories().jobApplications();
    private final JobOpeningRepository jobOpeningRepository = PersistenceContext.repositories().jobOpenings();
    private final JobRequirementsRepository jobRequirementsRepository = PersistenceContext.repositories().jobRequirements();

    private final JobApplicationManagementService jobApplicationManagementService = new JobApplicationManagementService(jobApplicationRepository);
    private final JobOpeningManagementService jobOpeningManagementService = new JobOpeningManagementService(jobOpeningRepository);
    private final JobRequirementsManagementService jobRequirementsManagementService = new JobRequirementsManagementService(jobRequirementsRepository);

    public Iterable<JobOpening> jobOpeningsWithApplicationPhase() {
        return jobOpeningManagementService.jobOpeningsWithPhaseOn(Phase.APPLICATION);
    }

    public Iterable<JobApplication> jobApplicationsOf(JobOpening jobOpening) {
        return jobApplicationManagementService.jobApplicationsOf(jobOpening);
    }

    public List<JobApplication> verifyJobRequirements(List<JobApplication> jobApplicationList, JobOpening jobOpening) throws Exception {
        return jobRequirementsManagementService.verifyJobRequirements(jobApplicationList, jobOpening);
    }

    public void saveRequirements(List<JobApplication> requirements) {
        jobApplicationManagementService.saveRequirements(requirements);
    }
}
