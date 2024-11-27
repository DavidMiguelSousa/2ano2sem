package eapli.base.app.backoffice.console.presentation.operator;

import eapli.base.app.backoffice.console.presentation.authz.AddUserUI;
import eapli.base.candidatemanagement.application.RegisterCandidateController;

public class RegisterCandidateUI extends AddUserUI {

    private final RegisterCandidateController theController = new RegisterCandidateController();

    @Override
    protected boolean doShow() {
        super.doShow();
        theController.registerCandidate(super.createdUser());
        return true;
    }

    @Override
    public String headline() {
        return "Register Candidate";
    }

}