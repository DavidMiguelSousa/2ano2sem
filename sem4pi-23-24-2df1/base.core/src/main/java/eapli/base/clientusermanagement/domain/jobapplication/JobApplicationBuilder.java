package eapli.base.clientusermanagement.domain.jobapplication;

import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.clientusermanagement.domain.ApprovalStatus;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.domain.jobopening.JobReference;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JobApplicationBuilder implements DomainFactory<JobApplication> {

    private JobApplicationReference jobApplicationReference;
    private JobOpening jobOpening;
    private LocalDate dateOfSubmission;
    private ApprovalStatus jobApplicationStatus;
    private Candidate candidate;
    private CurriculumVitae curriculumVitae;
    private PhoneNumber phoneNumber;
    private JobApplicationAdditionalData additionalData;

    public JobApplicationBuilder with(JobApplicationReference jobApplicationReference, JobOpening jobOpening, Candidate candidate) {
        this.jobApplicationReference = jobApplicationReference;
        this.jobOpening = jobOpening;
        this.candidate = candidate;
        this.jobApplicationStatus = ApprovalStatus.PENDING;

        return this;
    }

    public JobApplicationBuilder withJobApplicationReference(JobApplicationReference jobApplicationReference) {
        this.jobApplicationReference = jobApplicationReference;
        return this;
    }

    public JobApplicationBuilder withJobApplicationReference(JobReference jobOpeningReference) {
        this.jobApplicationReference = JobApplicationReference.valueOf(jobOpeningReference.reference() + "-" + candidate.identity());
        return this;
    }

    public JobApplicationBuilder withJobOpening(JobOpening jobOpening) {
        this.jobOpening = jobOpening;
        return this;
    }

    public JobApplicationBuilder withCandidate(Candidate candidate) {
        this.candidate = candidate;
        return this;
    }

    public JobApplicationBuilder withCurriculumVitae(CurriculumVitae curriculumVitae) {
        this.curriculumVitae = curriculumVitae;
        return this;
    }

    public JobApplicationBuilder withCurriculumVitae(File file) {
        this.curriculumVitae = new CurriculumVitae(file);
        return this;
    }

    public JobApplicationBuilder withCurriculumVitae(String fileName) {
        this.curriculumVitae = new CurriculumVitae(fileName);
        return this;
    }

    public JobApplicationBuilder withPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public JobApplicationBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = new PhoneNumber(phoneNumber);
        return this;
    }

    public JobApplicationBuilder withAdditionalData(JobApplicationAdditionalData additionalData) {
        this.additionalData = additionalData;
        return this;
    }

    public JobApplicationBuilder withAdditionalData(List<File> files) {
        List<String> fileNames = new ArrayList<>();
        files.forEach(file -> fileNames.add(file.getName()));
        this.additionalData = new JobApplicationAdditionalData(fileNames);
        return this;
    }

    public JobApplicationBuilder withJobApplicationStatus(ApprovalStatus jobApplicationStatus) {
        this.jobApplicationStatus = jobApplicationStatus;
        return this;
    }

    @Override
    public JobApplication build() {
        if (dateOfSubmission == null) {
            dateOfSubmission = LocalDate.now();
        }
        return new JobApplication(jobApplicationReference, jobOpening, candidate, curriculumVitae, phoneNumber, additionalData, dateOfSubmission, jobApplicationStatus);
    }

}
