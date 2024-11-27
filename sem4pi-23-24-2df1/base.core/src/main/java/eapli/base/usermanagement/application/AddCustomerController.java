package eapli.base.usermanagement.application;

import eapli.base.clientusermanagement.domain.Customer;
import eapli.base.clientusermanagement.domain.CustomerCode;
import eapli.base.clientusermanagement.domain.address.Address;
import eapli.base.services.CustomerManagementService;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.Optional;


public class AddCustomerController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CustomerManagementService customerSvc = new CustomerManagementService();

    public Customer addCustomer(final String email, final String code, final Address address) {
//        SystemUserBuilder userBuilder = UserBuilderHelper.builder();
//        SystemUser customerUser = userBuilder.withEmailAsUsername(email).withName(firstName, lastName).withPassword(password).build();
        SystemUser systemUser = customerSvc.userOfIdentity(Username.valueOf(email)).orElseThrow();
        CustomerCode customerCode = new CustomerCode(code);

        return customerSvc.registerNewCustomer(systemUser, customerCode, address);
    }

    public Iterable<Customer> allCustomers() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN);

        return customerSvc.allCustomer();
    }

    public Optional<Customer> find(String username) {
        return customerSvc.customerOfIdentity(username);
    }
}
