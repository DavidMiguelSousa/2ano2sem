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
package eapli.base.app.backoffice.console.presentation.authz;

import eapli.base.clientusermanagement.domain.address.*;
import eapli.base.services.ConsoleUtils;
import eapli.base.usermanagement.application.AddCustomerController;
import eapli.base.usermanagement.application.AddUserController;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * UI for adding a user to the application.
 * <p>
 * Created by nuno on 22/03/16.
 */
public class AddUserUI extends AbstractUI {

    private final AddUserController theController = new AddUserController();
    private final AddCustomerController customerController = new AddCustomerController();
    private SystemUser user;

    @Override
    protected boolean doShow() {
        // FIXME avoid duplication with SignUpUI.
        final String firstName = Console.readLine("First Name");
        final String lastName = Console.readLine("Last Name");
        final String email = Console.readLine("E-Mail");
        final String password = theController.generatePassword(email);
        System.out.println("Password will be sent by email.");

        final Set<Role> roleTypes = selectRole();

        try {
            user = theController.addUser(password, firstName, lastName, email, roleTypes);
            if (theController.authenticatedCanCreateCustomer() && roleTypes.contains(BaseRoles.CUSTOMER)) addCustomer(email);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("That username is already in use.");
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    private void addCustomer(String email) {
        final District district = showDistricts();
        final County county = County.valueOf(Console.readLine("County"));
        final Parish parish = Parish.valueOf(Console.readLine("Parish"));
        final Street street = Street.valueOf(Console.readLine("Street"));
        boolean postalCodeIsNotValid = false;
        final DoorNumber doorNumber = new DoorNumber(ConsoleUtils.readIntegerInScale("Door Number", 0, Integer.MAX_VALUE));
        PostalCode postalCode = null;
        do {
            try {
                postalCode = new PostalCode(Console.readLine("Postal Code"));
                postalCodeIsNotValid = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Postal Code should be in the format XXXX-XXX");
            }
        } while (!postalCodeIsNotValid);

        final Address address = new Address(district, county, parish, street, doorNumber, postalCode);
        final String customerCode = Console.readLine("Customer Code");

        try {
            customerController.addCustomer(email, customerCode, address);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("That username is already in use.");
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private Set<Role> selectRole() {
        final Set<Role> selectedRoles = new HashSet<>();
        if (theController.authenticatedIsOperator()) {
            selectedRoles.add(BaseRoles.CANDIDATE);
        } else if (theController.authenticatedIsCustomerManager()) {
            selectedRoles.add(BaseRoles.CUSTOMER);
        } else if (theController.authenticatedIsAdmin()) {
            SelectWidget<Role> selector = new SelectWidget<>("Select Role", List.of(BaseRoles.CUSTOMER_MANAGER, BaseRoles.OPERATOR));
            selector.show();
            selectedRoles.add(selector.selectedElement());
        }
        return selectedRoles;
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

    public SystemUser createdUser() {
        return user;
    }

    @Override
    public String headline() {
        return "Add User";
    }
}
