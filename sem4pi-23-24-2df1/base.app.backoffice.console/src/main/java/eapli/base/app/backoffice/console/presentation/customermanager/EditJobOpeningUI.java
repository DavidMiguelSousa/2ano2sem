package eapli.base.app.backoffice.console.presentation.customermanager;

import eapli.base.app.backoffice.console.presentation.printer.JobOpeningPrinter;
import eapli.base.clientusermanagement.domain.address.*;
import eapli.base.clientusermanagement.domain.jobopening.ContractType;
import eapli.base.clientusermanagement.domain.jobopening.JobMode;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.domain.jobopening.Status;
import eapli.base.interviewmanagement.domain.NumberOfVacancies;
import eapli.base.usermanagement.application.EditJobOpeningController;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.List;

public class EditJobOpeningUI extends AbstractUI {

    EditJobOpeningController controller = new EditJobOpeningController();
    final Iterable<JobOpening> jobOpenings = controller.activeJobOpenings();
    final Iterable<JobMode> jobModes = List.of(JobMode.values());
    final Iterable<ContractType> contractTypes = List.of(ContractType.values());
    final Iterable<District> districts = List.of(District.values());
    final Iterable<String> sections = List.of("Edit Job Title",
            "Edit Contract Type",
            "Edit Job Mode",
            "Edit Job Description",
            "Edit Address",
            "Edit Number of Vacancies",
            "Edit Approval Status");

    SelectWidget<JobOpening> jobOpeningSelectWidget;
    SelectWidget<ContractType> contractTypeSelectWidget;
    SelectWidget<JobMode> jobModeSelectWidget;
    SelectWidget<String> optionSelector;
    SelectWidget<District> districtSelectWidget;

    @Override
    protected boolean doShow() {
        buildSelectors();
        System.out.printf("# %-15s%-30s%-15s%-12s%-45s%-20s%-15s%-30s%n", "Job Reference", "Title", "Contract Type", "Job Mode", "Address", "Customer", "Vacancies", "Description");

        jobOpeningSelectWidget.show();
        JobOpening theJobOpening = jobOpeningSelectWidget.selectedElement();

        optionSelector.show();
        int option = optionSelector.selectedOption();

        switch (option) {
            case (1):
                Designation jobTitle = Designation.valueOf(Console.readLine("Input new job title:"));

                controller.changeJobTitleTo(jobTitle, theJobOpening);
            case (2):
                contractTypeSelectWidget.show();
                ContractType contractType = contractTypeSelectWidget.selectedElement();

                controller.changeContractTypeTo(contractType, theJobOpening);
            case (3):
                jobModeSelectWidget.show();
                JobMode jobMode = jobModeSelectWidget.selectedElement();

                controller.changeJobModeTo(jobMode, theJobOpening);
            case (4):
                Description jobDescription = Description.valueOf(Console.readLine("Input a new job description:"));

                controller.changeJobDescriptionTo(jobDescription, theJobOpening);
            case (5):
                districtSelectWidget.show();
                District district = districtSelectWidget.selectedElement();
                County county = County.valueOf(Console.readLine("Input the District name"));
                Parish parish = Parish.valueOf(Console.readLine("Input the Parish name"));
                Street street = Street.valueOf(Console.readLine("Input Street name"));
                DoorNumber doorNumber = DoorNumber.valueOf(Console.readInteger("Input the Door Number"));
                PostalCode postalCode = PostalCode.valueOf(Console.readLine("Input the Postal Code (XXXX-XXX)"));

                Address address = new Address(district, county, parish, street, doorNumber, postalCode);

                controller.changeAddressTo(address, theJobOpening);
            case (6):
                NumberOfVacancies numberOfVacancies = NumberOfVacancies.valueOf(Console.readInteger("Input a new number of vacancies"));

                controller.changeNumberOfVacanciesTo(numberOfVacancies, theJobOpening);
        }

        controller.saveToRepository(theJobOpening);

        return true;
    }

    private void buildSelectors() {
        optionSelector = new SelectWidget<>("Choose a section to edit", sections);
        jobOpeningSelectWidget = new SelectWidget<>("Choose a job opening", jobOpenings, new JobOpeningPrinter());
        contractTypeSelectWidget = new SelectWidget<>("Choose a new contract type", contractTypes);
        jobModeSelectWidget = new SelectWidget<>("Choose a new job mode", jobModes);
        districtSelectWidget = new SelectWidget<>("Choose a district", districts);
    }

    @Override
    public String headline() {
        return "Edit Job Opening";
    }
}
