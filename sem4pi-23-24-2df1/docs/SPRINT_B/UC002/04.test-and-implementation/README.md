# UC002 - As Admin, I want to enable users of the backoffice

# 4. Tests

In this project, a Test-Driven Development (TDD) approach was used.

```java
class ActivateUserControllerTest {
    
    private ActivateUserController controller;
    private UserRepository repo;
    private final SystemUserBuilder builder = UserBuilderHelper.builder();
    private SystemUser user1;
    private SystemUser user2;
    private SystemUser user3;
    
    @BeforeEach
    void setUp() {
      controller = new ActivateUserController();
      repo = new InMemoryUserRepository();
      user1 = builder.with("test1@domain.com", "Password123!", "Test", "Test", "test1@domain.com").build();
      user2 = builder.with("test2@domain.com", "Password123!", "Name", "Name", "test2@domain.com").build();
      user3 = builder.with("test3@domain.com", "Password123!", "TestName", "TestName", "test3@domain.com").build();
    }
    
}
```

**Test 1:** Check only inactive users are listed.

```java
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.inmemory.InMemoryUserRepository;

import java.util.Calendar;
import java.util.List;

class ActivateUserControllerTest {

  @Test
  void testListInactiveUsers() {
      SystemUser user1 = new SystemUser("test@domain.com", "Password123!", "test@domain.com", "Test", "Test");
      SystemUser user2 = new SystemUser("test2@domain.com", "Password123!", "test2@domain.com", "Name", "Name");
      SystemUser user3 = new SystemUser("test3@domain.com", "Password123!", "test3@domain.com", "TestName", "TestName");

      InMemoryUserRepository repo = new InMemoryUserRepository();
      
      repo.save(user1);
      repo.save(user2);
      repo.save(user3);

      user1.assignToRole(BaseRoles.CUSTOMER_MANAGER);
      user2.deactivate(Calendar.getInstance());

      ActivateUserController controller = new ActivateUserController();

      Iterable<SystemUser> expectedUsers = List.of(user2);
      Iterable<SystemUser> actualUsers = controller.inactiveUsers();

      assertEquals(expectedUsers, actualUsers);
  }
  
}
```

**Test 2:** Check no data is listed when there are no inactive users.

```java
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.inmemory.InMemoryUserRepository;

import java.util.Calendar;
import java.util.List;

class ActivateUserControllerTest {

  @Test
  void testNoData() {
      SystemUser user2 = new SystemUser("test2@domain.com", "Password123!", "test2@domain.com", "Name", "Name");
      SystemUser user3 = new SystemUser("test3@domain.com", "Password123!", "test3@domain.com", "TestName", "TestName");

      InMemoryUserRepository repo = new InMemoryUserRepository();
  
      repo.save(user2);
      repo.save(user3);

      ActivateUserController controller = new ActivateUserController();

      Iterable<SystemUser> expectedUsers = List.of();
      Iterable<SystemUser> actualUsers = controller.inactiveUsers();

      assertEquals(expectedUsers, actualUsers);
  }
  
}
```

**Test 3:** Check activate user success.

```java
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.inmemory.InMemoryUserRepository;

import java.util.Calendar;
import java.util.List;

class ActivateUserControllerTest {

  @Test
  void activateInactiveUserSuccessTest() {
      SystemUser user2 = new SystemUser("test2@domain.com", "Password123!", "test2@domain.com", "Name", "Name");

      InMemoryUserRepository repo = new InMemoryUserRepository();
  
      repo.save(user2);
      
      user2.deactivate(Calendar.getInstance());

      ActivateUserController controller = new ActivateUserController();

      SystemUser systemUser = controller.activateUser(user2);

      assertEquals(user2, systemUser);
      assertTrue(systemUser.isActive());
  }
  
}
```

**Test 4:** Check activate already active user insuccess.

```java
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.inmemory.InMemoryUserRepository;

import java.util.Calendar;
import java.util.List;

class ActivateUserControllerTest {

  @Test
  void activateAlreadyActiveUserInsuccessTest() {
      SystemUser user2 = new SystemUser("test2@domain.com", "Password123!", "test2@domain.com", "Name", "Name");
      
      InMemoryUserRepository repo = new InMemoryUserRepository();
  
      repo.save(user2);
    
      ActivateUserController controller = new ActivateUserController();

      assertThrows(Exception e -> controller.activateUser(user2));
  }
  
}
```

# 5. Construction(Implementation)

To help achieve this Use Case's requirements, some EAPLI framework classes were used.

## Class ActivateUserAction

```java
@Override
public boolean execute () {
  return new ActivateUserUI().show();
}
```

## Class ActivateUserUI

```java
@Override
protected boolean doShow() {
  final List<SystemUser> list = new ArrayList<>();
  final Iterable<SystemUser> iterable = this.theController.inactiveUsers();
  if (!iterable.iterator().hasNext()) {
    System.out.println("There are no registered inactive users.");
  } else {
    int cont = 1;
    System.out.println("Select user to activate\n");
    System.out.printf("%-6s%-30s%-40s%-40s%n", "Nº:", "Email", "Firstname", "Lastname");
    for (final SystemUser user : iterable) {
      list.add(user);
      System.out.printf("%-6d%-30s%-40s%-40s%n", cont, user.email(), user.name().firstName(),
              user.name().lastName());
      cont++;
    }
    final int option = Console.readInteger("Enter user nº to activate or 0 to finish ");
    if (option == 0) {
      System.out.println("No user selected");
    } else {
      try {
        this.theController.activateUser(list.get(option - 1));
      } catch (IntegrityViolationException | ConcurrencyException ex) {
        LOGGER.error("Error performing the operation", ex);
        System.out.println(
                "Unfortunatelly there was an unexpected error in the application. Please try again and if the problem persists, contact your system admnistrator.");
      }
    }
  }
  return true;
}
```

## Class ActivateUserController

```java
public SystemUser activateUser(final SystemUser user) {
  authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN);

  return userSvc.activateUser(user);
}
```

# 6. Integration and Demo

* A new option in the backoffice users menu was added. However, to activate _active users_, the authenticated
  user must have admin permissions.

# 7. Observations

* To run this Use Case, check the Integration and Demonstration section in this Use Case [Read Me](../README.md) file.