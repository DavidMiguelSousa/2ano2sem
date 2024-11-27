package notification;

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
import eapli.base.clientusermanagement.domain.rank.Rank;
import eapli.base.clientusermanagement.domain.rank.RankStatus;
import eapli.base.clientusermanagement.repositories.JobApplicationRepository;
import eapli.base.clientusermanagement.repositories.JobOpeningRepository;
import eapli.base.interviewmanagement.domain.InterviewModel;
import eapli.base.interviewmanagement.domain.NumberOfVacancies;
import eapli.base.services.JobOpeningManagementService;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.*;
import eapli.framework.time.domain.model.DateInterval;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tcp_chat.TcpService;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


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

    @Test
    void sendEmail() {
        boolean result = jobOpeningManagementService.sendEmail(Phase.INTERVIEW, jobOpening1, Status.COMPLETED);
        assertEquals(true, result);
    }

    @Test
    void sendEmailCandidateAccepted(){
        boolean result = jobOpeningManagementService.sendEmailCandidateAccepted(rank,jobApplication1,jobOpening1);
        assertEquals(true, result);

    }

    @Test
    void sendEmailCandidateNotAccepted(){
        boolean result = jobOpeningManagementService.sendEmailCandidateNotAccepted(jobApplication1, jobOpening1);
        assertEquals(true, result);

    }

    @Test
    void sendEmailCustomer(){
        boolean result = jobOpeningManagementService.sendEmailCustomer(Map.of(rank, jobApplication1), jobOpening1);
        assertEquals(true, result);
    }

}