package eapli.base.usermanagement.application;

import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.domain.jobopening.JobReference;
import eapli.base.clientusermanagement.domain.jobopening.Phase;
import eapli.base.clientusermanagement.repositories.JobApplicationRepository;
import eapli.base.clientusermanagement.repositories.JobOpeningRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.services.CandidateManagementService;
import eapli.base.services.FileManagementService;
import eapli.base.services.JobApplicationManagementService;
import eapli.base.services.JobOpeningManagementService;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ImportCandidateFilesController {
    private final JobOpeningRepository jobOpeningRepo = PersistenceContext.repositories().jobOpenings();
    private final JobApplicationRepository jobApplicationRepo = PersistenceContext.repositories().jobApplications();

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CandidateManagementService candidateSvc = new CandidateManagementService(PersistenceContext.repositories().candidates());
    private final JobOpeningManagementService jobOpeningSvc = new JobOpeningManagementService(jobOpeningRepo);
    private final JobApplicationManagementService jobApplicationSvc = new JobApplicationManagementService(jobApplicationRepo);
    private final FileManagementService fileSvc = new FileManagementService();

    public boolean existsCandidateFiles(Path sharedFolder, Username candidateEmail, JobReference jobReference) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER, BaseRoles.OPERATOR);
        return fileSvc.existsCandidateFiles(sharedFolder, jobReference, candidateEmail);
    }

    public JobApplication createNewApplication(Path sharedFolder, Candidate candidate, JobOpening jobOpening) throws IOException {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER, BaseRoles.OPERATOR);
        return fileSvc.registerApplication(sharedFolder, candidate, jobOpening);
    }

    public Iterable<Candidate> allCandidates() {
        return candidateSvc.allCandidates();
    }

    public Iterable<JobOpening> jobOpeningsInApplicationPhase() {
        return jobOpeningSvc.jobOpeningsWithPhaseOn(Phase.APPLICATION);
    }

    public Iterable<JobApplication> applicationsByJobReference(JobOpening jobOpening) {
        return jobApplicationSvc.jobApplicationsOf(jobOpening);
    }

    public Candidate createCandidate(Path sharedFolder, Username candidateEmail, JobReference jobReference) {
        SystemUser systemUser = fileSvc.candidateData(sharedFolder, candidateEmail, jobReference);
        return candidateSvc.registerCandidate(systemUser);
    }

    public File[] allApplicationFiles(Path sharedFolder, JobApplication jobApplication) {
        return fileSvc.allApplicationFiles(sharedFolder, jobApplication.candidate(), jobApplication.jobOpening());
    }
}
