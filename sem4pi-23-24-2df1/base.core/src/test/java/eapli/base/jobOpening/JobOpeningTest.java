/*package eapli.base.jobOpening;

import static org.junit.Assert.*;

import eapli.base.clientusermanagement.domain.ApprovalStatus;
import eapli.base.clientusermanagement.domain.Customer;
import eapli.base.clientusermanagement.domain.address.*;
import eapli.base.clientusermanagement.domain.jobopening.*;
import eapli.base.interviewmanagement.domain.InterviewModel;
import eapli.base.interviewmanagement.domain.NumberOfVacancies;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.time.domain.model.DateInterval;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class JobOpeningTest {
    private JobReference jobReference;
    private Designation jobTitle;
    private Address address;
    private Customer customer;
    private NumberOfVacancies numberOfVacancies;
    private Description jobDescription;
    private Map<Phase, DateInterval> phases;
    private InterviewModel interviewModel;
    private JobRequirements jobRequirements;

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

    @Test
    public void testApprovalStatusEffectOnActive() {
        JobOpening jobOpening = new JobOpening(jobReference, jobTitle, ContractType.FULL_TIME, JobMode.REMOTE, address, customer, numberOfVacancies, jobDescription, phases, interviewModel, jobRequirements);
        assertTrue(jobOpening.isActive());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testJobOpeningWithNullJobReference() {
        new JobOpening(null, jobTitle, ContractType.FULL_TIME, JobMode.REMOTE, address, customer, numberOfVacancies, jobDescription, phases, interviewModel, jobRequirements);
    }

    @Test
    public void testEqualsForIdenticalJobOpenings() {
        JobOpening jobOpening1 = new JobOpening(jobReference, jobTitle, ContractType.FULL_TIME, JobMode.REMOTE, address, customer, numberOfVacancies, jobDescription, phases, interviewModel, jobRequirements);
        JobOpening jobOpening2 = new JobOpening(jobReference, jobTitle, ContractType.FULL_TIME, JobMode.REMOTE, address, customer, numberOfVacancies, jobDescription, phases, interviewModel, jobRequirements);

        assertEquals(jobOpening1, jobOpening2);
        assertEquals(jobOpening2, jobOpening1);
    }

    @Test
    public void testEqualsForDifferentJobOpenings() {
        JobOpening jobOpening1 = new JobOpening(jobReference, jobTitle, ContractType.FULL_TIME, JobMode.REMOTE, address, customer, numberOfVacancies, jobDescription, phases, interviewModel, jobRequirements);

        NumberOfVacancies differentNumberOfVacancies = new NumberOfVacancies(10);
        JobOpening jobOpening2 = new JobOpening(jobReference, jobTitle, ContractType.FULL_TIME, JobMode.REMOTE, address, customer, differentNumberOfVacancies, jobDescription, phases, interviewModel, jobRequirements);

        assertNotEquals(jobOpening1, jobOpening2);
        assertNotEquals(jobOpening2, jobOpening1);
    }

}*/
