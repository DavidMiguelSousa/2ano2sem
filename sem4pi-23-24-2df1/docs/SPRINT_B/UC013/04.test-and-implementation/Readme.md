# US 1001 - As Customer Manager I want to register a customer and that the system automatically creates a user for that customer.

# 4. Tests 

**Test 1:** Check if the password is generated correctly.
```java
class PasswordGeneratorTest {

    @Test
    void generatePassword() {
        for (int i = 0; i < 100; i++){
            String pass = PasswordGenerator.generatePassword();
            System.out.println(pass + " " + "Size: " + pass.length());
            assertTrue(pass.length() >= 8);
            assertTrue(pass.matches(".*\\d.*\\d.*"));
            assertTrue(pass.matches(".*[A-Z].*[A-Z].*[A-Z].*"));
        }
    }
}

```

**Test 2:** Check if the phone number is valid.

```java
class EmployeeTest {
    @Test
    void ensureInvalidPhone() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Employee("test@example.com", "Test Employee", "12345",
                    new Passport("123456789", "", ""), new Location("State", "City", "District", "123 Main St", "1234-567"),
                    "123456789", Role.STORE_MANAGER, new Agency("123"));
        });
    }
}
   ```
**Test 3:** Check if the email is valid.

```java
class EmployeeTest {
    @Test
    void ensureInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Employee("testexample.com", "Test Employee", "123456789",
                    new Passport("123456789", "", ""), new Location("State", "City", "District", "123 Main St", "1234-567"),
                    "123456789", Role.STORE_MANAGER, new Agency("123"));
        });
    }
}
   ```

**Test 4:** Check if employee with the same tax number and with different roles and emails are equal.
    
```java
class EmployeeTest {
    @Test 
    void ensureEmployeesCanHaveSameTaxNumberButDifferentRolesAndEmail() {
    Employee employee1 = new Employee("test1@example.com", "Test Employee", "123456789", 
            new Passport("123456789", "", ""), new Location("State", "City", "District", "123 Main St", "1234-567"),
            "123456789", Role.STORE_MANAGER, new Agency("123"));
    
    Employee employee2 = new Employee("test2@example.com", "Test Employee", "123456789",
            new Passport("123456789", "", ""), new Location("State", "City", "District", "123 Main St", "1234-567"),
            "123456789", Role.AGENT, new Agency("123"));
    
    Employee employee3 = new Employee("test1@example.com", "Test Employee", "123456789",
             new Passport("123456789", "", ""), new Location("State", "City", "District", "123 Main St", "1234-567"),
             "123456789", Role.AGENT, new Agency("123"));
    
    assertEquals(employee1, employee2);
    
    assertThrows(IllegalArgumentException.class, () -> {
        employee1.equals(employee3);});
    
    assertThrows(IllegalArgumentException.class, () -> {
        employee2.equals(employee3);});
    }
}
```

*It is also recommended to organize this content by subsections.* 

# 5. Construction (Implementation)


## Class CreateTaskController 



## Class Organization


# 6. Integration and Demo 

* A new option on the Employee menu options was added.


# 7. Observations







