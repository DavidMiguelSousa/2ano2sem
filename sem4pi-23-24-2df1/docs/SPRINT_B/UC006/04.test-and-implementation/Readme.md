# UC006 - As Customer Manager I want to register a Job Opening

# 4. Tests

**SetUp:**

```java
class JobOpeningTest {

@Before
public void setUp() {
    jobReference = new JobReference("REF_123");
    jobTitle = Designation.valueOf("Software Engineer");
    address = new Address(District.ACORES, County.valueOf("aaa"), Parish.valueOf("aaa"), Street.valueOf("aaa"), DoorNumber.valueOf(12), PostalCode.valueOf("4400-146"));
    customer = new Customer();
    numberOfVacancies = new NumberOfVacancies(5);
    jobDescription = Description.valueOf("Development of Java applications");
    phases = new HashMap<>();
}
}
```
**Test 1:** Check constructor and properties.

```java
class JobOpeningTest {

  @Test
  public void testJobOpeningConstructorAndProperties() {
    JobOpening jobOpening = new JobOpening(jobReference, jobTitle, ContractType.FULL_TIME, JobMode.REMOTE, address, customer, numberOfVacancies, jobDescription, phases, interviewModel, jobRequirements);

    assertEquals(jobReference, jobOpening.jobReference());
    assertEquals(jobTitle, jobOpening.jobTitle());
    assertEquals(ContractType.FULL_TIME, jobOpening.contractType());
    assertEquals(JobMode.REMOTE, jobOpening.mode());
    assertEquals(address, jobOpening.address());
    assertEquals(customer, jobOpening.customer());
    assertEquals(numberOfVacancies, jobOpening.numberOfVacancies());
    assertEquals(jobDescription, jobOpening.jobDescription());
    assertEquals(phases, jobOpening.phases());
    assertEquals(interviewModel, jobOpening.interviewModel());
    assertEquals(jobRequirements, jobOpening.jobRequirements());
    assertTrue(jobOpening.status() == ApprovalStatus.ACCEPTED);
    assertTrue(jobOpening.isActive());
  }
  
}
```

**Test 2:** Test Status after created.

```java
class JobOpeningTest {

  @Test
  public void testApprovalStatusEffectOnActive() {
    JobOpening jobOpening = new JobOpening(jobReference, jobTitle, ContractType.FULL_TIME, JobMode.REMOTE, address, customer, numberOfVacancies, jobDescription, phases, interviewModel, jobRequirements);
    assertTrue(jobOpening.isActive());
  }
  
}
```
**Test 3:** Test null Job Reference.

```java
class JobOpeningTest {

  @Test(expected = IllegalArgumentException.class)
  public void testJobOpeningWithNullJobReference() {
    new JobOpening(null, jobTitle, ContractType.FULL_TIME, JobMode.REMOTE, address, customer, numberOfVacancies, jobDescription, phases, interviewModel, jobRequirements);
  }
  
}
```

**Test 4:** Test equals job openings.

```java
class JobOpeningTest {

  @Test
  public void testEqualsForIdenticalJobOpenings() {
    JobOpening jobOpening1 = new JobOpening(jobReference, jobTitle, ContractType.FULL_TIME, JobMode.REMOTE, address, customer, numberOfVacancies, jobDescription, phases, interviewModel, jobRequirements);
    JobOpening jobOpening2 = new JobOpening(jobReference, jobTitle, ContractType.FULL_TIME, JobMode.REMOTE, address, customer, numberOfVacancies, jobDescription, phases, interviewModel, jobRequirements);

    assertEquals(jobOpening1, jobOpening2);
    assertEquals(jobOpening2, jobOpening1);
  }
  
}
```

**Test 5:** Test not equals job openings.

```java
class JobOpeningTest {

    @Test
    public void testEqualsForDifferentJobOpenings() {
        JobOpening jobOpening1 = new JobOpening(jobReference, jobTitle, ContractType.FULL_TIME, JobMode.REMOTE, address, customer, numberOfVacancies, jobDescription, phases, interviewModel, jobRequirements);

        NumberOfVacancies differentNumberOfVacancies = new NumberOfVacancies(10);
        JobOpening jobOpening2 = new JobOpening(jobReference, jobTitle, ContractType.FULL_TIME, JobMode.REMOTE, address, customer, differentNumberOfVacancies, jobDescription, phases, interviewModel, jobRequirements);

        assertNotEquals(jobOpening1, jobOpening2);
        assertNotEquals(jobOpening2, jobOpening1);
    }

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
