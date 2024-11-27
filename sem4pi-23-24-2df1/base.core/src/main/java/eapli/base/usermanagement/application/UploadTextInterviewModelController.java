package eapli.base.usermanagement.application;

import eapli.base.clientusermanagement.domain.Customer;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.repositories.JobApplicationRepository;
import eapli.base.clientusermanagement.repositories.JobOpeningRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.interviewmanagement.repositories.InterviewModelRepository;
import eapli.base.services.CustomerManagementService;
import eapli.base.services.InterviewManagementService;
import eapli.base.services.JobApplicationManagementService;
import eapli.base.services.JobOpeningManagementService;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import jakarta.persistence.Persistence;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class UploadTextInterviewModelController {

    private final InterviewModelRepository interviewModelRepo = PersistenceContext.repositories().interviewModels();
    private final JobOpeningRepository jobOpeningRepo = PersistenceContext.repositories().jobOpenings();
    private final JobApplicationRepository jobApplicationRepo = PersistenceContext.repositories().jobApplications();

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final InterviewManagementService interviewSvc = new InterviewManagementService(interviewModelRepo);
    private final JobOpeningManagementService jobOpeningManagementService = new JobOpeningManagementService(jobOpeningRepo);
    private final JobApplicationManagementService jobApplicationManagementService = new JobApplicationManagementService(jobApplicationRepo);

    public Iterable<JobOpening> allJobOpening() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.CUSTOMER_MANAGER, BaseRoles.ADMIN);

        return jobOpeningManagementService.findAllAvailable();
    }

    public Iterable<JobApplication> allJobApplicationsByJobOpening(JobOpening jobOpening) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.CUSTOMER_MANAGER, BaseRoles.ADMIN);

        return jobApplicationManagementService.jobApplicationsOf(jobOpening);
    }

    public boolean exportTextInterview(String exportFile, String nameFile){
        return interviewSvc.exportTextInterview(exportFile, nameFile);
    }

    public boolean importTextInterview(String downloadsPath, String destinationPath , String nameFile) throws IOException {
        return interviewSvc.importTextInterview(downloadsPath, destinationPath, nameFile);
    }

    public String searchInterviewModels(String s) {
        return interviewSvc.searchInterviewModels(s);
    }

    public Iterable<JobOpening> allJobOpeningWithInterviewModel(){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.CUSTOMER_MANAGER, BaseRoles.ADMIN);

        Iterable<JobOpening> allJobOpenings = jobOpeningManagementService.findAllAvailable();

        List<JobOpening> jobOpeningsWithInterviewModel = new ArrayList<>();
        for (JobOpening jobOpening : allJobOpenings) {
            if (jobOpening.interviewModel() != null) {
                jobOpeningsWithInterviewModel.add(jobOpening);
            }
        }

        return jobOpeningsWithInterviewModel;
    }

    public boolean validateGrammarInterview(String filename) {
        return interviewSvc.validateGrammarInterview(filename);
    }

}
