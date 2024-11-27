package eapli.base.services;

import eapli.base.clientusermanagement.domain.Customer;
import eapli.base.clientusermanagement.domain.CustomerBuilder;
import eapli.base.clientusermanagement.domain.CustomerCode;
import eapli.base.clientusermanagement.domain.address.Address;
import eapli.base.clientusermanagement.repositories.CustomerRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;

import java.util.Optional;

@ApplicationService
public class CustomerManagementService {
    private final UserRepository userRepo = PersistenceContext.repositories().users();
    private final CustomerRepository customerRepo = PersistenceContext.repositories().customers();

    public Customer registerNewCustomer(SystemUser customerUser, CustomerCode customerCode, Address address) {
        final var customerBuilder = new CustomerBuilder();
        customerBuilder.withSystemUser(customerUser).withCustomerCode(customerCode).withAddress(address);
        final var newCustomer = customerBuilder.build();
        return customerRepo.save(newCustomer);
    }

    public Iterable<Customer> allCustomer() {
        return customerRepo.findAll();
    }

    public Optional<Customer> customerOfIdentity(String email) {
        return customerRepo.findByUsername(Username.valueOf(email));
    }

    public Optional<SystemUser> userOfIdentity(Username username) {
        return userRepo.ofIdentity(username);
    }

    public Optional<Customer> customerWithCode(CustomerCode customerCode) {
        return customerRepo.findByCustomerCode(customerCode);
    }
}
