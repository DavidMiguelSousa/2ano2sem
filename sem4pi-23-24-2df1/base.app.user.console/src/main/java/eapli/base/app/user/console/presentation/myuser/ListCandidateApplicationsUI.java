package eapli.base.app.user.console.presentation.myuser;

import application.ListCandidateApplicationsController;
import eapli.framework.presentation.console.AbstractUI;

import java.io.IOException;

public class ListCandidateApplicationsUI extends AbstractUI {

    private final ListCandidateApplicationsController theController = new ListCandidateApplicationsController();

    @Override
    protected boolean doShow() {
        try {
            System.out.println(theController.retrieveCandidateApplicationsFromServer());
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
        return "List Candidate Applications (including number of applicants)";
    }
}
