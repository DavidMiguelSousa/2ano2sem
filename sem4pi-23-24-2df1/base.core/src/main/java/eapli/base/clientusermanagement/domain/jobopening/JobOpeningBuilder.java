package eapli.base.clientusermanagement.domain.jobopening;

import eapli.base.clientusermanagement.domain.ApprovalStatus;
import eapli.base.clientusermanagement.domain.Customer;
import eapli.base.clientusermanagement.domain.address.Address;
import eapli.base.interviewmanagement.domain.InterviewModel;
import eapli.base.interviewmanagement.domain.NumberOfVacancies;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.time.domain.model.DateInterval;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class JobOpeningBuilder implements DomainFactory<JobOpening> {
    private JobReference jobReference;
    private Designation jobTitle;
    private ContractType contractType;
    private JobMode jobMode;
    private Address address;
    private Customer customer;
    private NumberOfVacancies numberOfVacancies;
    private Description jobDescription;
    private Map<Phase, PhaseDetails> phases;
    private InterviewModel interviewModel;
    private JobRequirements jobRequirements;
    private Status status;

    public JobOpeningBuilder withJobReference(JobReference jobReference) {
        this.jobReference = jobReference;
        return this;
    }

    public JobOpeningBuilder withJobReference(String jobReference) {
        this.jobReference = new JobReference(jobReference);
        return this;
    }

    public JobOpeningBuilder withJobTitle(Designation jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    public JobOpeningBuilder withContractType(ContractType contractType) {
        this.contractType = contractType;
        return this;
    }

    public JobOpeningBuilder withMode(JobMode jobMode) {
        this.jobMode = jobMode;
        return this;
    }

    public JobOpeningBuilder withAddress(Address address) {
        this.address = address;
        return this;
    }

    public JobOpeningBuilder withCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public JobOpeningBuilder withNumberOfVacancies(NumberOfVacancies numberOfVacancies) {
        this.numberOfVacancies = numberOfVacancies;
        return this;
    }

    public JobOpeningBuilder withJobDescription(Description jobDescription) {
        this.jobDescription = jobDescription;
        return this;
    }

    public JobOpeningBuilder withPhases(Map<Phase, PhaseDetails> phases) {
        this.phases = phases;
        return this;
    }

    public JobOpeningBuilder withInterviewModel(InterviewModel interviewModel) {
        this.interviewModel = interviewModel;
        return this;
    }

    public JobOpeningBuilder withJobRequirements(JobRequirements jobRequirements) {
        this.jobRequirements = jobRequirements;
        return this;
    }

    public JobOpeningBuilder withStatus(Status status) {
        this.status = status;
        return this;
    }

    public JobOpeningBuilder withStatus(String status) {
        this.status = Status.valueOf(status);
        return this;
    }

    public JobOpeningBuilder with(JobReference jobReference, Designation jobTitle, ContractType contractType, JobMode jobMode, Address address, Customer customer, NumberOfVacancies numberOfVacancies, Description jobDescription, Map<Phase, PhaseDetails> phases, InterviewModel interviewModel, JobRequirements jobRequirements, Status status) {
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
        this.status = status;
        return this;
    }

//    public JobOpening build() {
//        if (phases == null || interviewModel == null || jobRequirements == null) {
//            return new JobOpening(jobReference, jobTitle, contractType, jobMode, address, customer, numberOfVacancies, jobDescription);
//        }
//        return new JobOpening(jobReference, jobTitle, contractType, jobMode, address, customer, numberOfVacancies, jobDescription, phases, interviewModel, jobRequirements);
//    }

    public JobOpening build() {
        if (phases == null && interviewModel == null && jobRequirements == null) {
            return new JobOpening(jobReference, jobTitle, contractType, jobMode, address, customer, numberOfVacancies, jobDescription);
        } else if (phases == null && interviewModel == null) {
            return new JobOpening(jobReference, jobTitle, contractType, jobMode, address, customer, numberOfVacancies, jobDescription, jobRequirements);
        } else if (phases == null && jobRequirements == null) {
            return new JobOpening(jobReference, jobTitle, contractType, jobMode, address, customer, numberOfVacancies, jobDescription, interviewModel);
        } else if (interviewModel == null && jobRequirements == null) {
            return new JobOpening(jobReference, jobTitle, contractType, jobMode, address, customer, numberOfVacancies, jobDescription, phases);
        } else if (phases == null) {
            return new JobOpening(jobReference, jobTitle, contractType, jobMode, address, customer, numberOfVacancies, jobDescription, interviewModel, jobRequirements);
        } else if (interviewModel == null) {
            return new JobOpening(jobReference, jobTitle, contractType, jobMode, address, customer, numberOfVacancies, jobDescription, phases, jobRequirements);
        } else if (jobRequirements == null) {
            return new JobOpening(jobReference, jobTitle, contractType, jobMode, address, customer, numberOfVacancies, jobDescription, phases, interviewModel);
        } else {
            return new JobOpening(jobReference, jobTitle, contractType, jobMode, address, customer, numberOfVacancies, jobDescription, phases, interviewModel, jobRequirements);
        }
    }

    public JobOpeningBuilder withJobOpeningWithoutPhases(JobOpening jobOpening) {
        JobOpeningBuilder builder = new JobOpeningBuilder();
        builder = builder.withJobReference(jobOpening.jobReference()).withAddress(jobOpening.address())
                .withContractType(jobOpening.contractType()).withCustomer(jobOpening.customer())
                .withJobDescription(jobOpening.jobDescription()).withJobTitle(jobOpening.jobTitle())
                .withMode(jobOpening.mode()).withNumberOfVacancies(jobOpening.numberOfVacancies())
                .withStatus(jobOpening.status());

        if (jobOpening.phases() != null) {
            builder.withPhases(jobOpening.phases());
        }

        return builder;
    }
}
