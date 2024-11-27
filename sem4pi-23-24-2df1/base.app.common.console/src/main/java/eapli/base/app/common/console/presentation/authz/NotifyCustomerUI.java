package eapli.base.app.common.console.presentation.authz;

import application.ListCustomerJobOpeningController;
import application.NotifyCustomerController;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;

import java.io.IOException;

public class NotifyCustomerUI {
    private final NotifyCustomerController theController = new NotifyCustomerController();

    protected boolean doShow(JobOpening jobOpening) {
        try {
            System.out.println(theController.receiveNotification(jobOpening));
        } catch (IOException e) {
            System.out.println("Error retrieving applications from server");
            e.printStackTrace();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
