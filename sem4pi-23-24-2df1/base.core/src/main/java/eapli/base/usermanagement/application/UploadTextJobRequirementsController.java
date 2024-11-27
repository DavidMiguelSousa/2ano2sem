package eapli.base.usermanagement.application;

import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.repositories.JobApplicationRepository;
import eapli.base.clientusermanagement.repositories.JobOpeningRepository;
import eapli.base.clientusermanagement.repositories.JobRequirementsRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.interviewmanagement.repositories.InterviewModelRepository;
import eapli.base.services.InterviewManagementService;
import eapli.base.services.JobApplicationManagementService;
import eapli.base.services.JobOpeningManagementService;
import eapli.base.services.JobRequirementsManagementService;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UploadTextJobRequirementsController {

    private final JobOpeningRepository jobOpeningRepo = PersistenceContext.repositories().jobOpenings();
    private final JobApplicationRepository jobApplicationRepo = PersistenceContext.repositories().jobApplications();

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final JobRequirementsRepository jobRequirementsRepository = PersistenceContext.repositories().jobRequirements();

    private final JobRequirementsManagementService jobRequirementsManagementService = new JobRequirementsManagementService(jobRequirementsRepository);
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

    public boolean exportTextJobRequirements(String exportFile, String nameFile){
        return jobRequirementsManagementService.exportJobRequirements(exportFile, nameFile);
    }

    public boolean importTextJobRequirements(String downloadsPath, String destinationPath , String nameFile) throws IOException {
        return jobRequirementsManagementService.importTextJobRequirements(downloadsPath, destinationPath, nameFile);
    }

    public String searchJobRequirements(String s) {
        return jobRequirementsManagementService.searchJobRequirements(s);
    }

    public Iterable<JobOpening> allJobOpeningWithJobRequirements(){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.CUSTOMER_MANAGER, BaseRoles.ADMIN);

        Iterable<JobOpening> allJobOpenings = jobOpeningManagementService.findAllAvailable();

        List<JobOpening> jobOpeningsWithInterviewModel = new ArrayList<>();
        for (JobOpening jobOpening : allJobOpenings) {
            if (jobOpening.jobRequirements() != null) {
                jobOpeningsWithInterviewModel.add(jobOpening);
            }
        }

        return jobOpeningsWithInterviewModel;
    }

    public boolean validateGrammarJobRequirements(String filename) {
        return jobRequirementsManagementService.validateGrammarJobRequirements(filename);
    }

}
