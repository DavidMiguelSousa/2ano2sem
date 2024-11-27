package eapli.base.usermanagement.application;

import eapli.base.clientusermanagement.domain.address.Address;
import eapli.base.clientusermanagement.domain.jobopening.ContractType;
import eapli.base.clientusermanagement.domain.jobopening.JobMode;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.domain.jobopening.Status;
import eapli.base.clientusermanagement.repositories.JobOpeningRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.interviewmanagement.domain.NumberOfVacancies;
import eapli.base.services.JobOpeningManagementService;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

@UseCaseController
public class EditJobOpeningController {
    private final JobOpeningRepository jobOpeningRepo = PersistenceContext.repositories().jobOpenings();

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final JobOpeningManagementService service = new JobOpeningManagementService(jobOpeningRepo);

    public Iterable<JobOpening> activeJobOpenings() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);

        return service.findAllAvailable();
    }

    public void changeJobTitleTo(Designation designation, JobOpening jobOpening) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);
        jobOpening.updateJobTitle(designation);

    }

    public void changeContractTypeTo(ContractType contractType, JobOpening jobOpening) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);
        jobOpening.updateContractType(contractType);

    }

    public void changeJobModeTo(JobMode jobMode, JobOpening jobOpening) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);
        jobOpening.updateJobMode(jobMode);

    }

    public void changeJobDescriptionTo(Description description, JobOpening jobOpening) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);
        jobOpening.updateJobDescription(description);

    }

    public void changeAddressTo(Address address, JobOpening jobOpening) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);
        jobOpening.updateAddress(address);

    }

    public void changeNumberOfVacanciesTo(NumberOfVacancies numberOfVacancies, JobOpening jobOpening) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);
        jobOpening.updateNumberOfVacancies(numberOfVacancies);

    }

    public JobOpening saveToRepository(JobOpening theJobOpening) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);

        return service.saveToRepository(theJobOpening);
    }
}
