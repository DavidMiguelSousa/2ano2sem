package application;

import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.domain.jobopening.Phase;
import eapli.base.clientusermanagement.domain.jobopening.Status;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.services.JobOpeningManagementService;
import org.antlr.v4.runtime.misc.Pair;

public class ResultProcessNotificationController {
    private final JobOpeningManagementService jobOpeningManagementService;

    public ResultProcessNotificationController() {
        this.jobOpeningManagementService = new JobOpeningManagementService(PersistenceContext.repositories().jobOpenings());
    }

    public Pair<JobOpening, Status> updatedJobOpeningAndStatus() {
        return jobOpeningManagementService.updatedJobOpeningAndStatus();
    }

    public boolean sendEmail(Phase phase, JobOpening jobOpening, Status status) {
        return jobOpeningManagementService.sendEmail(phase, jobOpening, status);
    }
}
