# UC001 - As Admin, I want to register users of the backoffice

# 4. Tests 

In this project, a Test-Driven Development (TDD) approach was used.

```java
class UserBuilderHelperTest {

    private PasswordPolicy policy;
    private InMemoryUserRepository repo;
    private SystemUser user1;
    private SystemUser user2;
    private SystemUser user3;

    @BeforeEach
    void setUp() {
        policy = new BasePasswordPolicy();
        repo = new InMemoryUserRepository();
        repo.forEach(u -> repo.delete(u));
        SystemUserBuilder builder = UserBuilderHelper.builder();
        user1 = builder.with("test@domain.com", "Password123!", "Test", "Test", "test@domain.com")
                .withRoles(BaseRoles.ADMIN)
                .build();
        user2 = builder.with("test@domain.com", "PassPass123!", "Name", "Name", "test@domain.com")
                .withRoles(BaseRoles.OPERATOR)
                .build();
        user3 = builder.with("test2@domain.com", "PassPass123!", "Names", "Names", "test2@domain.com")
                .withRoles(BaseRoles.CUSTOMER)
                .build();
    }
}
```

**Test 1:** Check the validity of the password policy (with a valid password).

```java
class UserBuilderHelperTest {
    @Test
    void checkValidPasswordTest() {
        String password = "Password123!";
        assertTrue(policy.isSatisfiedBy(password));
    }
}
```

**Test 2:** Check the validity of the password policy (with an invalid password because of the absence of a special character).

```java
class UserBuilderHelperTest {
    @Test
    void checkNoSpecialCharacterTest() {
        String password = "Password123";
        assertFalse(policy.isSatisfiedBy(password));
    }
}
```

**Test 3:** Check the validity of the password policy (with an invalid password because of the absence of a digit).

```java
class UserBuilderHelperTest {
    @Test
    void checkNoDigitTest() {
        String password = "Password!";
        assertFalse(policy.isSatisfiedBy(password));
    }
}
```

**Test 4:** Check the validity of the password policy (with an invalid password because of the absence of an uppercase letter).

```java
class UserBuilderHelperTest {
    @Test
    void checkNoUpperCaseLetterTest() {
        String password = "password123!";
        assertFalse(policy.isSatisfiedBy(password));
    }
}
```

**Test 5:** Check the validity of the password policy (with an invalid password because of the absence of at least eight characters).

```java
class UserBuilderHelperTest {
    @Test
    void checkNoEightCharactersTest() {
        String password = "Pass1!";
        assertFalse(policy.isSatisfiedBy(password));
    }
}
```

**Test 6:** Check the creation of a user is successfull.
```java
class UserBuilderHelperTest {
    @Test
    void createUserSuccessTest() {
        Username username = Username.valueOf("test@domain.com");
        EmailAddress email = EmailAddress.valueOf("test@domain.com");

        repo.save(user1);

        assertEquals(username, user1.username());
        assertEquals(email, user1.email());

        assertTrue(repo.ofIdentity(user1.username()).isPresent());
    }
}
```

**Test 7:** Check the creation of users with the same email fails for the second one.
```java
class UserBuilderHelperTest {
    @Test
    void createSameUserInsuccessTest() {
        repo.save(user1);
        repo.save(user2);

        assertEquals(1, repo.count());
    }
}
```

**Test 8:** Check the creation of users with different email is successfull.
```java
class UserBuilderHelperTest {
    @Test
    void createDifferentUsersSuccessTest() {
        repo.save(user1);
        repo.save(user3);

        assertEquals(2, repo.count());
    }
}
```

# 5. Construction (Implementation)

To help achieve this Use Case's requirements, some EAPLI framework classes were used.

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
    final String password = Console.readLine("Password");

    final Set<Role> roleTypes = new HashSet<>();
    boolean show;
    do {
        show = showRoles(roleTypes);
    } while (!show);

    try {
        this.theController.addUser(password, firstName, lastName, email, roleTypes);
    } catch (final IntegrityViolationException | ConcurrencyException e) {
        System.out.println("That username is already in use.");
    } catch (final IllegalArgumentException e) {
        System.out.println("The password must have at least 8 characters, one digit, one uppercase letter and one special character. Please try again.");
    }

    return false;
}
```

## Class AddUserController
```java
public SystemUser addUser(final String password, final String firstName,
        final String lastName, final String email, final Set<Role> roles) {
    SystemUserBuilder userBuilder = UserBuilderHelper.builder();
    userBuilder.withPassword(new PlainTextEncoder().encode(password));

    //using email as username
    return addUser(email, password, firstName, lastName, email, roles, CurrentTimeCalendars.now());
}
```

## Class SystemUserBuilder
```java
public SystemUserBuilder(final PasswordPolicy policy, final PasswordEncoder encoder) {
    Preconditions.noneNull(policy, encoder);

    this.policy = policy;
    this.encoder = encoder;
    roles = new RoleSet();
}
```

# 6. Integration and Demo 

* A new option in the backoffice users menu was added. However, to register _backoffice users_, the authenticated user must have admin permissions.

# 7. Observations

* To run this Use Case, check the Integration and Demonstration section in this Use Case [Read Me](../README.md) file.