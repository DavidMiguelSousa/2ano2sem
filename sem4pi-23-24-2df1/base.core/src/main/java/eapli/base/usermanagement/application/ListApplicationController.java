package eapli.base.usermanagement.application;

import eapli.base.Application;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.repositories.JobApplicationRepository;
import eapli.base.clientusermanagement.repositories.JobOpeningRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@UseCaseController
public class ListApplicationController {

    private final UserRepository jobs4uUserRepository = PersistenceContext
            .repositories().users();

    private final JobApplicationRepository jobApplicationRepository = PersistenceContext
            .repositories().jobApplications();
    private final JobOpeningRepository jobOpeningRepository = PersistenceContext.repositories().jobOpenings();

    public Iterable<JobApplication> allApplications() {
        return this.jobApplicationRepository.findAll();
    }
    public Iterable<JobApplication> allApplicationsByJobreference(String jobReference) {
        List<JobApplication> filteredJobApplications = new ArrayList<>();
        for (JobApplication jobApplication : this.jobApplicationRepository.findAll()) {
            if (jobApplication.jobOpening().jobReference().reference().equals(jobReference)) {
                filteredJobApplications.add(jobApplication);
            }
        }
        return filteredJobApplications;
    }
}
