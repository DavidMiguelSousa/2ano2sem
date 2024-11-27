/*package eapli.base.app.other.console;

import eapli.base.app.backoffice.console.presentation.printer.JobOpeningPrinter;
import eapli.base.clientusermanagement.domain.Customer;
import eapli.base.clientusermanagement.domain.address.*;
import eapli.base.clientusermanagement.domain.jobopening.*;
import eapli.base.interviewmanagement.domain.InterviewModel;
import eapli.base.interviewmanagement.domain.NumberOfVacancies;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.authz.domain.model.*;
import eapli.framework.time.domain.model.DateInterval;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JobOpeningPrinterTest  {
    private InterviewModel interviewModel;
    private JobRequirements jobRequirements;
    public JobOpeningPrinterTest() {
    }

    @Test
    public void testPrintJobOpening() {
        JobOpening jobOpening = createTestJobOpening();

        JobOpeningPrinter printer = new JobOpeningPrinter();

        printer.visit(jobOpening);
    }

    private JobOpening createTestJobOpening() {
        JobReference jobReference = new JobReference("DEF_456");
        String title = "Software Engineer";
        ContractType contractType = ContractType.FULL_TIME;
        JobMode modeWork = JobMode.REMOTE;
        String address = "123 Main St";
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.CUSTOMER);

        Customer customer = new Customer();

        int vacancies = 5;
        String description = "Description";
        String requirements = "Requirements";

        Map<Phase, DateInterval> phases = new HashMap<>();
        JobOpening jobOpening = new JobOpening(jobReference, Designation.valueOf(title),contractType,modeWork, new Address(District.ACORES, County.valueOf("aaa"), Parish.valueOf("aaa"), Street.valueOf("aaa"), DoorNumber.valueOf(12), PostalCode.valueOf("4400-146")), customer, NumberOfVacancies.valueOf(vacancies), Description.valueOf(description),phases, interviewModel, jobRequirements);
        return jobOpening;
    }


}*/