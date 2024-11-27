package eapli.base.usermanagement.application;

import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.domain.jobopening.Phase;
import eapli.base.clientusermanagement.repositories.JobApplicationRepository;
import eapli.base.clientusermanagement.repositories.JobOpeningRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.interviewmanagement.repositories.InterviewModelRepository;
import eapli.base.services.InterviewManagementService;
import eapli.base.services.JobApplicationManagementService;
import eapli.base.services.JobOpeningManagementService;

import java.util.List;

public class EvaluateJobOpeningsInterviewsController {
    private final JobApplicationRepository jobApplicationRepository = PersistenceContext.repositories().jobApplications();
    private final JobOpeningRepository jobOpeningRepository = PersistenceContext.repositories().jobOpenings();
    private final InterviewModelRepository interviewModelRepository = PersistenceContext.repositories().interviewModels();

    private final JobApplicationManagementService jobApplicationManagementService = new JobApplicationManagementService(jobApplicationRepository);
    private final JobOpeningManagementService jobOpeningManagementService = new JobOpeningManagementService(jobOpeningRepository);
    private final InterviewManagementService interviewManagementService = new InterviewManagementService(interviewModelRepository);

    public List<JobOpening> jobOpeningsWithInterviewPhaseCompleted() {
        return jobOpeningManagementService.jobOpeningsWithPhaseInterviewCompleted();
    }

    public Iterable<JobApplication> jobApplicationsOf(JobOpening jobOpening) {
        return jobApplicationManagementService.jobApplicationsOf(jobOpening);
    }

    public List<JobApplication> evaluateInterviews(List<JobApplication> jobApplicationList, JobOpening jobOpening) throws Exception {
        return interviewManagementService.evaluateInterviews(jobApplicationList, jobOpening);
    }

    public void saveGrades(List<JobApplication> grades) {
        jobApplicationManagementService.saveGrades(grades);
    }
}
