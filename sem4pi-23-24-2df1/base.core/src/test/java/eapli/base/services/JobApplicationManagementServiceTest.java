package eapli.base.services;

import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.candidatemanagement.domain.CandidateBuilder;
import eapli.base.clientusermanagement.domain.ApprovalStatus;
import eapli.base.clientusermanagement.domain.Customer;
import eapli.base.clientusermanagement.domain.CustomerBuilder;
import eapli.base.clientusermanagement.domain.CustomerCode;
import eapli.base.clientusermanagement.domain.address.*;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplicationBuilder;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplicationReference;
import eapli.base.clientusermanagement.domain.jobopening.*;
import eapli.base.clientusermanagement.repositories.JobApplicationRepository;
import eapli.base.interviewmanagement.domain.NumberOfVacancies;
import eapli.base.interviewmanagement.domain.interview.Interview;
import eapli.base.interviewmanagement.domain.interview.InterviewDate;
import eapli.base.interviewmanagement.domain.interview.InterviewGrade;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class JobApplicationManagementServiceTest {

    @InjectMocks
    private JobApplicationManagementService service;

    @Mock
    private JobApplicationRepository applicationRepository;

    private Candidate candidate1;
    private Candidate candidate2;
    private Candidate candidate3;
    private Candidate candidate4;
    private Candidate candidate5;
    private Candidate candidate6;
    private Candidate candidate7;
    private Candidate candidate8;

    private JobOpening jobOpening;
    private JobOpening jobOpening1;
    private JobOpening jobOpening2;

    private JobApplication jobApplication1;
    private JobApplication jobApplication2;
    private JobApplication jobApplication3;


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

        //users
        SystemUser customerUser = userBuilder.with("customer@domain.com", "Password123!", "Customer", "Customer", "customer@domain.com").build();
        SystemUser candidateUser1 = userBuilder.with("test1@domain.com", "Password123!", "Test", "Test", "test1@domain.com").build();
        SystemUser candidateUser2 = userBuilder.with("test2@domain.com", "Password123!", "Name", "Name", "test2@domain.com").build();
        SystemUser candidateUser3 = userBuilder.with("test3@domain.com", "Password123!", "Testname", "Testname", "test3@domain.com").build();
        SystemUser candidateUser4 = userBuilder.with("test4@domain.com", "Password123!", "NameTest", "NameTest", "test4@domain.com").build();
        SystemUser candidateUser5 = userBuilder.with("test5@domain.com", "Password123!", "Testname", "Nametest", "test5@domain.com").build();
        SystemUser candidateUser6 = userBuilder.with("test6@domain.com", "Password123!", "NameTest", "TestName", "test6@domain.com").build();
        SystemUser candidateUser7 = userBuilder.with("test7@domain.com", "Password123!", "Test", "Name", "test7@domain.com").build();

        //needed parameters
        Address address = new Address(District.PORTO, new County("County"), new Parish("Parish"), new Street("Street"), new DoorNumber(1), new PostalCode("4000-009"));

        //customer
        Customer customer = customerBuilder.with(customerUser, CustomerCode.valueOf("CUST"), address).build();

        //candidates
        candidate1 = candidateBuilder.with(candidateUser1).build();
        candidate2 = candidateBuilder.with(candidateUser2).build();
        candidate3 = candidateBuilder.with(candidateUser3).build();
        candidate4 = candidateBuilder.with(candidateUser4).build();
        candidate5 = candidateBuilder.with(candidateUser5).build();
        candidate6 = candidateBuilder.with(candidateUser6).build();
        candidate7 = candidateBuilder.with(candidateUser7).build();

        //job openings
        jobOpening = jobOpeningBuilder.withJobReference(JobReference.valueOf("CUST-000")).withAddress(address)
                .withContractType(ContractType.FULL_TIME).withCustomer(customer)
                .withJobDescription(Description.valueOf("Description first")).withJobTitle(Designation.valueOf("Job Title Zero"))
                .withMode(JobMode.REMOTE).withNumberOfVacancies(NumberOfVacancies.valueOf(1))
                .withStatus(Status.IN_PROGRESS).build();

        jobOpening1 = jobOpeningBuilder.withJobReference(JobReference.valueOf("CUST-001")).withAddress(address)
                .withContractType(ContractType.FULL_TIME).withCustomer(customer)
                .withJobDescription(Description.valueOf("Description first")).withJobTitle(Designation.valueOf("Job Title First"))
                .withMode(JobMode.REMOTE).withNumberOfVacancies(NumberOfVacancies.valueOf(1))
                .withStatus(Status.IN_PROGRESS).build();
        jobOpening2 = jobOpeningBuilder.withJobReference(JobReference.valueOf("CUST-002")).withAddress(address)
                .withContractType(ContractType.FULL_TIME).withCustomer(customer)
                .withJobDescription(Description.valueOf("Description second")).withJobTitle(Designation.valueOf("Job Title Second"))
                .withMode(JobMode.REMOTE).withNumberOfVacancies(NumberOfVacancies.valueOf(1))
                .withStatus(Status.IN_PROGRESS).build();

        //job applications
        jobApplication1 = jobApplicationBuilder.with(JobApplicationReference.valueOf("CUST-001-001"), jobOpening1, candidate1).build();
        jobApplication2 = jobApplicationBuilder.with(JobApplicationReference.valueOf("CUST-002-001"), jobOpening2, candidate1).build();
        jobApplication3 = jobApplicationBuilder.with(JobApplicationReference.valueOf("CUST-001-002"), jobOpening1, candidate2).build();
        jobApplication4 = jobApplicationBuilder.with(JobApplicationReference.valueOf("CUST-001-004"), jobOpening, candidate4).build();
        jobApplication5 = jobApplicationBuilder.with(JobApplicationReference.valueOf("CUST-002-005"), jobOpening, candidate5).build();
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
        when(applicationRepository.save(jobApplication1)).thenReturn(jobApplication1);
        when(applicationRepository.save(jobApplication2)).thenReturn(jobApplication2);
        when(applicationRepository.save(jobApplication3)).thenReturn(jobApplication3);
        when(applicationRepository.save(jobApplication4)).thenReturn(jobApplication4);
        when(applicationRepository.save(jobApplication5)).thenReturn(jobApplication5);
        when(applicationRepository.save(jobApplication6)).thenReturn(jobApplication6);
        when(applicationRepository.save(jobApplication7)).thenReturn(jobApplication7);

        when(applicationRepository.ofIdentity(jobApplication1.identity())).thenReturn(Optional.of(jobApplication1));
        when(applicationRepository.ofIdentity(jobApplication2.identity())).thenReturn(Optional.of(jobApplication2));
        when(applicationRepository.ofIdentity(jobApplication3.identity())).thenReturn(Optional.of(jobApplication3));
        when(applicationRepository.ofIdentity(jobApplication4.identity())).thenReturn(Optional.of(jobApplication4));
        when(applicationRepository.ofIdentity(jobApplication5.identity())).thenReturn(Optional.of(jobApplication5));
        when(applicationRepository.ofIdentity(jobApplication6.identity())).thenReturn(Optional.of(jobApplication6));
        when(applicationRepository.ofIdentity(jobApplication7.identity())).thenReturn(Optional.of(jobApplication7));


        when(applicationRepository.applicationsByCandidate(any(Candidate.class))).thenAnswer(invocation -> {
            Candidate candidate = invocation.getArgument(0);
            List<JobApplication> allApplications = Arrays.asList(jobApplication1, jobApplication2, jobApplication3);
            return allApplications.stream()
                    .filter(app -> app.candidate().equals(candidate))
                    .collect(Collectors.toList());
        });

        when(applicationRepository.applicationsByJobOpening(any(JobOpening.class))).thenAnswer(invocation -> {
            JobOpening jobOpening = invocation.getArgument(0);
            List<JobApplication> allApplications = Arrays.asList(jobApplication1, jobApplication2, jobApplication3);
            return allApplications.stream()
                    .filter(app -> app.jobOpening().equals(jobOpening))
                    .collect(Collectors.toList());
        });
    }

    @Test
    void onlyCandidateApplicationsTest() {
        Iterable<JobApplication> actualApplications = service.applicationsByCandidate(candidate1);

        List<JobApplication> expectedApplications = Arrays.asList(jobApplication1, jobApplication2);
        assertEquals(expectedApplications, actualApplications);
    }

    @Test
    void noDataTest() {
        Iterable<JobApplication> actualApplications = service.applicationsByCandidate(candidate3);

        List<JobApplication> expectedApplications = Collections.emptyList();
        assertEquals(expectedApplications, actualApplications);
    }

    @Test
    void correctNumberOfApplicationsForJobOpeningTest() {
        int actualJobOpening1 = service.numberOfApplications(jobOpening1);
        int actualJobOpening2 = service.numberOfApplications(jobOpening2);

        int expectedJobOpening1 = 2;
        int expectedJobOpening2 = 1;
        assertEquals(expectedJobOpening1, actualJobOpening1);
        assertEquals(expectedJobOpening2, actualJobOpening2);
    }

    @Test
    void correctStatusTest() {
        jobApplication3.accept();

        when(applicationRepository.ofIdentity(jobApplication3.identity())).thenReturn(Optional.of(jobApplication3));

        ApprovalStatus actualStatus1 = service.jobApplicationStatus(jobApplication1.identity());
        ApprovalStatus actualStatus2 = service.jobApplicationStatus(jobApplication2.identity());
        ApprovalStatus actualStatus3 = service.jobApplicationStatus(jobApplication3.identity());

        ApprovalStatus expectedStatus1And2 = ApprovalStatus.PENDING;
        ApprovalStatus expectedStatus3 = ApprovalStatus.ACCEPTED;

        assertEquals(expectedStatus1And2, actualStatus1);
        assertEquals(expectedStatus1And2, actualStatus2);
        assertEquals(expectedStatus3, actualStatus3);
    }

    @Test
    void jobApplicationsWithInterviewsWillReturnEmptyWhenRepositoryHasNoJobApplications() {
        List<JobApplication> expected = Collections.emptyList();
        List<JobApplication> jobApplications = new ArrayList<>();

        List<JobApplication> actual = service.filterJobApplicationsWithInterview(jobApplications);

        assertEquals(expected, actual);
    }

    @Test
    void jobApplicationsWithInterviewsWillReturnEmptyWhenRepositoryHasNoJobApplicationsWithInterviews() {
        List<JobApplication> expected = List.of(jobApplication4, jobApplication5, jobApplication7);
        List<JobApplication> jobApplications = List.of(jobApplication4, jobApplication5, jobApplication6, jobApplication7);

        List<JobApplication> actual = service.filterJobApplicationsWithInterview(jobApplications);

        assertEquals(expected, actual);
    }
}