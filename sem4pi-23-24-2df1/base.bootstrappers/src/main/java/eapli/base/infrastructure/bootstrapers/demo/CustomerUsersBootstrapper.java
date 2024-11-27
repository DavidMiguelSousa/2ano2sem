package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.clientusermanagement.domain.address.*;
import eapli.base.infrastructure.bootstrapers.CustomerUsersBootstrapperBase;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.domain.model.Role;

import java.util.HashSet;
import java.util.Set;


public class CustomerUsersBootstrapper extends CustomerUsersBootstrapperBase implements Action {

    private static final String PASSWORD = "ISEPorto1852!";
    private static final Address ADDRESS = new Address(District.PORTO, County.valueOf("Porto"), Parish.valueOf("Paranhos"), Street.valueOf("Rua Dr. António Bernardino de Almeida"), DoorNumber.valueOf(431), PostalCode.valueOf("4200-465"));

    @Override
    public boolean execute() {
        registerClient("1200347@isep.ipp.pt", PASSWORD, "Instituto Superior", "de Engenharia do Porto", "ISEP", ADDRESS);
        return true;
    }

    private void registerClient(final String email, final String password, final String firstName,
                                  final String lastName, final String customerCode, final Address address) {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.CUSTOMER);

        registerUser(email, password, firstName, lastName, email, roles);
        registerCustomer(email, customerCode, address);
    }
}