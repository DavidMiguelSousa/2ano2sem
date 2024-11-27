package eapli.base.infrastructure.bootstrapers;

import eapli.base.clientusermanagement.application.ListCustomerUsersController;
import eapli.base.clientusermanagement.domain.Customer;
import eapli.base.clientusermanagement.domain.address.Address;
import eapli.base.usermanagement.application.AddCustomerController;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerUsersBootstrapperBase extends UsersBootstrapperBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerUsersBootstrapperBase.class);

    final AddCustomerController addCustomerController = new AddCustomerController();
    final ListCustomerUsersController listCustomerUsersController = new ListCustomerUsersController();

    public CustomerUsersBootstrapperBase() {
        super();
    }

    protected Customer registerCustomer(final String email, final String customerCode, final Address address) {
        Customer c;
        try {
            c = addCustomerController.addCustomer(email, customerCode, address);
            LOGGER.debug("»»» %s", email);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            // assuming it is just a primary key violation due to the tentative
            // of inserting a duplicated user. let's just lookup that user
            c = listCustomerUsersController.find(email).orElseThrow(() -> e);
        }
        return c;
    }
}
