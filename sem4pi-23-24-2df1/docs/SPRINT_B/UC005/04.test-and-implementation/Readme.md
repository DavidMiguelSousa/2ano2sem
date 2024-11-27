# UC005 - As Customer Manager I want to register a customer and that the system automatically creates a user for that customer.

# 4. Tests

```java
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
package eapli.base.clientusermanagement.domain;

import java.util.HashSet;
import java.util.Set;

import eapli.base.clientusermanagement.domain.address.*;
import eapli.framework.infrastructure.authz.domain.model.*;
import org.junit.Test;

import eapli.base.usermanagement.domain.BaseRoles;

import static org.junit.Assert.*;

/**
 * Created by Nuno Bettencourt [NMB] on 03/04/16.
 */
public class CustomerTest {

    private final String aMecanographicNumber = "abc";
    private final String anotherMecanographicNumber = "xyz";
    private final Address address = new Address(District.PORTO, County.valueOf("Porto"), Parish.valueOf("Paranhos"), Street.valueOf("Rua Dr. António Bernardino de Almeida"), DoorNumber.valueOf(431), PostalCode.valueOf("4200-465"));

    public static SystemUser dummyUser(final String username, final Role... roles) {
        // should we load from spring context?
        final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
        return userBuilder.with(username, "duMMy1", "dummy", "dummy", "dummy@jobs4u.com").withRoles(roles).build();
    }

    public static SystemUser dummyUser(final String password, final String firstName, final String lastName, final String email,
                                       final Role... roles) {
        // should we load from spring context?
        final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
        return userBuilder.with(email, password, firstName, lastName, email).withRoles(roles).build();
    }

    private SystemUser getNewDummyUser() {
        return dummyUser("dummy@jobs4u.com", BaseRoles.CUSTOMER);
    }

    private SystemUser getNewDummyUser(String password, String firstName, String lastName, String email) {
        return dummyUser(password, firstName, lastName, email, BaseRoles.CUSTOMER);
    }

    @Test
    public void ensureCustomerUserEqualsPassesForTheSameMecanographicNumber() {

        final Customer aCustomer = new CustomerBuilder().withCustomerCode("DUMMY")
                .withSystemUser(getNewDummyUser()).build();

        final Customer anotherCustomer = new CustomerBuilder().withCustomerCode("DUMMY")
                .withSystemUser(getNewDummyUser()).build();

        final boolean expected = aCustomer.equals(anotherCustomer);

        assertTrue(expected);
    }

    @Test
    public void ensureCustomerUserEqualsFailsForDifferentMecanographicNumber() {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.ADMIN);

        CustomerBuilder builder = new CustomerBuilder();

        final Customer aCustomer = builder.withCustomerCode(aMecanographicNumber)
                .withSystemUser(getNewDummyUser("duMMy1", "dummy", "first", "a@b.ro")).build();

        final Customer anotherCustomer = builder.withCustomerCode(anotherMecanographicNumber)
                .withSystemUser(getNewDummyUser("duMMy2", "dummy", "second", "b@a.ro")).build();

        final boolean expected = aCustomer.sameAs(anotherCustomer);

        assertFalse(expected);
    }

    @Test
    public void ensureCustomerUserEqualsAreTheSameForTheSameInstance() {
        final Customer aCustomer = new Customer();

        final boolean expected = aCustomer.equals(aCustomer);

        assertTrue(expected);
    }

    @Test
    public void ensureCustomerUserEqualsFailsForDifferenteObjectTypes() {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.ADMIN);

        final Customer aCustomer = new CustomerBuilder().withCustomerCode("DUMMY")
                .withSystemUser(getNewDummyUser()).build();

        @SuppressWarnings("unlikely-arg-type") final boolean expected = aCustomer.equals(getNewDummyUser());

        assertFalse(expected);
    }

    @Test
    public void ensureCustomerUserIsTheSameAsItsInstance() {
        final Customer aCustomer = new CustomerBuilder().withCustomerCode("DUMMY").withAddress(address).
                withSystemUser(getNewDummyUser()).build();

        final boolean expected = aCustomer.sameAs(aCustomer);

        assertTrue(expected);
    }

    @Test
//FIXME
    public void ensureTwoCustomerUserWithDifferentMecanographicNumbersAreNotTheSame() {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.ADMIN);
        final Customer aCustomer = new CustomerBuilder().withCustomerCode(aMecanographicNumber)
                .withSystemUser(getNewDummyUser()).build();

        final Customer anotherCustomer = new CustomerBuilder()
                .withCustomerCode(anotherMecanographicNumber).withSystemUser(getNewDummyUser()).build();

        final boolean expected = aCustomer.sameAs(anotherCustomer);

        assertFalse(expected);
    }
    @Test
    public void verifyCustomerHasUser() {
        final Customer customer = new CustomerBuilder().withCustomerCode("DUMMY")
                .withSystemUser(getNewDummyUser()).build();

        final SystemUser result = customer.user();

        assertNotNull(result);
    }


//    @Test
// //FIXME
//    public void verifyCustomerWithAllDataHasNothingNull() {
//        final Customer customer = new CustomerBuilder().withCustomerCode("DUMMY").withAddress(address)
//                .withSystemUser(getNewDummyUser("duMMy1", "dummy", "first", "ab@c.ro")).build();
//
//        final SystemUser result = customer.user();
//
//        assertEquals(result.username(), Username.valueOf("ab@c.ro"));
//        assertEquals(result.email(), EmailAddress.valueOf("ab@c.ro"));
//        assertEquals(result.name(), Name.valueOf("dummy", "first"));
//    }
}
```

*It is also recommended to organize this content by subsections.*

# 5. Construction (Implementation)

## Class CreateTaskController

## Class Organization

# 6. Integration and Demonstration

## Register a Customer and automatically create a User for that Customer

* Via Bootstrap:

```java
public class CustomerUsersBootstrapper extends CostumerUsersBootstrapperBase implements Action {
    private static final String PASSWORD1 = "Password1";

    @Override
    public void execute() {
        //Create Costumer
        registerCostumer("costumer@costumer.com", PASSWORD1, "Costumer", "Bootstrap", "costumer@costumer.com", "customermanager@jobs4u.com");
    }

    private void registerCustomer(final String username, final String password,
                                  final String firstName, final String lastName, final String customerEmail, final String customerManagerEmail) {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.CUSTOMER);

        registerUser(username, password, firstName, lastName, customerEmail, roles);
        registerCustomer(customerEmail, customerManagerEmail);
        addCustomerToCustomerManagerList(customerEmail, customerManagerEmail);
    }
}
```

```java
public class CostumerUsersBootstrapperBase extends UsersBootstrapperBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(CostumerUsersBootstrapperBase.class);

    final AddCustomerController customerController = new AddCustomerController();
    final ListCustomersController listCustomerController = new ListCustomersController();

    public CostumerUsersBootstrapperBase() {
        super();
    }

    protected Customer registerCostumer(final String customerEmail, final String customerManagerEmail) {
        Customer c = null;
        try {
            c = customerController.addCustomer(customerEmail, customerManagerEmail);
            LOGGER.debug("»»» %s", customerEmail);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            // assuming it is just a primary key violation due to the tentative
            // of inserting a duplicated customer. let's just lookup that customer
            c = listCustomerController.find(Email.valueOf(customerEmail)).orElseThrow(() -> e);
        }
        return c;
    }

    protected void addCustomerToCustomerManagerList(final String username, final String customerManagerEmail) {
        final CustomerManager cm = customerManagerRepository.findByEmail(customerManagerEmail);
        final Customer c = customerRepository.findByEmail(username);
        cm.addCustomer(c);
        customerManagerRepository.save(cm);
    }

}
```

```java
public class UsersBootstrapperBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersBootstrapperBase.class);

    final AddUserController userController = new AddUserController();
    final ListUsersController listUserController = new ListUsersController();

    public UsersBootstrapperBase() {
        super();
    }

    /**
     * @param username user's username which is equal to the user's email
     * @param password user's password
     * @param firstName user's first name
     * @param lastName user's last name
     * @param email user's email
     * @param roles user's roles
     */
    protected SystemUser registerUser(final String username, final String password, final String firstName,
                                      final String lastName, final String email, final Set<Role> roles) {
        SystemUser u = null;
        try {
            u = userController.addUser(password, firstName, lastName, email, roles);
            LOGGER.debug("»»» %s", username);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            // assuming it is just a primary key violation due to the tentative
            // of inserting a duplicated user. let's just lookup that user
            u = listUserController.find(Username.valueOf(username)).orElseThrow(() -> e);
        }
        return u;
    }
}
```

* Via API:

```java
```

# 7. Observations

All tests for this Class were commented due to this errors:

``` java
- java: java.lang.NoSuchFieldError: Class com.sun.tools.javac.tree.JCTree$JCImport does not have member field 'com.sun.tools.javac.tree.JCTree qualid'
```

``` java
- java: java.lang.NoSuchFieldError: Class com.sun.tools.javac.tree.JCTree$JCImport does not have member field 'com.sun.tools.javac.tree.JCTree qualid'
```