package eapli.base.app.user.console.presentation.myuser;

import application.ResultProcessNotificationController;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.domain.jobopening.Phase;
import eapli.base.clientusermanagement.domain.jobopening.PhaseDetails;
import eapli.base.clientusermanagement.domain.jobopening.Status;
import eapli.framework.presentation.console.AbstractUI;
import org.antlr.v4.runtime.misc.Pair;

public class ResultProcessNotificationUI extends AbstractUI {

    private final ResultProcessNotificationController theController = new ResultProcessNotificationController();

    @Override
    protected boolean doShow() {

        Pair<JobOpening, Status> pair = theController.updatedJobOpeningAndStatus();
        JobOpening jobOpening = pair.a;

        try {
                boolean success = theController.sendEmail(Phase.INTERVIEW, jobOpening, Status.COMPLETED);
                if (success) {
                    System.out.println("Email sent successfully.");
                    return true;
                } else {
                    System.out.println("Error sending notification.");
                }
        } catch (Exception e) {
            System.out.println("Error sending notification");
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public String headline() { return "List Candidate Applications (including number of applicants)"; }
}

