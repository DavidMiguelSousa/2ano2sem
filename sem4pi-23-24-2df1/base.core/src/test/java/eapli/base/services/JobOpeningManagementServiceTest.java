package eapli.base.services;

import eapli.base.clientusermanagement.domain.Customer;
import eapli.base.clientusermanagement.domain.CustomerBuilder;
import eapli.base.clientusermanagement.domain.CustomerCode;
import eapli.base.clientusermanagement.domain.address.*;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplicationBuilder;
import eapli.base.clientusermanagement.domain.jobopening.*;
import eapli.base.clientusermanagement.repositories.JobOpeningRepository;
import eapli.base.interviewmanagement.domain.InterviewModel;
import eapli.base.interviewmanagement.domain.NumberOfVacancies;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import eapli.framework.time.domain.model.DateInterval;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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

    @Test
    public void openPhase() {
        jobOpening.updatePhase(Phase.APPLICATION, Status.COMPLETED);
        jobOpening.updatePhase(Phase.SCREENING, Status.PENDING);

        service.openPhase(jobOpening);
        assertEquals(Status.IN_PROGRESS, jobOpening.phases().get(Phase.SCREENING).status());
    }

    @Test
    public void openFirstPhase() {

        for (Phase phase : Phase.values()) {
            jobOpening.updatePhase(phase, Status.PENDING);
        }
        assertTrue(service.openPhase(jobOpening));
        assertEquals(Status.IN_PROGRESS, jobOpening.phases().get(Phase.APPLICATION).status());

    }

    @Test
    public void openLastPhase() {
        for (Phase phase : Phase.values()) {
            if (phase != Phase.RESULT) {
                jobOpening.updatePhase(phase, Status.COMPLETED);
            } else {
                jobOpening.updatePhase(phase, Status.PENDING);
            }
        }
        assertTrue(service.openPhase(jobOpening));
        assertEquals(Status.IN_PROGRESS, jobOpening.phases().get(Phase.RESULT).status());
    }


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


    @Test
    public void backToPreviousPhase() {

        jobOpening.updatePhase(Phase.INTERVIEW, Status.COMPLETED);
        jobOpening.updatePhase(Phase.ANALYSIS, Status.IN_PROGRESS);

        service.goBackToPreviousPhase(jobOpening);
        assertEquals(Status.IN_PROGRESS, jobOpening.phases().get(Phase.INTERVIEW).status());
        assertEquals(Status.PENDING, jobOpening.phases().get(Phase.ANALYSIS).status());
    }


    @Test
    public void noNextPhaseAfterResult() {

        jobOpening.updatePhase(Phase.APPLICATION, Status.COMPLETED);
        jobOpening.updatePhase(Phase.SCREENING, Status.COMPLETED);
        jobOpening.updatePhase(Phase.INTERVIEW, Status.COMPLETED);
        jobOpening.updatePhase(Phase.ANALYSIS, Status.COMPLETED);
        jobOpening.updatePhase(Phase.RESULT, Status.IN_PROGRESS);

        boolean result = service.openPhase(jobOpening);
        assertEquals(false, result);
    }

    @Test
    public void closeWithoutInterview() {


        setUpForCloseWithoutInterview();

        service.closePhase(jobOpening);
        assertEquals(Status.COMPLETED, jobOpening.phases().get(Phase.SCREENING).status());
    }

    @Test
    public void openWithoutInterview() {

        jobOpening.updatePhase(Phase.APPLICATION, Status.COMPLETED);
        jobOpening.updatePhase(Phase.SCREENING, Status.COMPLETED);

        jobOpening.phases().remove(Phase.INTERVIEW);

        service.openPhase(jobOpening);
        assertEquals(Status.IN_PROGRESS, jobOpening.phases().get(Phase.ANALYSIS).status());
    }

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

    @Test
    public void allCompletePhase() {
        jobOpening.updatePhase(Phase.APPLICATION, Status.COMPLETED);
        jobOpening.updatePhase(Phase.SCREENING, Status.COMPLETED);
        jobOpening.updatePhase(Phase.INTERVIEW, Status.COMPLETED);
        jobOpening.updatePhase(Phase.ANALYSIS, Status.COMPLETED);
        jobOpening.updatePhase(Phase.RESULT, Status.COMPLETED);

        boolean result = service.openPhase(jobOpening);
        assertEquals(true, result);
    }

    @Test
    void jobOpeningsWithPhaseInterviewCompletedWillReturnEmptyWhenRepositoryHasNoJobOpenings() {
        List<JobOpening> expected = Collections.emptyList();

        when(mockRepository.findAll()).thenReturn(Collections.emptyList());

        List<JobOpening> actual = service.jobOpeningsWithPhaseInterviewCompleted();

        assertEquals(expected, actual);
    }

    @Test
    void jobOpeningsWithPhaseInterviewCompletedWillReturnEmptyWhenRepositoryHasNoJobOpeningsWithPhaseInterviewComplete() {
        List<JobOpening> expected = Collections.emptyList();

        when(mockRepository.save(jobOpening1)).thenReturn(jobOpening1);
        when(mockRepository.save(jobOpening2)).thenReturn(jobOpening2);
        when(mockRepository.findAll()).thenReturn(List.of(jobOpening1, jobOpening2));

        List<JobOpening> actual = service.jobOpeningsWithPhaseInterviewCompleted();

        assertEquals(expected.size(), actual.size());
    }

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