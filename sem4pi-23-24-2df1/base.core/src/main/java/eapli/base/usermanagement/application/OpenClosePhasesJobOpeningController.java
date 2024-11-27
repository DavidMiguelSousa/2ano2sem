package eapli.base.usermanagement.application;

import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.domain.jobopening.Phase;
import eapli.base.clientusermanagement.domain.jobopening.PhaseDetails;
import eapli.base.clientusermanagement.domain.jobopening.Status;
import eapli.base.clientusermanagement.repositories.JobOpeningRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.services.JobOpeningManagementService;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.List;
import java.util.Map;

public class OpenClosePhasesJobOpeningController {
    private final JobOpeningRepository repo = PersistenceContext.repositories().jobOpenings();

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final JobOpeningManagementService jobOpeningSvc = new JobOpeningManagementService(repo);

    public Iterable<JobOpening> allJobOpening() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.CUSTOMER_MANAGER, BaseRoles.ADMIN);

        return jobOpeningSvc.findAllAvailable();
    }

    public List<JobOpening> obtainJobOpeningsAvailable() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.CUSTOMER_MANAGER);

        return jobOpeningSvc.findAllAvailable();
    }

    public boolean openPhase(JobOpening jobOpening){
        return jobOpeningSvc.openPhase(jobOpening);
    }

    public boolean closePhase(JobOpening jobOpening){
        return jobOpeningSvc.closePhase(jobOpening);
    }

    public boolean goBackToPreviousPhase(JobOpening jobOpening){
        return jobOpeningSvc.goBackToPreviousPhase(jobOpening);
    }

    public List<JobOpening> filterJobOpenings(List<JobOpening> jobOpenings){
        return jobOpeningSvc.filterJobOpenings(jobOpenings);
    }

    public boolean areAllPhasesComplete(Map<Phase, PhaseDetails> phases) {
        return jobOpeningSvc.areAllPhasesComplete(phases);
    }

    public void updateStatus(JobOpening jobOpeningSelected, Status status) {
        jobOpeningSvc.updateStatus(jobOpeningSelected, status);
    }

    public boolean managePhases(JobOpening jobOpeningSelected) {
        return jobOpeningSvc.managePhases(jobOpeningSelected);
    }

    public void saveToRepository(JobOpening jobOpeningSelected) {
        jobOpeningSvc.saveToRepository(jobOpeningSelected);
    }

    public void sendEmail(Phase phase, JobOpening jobOpening, Status status) {
        jobOpeningSvc.sendEmail(phase, jobOpening, status);
    }
}
