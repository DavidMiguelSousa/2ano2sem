# UC003 - As Admin, I want to disable users of the backoffice

# 4. Tests

**Test 1:** Check only active users are listed.

```java
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.inmemory.InMemoryUserRepository;

import java.util.Calendar;
import java.util.List;

class DeactivateUserControllerTest {

  @Test
  void testListActiveUsers() {
      SystemUser user1 = new SystemUser("test@domain.com", "Password123!", "test@domain.com", "Test", "Test");
      SystemUser user2 = new SystemUser("test2@domain.com", "Password123!", "test2@domain.com", "Name", "Name");
      SystemUser user3 = new SystemUser("test3@domain.com", "Password123!", "test3@domain.com", "TestName", "TestName");

      InMemoryUserRepository repo = new InMemoryUserRepository();
      
      repo.save(user1);
      repo.save(user2);
      repo.save(user3);

      user1.assignToRole(BaseRoles.CUSTOMER_MANAGER);
      user2.deactivate(Calendar.getInstance());

      DeactivateUserController controller = new DeactivateUserController();

      Iterable<SystemUser> expectedUsers = List.of(user1, user3);
      Iterable<SystemUser> actualUsers = controller.activeUsers();

      assertEquals(expectedUsers, actualUsers);
  }
  
}
```

**Test 2:** Check no data is listed when there are no active users.

```java
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.inmemory.InMemoryUserRepository;

import java.util.Calendar;
import java.util.List;

class DeactivateUserControllerTest {

  @Test
  void testNoData() {
      SystemUser user2 = new SystemUser("test2@domain.com", "Password123!", "test2@domain.com", "Name", "Name");
      SystemUser user3 = new SystemUser("test3@domain.com", "Password123!", "test3@domain.com", "TestName", "TestName");

      InMemoryUserRepository repo = new InMemoryUserRepository();
  
      repo.save(user2);
      repo.save(user3);
    
      user2.deactivate(Calendar.getInstance());
      user3.deactivate(Calendar.getInstance());

      DeactivateUserController controller = new DeactivateUserController();

      Iterable<SystemUser> expectedUsers = List.of();
      Iterable<SystemUser> actualUsers = controller.activeUsers();

      assertEquals(expectedUsers, actualUsers);
  }
  
}
```

**Test 3:** Check deactivate user success.

```java
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.inmemory.InMemoryUserRepository;

import java.util.Calendar;
import java.util.List;

class DeactivateUserControllerTest {

  @Test
  void deactivateUserTest() {
      SystemUser user2 = new SystemUser("test2@domain.com", "Password123!", "test2@domain.com", "Name", "Name");

      InMemoryUserRepository repo = new InMemoryUserRepository();
  
      repo.save(user2);
    
      DeactivateUserController controller = new DeactivateUserController();

      SystemUser systemUser = controller.deactivateUser(user2);

      assertEquals(user2, systemUser);
      assertFalse(systemUser.isActive());
  }
  
}
```

**Test 4:** Check deactivate already inactive user insuccess.

```java
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.inmemory.InMemoryUserRepository;

import java.util.Calendar;
import java.util.List;

class DeactivateUserControllerTest {

  @Test
  void deactivateUserTest() {
      SystemUser user2 = new SystemUser("test2@domain.com", "Password123!", "test2@domain.com", "Name", "Name");
      
      user2.deactivate(Calendar.getInstance());

      InMemoryUserRepository repo = new InMemoryUserRepository();
  
      repo.save(user2);
    
      DeactivateUserController controller = new DeactivateUserController();

      assertThrows(Exception e -> controller.deactivateUser(user2));
  }
  
}
```

# 5. Construction(Implementation)

To help achieve this Use Case's requirements, some EAPLI framework classes were used.

## Class DeactivateUserAction

```java
@Override
public boolean execute () {
  return new DeactivateUserUI().show();
}
```

## Class DeactivateUserUI

```java
@Override
protected boolean doShow() {
  final List<SystemUser> list = new ArrayList<>();
  final Iterable<SystemUser> iterable = this.theController.activeUsers();
  if (!iterable.iterator().hasNext()) {
    System.out.println("There is no registered User");
  } else {
    int cont = 1;
    System.out.println("Select user to deactivate\n");
    System.out.printf("%-6s%-30s%-40s%-40s%n", "Nº:", "Email", "Firstname", "Lastname");
    for (final SystemUser user : iterable) {
      list.add(user);
      System.out.printf("%-6d%-30s%-40s%-40s%n", cont, user.email(), user.name().firstName(),
              user.name().lastName());
      cont++;
    }
    final int option = Console.readInteger("Enter user nº to deactivate or 0 to finish ");
    if (option == 0) {
      System.out.println("No user selected");
    } else {
      try {
        this.theController.deactivateUser(list.get(option - 1));
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

## Class DeactivateUserController

```java
public SystemUser deactivateUser(final SystemUser user) {
  authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN);

  return userSvc.deactivateUser(user);
}
```

# 6. Integration and Demo

* A new option in the backoffice users menu was added. However, to deactivate _active users_, the authenticated
  user must have admin permissions.

# 7. Observations

* To run this Use Case, check the Integration and Demonstration section in this Use Case [Read Me](../README.md) file.