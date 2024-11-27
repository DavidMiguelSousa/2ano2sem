package eapli.base.clientusermanagement.domain.jobapplication;

import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.clientusermanagement.domain.ApprovalStatus;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.domain.rank.Rank;
import eapli.base.interviewmanagement.domain.interview.Interview;
import eapli.base.interviewmanagement.domain.interview.InterviewDate;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;

import java.io.File;
import java.io.Serial;
import java.time.LocalDate;
import java.util.Calendar;

@Entity
@Table(name = "JOBAPPLICATION")
public class JobApplication implements AggregateRoot<JobApplicationReference> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @EmbeddedId
    private JobApplicationReference jobApplicationReference;

    /**
     * cascade = CascadeType.NONE as the candidate is part of another aggregate
     */
    @OneToOne(optional = false)
    private Candidate candidate;

    @ManyToOne
    private JobOpening jobOpening;

    private LocalDate dateOfSubmission;

    private PhoneNumber phoneNumber;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus jobApplicationStatus;

    private CurriculumVitae curriculumVitae;

    private JobApplicationAdditionalData additionalData;

    @Column(length = 512)
    private Rank rank;

    private Interview interview;

    JobApplication(JobApplicationReference jobApplicationReference, JobOpening jobOpening, Candidate candidate, CurriculumVitae curriculumVitae, PhoneNumber phoneNumber, JobApplicationAdditionalData additionalData, LocalDate dateOfSubmission, ApprovalStatus jobApplicationStatus) {
        Preconditions.noneNull(jobApplicationReference, jobOpening, candidate, jobApplicationStatus);

        this.jobApplicationReference = jobApplicationReference;
        this.jobOpening = jobOpening;
        this.candidate = candidate;
        this.curriculumVitae = curriculumVitae;
        this.phoneNumber = phoneNumber;
        this.additionalData = additionalData;
        this.dateOfSubmission = dateOfSubmission;
        this.jobApplicationStatus = jobApplicationStatus;
        this.rank = new Rank();
        this.interview = new Interview();
    }

    protected JobApplication() {
        // for ORM
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public JobApplicationReference identity() {
        return this.jobApplicationReference;
    }

    public JobOpening jobOpening() {
        return this.jobOpening;
    }

    public void accept() {
        this.jobApplicationStatus = ApprovalStatus.ACCEPTED;
    }

    public void refuse() {
        this.jobApplicationStatus = ApprovalStatus.REFUSED;
    }

    public Candidate candidate() {
        return this.candidate;
    }

    public CurriculumVitae curriculumVitae() {
        return this.curriculumVitae;
    }

    public JobApplicationAdditionalData additionalData() {
        return this.additionalData;
    }

    public PhoneNumber phoneNumber() {
        return this.phoneNumber;
    }

    public LocalDate dateOfSubmission() {
        return this.dateOfSubmission;
    }

    public ApprovalStatus status() {
        return this.jobApplicationStatus;
    }

    public boolean isPending() {
        return this.jobApplicationStatus == ApprovalStatus.PENDING;
    }

    public boolean isAccepted() {
        return this.jobApplicationStatus == ApprovalStatus.ACCEPTED;
    }

    public boolean isRefused() {
        return this.jobApplicationStatus == ApprovalStatus.REFUSED;
    }

    public Rank rank() {
        return rank;
    }

    public void defineRank(Rank rank) {
        this.rank = rank;
    }

    public void recordInterviewTimestamp(Calendar timestamp) {
        InterviewDate interviewDate = new InterviewDate(timestamp);
        this.interview.defineDate(interviewDate);
    }

    public void addCV(File file) {
        if (!file.exists()) return;
        if (this.curriculumVitae == null) this.curriculumVitae = new CurriculumVitae();
        this.curriculumVitae.updateCvFileName(file);
    }

    public void addPhoneNumber(String phoneNumber) {
        this.phoneNumber = new PhoneNumber(phoneNumber);
    }

    public void addAdditionalFile(File file) {
        if (!file.exists()) return;
        if (this.additionalData == null) this.additionalData = new JobApplicationAdditionalData();
        this.additionalData.addFile(file);
    }

    public void addInterview(Interview interview) {
        this.interview = interview;
    }

    public Interview interview() {
        if (this.interview == null) return new Interview();
        else return this.interview;
    }

    @Override
    public boolean equals(Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "Job Application Reference: %s\n\tJob Opening Reference: %s\n\tCandidate: %s\n\tStatus: %s".formatted(jobApplicationReference, jobOpening.identity(), candidate.name(), jobApplicationStatus);
    }
}
