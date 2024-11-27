//package eapli.base.app.user.console.presentation.myuser;
//
//
//import application.ResultCandidatesNotificationController;
//import eapli.base.candidatemanagement.domain.Candidate;
//import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
//import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
//import eapli.base.clientusermanagement.domain.jobopening.Phase;
//import eapli.base.infrastructure.persistence.PersistenceContext;
//import eapli.base.services.JobApplicationManagementService;
//import eapli.base.services.JobOpeningManagementService;
//import eapli.framework.presentation.console.AbstractUI;
//import org.springframework.security.core.parameters.P;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ResultCandidatesNotificationUI extends AbstractUI {
//    private final ResultCandidatesNotificationController theController = new ResultCandidatesNotificationController();
//
//    @Override
//    protected boolean doShow() {
//
//        try {
//
//            for (JobOpening jobOpening : theController.allJobOpening()) {
//                Iterable<JobApplication> jobApplications = theController.jobApplicationsOf(jobOpening);
//                List<JobApplication> jobApplicationsList = new ArrayList<>();
//
//                for (JobApplication jobApplication : jobApplications) {
//                    if (theController.iscandidateAcceptedForJobOpening(jobApplication.candidate(), jobOpening).isPresent()) {
//                        boolean success = theController.sendEmailCandidateAccepted(jobApplications, jobOpening);
//                        if (success) {
//                            System.out.println("Email successfully sent to candidate accepted: " + jobApplication.candidate().user().email());
//                            jobApplicationsList.add(jobApplication);
//                        } else {
//                            System.out.println("Failed to send email to candidate accepted: " + jobApplication.candidate().user().email());
//                        }
//                    }else{
//                        boolean success = theController.sendEmailCandidateNotAccepted(jobApplication.candidate(), jobOpening);
//                        if (success) {
//                            System.out.println("Email successfully sent to candidate not accepted: " + jobApplication.candidate().user().email());
//                        } else {
//                            System.out.println("Failed to send email to candidate not accepted: " + jobApplication.candidate().user().email());
//                        }
//                    }
//                }
//
//
//                if (!jobApplicationsList.isEmpty()) {
//                    boolean success = theController.sendEmailCustomer(jobOpening, jobApplicationsList);
//                    if (!success) {
//                        System.out.println("Error sending notification to customer.");
//                        return false;
//                    }
//                }
//            }
//
//        }catch (Exception e){
//            System.out.println("Error sending notification");
//            e.printStackTrace();
//            return false;
//        }
//        return false;
//    }
//
//    @Override
//    public String headline() { return "List Candidate Applications (including number of applicants)"; }
//    }
//
