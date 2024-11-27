package eapli.base.app.backoffice.console.presentation.customermanager;

import eapli.base.app.backoffice.console.presentation.printer.JobApplicationPrinter;
import eapli.base.app.backoffice.console.presentation.printer.JobOpeningPrinter;
import eapli.base.app.backoffice.console.presentation.printer.SystemUserPrinter;
import eapli.base.clientusermanagement.domain.Customer;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.usermanagement.application.ListApplicationController;
import eapli.base.usermanagement.application.ListJobOpeningController;
import eapli.base.usermanagement.application.ListUsersController;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

import java.util.HashSet;
import java.util.Set;

public class ListApplicationJobOpeningUI extends AbstractUI {

    private final ListJobOpeningController theController = new ListJobOpeningController();

    private final ListApplicationController controller = new ListApplicationController();

    @Override
    protected boolean doShow() {
        System.out.println("Select Job Reference");
        final Set<JobOpening> jobOpenings = new HashSet<>();
        boolean show3;
        do {
            show3 = showJobOpening(jobOpenings);
        } while (!show3);

        final Iterable<JobApplication> applications = controller.allApplicationsByJobreference(jobOpenings.iterator().next().jobReference().reference());
        System.out.printf("%-30s %-30s %-30s %-30s\n", "APPLICATION REFERENCE", "STATUS", "DATE OF SUBMISSION", "JOB OPENING REFERENCE");
        for (JobApplication application : applications) {
            new JobApplicationPrinter().visit(application);
        }
        return true;
    }

    private boolean showJobOpening(final Set<JobOpening> customerUsers) {
        // TODO we could also use the "widget" classes from the framework...
        final Menu jobopeningMenu = builJobOpeningMenu(customerUsers);
        final MenuRenderer renderer = new VerticalMenuRenderer(jobopeningMenu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    private Menu builJobOpeningMenu(final Set<JobOpening> jobOpenings) {
        final Menu jobopeningMenu = new Menu();
        int counter = 1;
        for (final JobOpening jobOpening : theController.allJobOpening()) {
            jobopeningMenu.addItem(MenuItem.of(counter++, jobOpening.jobReference().reference(),() -> jobOpenings.add(jobOpening)));
        }
        return jobopeningMenu;
    }

    @Override
    public String headline() {
        return "Applications JobReference";
    }

}