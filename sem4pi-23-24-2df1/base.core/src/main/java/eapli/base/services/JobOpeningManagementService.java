package eapli.base.services;

import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.clientusermanagement.domain.Customer;
import eapli.base.clientusermanagement.domain.address.Address;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.rank.JobApplicationsRankComparator;
import eapli.base.clientusermanagement.domain.jobopening.*;
import eapli.base.clientusermanagement.domain.rank.Rank;
import eapli.base.clientusermanagement.repositories.JobOpeningRepository;
import eapli.base.interviewmanagement.domain.InterviewModel;
import eapli.base.interviewmanagement.domain.NumberOfVacancies;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import jakarta.transaction.Transactional;
import org.antlr.v4.runtime.misc.Pair;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;

import java.util.*;

public class JobOpeningManagementService {

    private static JobOpening updatedJobOpening;
    private static Status updatedStatus;
    private final JobOpeningRepository jobOpeningRepo;
    private final JobApplicationsRankComparator comparator = new JobApplicationsRankComparator();

    public JobOpeningManagementService(JobOpeningRepository jobOpeningRepo) {
        this.jobOpeningRepo = jobOpeningRepo;
    }

    public Pair<JobOpening, Status> updatedJobOpeningAndStatus() {
        return new Pair<>(updatedJobOpening, updatedStatus);
    }


    public JobOpening registerNewJobOpening(JobReference jobReference, Designation jobTitle, ContractType contractType, JobMode jobMode,
                                            Address address, Customer customer, NumberOfVacancies numberOfVacancies, Description jobDescription) {
        final var jobOpeningBuilder = new JobOpeningBuilder();
        jobOpeningBuilder.withJobReference(jobReference).withJobTitle(jobTitle).withContractType(contractType).withMode(jobMode)
                .withAddress(address).withCustomer(customer).withNumberOfVacancies(numberOfVacancies).withJobDescription(jobDescription);
        final var newJobOpening = jobOpeningBuilder.build();
        return jobOpeningRepo.save(newJobOpening);
    }

    public JobOpening registerNewJobOpening(JobReference jobReference, Designation jobTitle, ContractType contractType, JobMode jobMode,
                                            Address address, Customer customer, NumberOfVacancies numberOfVacancies, Description jobDescription, Map<Phase, PhaseDetails> phases) {
        final var jobOpeningBuilder = new JobOpeningBuilder();
        jobOpeningBuilder.withJobReference(jobReference).withJobTitle(jobTitle).withContractType(contractType).withMode(jobMode)
                .withAddress(address).withCustomer(customer).withNumberOfVacancies(numberOfVacancies).withJobDescription(jobDescription).withPhases(phases);
        final var newJobOpening = jobOpeningBuilder.build();
        return jobOpeningRepo.save(newJobOpening);
    }

    public List<JobOpening> findAllAvailable() {
        List<JobOpening> jobOpenings = new ArrayList<>();
        jobOpeningRepo.findAll().forEach(jobOpenings::add);
        jobOpenings.sort(new JobOpeningComparator());
        return jobOpenings;
    }

    public int findLastRecord() {
        return jobOpeningRepo.findLastRecord();
    }

    @Transactional
    public JobOpening saveToRepository(JobOpening jobOpening) {
        return jobOpeningRepo.save(jobOpening);
    }

    public JobOpening bootstrapSave(JobOpening jobOpening) {
        if (jobOpeningRepo.containsOfIdentity(jobOpening.identity())) {
            jobOpeningRepo.removeOfIdentity(jobOpening.identity());
        }
        return jobOpeningRepo.save(jobOpening);
    }

    public Optional<JobOpening> findJobOpeningById(JobReference id) {
        return jobOpeningRepo.findByJobReference(id);
    }

    public void setupPhases(JobOpening jobOpening, Map<Phase, PhaseDetails> phases) {
        jobOpening.setupPhases(phases);
        jobOpeningRepo.save(jobOpening);
    }

    public void assignInterviewModel(JobOpening jobOpening, InterviewModel interviewModel) {
//        JobOpeningBuilder builder = new JobOpeningBuilder().withJobOpeningWithoutPhases(jobOpening).withInterviewModel(interviewModel);
//        if (!jobOpening.phases().isEmpty()) builder.withPhases(jobOpening.phases());
//        if (jobOpening.jobRequirements() == null) builder.withJobRequirements(jobOpening.jobRequirements());
//        jobOpeningRepo.save(builder.build());

        jobOpening.assignInterviewModel(interviewModel);
    }

    public void assignJobRequirements(JobOpening jobOpening, JobRequirements jobRequirements) {
//        JobOpeningBuilder builder = new JobOpeningBuilder().withJobOpeningWithoutPhases(jobOpening).withJobRequirements(jobRequirements);
//        if (!jobOpening.phases().isEmpty()) builder.withPhases(jobOpening.phases());
//        if (jobOpening.interviewModel() == null) builder.withInterviewModel(jobOpening.interviewModel());
//        jobOpeningRepo.save(builder.build());

        jobOpening.assignJobRequirements(jobRequirements);
    }

    @Transactional
    public void updatePhases(JobOpening jobOpening, Phase phase, Status status) {
        jobOpening.updatePhase(phase, status);
//        jobOpeningRepo.save(jobOpening);
    }

    public Iterable<JobOpening> jobOpeningsWithPhaseOn(Phase phase) {
        Iterable<JobOpening> allJobOpenings = jobOpeningRepo.findAll();
        List<JobOpening> jobOpenings = new ArrayList<>();

        for (JobOpening jobOpening : allJobOpenings) {
            if (jobOpening.phases().isEmpty()) continue;
            if (jobOpening.phases().get(phase).status().equals(Status.IN_PROGRESS)) {
                jobOpenings.add(jobOpening);
            }
        }

        return jobOpenings;
    }

    public void updateJobOpeningRanksList(JobApplication jobApplication, JobOpening jobOpening) {
        List<JobApplication> list = jobOpening.applicationsRanked();
        list.add(jobApplication);

        list.sort(comparator);

        jobOpening.updateRanks(list);

        jobOpeningRepo.save(jobOpening);
    }

    public Iterable<JobOpening> pendingJobOpenings() {
        List<JobOpening> jobOpenings = findAllAvailable();
        List<JobOpening> pendingJobOpenings = new ArrayList<>();

        for (JobOpening jobOpening : jobOpenings) {
            if (jobOpening.phases().isEmpty()) continue;
            if (jobOpening.phases().get(Phase.APPLICATION).status().equals(Status.PENDING)) {
                pendingJobOpenings.add(jobOpening);
            }
        }

        return pendingJobOpenings;
    }

    public List<JobOpening> jobOpeningsWithPhaseInterviewCompleted() {
        Iterable<JobOpening> allJobOpenings = jobOpeningRepo.findAll();
        List<JobOpening> jobOpenings = new ArrayList<>();

        for (JobOpening jobOpening : allJobOpenings) {
            if (jobOpening.phases().isEmpty()) continue;
            if (jobOpening.phases().get(Phase.INTERVIEW).status().equals(Status.COMPLETED)) {
                jobOpenings.add(jobOpening);
            }
        }

        return jobOpenings;

    }

    private Phase findCurrentPhase(Map<Phase, PhaseDetails> phasesMap) {
        for (Phase phase : Phase.values()){
            if(phasesMap.containsKey(phase) && phasesMap.get(phase).status() == Status.IN_PROGRESS) {
                return phase;
            }
        }

        return null;
    }

    private Phase findNextPhase(Phase currentPhase, Map<Phase, PhaseDetails> phasesMap) {
        boolean foundCurrentPhase = false;

        for (Phase phase : Phase.values()){
            if(foundCurrentPhase && phasesMap.containsKey(phase)) {
                return phase;
            }
            if(phase == currentPhase){
                foundCurrentPhase = true;
            }
        }
        return null;
    }

    public boolean areAllPhasesComplete(Map<Phase, PhaseDetails> phasesMap) {
        return phasesMap.values().stream().allMatch(phaseDetails -> phaseDetails.status() == Status.COMPLETED);
    }

    public boolean goBackToPreviousPhase(JobOpening jobOpening) {
        Map<Phase, PhaseDetails> phasesMap = jobOpening.phases();
        Phase currentPhase = findCurrentPhase(phasesMap);
        Phase previousPhase = null;

        if (currentPhase != null) {
            previousPhase = findPreviousPhase(currentPhase, phasesMap);
        }

        if (previousPhase != null) {
            if (phasesMap.get(currentPhase).status() == Status.IN_PROGRESS) {
                updatePhases(jobOpening, currentPhase, Status.PENDING);
            }
            updatePhases(jobOpening, previousPhase, Status.IN_PROGRESS);
            return true;
        } else {
            return false;
        }
    }

    private Phase findPreviousPhase(Phase currentPhase, Map<Phase, PhaseDetails> phasesMap) {
        Phase previousPhase = null;
        for (Phase phase : Phase.values()) {
            if (phase == currentPhase) {
                break;
            }
            if (phasesMap.containsKey(phase)) {
                previousPhase = phase;
            }
        }
        return previousPhase;
    }

    @Transactional
    public boolean managePhases(JobOpening jobOpening) {
        if (closePhase(jobOpening)) {
            return openPhase(jobOpening);
        }
        return false;
    }

    public boolean openPhase(JobOpening jobOpening) {
        try {
            Map<Phase, PhaseDetails> phasesMap = jobOpening.phases();

            if (phasesMap.values().stream().allMatch(details -> details.status() == Status.PENDING)) {
                updatePhases(jobOpening, Phase.APPLICATION, Status.IN_PROGRESS);
                return true;
            }

            boolean interviewExists = phasesMap.containsKey(Phase.INTERVIEW) && phasesMap.get(Phase.INTERVIEW).status() != Status.PENDING;

            if (!interviewExists) {
                for (Phase phase : Phase.values()) {
                    PhaseDetails details = phasesMap.get(phase);
                    if (details != null && details.status() == Status.PENDING) {
                        updatePhases(jobOpening, phase, Status.IN_PROGRESS);
                        return true;
                    }
                }
            }

            Phase previousPhase = null;
            for (Phase phase : Phase.values()) {
                PhaseDetails phaseDetails = phasesMap.get(phase);
                if (phaseDetails != null && phaseDetails.status() == Status.PENDING) {
                    if (previousPhase != null && phasesMap.get(previousPhase).status() == Status.COMPLETED) {
                        updatePhases(jobOpening, phase, Status.IN_PROGRESS);
                        return true;
                    }
                    break;
                }
                previousPhase = phase;
            }

            if (areAllPhasesComplete(phasesMap)) {
                updatePhases(jobOpening, Phase.RESULT, Status.COMPLETED);
                return true;
            }

            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean closePhase(JobOpening jobOpening){
        try {
            Map<Phase, PhaseDetails> phasesMap = jobOpening.phases();
            Phase currentPhase = findCurrentPhase(phasesMap);

            if (currentPhase != null) {
                if (currentPhase == Phase.RESULT) {
                    updatePhases(jobOpening, currentPhase, Status.COMPLETED);
                    return true;
                } else if (currentPhase != Phase.RESULT) {
                    Phase previousPhase = findPreviousPhase(currentPhase, phasesMap);

                    if (previousPhase == null || phasesMap.get(previousPhase).status() == Status.COMPLETED) {
                        updatePhases(jobOpening, currentPhase, Status.COMPLETED);
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            // Handle exception, e.g., log or retry
            return false;
        }
    }

    public List<JobOpening> filterJobOpenings(List<JobOpening> jobOpenings) {
        List<JobOpening> filtered = new ArrayList<>();

        for (JobOpening jobOpening : jobOpenings) {
            if (!jobOpening.phases().isEmpty()) {
                filtered.add(jobOpening);
            }
        }

        return filtered;
    }

    public void updateStatus(JobOpening jobOpening, Status newStatus) {
        jobOpening.updateStatus(newStatus);
        jobOpeningRepo.save(jobOpening);
        updatedJobOpening = jobOpening;
        updatedStatus = newStatus;
    }

    private boolean sendNotification(String destination, String subject, String message){

        try {
            Email email = new SimpleEmail();
            email.setHostName("frodo.dei.isep.ipp.pt");
            email.setSmtpPort(25);
            email.setSSLOnConnect(false);
            email.setFrom("customermanager@jobs4u.com", "Customer Manager");
            email.setSubject(subject);
            email.setMsg(message);
            email.addTo(destination);
            email.send();
            System.out.printf("Email sent to %s with subject %s and message %s\n", destination, subject, message);
            return true;
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean sendEmail(Phase phase, JobOpening jobOpening, Status status) {
        System.out.println("Sending email...");
        String email = jobOpening.customer().user().email().toString();
        String subject = "Jobs4U - The Job Opening " + jobOpening.jobTitle().toString() + " has a new phase";
        String message = "The phase " + phase.toString() + " of job opening " +
                jobOpening.jobReference().toString() + " has been altered to " + status.status() + "!";
        return sendNotification(email, subject, message);
    }

    public boolean sendEmailCandidateAccepted(Rank rank, JobApplication jobApplication, JobOpening jobOpening){
        String emailCandidate = jobApplication.candidate().email().toString();
        String subjectCandidate = "Congratulations! - Results of candidates for job opening";
        String messageCandidate = "Great news! You've been selected for the " + jobOpening.jobTitle().toString() +
                " position in " + jobOpening.customer().customerCode().toString() + "!\n" +
                "Your rank is: " + rank.rank() + ".";
        return sendNotification(emailCandidate, subjectCandidate, messageCandidate);
    }

    public boolean sendEmailCandidateNotAccepted(JobApplication jobApplication, JobOpening jobOpening){
        String emailCandidate = jobApplication.candidate().email().toString();
        String subjectCandidate = "Results of candidates for job opening";
        String messageCandidate = "Bad news! Unfortunately, you have not been selected for the " + jobOpening.jobTitle() +
                " position in " + jobOpening.customer().customerCode().toString() + ".";
        return sendNotification(emailCandidate, subjectCandidate, messageCandidate);
    }

    public boolean sendEmailCustomer(Map<Rank, JobApplication> rank, JobOpening jobOpening){
        String emailCustomer = jobOpening.customer().user().email().toString();
        String subjectCustomer = "Candidate List for Job Opening";

        StringBuilder candidateListBuilder = new StringBuilder();
        for (Map.Entry<Rank, JobApplication> entry : rank.entrySet()) {
            JobApplication jobApplication = entry.getValue();
            candidateListBuilder.append(jobApplication.candidate().name()).append(" - ").append(jobApplication.candidate().email().toString());
            if (jobApplication.phoneNumber() != null) {
                candidateListBuilder.append(", ").append(jobApplication.phoneNumber().toString());
            }
            candidateListBuilder.append("\n");
        }

        String messageCustomer = "Just wanted to let you know that we've wrapped up the selection process for job opening " +
                jobOpening.jobReference().toString() + ".\nHere’s the list of candidates:\n" +
                candidateListBuilder.toString();

        return sendNotification(emailCustomer, subjectCustomer, messageCustomer);
    }


}
