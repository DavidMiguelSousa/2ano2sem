# UC038 - As Candidate, I want to list all my applications and their state (including the number of applicants)

# 4. Tests

In this project, a Test-Driven Development (TDD) approach was used.

```java
package tcp_chat;

import domain.MessageCode;
import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.candidatemanagement.domain.CandidateBuilder;
import eapli.base.candidatemanagement.repositories.CandidateRepository;
import eapli.base.clientusermanagement.domain.Customer;
import eapli.base.clientusermanagement.domain.CustomerBuilder;
import eapli.base.clientusermanagement.domain.CustomerCode;
import eapli.base.clientusermanagement.domain.address.*;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplicationBuilder;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplicationReference;
import eapli.base.clientusermanagement.domain.jobopening.*;
import eapli.base.clientusermanagement.repositories.JobApplicationRepository;
import eapli.base.interviewmanagement.domain.InterviewModel;
import eapli.base.interviewmanagement.domain.NumberOfVacancies;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.*;
import eapli.framework.time.domain.model.DateInterval;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.*;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TcpChatSrvTest {

    @Mock
    private JobApplicationRepository jobApplicationRepository;

    @Mock
    private CandidateRepository candidateRepository;

    @InjectMocks
    private TcpService service;

    private Candidate candidate1;
    private Candidate candidate2;

    private JobApplication jobApplication1;
    private JobApplication jobApplication2;
    private JobApplication jobApplication3;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        CandidateBuilder candidateBuilder = new CandidateBuilder();
        CustomerBuilder customerBuilder = new CustomerBuilder();
        JobOpeningBuilder jobOpeningBuilder = new JobOpeningBuilder();
        JobApplicationBuilder jobApplicationBuilder = new JobApplicationBuilder();

        SystemUser user1 = userBuilder.with(EmailAddress.valueOf("test@domain.com"), Password.encodedAndValid("Password1!", new BasePasswordPolicy(), new PlainTextEncoder()).get(), Name.valueOf("Test", "Test")).build();
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

        SystemUser user3 = userBuilder.with("customer@jobs4u.com", "Password1!", "Customer", "JobsForU", "customer@jobs4u.com").build();
        Customer customer = customerBuilder.withSystemUser(user3).withCustomerCode(CustomerCode.valueOf("CUST")).withAddress(address).build();

        JobOpening jobOpening1 = jobOpeningBuilder.with(jobReference1, jobTitle, ContractType.FULL_TIME, JobMode.HYBRID, address,
                customer, NumberOfVacancies.valueOf(1), description, phases, interviewModel, jobRequirements, Status.IN_PROGRESS).build();

        JobOpening jobOpening2 = jobOpeningBuilder.with(jobReference2, jobTitle, ContractType.FULL_TIME, JobMode.HYBRID, address,
                customer, NumberOfVacancies.valueOf(1), description, phases, interviewModel, jobRequirements, Status.IN_PROGRESS).build();

        JobApplicationReference appRef1 = new JobApplicationReference("CUST-001" + candidate1.email().toString());
        JobApplicationReference appRef2 = new JobApplicationReference("CUST-001" + candidate2.email().toString());
        JobApplicationReference appRef3 = new JobApplicationReference("CUST-002" + candidate1.email().toString());

        jobApplication1 = jobApplicationBuilder.with(appRef1, jobOpening1, candidate1).build();
        jobApplication2 = jobApplicationBuilder.with(appRef2, jobOpening1, candidate2).build();
        jobApplication3 = jobApplicationBuilder.with(appRef3, jobOpening2, candidate1).build();

        when(candidateRepository.ofIdentity(candidate1.identity())).thenReturn(Optional.of(candidate1));
        when(candidateRepository.ofIdentity(candidate2.identity())).thenReturn(Optional.of(candidate2));

        when(jobApplicationRepository.applicationsByCandidate(candidate1)).thenReturn(Stream.of(jobApplication1, jobApplication2, jobApplication3).filter(jobApplication -> jobApplication.candidate().equals(candidate1)).toList());
        when(jobApplicationRepository.applicationsByCandidate(candidate2)).thenReturn(Stream.of(jobApplication1, jobApplication2, jobApplication3).filter(jobApplication -> jobApplication.candidate().equals(candidate2)).toList());

        when(jobApplicationRepository.numberOfApplicants(jobApplication1)).thenReturn(Stream.of(jobApplication1, jobApplication2, jobApplication3).filter(jobApplication -> jobApplication.jobOpening().equals(jobApplication1.jobOpening())).toList().size());
        when(jobApplicationRepository.numberOfApplicants(jobApplication2)).thenReturn(Stream.of(jobApplication1, jobApplication2, jobApplication3).filter(jobApplication -> jobApplication.jobOpening().equals(jobApplication2.jobOpening())).toList().size());
        when(jobApplicationRepository.numberOfApplicants(jobApplication3)).thenReturn(Stream.of(jobApplication1, jobApplication2, jobApplication3).filter(jobApplication -> jobApplication.jobOpening().equals(jobApplication3.jobOpening())).toList().size());

        service = new TcpService(candidateRepository, jobApplicationRepository);
    }

}
```

**Test 1:** Check communicate method gives the right response from the server.

```java
@Test
    public void testCommunicate() {
        String expected = """
                CANDIDATE TEST TEST APPLICATIONS

                APPLICATION REFERENCE          STATUS                         DATE OF SUBMISSION             JOB OPENING REFERENCE          NUMBER OF APPLICANTS         \s

                1    . CUST-001test@domain.com        Pending                        2024-06-07                     CUST-001                       2                            \s
                2    . CUST-002test@domain.com        Pending                        2024-06-07                     CUST-002                       1                            \s
                """;
        String actual = service.communicate(MessageCode.LIST_CANDIDATE_APPLICATIONS, candidate1.email().toString(), "");
        assertEquals(expected.trim(), actual.trim());
    }
```

# 5. Construction(Implementation)

To help achieve this Use Case's requirements, some EAPLI framework classes were used.

## Class ListCandidateApplicationsAction

```java
@Override
public boolean execute () {
  return new ListCandidateApplicationsUI().show();
}
```

## Class ListCandidateApplicationsUI

```java
import eapli.framework.infrastructure.authz.application.UserSession;

@Override
private boolean doShow() {
    final Candidate loggedCandidate = this.theController.getLoggedCandidate();
    try {
        System.out.println(theController.retrieveCandidateApplicationsFromServer(loggedCandidate));
    } catch (IOException e) {
        System.out.println("Error retrieving applications from server");
        e.printStackTrace();
        return false;
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    return true;
}
```

## Class ListCandidateApplicationsController

```java
public String retrieveCandidateApplicationsFromServer(Candidate candidate) throws Exception {
    return tcpService.communicate(MessageCode.LIST_CANDIDATE_APPLICATIONS, candidate.email().toString(), "");
}
```

## Class TcpService

```java
public String communicate (MessageCode code, String data1, String data2) throws Exception {
    //start the server
    Thread serverThread = new Thread(() -> {
        try {
            TcpChatSrv.startServer(jobApplicationRepository, candidateRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }
    });
    serverThread.start();

    //give the server some time to start
    Thread.sleep(1000);

    // Connect to the server
    TcpChatCli.connect();

    //prepare the message
    final byte versionBytes = 1;
    final byte codeBytes = (byte) code.code();
    final byte[] data1Bytes = data1.getBytes();
    final byte[] data2Bytes = data2.getBytes();

    //listen for response
    TcpChatCli.listenForResponse();

    //send the message
    TcpChatCli.sendMessage(versionBytes, codeBytes, data1Bytes, data2Bytes);

    //receive the response from the server
    String response = TcpChatCli.receiveMessage();

    //close the client connection
    TcpChatCli.close();

    //stop the server
    TcpChatSrv.stopServer();

    //wait for the server thread to finish
    serverThread.join();

    //return the response
    return response;
}
```

# 6. Integration and Demo

* A new option in the candidate applications menu was added. However, to _list its own applications_, the authenticated user must be able to log in the Candidate App.

# 7. Observations

* To run this Use Case, check the Integration and Demonstration section in this Use Case [Read Me](../README.md) file.