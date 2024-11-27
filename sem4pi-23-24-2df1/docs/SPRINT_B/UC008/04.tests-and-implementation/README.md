# UC 008 - As Operator, register a candidate.

# 4. Tests

All classes and methods used in this use case are part of the EAPLI framework, which is pre-tested and proven to work.

*It is also recommended to organize this content by subsections.*

# 5. Construction (Implementation)

## Class AddUserAction

```java
@Override
public boolean execute() {
    return new AddUserUI().show();
}
```

## Class AddUserUI

```java
    protected boolean doShow() {
    // FIXME avoid duplication with SignUpUI.
    final String firstName = Console.readLine("First Name");
    final String lastName = Console.readLine("Last Name");
    final String email = Console.readLine("E-Mail");
    final String password = theController.generatePassword(email);
    System.out.println("Password will be sent by email");

    final Set<Role> roleTypes = new HashSet<>();
    boolean show;
    do {
        show = showRoles(roleTypes);
    } while (!show);

    try {
        this.theController.addUser(password, firstName, lastName, email, roleTypes);
        if (theController.authenticatedCanCreateCustomer() && roleTypes.contains(BaseRoles.CUSTOMER)) addCustomer(email);
    } catch (final IntegrityViolationException | ConcurrencyException e) {
        System.out.println("That username is already in use.");
    } catch (final IllegalArgumentException e) {
        System.out.println(e.getMessage());
        System.out.println("The password must have at least 8 characters, one digit, one uppercase letter and one special character. Please try again.");
    }

    return false;
}
```

## Class AddUserController

```java
    public SystemUser addUser(final String username, final String password, final String firstName,
            final String lastName,
            final String email, final Set<Role> roles, final Calendar createdOn) {

        return userSvc.registerNewUser(username, password, firstName, lastName, email, roles,
                createdOn);
    }

    public SystemUser addUser(final String password, final String firstName,
            final String lastName, final String email, final Set<Role> roles) {
        SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        userBuilder.withPassword(new PlainTextEncoder().encode(password));

        //using email as username
        return addUser(email, password, firstName, lastName, email, roles, CurrentTimeCalendars.now());
    }
```

# 6. Integration and Demo

* 

# 7. Observations





