# UC004 - As Admin, I want to list users of the backoffice

# 4. Tests

**Test 1:** Check only active backoffice users are listed.

```java
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.inmemory.InMemoryUserRepository;

import java.util.Calendar;
import java.util.List;

class ListUsersControllerTest {

  @Test
  void testListActiveBackofficeUsers() {
      SystemUser user1 = new SystemUser("test@domain.com", "Password123!", "test@domain.com", "Test", "Test");
      SystemUser user2 = new SystemUser("test2@domain.com", "Password123!", "test2@domain.com", "Name", "Name");
      SystemUser user3 = new SystemUser("test3@domain.com", "Password123!", "test3@domain.com", "TestName", "TestName");

      user1.assignToRole(BaseRoles.CUSTOMER_MANAGER);
      user2.deactivate(Calendar.getInstance());

      ListUsersController controller = new ListUsersController();
      InMemoryUserRepository repo = new InMemoryUserRepository();

      repo.save(user1);
      repo.save(user2);
      repo.save(user3);

      Iterable<SystemUser> expectedUsers = List.of(user);
      Iterable<SystemUser> actualUsers = controller.activeBackofficeUsers();

      assertEquals(expectedUsers, actualUsers);
  }
  
}
```

**Test 2:** Check no data is listed when there are no active backoffice users.

```java
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.inmemory.InMemoryUserRepository;

import java.util.Calendar;
import java.util.List;

class ListUsersControllerTest {

  @Test
  void testNoData() {
      SystemUser user2 = new SystemUser("test2@domain.com", "Password123!", "test2@domain.com", "Name", "Name");
      SystemUser user3 = new SystemUser("test3@domain.com", "Password123!", "test3@domain.com", "TestName", "TestName");
    
      user2.deactivate(Calendar.getInstance());
    
      ListUsersController controller = new ListUsersController();
      InMemoryUserRepository repo = new InMemoryUserRepository();
    
      repo.save(user2);
      repo.save(user3);

      Iterable<SystemUser> expectedUsers = List.of();
      Iterable<SystemUser> actualUsers = controller.activeBackofficeUsers();

      assertEquals(expectedUsers, actualUsers);
  }
  
}
```

# 5. Construction(Implementation)

To help achieve this Use Case's requirements, some EAPLI framework classes were used.

## Class ListActiveBackofficeUsersAction

```java
@Override
public boolean execute () {
  return new ListActiveBackofficeUsersUI().show();
}
```

## Class ListActiveBackofficeUsersUI

```java
@Override
protected Iterable<SystemUser> elements() {
    return theController.activeBackofficeUsers();
}
```

## Class ListUsersController

```java
public Iterable<SystemUser> activeBackofficeUsers() {
    authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN);

    List<SystemUser> activeBackofficeUsers = new ArrayList<>();
    for (SystemUser u : userSvc.activeUsers()) {
        if (BaseRoles.isCollaborator(u.roleTypes().toArray(new Role[0]))) {
            activeBackofficeUsers.add(u);
        }
    }
    return activeBackofficeUsers;
}
```

# 6. Integration and Demo

* A new option in the backoffice users menu was added. However, to list _active backoffice users_, the authenticated
  user must have admin permissions.

# 7. Observations

* To run this Use Case, check the Integration and Demonstration section in this Use Case [Read Me](../README.md) file.