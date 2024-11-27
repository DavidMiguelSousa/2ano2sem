package eapli.base.infrastructure.bootstrapers;

import eapli.base.clientusermanagement.domain.Customer;
import eapli.base.clientusermanagement.domain.address.*;
import eapli.base.clientusermanagement.domain.jobopening.*;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.integration.interview_model.export.application.ExportInterviewModelsController;
import eapli.base.interviewmanagement.domain.InterviewModel;
import eapli.base.interviewmanagement.domain.NumberOfVacancies;
import eapli.base.usermanagement.application.AddJobOpeningController;
import eapli.base.usermanagement.application.SelectJobRequirementsController;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.time.domain.model.DateInterval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JobOpeningBootstrapperBase  implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobOpeningBootstrapperBase.class);

    final AddJobOpeningController addJobOpeningController = new AddJobOpeningController();
    final ExportInterviewModelsController exportInterviewModelsController = new ExportInterviewModelsController();
    final SelectJobRequirementsController selectJobRequirementsController = new SelectJobRequirementsController();

    @Override
    public boolean execute() {
        final Customer customer = PersistenceContext.repositories().customers().findAllActive().iterator().next();
        final Address address = new Address(District.VILA_REAL, County.valueOf("Chaves"), Parish.valueOf("Madalena"), Street.valueOf("Caminho do Prado"),DoorNumber.valueOf(12), PostalCode.valueOf("5400-466"));
        final Map<Phase, PhaseDetails> mapPhase = new HashMap<>();
        final InterviewModel interviewModel = exportInterviewModelsController.randomInterviewModel().orElse(null);
        final JobRequirements jobRequirements = selectJobRequirementsController.randomJobRequirements().orElse(null);

        Calendar startDate = Calendar.getInstance();
        startDate.set(2023, Calendar.FEBRUARY, 25);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2023, Calendar.FEBRUARY, 25);

        mapPhase.put(Phase.APPLICATION, new PhaseDetails(new DateInterval(startDate,endDate), Status.PENDING));
        mapPhase.put(Phase.SCREENING, new PhaseDetails(new DateInterval(startDate,endDate), Status.PENDING));
        mapPhase.put(Phase.INTERVIEW, new PhaseDetails(new DateInterval(startDate,endDate), Status.PENDING));
        mapPhase.put(Phase.ANALYSIS, new PhaseDetails(new DateInterval(startDate,endDate), Status.PENDING));
        mapPhase.put(Phase.RESULT, new PhaseDetails(new DateInterval(startDate,endDate), Status.PENDING));

        // job opening sem fases ativas
        JobOpening jobOpening1 = registerJobOpening(new JobReference("ISEP-001"), Designation.valueOf("Job Title 1"), ContractType.FULL_TIME, JobMode.HYBRID,
                address, customer, NumberOfVacancies.valueOf(1), Description.valueOf("Job without phases in progress."), mapPhase);

        // job opening com fase APPLICATION em progresso
        mapPhase.get(Phase.APPLICATION).updateStatus(Status.IN_PROGRESS);

        JobOpening jobOpening2 = registerJobOpening(new JobReference("ISEP-002"), Designation.valueOf("Job Title 2"), ContractType.FULL_TIME, JobMode.HYBRID,
                address, customer, NumberOfVacancies.valueOf(1), Description.valueOf("Job with phase application in progress."), mapPhase);

        // job opening com fase SCREENING em progresso
        mapPhase.get(Phase.APPLICATION).updateStatus(Status.COMPLETED);
        mapPhase.get(Phase.SCREENING).updateStatus(Status.IN_PROGRESS);

        JobOpening jobOpening3 = registerJobOpening(new JobReference("ISEP-003"), Designation.valueOf("Job Title 3"), ContractType.FULL_TIME, JobMode.HYBRID,
                address, customer, NumberOfVacancies.valueOf(1), Description.valueOf("Job with phase screening in progress."), mapPhase);

        // job opening com fase INTERVIEW em progresso
        mapPhase.get(Phase.SCREENING).updateStatus(Status.COMPLETED);
        mapPhase.get(Phase.INTERVIEW).updateStatus(Status.IN_PROGRESS);

        JobOpening jobOpening4 = registerJobOpening(new JobReference("ISEP-004"), Designation.valueOf("Job Title 4"), ContractType.FULL_TIME, JobMode.HYBRID,
                address, customer, NumberOfVacancies.valueOf(1), Description.valueOf("Job with phase interview in progress."), mapPhase);

        // job opening com fase ANALYSIS em progresso
        mapPhase.get(Phase.INTERVIEW).updateStatus(Status.COMPLETED);
        mapPhase.get(Phase.ANALYSIS).updateStatus(Status.IN_PROGRESS);

        JobOpening jobOpening5 = registerJobOpening(new JobReference("ISEP-005"), Designation.valueOf("Job Title 5"), ContractType.FULL_TIME, JobMode.HYBRID,
                address, customer, NumberOfVacancies.valueOf(1), Description.valueOf("Job with phase analysis in progress."), mapPhase);

        // job opening com fase RESULT em progresso
        mapPhase.get(Phase.ANALYSIS).updateStatus(Status.COMPLETED);
        mapPhase.get(Phase.RESULT).updateStatus(Status.IN_PROGRESS);

        JobOpening jobOpening6 = registerJobOpening(new JobReference("ISEP-006"), Designation.valueOf("Job Title 6"), ContractType.FULL_TIME, JobMode.HYBRID,
                address, customer, NumberOfVacancies.valueOf(1), Description.valueOf("Job with phase result in progress."), mapPhase);

        // job opening com todas as fases completas
        mapPhase.get(Phase.RESULT).updateStatus(Status.COMPLETED);

        JobOpening jobOpening7 = registerJobOpening(new JobReference("ISEP-007"), Designation.valueOf("Job Title 7"), ContractType.FULL_TIME, JobMode.HYBRID,
                address, customer, NumberOfVacancies.valueOf(1), Description.valueOf("Job with all phases completed."), mapPhase);

        assignInterviewModel(jobOpening1, interviewModel);
        assignInterviewModel(jobOpening2, interviewModel);
        assignInterviewModel(jobOpening3, interviewModel);
        assignInterviewModel(jobOpening4, interviewModel);
        assignInterviewModel(jobOpening5, interviewModel);
        assignInterviewModel(jobOpening6, interviewModel);
        assignInterviewModel(jobOpening7, interviewModel);

        assignJobRequirements(jobOpening1, jobRequirements);
        assignJobRequirements(jobOpening2, jobRequirements);
        assignJobRequirements(jobOpening3, jobRequirements);
        assignJobRequirements(jobOpening4, jobRequirements);
        assignJobRequirements(jobOpening5, jobRequirements);
        assignJobRequirements(jobOpening6, jobRequirements);
        assignJobRequirements(jobOpening7, jobRequirements);

        return true;
    }

    protected JobOpening registerJobOpening(final JobReference jobReference,final Designation jobTitle,final ContractType contractType,final JobMode jobMode,
                                           final Address address,final Customer customer,final NumberOfVacancies numberOfVacancies,final Description jobDescription, final Map<Phase, PhaseDetails> mapPhase) {
        JobOpening jobOpening = null;
        try {
            jobOpening = addJobOpeningController.addJobOpeningWithPhase(jobReference, jobTitle, contractType, jobMode, address, customer, numberOfVacancies, jobDescription, mapPhase);
            LOGGER.debug("»»» %s", jobReference);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            // assuming it is just a primary key violation due to the tentative
            // of inserting a duplicated user. let's just lookup that user
            Optional<JobOpening> optionalJobOpening = addJobOpeningController.findByJobReference(jobReference);
            if (optionalJobOpening.isPresent()) jobOpening = optionalJobOpening.get();
        }
        return jobOpening;
    }

    protected void assignInterviewModel(JobOpening jobOpening, InterviewModel interviewModel) {
        addJobOpeningController.assignInterviewModel(jobOpening, interviewModel);
        addJobOpeningController.bootstrapSave(jobOpening);
    }

    protected void assignJobRequirements(JobOpening jobOpening, JobRequirements jobRequirements) {
        addJobOpeningController.assignJobRequirements(jobOpening, jobRequirements);
        addJobOpeningController.bootstrapSave(jobOpening);
    }

}
