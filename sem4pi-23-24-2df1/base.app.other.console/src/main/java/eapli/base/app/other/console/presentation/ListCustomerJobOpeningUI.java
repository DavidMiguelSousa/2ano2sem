package eapli.base.app.other.console.presentation;

import application.ListCandidateApplicationsController;
import application.ListCustomerJobOpeningController;
import eapli.framework.presentation.console.AbstractUI;

import java.io.IOException;

public class ListCustomerJobOpeningUI extends AbstractUI {

    private final ListCustomerJobOpeningController theController = new ListCustomerJobOpeningController();

    @Override
    protected boolean doShow() {
        try {
            System.out.println(theController.retrieveCustomerJobOpeningsFromServer());
        } catch (IOException e) {
            System.out.println("Error retrieving applications from server");
            e.printStackTrace();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public String headline() {
        return "List Customer Job Openings (including number of applicants)";
    }
}

