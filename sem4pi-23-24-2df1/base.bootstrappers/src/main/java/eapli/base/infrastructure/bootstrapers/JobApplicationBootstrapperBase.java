package eapli.base.infrastructure.bootstrapers;

import eapli.base.candidatemanagement.application.ListCandidatesController;
import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.domain.jobopening.JobReference;
import eapli.base.usermanagement.application.ImportCandidateFilesController;
import eapli.base.usermanagement.application.ListJobOpeningController;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;

public class JobApplicationBootstrapperBase implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobApplicationBootstrapperBase.class);

    final ImportCandidateFilesController importCandidateFilesController = new ImportCandidateFilesController();

    final ListCandidatesController listCandidatesController = new ListCandidatesController();

    final ListJobOpeningController listJobOpeningController = new ListJobOpeningController();

    final

    @Override
    public boolean execute() {
        JobOpening jobOpening = listJobOpeningController.find(JobReference.valueOf("ISEP-002")).orElse(null);
        Candidate candidate = listCandidatesController.find("candidate@gmail.com").orElse(null);
        registerNewJobApplication(jobOpening, candidate);
        return true;
    }


    protected JobApplication registerNewJobApplication(final JobOpening jobOpening, final Candidate candidate) {
        if (jobOpening == null || candidate == null) {
            LOGGER.warn("»»» unable to register new job application");
            return null;
        }
        JobApplication jobApplication = null;
        try {
            jobApplication = importCandidateFilesController.createNewApplication(Path.of("SCOMP/files/fileBotFiles"), candidate, jobOpening);
            LOGGER.debug("»»» %s", jobApplication.identity());
        } catch (final IntegrityViolationException | ConcurrencyException | IOException e) {
            // assuming it is just a primary key violation due to the tentative
            // of inserting a duplicated user. let's just lookup that user
            Iterable<JobApplication> optionalJobApplication = importCandidateFilesController.applicationsByJobReference(jobOpening);
            if (optionalJobApplication.iterator().hasNext()) jobApplication = optionalJobApplication.iterator().next();
        }
        return jobApplication;
    }
}
