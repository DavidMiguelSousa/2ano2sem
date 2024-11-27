package eapli.base.usermanagement.application;

import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.domain.jobopening.Phase;
import eapli.base.clientusermanagement.domain.rank.Rank;
import eapli.base.clientusermanagement.repositories.JobApplicationRepository;
import eapli.base.clientusermanagement.repositories.JobOpeningRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.services.JobApplicationManagementService;
import eapli.base.services.JobOpeningManagementService;

import java.util.List;
import java.util.Map;

public class RankJobApplicationsController {
    private final JobOpeningRepository jobOpeningRepo = PersistenceContext.repositories().jobOpenings();
    private final JobApplicationRepository jobApplicationRepo = PersistenceContext.repositories().jobApplications();

    private final JobOpeningManagementService jobOpeningManagementService = new JobOpeningManagementService(jobOpeningRepo);
    private final JobApplicationManagementService jobApplicationManagementService = new JobApplicationManagementService(jobApplicationRepo);
    public Iterable<JobOpening> jobOpeningsWithAnalysisPhaseOn() {
        return jobOpeningManagementService.jobOpeningsWithPhaseOn(Phase.ANALYSIS);
    }

    public Iterable<JobApplication> jobApplicationsOf(JobOpening jobOpening) {
        return jobApplicationManagementService.jobApplicationsOf(jobOpening);
    }

    public void saveJobApplicationRank(JobApplication jobApplication, Rank notRanked) {
        jobApplicationManagementService.saveJobApplicationRank(jobApplication, notRanked);
    }

    public void updateJobOpeningRankList(JobApplication jobApplication, JobOpening jobOpening) {
        jobOpeningManagementService.updateJobOpeningRanksList(jobApplication, jobOpening);
    }

    public boolean sendEmailCandidateAccepted(Rank rank, JobApplication jobApplication, JobOpening jobOpening) {
        return jobOpeningManagementService.sendEmailCandidateAccepted(rank, jobApplication, jobOpening);
    }

    public boolean sendEmailCandidateNotAccepted(JobApplication jobApplication, JobOpening jobOpening) {
        return jobOpeningManagementService.sendEmailCandidateNotAccepted(jobApplication, jobOpening);
    }

    public boolean sendEmailCustomer(Map<Rank, JobApplication> rank, JobOpening jobOpening) {
        return jobOpeningManagementService.sendEmailCustomer(rank, jobOpening);
    }
}
