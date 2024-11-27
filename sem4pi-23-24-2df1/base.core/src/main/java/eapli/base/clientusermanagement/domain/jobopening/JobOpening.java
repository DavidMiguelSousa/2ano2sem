package eapli.base.clientusermanagement.domain.jobopening;

import eapli.base.clientusermanagement.domain.Customer;
import eapli.base.clientusermanagement.domain.address.Address;
import eapli.base.interviewmanagement.domain.InterviewModel;
import eapli.base.interviewmanagement.domain.NumberOfVacancies;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.time.domain.model.DateInterval;
import jakarta.persistence.*;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;

import java.io.Serial;
import java.util.*;

@Entity
public class JobOpening implements AggregateRoot<JobReference> {
    @Serial
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @EmbeddedId
    private JobReference jobReference;

    private Designation jobTitle;

    private Description jobDescription;

    private Address address;

    @ManyToOne
    private Customer customer;

    //    @Column(insertable=false, updatable=false)
    private NumberOfVacancies numberOfVacancies;

    @Enumerated(EnumType.STRING)
    private ContractType contractType;

    @Enumerated(EnumType.STRING)
    private JobMode jobMode;

    @ElementCollection(fetch = FetchType.LAZY)
    @MapKeyEnumerated(EnumType.STRING)
    private Map<Phase, PhaseDetails> phases;

    private Status status;

    @ManyToOne
    private JobRequirements jobRequirements;

    @ManyToOne
    private InterviewModel interviewModel;

    //@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "jobOpening", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobApplication> applicationsRanked;

    JobOpening(JobReference jobReference, Designation jobTitle, ContractType contractType, JobMode jobMode,
               Address address, Customer customer, NumberOfVacancies numberOfVacancies, Description jobDescription) {
        this.jobReference = jobReference;
        this.jobTitle = jobTitle;
        this.contractType = contractType;
        this.jobMode = jobMode;
        this.address = address;
        this.customer = customer;
        this.numberOfVacancies = numberOfVacancies;
        this.jobDescription = jobDescription;
        this.phases = new HashMap<>();
        this.status = Status.PENDING;
        this.applicationsRanked = new ArrayList<>();
    }

    JobOpening(JobReference jobReference, Designation jobTitle, ContractType contractType, JobMode jobMode,
               Address address, Customer customer, NumberOfVacancies numberOfVacancies, Description jobDescription,
               Map<Phase, PhaseDetails> phases, InterviewModel interviewModel, JobRequirements jobRequirements) {
        this.jobReference = jobReference;
        this.jobTitle = jobTitle;
        this.contractType = contractType;
        this.jobMode = jobMode;
        this.address = address;
        this.customer = customer;
        this.numberOfVacancies = numberOfVacancies;
        this.jobDescription = jobDescription;
        this.phases = phases;
        this.interviewModel = interviewModel;
        this.jobRequirements = jobRequirements;
        this.status = Status.IN_PROGRESS;
        this.applicationsRanked = new ArrayList<>();
    }

    public JobOpening(JobReference jobReference, Designation jobTitle, ContractType contractType, JobMode jobMode,
                      JobRequirements jobRequirements, Address address, Customer customer, NumberOfVacancies numberOfVacancies,
                      Description jobDescription, Map<Phase, PhaseDetails> phases, boolean active, Status status,
                      InterviewModel interviewModel) {
        this.jobReference = jobReference;
        this.jobTitle = jobTitle;
        this.contractType = contractType;
        this.jobMode = jobMode;
        this.jobRequirements = jobRequirements;
        this.address = address;
        this.customer = customer;
        this.numberOfVacancies = numberOfVacancies;
        this.jobDescription = jobDescription;
        this.phases = phases;
        this.interviewModel = interviewModel;
        this.status = status;
        this.applicationsRanked = new ArrayList<>();
    }

    protected JobOpening() {
        //for ORM
    }

    public JobOpening(JobReference jobReference, Designation jobTitle, ContractType contractType, JobMode jobMode,
                      Address address, Customer customer, NumberOfVacancies numberOfVacancies, Description jobDescription,
                      JobRequirements jobRequirements) {
        this.jobReference = jobReference;
        this.jobTitle = jobTitle;
        this.contractType = contractType;
        this.jobMode = jobMode;
        this.address = address;
        this.customer = customer;
        this.numberOfVacancies = numberOfVacancies;
        this.jobDescription = jobDescription;
        this.jobRequirements = jobRequirements;
        this.phases = new HashMap<>();
        this.interviewModel = null;
        this.status = Status.PENDING;
        this.applicationsRanked = new ArrayList<>();
    }

    public JobOpening(JobReference jobReference, Designation jobTitle, ContractType contractType, JobMode jobMode,
                      Address address, Customer customer, NumberOfVacancies numberOfVacancies, Description jobDescription,
                      InterviewModel interviewModel) {
        this.jobReference = jobReference;
        this.jobTitle = jobTitle;
        this.contractType = contractType;
        this.jobMode = jobMode;
        this.address = address;
        this.customer = customer;
        this.numberOfVacancies = numberOfVacancies;
        this.jobDescription = jobDescription;
        this.jobRequirements = null;
        this.phases = new HashMap<>();
        this.interviewModel = interviewModel;
        this.status = Status.PENDING;
        this.applicationsRanked = new ArrayList<>();
    }

    public JobOpening(JobReference jobReference, Designation jobTitle, ContractType contractType, JobMode jobMode,
                      Address address, Customer customer, NumberOfVacancies numberOfVacancies, Description jobDescription,
                      Map<Phase, PhaseDetails> phases) {
        this.jobReference = jobReference;
        this.jobTitle = jobTitle;
        this.contractType = contractType;
        this.jobMode = jobMode;
        this.address = address;
        this.customer = customer;
        this.numberOfVacancies = numberOfVacancies;
        this.jobDescription = jobDescription;
        this.jobRequirements = null;
        this.phases = phases;
        this.interviewModel = null;
        this.status = Status.PENDING;
        this.applicationsRanked = new ArrayList<>();
    }

    public JobOpening(JobReference jobReference, Designation jobTitle, ContractType contractType, JobMode jobMode,
                      Address address, Customer customer, NumberOfVacancies numberOfVacancies, Description jobDescription,
                      InterviewModel interviewModel, JobRequirements jobRequirements) {
        this.jobReference = jobReference;
        this.jobTitle = jobTitle;
        this.contractType = contractType;
        this.jobMode = jobMode;
        this.address = address;
        this.customer = customer;
        this.numberOfVacancies = numberOfVacancies;
        this.jobDescription = jobDescription;
        this.jobRequirements = jobRequirements;
        this.phases = new HashMap<>();
        this.interviewModel = interviewModel;
        this.status = Status.PENDING;
        this.applicationsRanked = new ArrayList<>();
    }

    public JobOpening(JobReference jobReference, Designation jobTitle, ContractType contractType, JobMode jobMode,
                      Address address, Customer customer, NumberOfVacancies numberOfVacancies, Description jobDescription,
                      Map<Phase, PhaseDetails> phases, JobRequirements jobRequirements) {
        this.jobReference = jobReference;
        this.jobTitle = jobTitle;
        this.contractType = contractType;
        this.jobMode = jobMode;
        this.address = address;
        this.customer = customer;
        this.numberOfVacancies = numberOfVacancies;
        this.jobDescription = jobDescription;
        this.jobRequirements = jobRequirements;
        this.phases = phases;
        this.interviewModel = null;
        this.status = Status.PENDING;
        this.applicationsRanked = new ArrayList<>();
    }

    public JobOpening(JobReference jobReference, Designation jobTitle, ContractType contractType, JobMode jobMode,
                      Address address, Customer customer, NumberOfVacancies numberOfVacancies, Description jobDescription,
                      Map<Phase, PhaseDetails> phases, InterviewModel interviewModel) {
        this.jobReference = jobReference;
        this.jobTitle = jobTitle;
        this.contractType = contractType;
        this.jobMode = jobMode;
        this.address = address;
        this.customer = customer;
        this.numberOfVacancies = numberOfVacancies;
        this.jobDescription = jobDescription;
        this.jobRequirements = null;
        this.phases = phases;
        this.interviewModel = interviewModel;
        this.status = Status.PENDING;
        this.applicationsRanked = new ArrayList<>();
    }


    public JobReference jobReference() {
        return this.jobReference;
    }

    public Designation jobTitle() {
        return this.jobTitle;
    }

    public ContractType contractType() {
        return this.contractType;
    }

    public JobMode mode() {
        return this.jobMode;
    }

    public Address address() {
        return this.address;
    }

    public Customer customer() {
        return this.customer;
    }

    public NumberOfVacancies numberOfVacancies() {
        return this.numberOfVacancies;
    }

    public Description jobDescription() {
        return this.jobDescription;
    }

    public Map<Phase, PhaseDetails> phases() {
        return this.phases;
    }

    public InterviewModel interviewModel() {
        return this.interviewModel;
    }

    public JobRequirements jobRequirements() {
        return this.jobRequirements;
    }
    public Status status() { return status;}

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public JobReference identity() {
        return this.jobReference;
    }

    public void setupPhases(Map<Phase, PhaseDetails> phases) {
        this.phases = phases;
    }

    public void assignInterviewModel(InterviewModel interviewModel) {
        this.interviewModel = interviewModel;
    }

    public void assignJobRequirements(JobRequirements jobRequirements) {
        this.jobRequirements = jobRequirements;
    }

    public void updateJobTitle(Designation jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void updateContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public void updateJobMode(JobMode jobMode) {
        this.jobMode = jobMode;
    }

    public void updateJobDescription(Description jobDescription) {
        this.jobDescription = jobDescription;
    }

    public void updateAddress(Address address) {
        this.address = address;
    }

    public void updateNumberOfVacancies(NumberOfVacancies numberOfVacancies) {
        this.numberOfVacancies = numberOfVacancies;
    }

    public void updateRanks(List<JobApplication> applicationsRanked) {
        this.applicationsRanked = applicationsRanked;
    }

    public List<JobApplication> applicationsRanked() {
        return this.applicationsRanked;
    }

    public void updatePhase(Phase phase, Status status) {
        PhaseDetails phaseDetails = this.phases.get(phase);
        if (phaseDetails == null) {
            phaseDetails = new PhaseDetails(status);
            this.phases.put(phase, phaseDetails);
        } else {
            phaseDetails.updateStatus(status);
        }
    }

    public void updateStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }
}
