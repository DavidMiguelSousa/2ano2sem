# UC027 - As Customer Manager, I want to close phases of the process for a job opening

# 4. Tests

In this project, a Test-Driven Development (TDD) approach was used.

```java
class JobOpeningManagementServiceTest {

    @InjectMocks
    private JobOpeningManagementService service;

    @Mock
    private JobOpeningRepository mockRepository;
    private JobOpening jobOpening;
    private JobOpening jobOpening1;
    private JobOpening jobOpening2;
    private JobOpening jobOpening3;
    private JobOpening jobOpening4;

    @BeforeEach
    void setUp() {
        //service
        MockitoAnnotations.openMocks(this);

        //builders
        JobOpeningBuilder jobOpeningBuilder = new JobOpeningBuilder();
        SystemUserBuilder systemUserBuilder = UserBuilderHelper.builder();
        CustomerBuilder customerBuilder = new CustomerBuilder();
        JobApplicationBuilder jobApplicationBuilder = new JobApplicationBuilder();

        //references
        JobReference jobReference = new JobReference("JobReference");
        JobReference jobReference1 = new JobReference("JobReference1");
        JobReference jobReference2 = new JobReference("JobReference2");
        JobReference jobReference3 = new JobReference("JobReference3");
        JobReference jobReference4 = new JobReference("JobReference4");

        //extra
        Designation jobTitle = Designation.valueOf("JobTitle");
        Address address = new Address(District.AVEIRO, County.valueOf("County"), Parish.valueOf("Parish"),
                Street.valueOf("Street"), DoorNumber.valueOf(123), PostalCode.valueOf("4250-420"));
        Description description = Description.valueOf("Description");

        //phases' interval
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.set(2023, Calendar.JANUARY, 1);
        end.set(2023, Calendar.DECEMBER, 31);
        DateInterval interval = new DateInterval(start, end);

        //phases
        Map<Phase, PhaseDetails> phases = new HashMap<>();
        phases.put(Phase.APPLICATION, new PhaseDetails(interval, Status.PENDING));
        phases.put(Phase.SCREENING, new PhaseDetails(interval, Status.PENDING));
        phases.put(Phase.INTERVIEW, new PhaseDetails(interval, Status.PENDING));
        phases.put(Phase.ANALYSIS, new PhaseDetails(interval, Status.PENDING));
        phases.put(Phase.RESULT, new PhaseDetails(interval, Status.PENDING));

        InterviewModel interviewModel = new InterviewModel(Designation.valueOf("InterviewModel"), Description.valueOf("InterviewModel"));
        JobRequirements jobRequirements = new JobRequirements(Designation.valueOf("JobRequirements"), Description.valueOf("JobRequirements"));

        //customer
        SystemUser systemUser = systemUserBuilder.with("customer@jobs4u.com", "Password1!", "Customer", "JobsForU", "customer@jobs4u.com").build();
        Customer customer = customerBuilder.withSystemUser(systemUser).withCustomerCode(CustomerCode.valueOf("C0000")).withAddress(address).build();

        //jobOpening
        jobOpening = jobOpeningBuilder.with(jobReference, jobTitle, ContractType.FULL_TIME, JobMode.HYBRID, address,
                customer, NumberOfVacancies.valueOf(1), description, phases, interviewModel, jobRequirements, Status.IN_PROGRESS).build();

        //jobOpening1 has Phase.INTERVIEW as pending
        Map<Phase, PhaseDetails> phases1 = new HashMap<>(phases);
        jobOpening1 = jobOpeningBuilder.with(jobReference1, jobTitle, ContractType.FULL_TIME, JobMode.HYBRID, address,
                customer, NumberOfVacancies.valueOf(1), description, phases1, interviewModel, jobRequirements, Status.IN_PROGRESS).build();

        //jobOpening2 has Phase.INTERVIEW as in progress
        Map<Phase, PhaseDetails> phases2 = new HashMap<>(phases1);
        phases2.put(Phase.APPLICATION, new PhaseDetails(interval, Status.COMPLETED));
        phases2.put(Phase.SCREENING, new PhaseDetails(interval, Status.COMPLETED));
        phases2.put(Phase.INTERVIEW, new PhaseDetails(interval, Status.IN_PROGRESS));
        jobOpening2 = jobOpeningBuilder.with(jobReference2, jobTitle, ContractType.FULL_TIME, JobMode.HYBRID, address,
                customer, NumberOfVacancies.valueOf(1), description, phases2, interviewModel, jobRequirements, Status.IN_PROGRESS).build();

        //jobOpening3 has Phase.INTERVIEW as completed
        Map<Phase, PhaseDetails> phases3 = new HashMap<>(phases2);
        phases3.put(Phase.INTERVIEW, new PhaseDetails(interval, Status.COMPLETED));
        jobOpening3 = jobOpeningBuilder.with(jobReference3, jobTitle, ContractType.FULL_TIME, JobMode.HYBRID, address,
                customer, NumberOfVacancies.valueOf(1), description, phases3, interviewModel, jobRequirements, Status.IN_PROGRESS).build();

        //jobOpening4 has all phases completed
        Map<Phase, PhaseDetails> phases4 = new HashMap<>(phases3);
        phases4.put(Phase.ANALYSIS, new PhaseDetails(interval, Status.COMPLETED));
        phases4.put(Phase.RESULT, new PhaseDetails(interval, Status.COMPLETED));
        jobOpening4 = jobOpeningBuilder.with(jobReference4, jobTitle, ContractType.FULL_TIME, JobMode.HYBRID, address,
                customer, NumberOfVacancies.valueOf(1), description, phases4, interviewModel, jobRequirements, Status.IN_PROGRESS).build();

        //repository
//        when(mockRepository.findAll()).thenReturn(Collections.singletonList(jobOpening));
//        when(mockRepository.save(jobOpening)).thenReturn(jobOpening);
    }

    void setUpForCloseWithoutInterview() {
        jobOpening.updatePhase(Phase.APPLICATION, Status.COMPLETED);
        jobOpening.updatePhase(Phase.SCREENING, Status.IN_PROGRESS);
        jobOpening.updatePhase(Phase.ANALYSIS, Status.PENDING);
        jobOpening.updatePhase(Phase.RESULT, Status.PENDING);
    }
    
}
```

**Test 1:** Check closing the next phase with a status of 'in_progress' and verify if the status is updated correctly.

```java
class JobOpeningServiceTest {

    @Test
    public void closePhase() {
        jobOpening.updatePhase(Phase.APPLICATION, Status.COMPLETED);
        jobOpening.updatePhase(Phase.SCREENING, Status.COMPLETED);
        jobOpening.updatePhase(Phase.INTERVIEW, Status.COMPLETED);
        jobOpening.updatePhase(Phase.ANALYSIS, Status.IN_PROGRESS);
        jobOpening.updatePhase(Phase.RESULT, Status.PENDING);

        service.closePhase(jobOpening);
        assertEquals(Status.COMPLETED, jobOpening.phases().get(Phase.ANALYSIS).status());
    }
}
```

**Test 2:** Test returning to the previous phase and verify if the statuses are also reverted.

```java
class JobOpeningServiceTest {

    @Test
    public void backToPreviousPhase() {

        jobOpening.updatePhase(Phase.INTERVIEW, Status.COMPLETED);
        jobOpening.updatePhase(Phase.ANALYSIS, Status.IN_PROGRESS);

        service.goBackToPreviousPhase(jobOpening);
        assertEquals(Status.IN_PROGRESS, jobOpening.phases().get(Phase.INTERVIEW).status());
        assertEquals(Status.PENDING, jobOpening.phases().get(Phase.ANALYSIS).status());
    }
}
```

**Test 3:** Test closing a phase when the INTERVIEW phase does not exist and verify if the next phase (Screening) is correctly closed.

```java
class JobOpeningServiceTest {

    @Test
    public void closeWithoutInterview() {
        
        setUpForCloseWithoutInterview();

        service.closePhase(jobOpening);
        assertEquals(Status.COMPLETED, jobOpening.phases().get(Phase.SCREENING).status());
    }
}
```

**Test 4:** Verify if the previous phase being in pending and not completed makes it impossible to close the current phase.
```java
class JobOpeningServiceTest {

    @Test
    public void closeWithoutPendingPreviousPhase() {

        jobOpening.updatePhase(Phase.APPLICATION, Status.COMPLETED);
        jobOpening.updatePhase(Phase.SCREENING, Status.COMPLETED);
        jobOpening.updatePhase(Phase.INTERVIEW, Status.PENDING);
        jobOpening.updatePhase(Phase.ANALYSIS, Status.IN_PROGRESS);
        jobOpening.updatePhase(Phase.RESULT, Status.PENDING);

        boolean result = service.closePhase(jobOpening);
        assertEquals(false, result);
    }

}
```

# 5. Construction (Implementation)

To help achieve this Use Case's requirements, some EAPLI framework classes were used.

## Class ClosePhasesJobOpeningAction

```java
@Override
public boolean execute() {
    return new ClosePhasesJobOpeningUI().show();
}
```

## Class ClosePhasesJobOpeningUI

```java
  public boolean show() {

    System.out.println("Setup Job Opening Phase");

    System.out.println("\nObtaining job openings available...\n");
    List<JobOpening> jobOpeningsAvailable = theController.filterJobOpenings(theController.obtainJobOpeningsAvailable());

    if (jobOpeningsAvailable.isEmpty()) {
        System.out.println("There are no job openings available.");
        return false;
    }


    final Iterable<JobOpening> jobOpenings = theController.allJobOpening();


    if (jobOpenings == null) {
        System.out.println("No job openings available.");
        return false;
    }

    SelectWidget<JobOpening> selectorJobOpening = new SelectWidget<>("Select job Opening", jobOpenings, new JobOpeningPrinter());
    selectorJobOpening.show();
    JobOpening jobOpeningSelected = selectorJobOpening.selectedElement();


    if (jobOpeningSelected == null) {
        System.out.println("No job opening selected.");
        return false;
    }else if (jobOpeningSelected.phases().isEmpty()) {
        System.out.println("The phases for this job opening have not been defined yet.");
        return false;
    }else if (theController.areAllPhasesComplete(jobOpeningSelected.phases())) {
        theController.updateStatus(jobOpeningSelected, Status.COMPLETED);
        System.out.println("All phases are completed. The job opening status has been updated to completed.");
        return true;
    }

    boolean validOption = true;

    while (validOption) {

        displayPhasesAndStatus(jobOpeningSelected);

        int option = Console.readInteger("Do you want to:\n" +
                "1. Close the current phase\n" +
                "2. Go back to the previous phase\n" +
                "3. Open the phase\n" +
                "4. Save and Exit\n" +
                "5. Cancel\n" +
                "Choose an option: ");

        switch (option) {
            case 1:
                boolean success = theController.managePhases(jobOpeningSelected);
                if (success) {
                    System.out.println("Closed the current phase and opened the next phase successfully.");
                } else {
                    System.out.println("Opened the next phase successfully, but no more phases to open.");
                    break;
                }
                break;
            case 2:
                success = theController.goBackToPreviousPhase(jobOpeningSelected);
                if (success) {
                    System.out.println("Opened the phase.");
                } else {
                    System.out.println("No previous phase available.");
                }
                break;
            case 3:
                success = theController.openPhase(jobOpeningSelected);
                if
                (success) {
                    System.out.println("Opened the phase.");
                } else {
                    System.out.println("Failed to open the phase. Either there are no phases available or the phases are not all pending.");
                }
                break;
            case 4:
                theController.saveToRepository(jobOpeningSelected);
                if (jobOpeningSelected.phases().get(Phase.INTERVIEW).status() == Status.COMPLETED){
                    theController.sendEmail(Phase.INTERVIEW, jobOpeningSelected, Status.COMPLETED);
                }
                System.out.println("Job opening saved. Exiting...");
                validOption = false;
                break;
            case 5:
                validOption = false;
                break;
            default:
                System.out.println("Invalid option. Please choose a valid option.");
                break;
        }
    }
    return true;
}

public void displayPhasesAndStatus(JobOpening jobOpening) {
    Map<Phase, PhaseDetails> phasesMap = jobOpening.phases();
    System.out.println("\nPhases and Their Current Status:");

    Phase currentPhase = null;
    Phase previousPhase = null;
    for (Phase phase : Phase.values()) {
        if (phasesMap.containsKey(phase)) {
            Status status = phasesMap.get(phase).status();
            System.out.println(" - " + phase + ": " + status);
            if (status == Status.IN_PROGRESS) {
                currentPhase = phase;
                if (phase.ordinal() > 0) {
                    previousPhase = Phase.values()[phase.ordinal() - 1];
                }
            }
        }
    }

    if (currentPhase != null) {
        if (previousPhase != null) {
            System.out.println("Previous phase: " + previousPhase + ": " + phasesMap.get(previousPhase).status());
        }
        System.out.println("Current phase: " + currentPhase + ": " + phasesMap.get(currentPhase).status());
        if (currentPhase.ordinal() + 1 < Phase.values().length) {
            Phase nextPhase = Phase.values()[currentPhase.ordinal() + 1];
            if (phasesMap.containsKey(nextPhase)) {
                System.out.println("Next phase: " + nextPhase + ": " + phasesMap.get(nextPhase).status());
            } else {
                System.out.println("Next phase: " + nextPhase + ": Not started yet");
            }
        }
    } else {
        System.out.println("No active phases currently.");
    }
}
```

## Class ClosePhasesJobOpeningController

```java
public Iterable<JobOpening> allJobOpening() {
    authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.CUSTOMER_MANAGER, BaseRoles.ADMIN);

    return jobOpeningSvc.findAllAvailable();
}

public List<JobOpening> obtainJobOpeningsAvailable() {
    authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.CUSTOMER_MANAGER);

    return jobOpeningSvc.findAllAvailable();
}

public boolean closePhase(JobOpening jobOpening){
    return jobOpeningSvc.closePhase(jobOpening);
}

public boolean goBackToPreviousPhase(JobOpening jobOpening){
    return jobOpeningSvc.goBackToPreviousPhase(jobOpening);
}

public List<JobOpening> filterJobOpenings(List<JobOpening> jobOpenings){
    return jobOpeningSvc.filterJobOpenings(jobOpenings);
}

public void updateStatus(JobOpening jobOpeningSelected, Status status) {
    jobOpeningSvc.updateStatus(jobOpeningSelected, status);
}

public boolean managePhases(JobOpening jobOpeningSelected) {
    return jobOpeningSvc.managePhases(jobOpeningSelected);
}

public void saveToRepository(JobOpening jobOpeningSelected) {
    jobOpeningSvc.saveToRepository(jobOpeningSelected);
}
```

## Class JobOpeningManagementService

```java
 @Transactional
public boolean managePhases(JobOpening jobOpening) {
    if (closePhase(jobOpening)) {
        return openPhase(jobOpening);
    }
    return false;
}

public void updatePhases(JobOpening jobOpening, Phase currentPhase, Status status) {
    jobOpeningSvc.updatePhases(jobOpening, currentPhase, status);
}

public boolean closePhase(JobOpening jobOpening){
    try {
        Map<Phase, PhaseDetails> phasesMap = jobOpening.phases();
        Phase currentPhase = findCurrentPhase(phasesMap);

        if (currentPhase != null) {
            if (currentPhase == Phase.RESULT) {
                updatePhases(jobOpening, currentPhase, Status.COMPLETED);
                return true;
            } else if (currentPhase != Phase.RESULT) {
                Phase previousPhase = findPreviousPhase(currentPhase, phasesMap);

                if (previousPhase == null || phasesMap.get(previousPhase).status() == Status.COMPLETED) {
                    updatePhases(jobOpening, currentPhase, Status.COMPLETED);
                    return true;
                }
            }
        }
        return false;
    } catch (Exception e) {
        // Handle exception, e.g., log or retry
        return false;
    }
}

private Phase findPreviousPhase(Phase currentPhase, Map<Phase, PhaseDetails> phasesMap) {
    Phase previousPhase = null;
    for (Phase phase : Phase.values()) {
        if (phase == currentPhase) {
            break;
        }
        if (phasesMap.containsKey(phase)) {
            previousPhase = phase;
        }
    }
    return previousPhase;
}

private Phase findCurrentPhase(Map<Phase, PhaseDetails> phasesMap) {
    for (Phase phase : Phase.values()){
        if(phasesMap.containsKey(phase) && phasesMap.get(phase).status() == Status.IN_PROGRESS){
            return phase;
        }
    }

    return null;
}

public boolean goBackToPreviousPhase(JobOpening jobOpening) {
    Map<Phase, PhaseDetails> phasesMap = jobOpening.phases();
    Phase currentPhase = findCurrentPhase(phasesMap);
    Phase previousPhase = null;

    if (currentPhase != null) {
        previousPhase = findPreviousPhase(currentPhase, phasesMap);
    }

    if (previousPhase != null) {
        if (phasesMap.get(currentPhase).status() == Status.IN_PROGRESS) {
            updatePhases(jobOpening, currentPhase, Status.PENDING);
        }
        updatePhases(jobOpening, previousPhase, Status.IN_PROGRESS);
        return true;
    } else {
        return false;
    }
}

public List<JobOpening> filterJobOpenings(List<JobOpening> jobOpenings) {
    List<JobOpening> filtered = new ArrayList<>();

    for (JobOpening jobOpening : jobOpenings) {
        if (!jobOpening.phases().isEmpty()) {
            filtered.add(jobOpening);
        }
    }

    return filtered;
}
```

# 6. Integration and Demo 

* A new option in the backoffice users menu was added.

# 7. Observations

* To run this Use Case, check the Integration and Demonstration section in this Use Case [Read Me](../README.md) file.