/*
 * Copyright (c) 2013-2024 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.app.backoffice.console.presentation.customermanager;

import eapli.base.app.backoffice.console.presentation.authz.SelectInterviewModelAction;
import eapli.base.app.backoffice.console.presentation.authz.SelectJobRequirementsAction;
import eapli.base.clientusermanagement.domain.Customer;
import eapli.base.clientusermanagement.domain.address.*;
import eapli.base.clientusermanagement.domain.jobopening.ContractType;
import eapli.base.clientusermanagement.domain.jobopening.JobMode;
import eapli.base.clientusermanagement.domain.jobopening.JobReference;
import eapli.base.interviewmanagement.domain.NumberOfVacancies;
import eapli.base.services.ConsoleUtils;
import eapli.base.usermanagement.application.AddJobOpeningController;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

import java.util.HashSet;
import java.util.Set;

/**
 * UI for adding a user to the application.
 * <p>
 * Created by nuno on 22/03/16.
 */
public class AddJobOpeningUI extends AbstractUI {

    private final AddJobOpeningController theController = new AddJobOpeningController();

    @Override
    protected boolean doShow() {
        final Designation title = Designation.valueOf(Console.readLine("Title"));
        final Description description = Description.valueOf(Console.readLine("Description"));
        System.out.println("Address:");
        final District district = showDistricts();
        final County county = County.valueOf(Console.readLine("County"));
        final Parish parish = Parish.valueOf(Console.readLine("Parish"));
        final Street street = Street.valueOf(Console.readLine("Street"));
        final DoorNumber doorNumber = DoorNumber.valueOf(ConsoleUtils.readIntegerInScale("Door Number: ", 0, Integer.MAX_VALUE));
        final PostalCode postalCode = PostalCode.valueOf(Console.readLine("Postal Code (XXXX-XXX)"));

        final Address address = new Address(district, county, parish, street, doorNumber, postalCode);

        System.out.println("Select Contract Type");
        final Set<ContractType> contractTypes = new HashSet<>();
        boolean show;
        do {
            show = showContractType(contractTypes);
        } while (!show);

        System.out.println("Select Mode Work");
        final Set<JobMode> modework = new HashSet<>();
        do {
            show = showModeWork(modework);
        } while (!show);

        System.out.println("Select Customer");
        final Set<Customer> customer = new HashSet<>();
        do {
            show = showCustomerUser(customer);
        } while (!show);

        final NumberOfVacancies vacancies = NumberOfVacancies.valueOf(ConsoleUtils.readIntegerInScale("Number of Vacancies: ", 1, Integer.MAX_VALUE));

        final int jobReference = theController.findLastRecord() + 1;

        try {
            this.theController.addJobOpening(new JobReference(customer.iterator().next().customerCode().code()+"-"+jobReference), title, contractTypes.iterator().next(), modework.iterator().next(),address, customer.iterator().next(), vacancies, description);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("Error.");
        }

        System.out.println("Job opening added with \"PENDING\" state. It will be active as soon as you setup its phases, job requirements and interview model.");
        return true;
    }

    private boolean showContractType(final Set<ContractType> contractType) {
        // TODO we could also use the "widget" classes from the framework...
        final Menu rolesMenu = buildContractTypeMenu(contractType);
        final MenuRenderer renderer = new VerticalMenuRenderer(rolesMenu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    private Menu buildContractTypeMenu(final Set<ContractType> contractType) {
        final Menu contractTypeMenu = new Menu();
        int counter = 1;
        for (final ContractType contractType1 : theController.allContractTypes()) {
            contractTypeMenu.addItem(MenuItem.of(counter++, contractType1.toString(),() -> contractType.add(contractType1)));
        }
        return contractTypeMenu;
    }

    private boolean showModeWork(final Set<JobMode> modeWorks) {
        // TODO we could also use the "widget" classes from the framework...
        final Menu rolesMenu = buildModeWorkMenu(modeWorks);
        final MenuRenderer renderer = new VerticalMenuRenderer(rolesMenu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    private Menu buildModeWorkMenu(final Set<JobMode> modeWorks) {
        final Menu modeWorksMenu = new Menu();
        int counter = 1;
        for (final JobMode modeWork : theController.allJobModes()) {
            modeWorksMenu.addItem(MenuItem.of(counter++, modeWork.toString(),() -> modeWorks.add(modeWork)));
        }
        return modeWorksMenu;
    }

    private boolean showCustomerUser(final Set<Customer> customerUsers) {
        // TODO we could also use the "widget" classes from the framework...
        final Menu customerUserMenu = buildCustomerUserMenu(customerUsers);
        final MenuRenderer renderer = new VerticalMenuRenderer(customerUserMenu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    private Menu buildCustomerUserMenu(final Set<Customer> costumerUsers) {
        final Menu costumerUserMenu = new Menu();
        int counter = 1;

        for (final Customer costumerUser : theController.findAllCustomers()) {
            costumerUserMenu.addItem(MenuItem.of(counter++, costumerUser.user().name().toString(),() -> costumerUsers.add(costumerUser)));
        }
        return costumerUserMenu;
    }

    private District showDistricts() {
        System.out.println("\nDistricts:");
        int i = 1;
        for (District district : District.values()) {
            System.out.println(i + ". " + district);
            i++;
        }
        int option = Console.readInteger("Choose a district");
        return District.values()[option - 1];
    }

    @Override
    public String headline() {
        return "Add Job Opening";
    }
}
