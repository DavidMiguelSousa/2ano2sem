package eapli.base.app.backoffice.console.presentation.admin;

import eapli.base.app.backoffice.console.presentation.authz.ListUsersUI;
import eapli.base.usermanagement.application.ListUsersController;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class ListActiveBackofficeUsersUI extends ListUsersUI {

    private final ListUsersController theController = new ListUsersController();
    @Override
    protected Iterable<SystemUser> elements() {
        return theController.activeBackofficeUsers();
    }
    @Override
    public String headline() {
        return "List Active Backoffice Users";
    }
}
