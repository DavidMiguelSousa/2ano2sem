//package application;
//
//import eapli.base.candidatemanagement.domain.Candidate;
//import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
//import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
//import eapli.base.infrastructure.persistence.PersistenceContext;
//import eapli.base.services.JobApplicationManagementService;
//import eapli.base.services.JobOpeningManagementService;
//import eapli.base.usermanagement.domain.BaseRoles;
//
//import java.util.List;
//import java.util.Optional;
//
//public class ResultCandidatesNotificationController {
//
//    private final JobApplicationManagementService jobApplicationManagementService;
//    private final JobOpeningManagementService jobOpeningManagementService;
//
//    public ResultCandidatesNotificationController() {
//        this.jobOpeningManagementService = new JobOpeningManagementService(PersistenceContext.repositories().jobOpenings());
//        this.jobApplicationManagementService = new JobApplicationManagementService(PersistenceContext.repositories().jobApplications());
//    }
//
//    public Optional<Candidate> iscandidateAcceptedForJobOpening(Candidate candidate, JobOpening jobOpening){
//        return jobApplicationManagementService.iscandidateAcceptedForJobOpening(candidate, jobOpening);
//    }
//
//    public Iterable<JobApplication> jobApplicationsOf(JobOpening jobOpening) {
//        return jobApplicationManagementService.jobApplicationsOf(jobOpening);
//    }
//
//    public Iterable<JobOpening> allJobOpening() {
//        return jobOpeningManagementService.findAllAvailable();
//    }
//
//    public boolean sendEmailCandidateAccepted(List<JobApplication> jobApplications, JobOpening jobOpening) {
//        return jobOpeningManagementService.sendEmailCandidateAccepted(jobApplications, jobOpening);
//    }
//
//    public boolean sendEmailCandidateNotAccepted(Candidate candidate, JobOpening jobOpening) {
//        return jobOpeningManagementService.sendEmailCandidateNotAccepted(candidate, jobOpening);
//    }
//
//    public boolean sendEmailCustomer(JobOpening jobOpening, List<JobApplication> jobApplicationsList) {
//        return jobOpeningManagementService.sendEmailCustomer(jobOpening, jobApplicationsList);
//    }
//}
