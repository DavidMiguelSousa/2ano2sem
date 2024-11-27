package eapli.base.usermanagement.application;

import eapli.base.clientusermanagement.domain.Customer;
import eapli.base.clientusermanagement.domain.address.Address;
import eapli.base.clientusermanagement.domain.jobopening.*;
import eapli.base.clientusermanagement.repositories.JobApplicationRepository;
import eapli.base.clientusermanagement.repositories.JobOpeningRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.interviewmanagement.domain.InterviewModel;
import eapli.base.interviewmanagement.domain.NumberOfVacancies;
import eapli.base.services.CustomerManagementService;
import eapli.base.services.JobOpeningManagementService;
import eapli.base.services.PasswordGenerator;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;


public class AddJobOpeningController {
    private final JobOpeningRepository repo = PersistenceContext.repositories().jobOpenings();

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final JobOpeningManagementService jobOpeningManagementService = new JobOpeningManagementService(repo);
    private final CustomerManagementService customerManagementService = new CustomerManagementService();
    private final UserManagementService userManagementService = AuthzRegistry.userService();

    public JobOpening addJobOpening(JobReference jobReference, Designation jobTitle, ContractType contractType, JobMode jobMode,
                                    Address address, Customer customer, NumberOfVacancies numberOfVacancies, Description jobDescription) {

        if (customerManagementService.customerWithCode(customer.identity()).isEmpty()) {
            if (userManagementService.userOfIdentity(customer.user().identity()).isEmpty()) {
                userManagementService.registerNewUser(customer.user().email().toString(), PasswordGenerator.generatePassword(customer.user().email().toString()), customer.user().name().firstName(), customer.user().name().lastName(), new HashSet<>(customer.user().roleTypes()), Calendar.getInstance());
            }
            customerManagementService.registerNewCustomer(customer.user(), customer.identity(), customer.address());
        }
        return jobOpeningManagementService.registerNewJobOpening(jobReference, jobTitle, contractType, jobMode, address, customer, numberOfVacancies, jobDescription);
    }

    public JobOpening addJobOpeningWithPhase(JobReference jobReference, Designation jobTitle, ContractType contractType, JobMode jobMode,
                                    Address address, Customer customer, NumberOfVacancies numberOfVacancies, Description jobDescription, Map<Phase, PhaseDetails> mapPhase) {

        if (customerManagementService.customerWithCode(customer.identity()).isEmpty()) {
            if (userManagementService.userOfIdentity(customer.user().identity()).isEmpty()) {
                userManagementService.registerNewUser(customer.user().email().toString(), PasswordGenerator.generatePassword(customer.user().email().toString()), customer.user().name().firstName(), customer.user().name().lastName(), new HashSet<>(customer.user().roleTypes()), Calendar.getInstance());
            }
            customerManagementService.registerNewCustomer(customer.user(), customer.identity(), customer.address());
        }
        return jobOpeningManagementService.registerNewJobOpening(jobReference, jobTitle, contractType, jobMode, address, customer, numberOfVacancies, jobDescription, mapPhase);
    }

    public ContractType[] allContractTypes() {
        return ContractType.values();
    }

    public JobMode[] allJobModes() {
        return JobMode.values();
    }

    public int findLastRecord() {
        return jobOpeningManagementService.findLastRecord();
    }

    public Iterable<JobOpening> allJobOpening() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.CUSTOMER_MANAGER, BaseRoles.ADMIN);

        return jobOpeningManagementService.findAllAvailable();
    }

    public Optional<JobOpening> findByJobReference(JobReference jobReference) {
        return jobOpeningManagementService.findJobOpeningById(jobReference);
    }

    public Iterable<Customer> findAllCustomers() {
        return customerManagementService.allCustomer();
    }

    public void assignInterviewModel(JobOpening jobOpening, InterviewModel interviewModel) {
        jobOpeningManagementService.assignInterviewModel(jobOpening, interviewModel);
    }

    public void assignJobRequirements(JobOpening jobOpening, JobRequirements jobRequirements) {
        jobOpeningManagementService.assignJobRequirements(jobOpening, jobRequirements);
    }

    public JobOpening bootstrapSave(JobOpening jobOpening) {
        return jobOpeningManagementService.bootstrapSave(jobOpening);
    }
}
