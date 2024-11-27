# UC033 - As Customer Manager, I want to get an ordered list of candidates, using the job interview points (grades), to help me analyze the candidates.

# 4. Tests 

In this project, a Test-Driven Development (TDD) approach was used.

## JobOpeningManagementServiceTest

```java
class JobOpeningManagementServiceTest {

    private JobOpening jobOpening1;
    private JobOpening jobOpening2;
    private JobOpening jobOpening3;
    private JobOpening jobOpening4;
    
    @BeforeEach
    void setUp() {
        //builders
        JobOpeningBuilder jobOpeningBuilder = new JobOpeningBuilder();
        SystemUserBuilder systemUserBuilder = UserBuilderHelper.builder();
        CustomerBuilder customerBuilder = new CustomerBuilder();
        JobApplicationBuilder jobApplicationBuilder = new JobApplicationBuilder();

        //references
        JobReference jobReference1 = new JobReference("JobReference1");
        JobReference jobReference2 = new JobReference("JobReference2");
        JobReference jobReference3 = new JobReference("JobReference3");
        JobReference jobReference4 = new JobReference("JobReference4");
        
        //extra
        Designation jobTitle = Designation.valueOf("JobTitle");
        Address address = new Address(District.AVEIRO, County.valueOf("County"), Parish.valueOf("Parish"),
                Street.valueOf("Street"), DoorNumber.valueOf(123), PostalCode.valueOf("4250-420"));
        Description description = Description.valueOf("Description");

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
        SystemUser systemUser = systemUserBuilder.with("customer@jobs4u.com", "Password1!", "Customer", "Jobs4U", "customer@jobs4u.com").build();
        Customer customer = customerBuilder.withSystemUser(systemUser).withCustomerCode(CustomerCode.valueOf("C0000")).withAddress(address).build();
        
        //jobOpenings
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
    }
}
```

**Test 1:** Check if the method jobOpeningsWithPhaseInterviewCompleted() will return an empty list, if there are no job openings saved in the repository.

```java
class JobOpeningManagementServiceTest {
    @Test
    void jobOpeningsWithPhaseInterviewCompletedWillReturnEmptyWhenRepositoryHasNoJobOpenings() {
        List<JobOpening> expected = Collections.emptyList();

        when(mockRepository.findAll()).thenReturn(Collections.emptyList());

        List<JobOpening> actual = service.jobOpeningsWithPhaseInterviewCompleted();

        assertEquals(expected, actual);
    }
}
```

**Test 2:** Check if the method jobOpeningsWithPhaseInterviewCompleted() will return a empty list, if there are no job openings with interview phase complete saved in the repository.

```java
class JobOpeningManagementServiceTest{
    @Test
    void jobOpeningsWithPhaseInterviewCompletedWillReturnEmptyWhenRepositoryHasNoJobOpeningsWithPhaseInterviewComplete() {
        List<JobOpening> expected = Collections.emptyList();

        when(mockRepository.save(jobOpening1)).thenReturn(jobOpening1);
        when(mockRepository.save(jobOpening2)).thenReturn(jobOpening2);
        when(mockRepository.findAll()).thenReturn(List.of(jobOpening1, jobOpening2));

        List<JobOpening> actual = service.jobOpeningsWithPhaseInterviewCompleted();

        assertEquals(expected.size(), actual.size());
    }
}
```

**Test 3:** Check if the method jobOpeningsWithPhaseInterviewCompleted() will return a list with one job opening, if there is one job opening with interview phase complete saved in the repository.

```java
class JobOpeningManagementServiceTest{
    @Test
    void jobOpeningsWithPhaseInterviewCompletedWillReturnOneWhenRepositoryHasJobOpeningsWithPhaseInterviewComplete() {
        List<JobOpening> expected = List.of(jobOpening3);

        when(mockRepository.save(jobOpening1)).thenReturn(jobOpening1);
        when(mockRepository.save(jobOpening2)).thenReturn(jobOpening2);
        when(mockRepository.save(jobOpening3)).thenReturn(jobOpening3);
        when(mockRepository.findAll()).thenReturn(List.of(jobOpening1, jobOpening2, jobOpening3));

        List<JobOpening> actual = service.jobOpeningsWithPhaseInterviewCompleted();

        assertEquals(expected, actual);
    }
}
```

**Test 4:** Check if the method jobOpeningsWithPhaseInterviewCompleted() will return a list job openings, if there are job openings with interview phase complete saved in the repository.

```java
class JobOpeningManagementServiceTest{
    @Test
    void jobOpeningsWithPhaseInterviewCompletedWillReturnListWhenRepositoryHasJobOpeningsWithPhaseInterviewComplete() {
        List<JobOpening> expected = List.of(jobOpening3, jobOpening4);

        when(mockRepository.save(jobOpening1)).thenReturn(jobOpening1);
        when(mockRepository.save(jobOpening2)).thenReturn(jobOpening2);
        when(mockRepository.save(jobOpening3)).thenReturn(jobOpening3);
        when(mockRepository.save(jobOpening4)).thenReturn(jobOpening4);
        when(mockRepository.findAll()).thenReturn(List.of(jobOpening1, jobOpening2, jobOpening3, jobOpening4));

        List<JobOpening> actual = service.jobOpeningsWithPhaseInterviewCompleted();

        assertEquals(expected, actual);
    }
}
```

## JobApplicationManagementServiceTest

```java


class JobApplicationManagementServiceTest {
    @InjectMocks
    private JobApplicationManagementService service;

    @Mock
    private JobApplicationRepository applicationRepository;

    private Candidate candidate1;
    private Candidate candidate2;
    private Candidate candidate3;
    private Candidate candidate4;

    private JobOpening jobOpening;

    private JobApplication jobApplication4;
    private JobApplication jobApplication5;
    private JobApplication jobApplication6;
    private JobApplication jobApplication7;

    private Map<Phase, PhaseDetails> phases;

    @BeforeEach
    void setUp() {
        //mockito
        MockitoAnnotations.openMocks(this);

        //service
        service = new JobApplicationManagementService(applicationRepository);

        //builders
        SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        CustomerBuilder customerBuilder = new CustomerBuilder();
        CandidateBuilder candidateBuilder = new CandidateBuilder();
        JobOpeningBuilder jobOpeningBuilder = new JobOpeningBuilder();
        JobApplicationBuilder jobApplicationBuilder = new JobApplicationBuilder();

        //needed parameters
        Address address = new Address(District.PORTO, new County("County"), new Parish("Parish"), new Street("Street"), new DoorNumber(1), new PostalCode("4000-009"));

        //users
        SystemUser customerUser = userBuilder.with("customer@domain.com", "Password123!", "Customer", "Customer", "customer@domain.com").build();
        SystemUser candidateUser4 = userBuilder.with("test4@domain.com", "Password123!", "NameTest", "NameTest", "test4@domain.com").build();
        SystemUser candidateUser5 = userBuilder.with("test5@domain.com", "Password123!", "Testname", "Nametest", "test5@domain.com").build();
        SystemUser candidateUser6 = userBuilder.with("test6@domain.com", "Password123!", "NameTest", "TestName", "test6@domain.com").build();
        SystemUser candidateUser7 = userBuilder.with("test7@domain.com", "Password123!", "Test", "Name", "test7@domain.com").build();
        
        //customers
        Customer customer = customerBuilder.with(customerUser, CustomerCode.valueOf("CUST"), address).build();

        //candidates
        candidate4 = candidateBuilder.with(candidateUser1).build();
        candidate5 = candidateBuilder.with(candidateUser2).build();
        candidate6 = candidateBuilder.with(candidateUser3).build();
        candidate7 = candidateBuilder.with(candidateUser4).build();

        //jobOpenings
        jobOpening = jobOpeningBuilder.withJobReference(JobReference.valueOf("CUST-001")).withAddress(address)
                .withContractType(ContractType.FULL_TIME).withCustomer(customer)
                .withJobDescription(Description.valueOf("Description first")).withJobTitle(Designation.valueOf("Job Title First"))
                .withMode(JobMode.REMOTE).withNumberOfVacancies(NumberOfVacancies.valueOf(1))
                .withStatus(Status.IN_PROGRESS).build();

        //jobApplications
        jobApplication4 = jobApplicationBuilder.with(JobApplicationReference.valueOf("CUST-001-004"), jobOpening, candidate4).build();
        jobApplication5 = jobApplicationBuilder.with(JobApplicationReference.valueOf("CUST-001-005"), jobOpening, candidate5).build();
        jobApplication6 = jobApplicationBuilder.with(JobApplicationReference.valueOf("CUST-001-006"), jobOpening, candidate6).build();
        jobApplication7 = jobApplicationBuilder.with(JobApplicationReference.valueOf("CUST-001-007"), jobOpening, candidate7).build();

        //interviews
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, Calendar.JANUARY, 1);

        Interview interview1 = new Interview(new InterviewDate(calendar), Designation.valueOf("Interview"), Description.valueOf("Interview1"));
        InterviewGrade grade1 = new InterviewGrade(40.0);
        interview1.defineGrade(grade1);

        Interview interview2 = new Interview(new InterviewDate(calendar), Designation.valueOf("Interview"), Description.valueOf("Interview2"));
        InterviewGrade grade2 = new InterviewGrade(70.0);
        interview2.defineGrade(grade2);

        Interview interview3 = new Interview(new InterviewDate(calendar), Designation.valueOf("Interview"), Description.valueOf("Interview3"));
        InterviewGrade grade3 = new InterviewGrade(90.0);
        interview3.defineGrade(grade3);
        
        //assign interviews
        jobApplication4.addInterview(interview1);
        jobApplication5.addInterview(interview2);
        jobApplication7.addInterview(interview3);

        //repository
        when(applicationRepository.save(jobApplication4)).thenReturn(jobApplication4);
        when(applicationRepository.save(jobApplication5)).thenReturn(jobApplication5);
        when(applicationRepository.save(jobApplication6)).thenReturn(jobApplication6);
        when(applicationRepository.save(jobApplication7)).thenReturn(jobApplication7);
    }
}
```

**Test 1:** Check if the method filterJobApplicationsWithInterview() will return an empty list, if there are no job applications saved in the repository.

```java
class JobApplicationManagementServiceTest {
    @Test
    void jobApplicationsWithInterviewsWillReturnEmptyWhenRepositoryHasNoJobApplications() {
        List<JobApplication> expected = Collections.emptyList();
        List<JobApplication> jobApplications = new ArrayList<>();

        List<JobApplication> actual = service.filterJobApplicationsWithInterview(jobApplications);

        assertEquals(expected, actual);
    }
}
```

**Test 2:** Check if the method filterJobApplicationsWithInterview() will return a empty list, if there are no job applications with interviews saved in the repository.

```java
class JobApplicationManagementServiceTest {
    @Test
    void jobApplicationsWithInterviewsWillReturnEmptyWhenRepositoryHasNoJobApplicationsWithInterviews() {
        List<JobApplication> expected = List.of(jobApplication4, jobApplication5, jobApplication7);
        List<JobApplication> jobApplications = List.of(jobApplication4, jobApplication5, jobApplication6, jobApplication7);

        List<JobApplication> actual = service.filterJobApplicationsWithInterview(jobApplications);

        assertEquals(expected, actual);
    }
}
```

# 5. Construction (Implementation)

To help achieve this Use Case's requirements, some EAPLI framework classes were used.

## Class ListJobOpeningsInterviewGradesAction
```java
@Override
public boolean execute() {
    return new ListJobOpeningsInterviewGradesUI().show();
}
```

## Class ListJobOpeningsInterviewGradesUI

```java
private ListJobOpeningsInterviewGradesController controller = new ListJobOpeningsInterviewGradesController();

public boolean doShow() {
    Iterable<JobOpening> allJobOpenings = controller.jobOpeningsWithPhaseInterviewCompleted();
    List<JobOpening> jobOpenings = new ArrayList<>();
    allJobOpenings.forEach(jobOpenings::add);

    if (jobOpenings.isEmpty()) {
        System.out.println("No job openings with interview phase completed.\n");
        return false;
    }

    SelectWidget<JobOpening> selector = new SelectWidget<>("Select a Job Opening", jobOpenings, new JobOpeningPrinter());
    selector.show();
    JobOpening jobOpening = selector.selectedElement();

    List<JobApplication> jobApplications = controller.jobApplicationsOf(jobOpening);

    if (jobApplications.isEmpty()) {
        System.out.println("No job applications for that job opening were found.\nTry again later!\n");
        return false;
    }

    List<JobApplication> jobApplicationsFiltered = controller.filterJobApplicationsWithInterview(jobApplications);

    if (jobApplicationsFiltered.isEmpty()) {
        System.out.println("No job applications with interviews for that job opening were found.\nTry again later!\n");
        return false;
    }

    System.out.println("Job Opening's [" + jobOpening.jobReference() + "] Interview Grades:\n");
    for (JobApplication jobApplication : jobApplicationsFiltered) {
        System.out.println(jobApplication.candidate().username() + " - " + jobApplication.interview().grade().value());
    }

    Console.readLine("Press any key to exit...");

    return false;
}

@Override
public String headline() {
    return "List Job Opening's Interview Grades";
}
```

## Class ListJobOpeningsInterviewGradesController
```java
private final JobOpeningRepository jobOpeningRepository = PersistenceContext.repositories().jobOpenings();
private final JobApplicationRepository jobApplicationRepository = PersistenceContext.repositories().jobApplications();
private final JobOpeningManagementService jobOpeningManagementService = new JobOpeningManagementService(jobOpeningRepository);
private final JobApplicationManagementService jobApplicationManagementService = new JobApplicationManagementService(jobApplicationRepository);

public Iterable<JobOpening> jobOpeningsWithPhaseInterviewCompleted() {
    return jobOpeningManagementService.jobOpeningsWithPhaseInterviewCompleted();
}

public List<JobApplication> jobApplicationsOf(JobOpening jobOpening) {
    return jobApplicationManagementService.jobApplicationsOf(jobOpening);
}

public List<JobApplication> filterJobApplicationsWithInterview(List<JobApplication> jobApplications) {
    return jobApplicationManagementService.filterJobApplicationsWithInterview(jobApplications);
}
```

## Class JobApplicationManagementService
```java
private final JobApplicationRepository jobApplicationRepository;

public JobApplicationManagementService(JobApplicationRepository jobApplicationRepository) {
    this.jobApplicationRepository = jobApplicationRepository;
}

public List<JobApplication> filterJobApplicationsWithInterview(List<JobApplication> jobApplications) {
    return jobApplicationsOfJobOpening.stream().filter(jobApplication -> jobApplication.interview().isPresent()).collect(Collectors.toList());
}
```


# 6. Integration and Demonstration 

* A new option in the backoffice users menu was added. However, to display _job opening's interview grades_, the authenticated user must have customer manager permissions.

# 7. Observations

* To run this Use Case, check the Integration and Demonstration section in this Use Case [Read Me](../README.md) file.