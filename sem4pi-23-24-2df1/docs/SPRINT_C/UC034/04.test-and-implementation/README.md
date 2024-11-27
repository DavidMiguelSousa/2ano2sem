# UC034 -  As Customer Manager, I want to publish the results of the selection of candidates for a job opening, so that candidates and customer are notified by email of the result.

# 4. Tests

In this project, a Test-Driven Development (TDD) approach was used.

```java
class EmailServiceTest {
    @InjectMocks
    private JobOpeningManagementService jobOpeningManagementService;
    @Mock
    private JobApplicationRepository jobApplicationRepository;
    @Mock
    private CandidateRepository candidateRepository;
    private Candidate candidate1;
    private Candidate candidate2;

    private JobApplication jobApplication1;
    private JobApplication jobApplication2;
    private JobApplication jobApplication3;

    private JobOpening jobOpening1;
    private JobOpening jobOpening2;
    private List<JobApplication> jobApplications;
    private List<JobOpening> jobOpenings;

    private Rank rank;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        CandidateBuilder candidateBuilder = new CandidateBuilder();
        CustomerBuilder customerBuilder = new CustomerBuilder();
        JobOpeningBuilder jobOpeningBuilder = new JobOpeningBuilder();
        JobApplicationBuilder jobApplicationBuilder = new JobApplicationBuilder();

        SystemUser user1 = userBuilder.with(EmailAddress.valueOf("1200347@isep.ipp.pt"), Password.encodedAndValid("Password1!", new BasePasswordPolicy(), new PlainTextEncoder()).get(), Name.valueOf("Test", "Test")).build();
        SystemUser user2 = userBuilder.with(EmailAddress.valueOf("name@domain.com"), Password.encodedAndValid("Password2!", new BasePasswordPolicy(), new PlainTextEncoder()).get(), Name.valueOf("Name", "Name")).build();

        candidate1 = candidateBuilder.with(user1).build();
        candidate2 = candidateBuilder.with(user2).build();

        JobReference jobReference1 = new JobReference("CUST-001");
        JobReference jobReference2 = new JobReference("CUST-002");

        Designation jobTitle = Designation.valueOf("JobTitle");
        Address address = new Address(District.AVEIRO, County.valueOf("County"), Parish.valueOf("Parish"),
                Street.valueOf("Street"), DoorNumber.valueOf(123), PostalCode.valueOf("4250-420"));
        Description description = Description.valueOf("Description");

        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.set(2023, Calendar.JANUARY, 1);
        end.set(2023, Calendar.DECEMBER, 31);
        DateInterval interval = new DateInterval(start, end);

        Map<Phase, PhaseDetails> phases = new HashMap<>();
        phases.put(Phase.APPLICATION, new PhaseDetails(interval, Status.COMPLETED));
        phases.put(Phase.SCREENING, new PhaseDetails(interval, Status.COMPLETED));
        phases.put(Phase.INTERVIEW, new PhaseDetails(interval, Status.COMPLETED));
        phases.put(Phase.ANALYSIS, new PhaseDetails(interval, Status.COMPLETED));
        phases.put(Phase.RESULT, new PhaseDetails(interval, Status.COMPLETED));

        InterviewModel interviewModel = new InterviewModel(Designation.valueOf("InterviewModel"), Description.valueOf("InterviewModel"));
        JobRequirements jobRequirements = new JobRequirements(Designation.valueOf("JobRequirements"), Description.valueOf("JobRequirements"));

        SystemUser user3 = userBuilder.with("1200347@isep.ipp.pt", "Password1!", "Customer", "JobsForU", "1200347@isep.ipp.pt").build();
        Customer customer = customerBuilder.withSystemUser(user3).withCustomerCode(CustomerCode.valueOf("CUST")).withAddress(address).build();

        jobOpening1 = jobOpeningBuilder.with(jobReference1, jobTitle, ContractType.FULL_TIME, JobMode.HYBRID, address,
                customer, NumberOfVacancies.valueOf(1), description, phases, interviewModel, jobRequirements, Status.COMPLETED).build();

        jobOpening2 = jobOpeningBuilder.with(jobReference2, jobTitle, ContractType.FULL_TIME, JobMode.HYBRID, address,
                customer, NumberOfVacancies.valueOf(1), description, phases, interviewModel, jobRequirements, Status.IN_PROGRESS).build();

        jobOpenings = List.of(jobOpening1, jobOpening2);

        JobApplicationReference appRef1 = new JobApplicationReference("CUST-001" + candidate1.email().toString());
        JobApplicationReference appRef2 = new JobApplicationReference("CUST-001" + candidate2.email().toString());
        JobApplicationReference appRef3 = new JobApplicationReference("CUST-002" + candidate1.email().toString());

        jobApplication1 = jobApplicationBuilder.with(appRef1, jobOpening1, candidate1).build();
        jobApplication2 = jobApplicationBuilder.with(appRef2, jobOpening1, candidate2).build();
        jobApplication3 = jobApplicationBuilder.with(appRef3, jobOpening2, candidate1).build();

        jobApplications = List.of(jobApplication1);

        when(candidateRepository.ofIdentity(candidate1.identity())).thenReturn(Optional.of(candidate1));
        when(candidateRepository.ofIdentity(candidate2.identity())).thenReturn(Optional.of(candidate2));

        when(jobApplicationRepository.applicationsByCandidate(candidate1)).thenReturn(Stream.of(jobApplication1, jobApplication2, jobApplication3).filter(jobApplication -> jobApplication.candidate().equals(candidate1)).toList());
        when(jobApplicationRepository.applicationsByCandidate(candidate2)).thenReturn(Stream.of(jobApplication1, jobApplication2, jobApplication3).filter(jobApplication -> jobApplication.candidate().equals(candidate2)).toList());

        when(jobApplicationRepository.numberOfApplicants(jobApplication1)).thenReturn(Stream.of(jobApplication1, jobApplication2, jobApplication3).filter(jobApplication -> jobApplication.jobOpening().equals(jobApplication1.jobOpening())).toList().size());
        when(jobApplicationRepository.numberOfApplicants(jobApplication2)).thenReturn(Stream.of(jobApplication1, jobApplication2, jobApplication3).filter(jobApplication -> jobApplication.jobOpening().equals(jobApplication2.jobOpening())).toList().size());
        when(jobApplicationRepository.numberOfApplicants(jobApplication3)).thenReturn(Stream.of(jobApplication1, jobApplication2, jobApplication3).filter(jobApplication -> jobApplication.jobOpening().equals(jobApplication3.jobOpening())).toList().size());

        rank = new Rank(1, RankStatus.RANKED);

    }
}
```

**Test 1:** Check the email sending candidate accepted.

```java
 @Test
void sendEmailCandidateAccepted(){
    boolean result = jobOpeningManagementService.sendEmailCandidateAccepted(rank,jobApplication1,jobOpening1);
    assertEquals(true, result);

}
```

**Test 2:** Check the email sending candidate not accepted.

```java
@Test
void sendEmailCandidateNotAccepted(){
    boolean result = jobOpeningManagementService.sendEmailCandidateNotAccepted(jobApplication1, jobOpening1);
    assertEquals(true, result);

}
```

**Test 3:** Check the email sending customer.

```java
@Test
void sendEmailCustomer(){
    boolean result = jobOpeningManagementService.sendEmailCustomer(Map.of(rank, jobApplication1), jobOpening1);
    assertEquals(true, result);
}
```

# 5. Construction (Implementation)

To help achieve this Use Case's requirements, some EAPLI framework classes were used.

## Class RankJobApplicationsUI
```java
  protected boolean doShow() {
    Iterable<JobOpening> jobOpenings = controller.jobOpeningsWithAnalysisPhaseOn();
    List<JobOpening> jobOpeningsList = new ArrayList<>();

    jobOpenings.forEach(jobOpeningsList::add);

    if (jobOpeningsList.isEmpty()) {
        System.out.print("No job openings with analysis phase on available.\n");
        return false;
    }

    JobOpening jobOpening;

    do {
        SelectWidget<JobOpening> selector = new SelectWidget<>("Select Job Opening", jobOpeningsList, new JobOpeningPrinter());
        selector.show();
        jobOpening = selector.selectedElement();

        if (!jobOpening.applicationsRanked().isEmpty())
            System.out.print("""
                    ##Candidates were already ranked.##

                    """);

    } while (!jobOpening.applicationsRanked().isEmpty());

    int numberOfVacancies = jobOpening.numberOfVacancies().value();

    Iterable<JobApplication> jobApplications = controller.jobApplicationsOf(jobOpening);
    List<JobApplication> jobApplicationsList = new ArrayList<>();

    jobApplications.forEach(jobApplicationsList::add);

    Map<Rank, JobApplication> ranks = rankJobApplications(jobApplicationsList, numberOfVacancies);

    saveJobApplicationRanks(jobOpening, ranks, jobApplicationsList);

    for (Map.Entry<Rank, JobApplication> entry : ranks.entrySet()) {
        if(entry.getKey().status().equals(RankStatus.RANKED)){
            controller.sendEmailCandidateAccepted(entry.getKey(),entry.getValue(), jobOpening);
        }else{
            controller.sendEmailCandidateNotAccepted(entry.getValue(), jobOpening);
        }
    }

    controller.sendEmailCustomer(ranks, jobOpening);

    return false;
}
```

## Class RankJobApplicationsController
```java
public boolean sendEmailCandidateAccepted(Rank rank, JobApplication jobApplication, JobOpening jobOpening) {
    return jobOpeningManagementService.sendEmailCandidateAccepted(rank, jobApplication, jobOpening);
}

public boolean sendEmailCandidateNotAccepted(JobApplication jobApplication, JobOpening jobOpening) {
    return jobOpeningManagementService.sendEmailCandidateNotAccepted(jobApplication, jobOpening);
}

public boolean sendEmailCustomer(Map<Rank, JobApplication> rank, JobOpening jobOpening) {
    return jobOpeningManagementService.sendEmailCustomer(rank, jobOpening);
}
```

## Class JobOpeningManagementService

```java
 public boolean sendNotification(String destination, String subject, String message){

    try {
        Email email = new SimpleEmail();
        email.setHostName("frodo.dei.isep.ipp.pt");
        email.setSmtpPort(25);
        email.setSSLOnConnect(false);
        email.setFrom("customermanager@jobs4u.com", "Customer Manager");
        email.setSubject(subject);
        email.setMsg(message);
        email.addTo(destination);
        email.send();
        System.out.printf("Email sent to %s with subject %s and message %s\n", destination, subject, message);
        return true;
    } catch (EmailException e) {
        throw new RuntimeException(e);
    }
}

public boolean sendEmailCandidateAccepted(Rank rank, JobApplication jobApplication, JobOpening jobOpening){
    String emailCandidate = jobApplication.candidate().email().toString();
    String subjectCandidate = "Congratulations! - Results of candidates for job opening";
    String messageCandidate = "Great news! You've been selected for the " + jobOpening.jobTitle() +
            " in Jobs4U \n" +
            "Your position is: " + rank.rank();
    return sendNotification(emailCandidate, subjectCandidate, messageCandidate);
}

public boolean sendEmailCandidateNotAccepted(JobApplication jobApplication, JobOpening jobOpening){
    String emailCandidate = jobApplication.candidate().email().toString();
    String subjectCandidate = "Results of candidates for job opening";
    String messageCandidate = "Bad news! Unfortunately, you have not been selected for the " + jobOpening.jobTitle() +
            " in Jobs4U.";
    return sendNotification(emailCandidate, subjectCandidate, messageCandidate);
}

public boolean sendEmailCustomer(Map<Rank, JobApplication> rank, JobOpening jobOpening){
    String emailCustomer = jobOpening.customer().user().email().toString();
    String subjectCustomer = "Candidate List for Job Opening";

    StringBuilder candidateListBuilder = new StringBuilder();
    for (Map.Entry<Rank, JobApplication> entry : rank.entrySet()) {
        JobApplication jobApplication = entry.getValue();
        candidateListBuilder.append(jobApplication.candidate().name()).append(" - ").append(jobApplication.candidate().email().toString());
        if (jobApplication.phoneNumber() != null) {
            candidateListBuilder.append(", ").append(jobApplication.phoneNumber().toString());
        }
        candidateListBuilder.append("\n");
    }

    String messageCustomer = "Just wanted to let you know that we've wrapped up the selection process for the " +
            jobOpening.jobTitle() + ".\nHere’s the list of candidates:\n" +
            candidateListBuilder.toString();

    return sendNotification(emailCustomer, subjectCustomer, messageCustomer);
}
```

# 6. Integration and Demo

* This functionality has been integrated with results of candidates for jobOpening.

# 7. Observations

* To run this Use Case, check the Integration and Demonstration section in this Use Case [Read Me](../README.md) file.