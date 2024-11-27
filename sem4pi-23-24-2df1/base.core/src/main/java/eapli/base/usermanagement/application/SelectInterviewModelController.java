package eapli.base.usermanagement.application;

import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.domain.jobopening.JobReference;
import eapli.base.clientusermanagement.repositories.JobApplicationRepository;
import eapli.base.clientusermanagement.repositories.JobOpeningRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.interviewmanagement.application.ListInterviewModelService;
import eapli.base.interviewmanagement.domain.InterviewModel;
import eapli.base.services.JobOpeningManagementService;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.*;

public class SelectInterviewModelController {
    private final JobOpeningRepository repo = PersistenceContext.repositories().jobOpenings();

    private final JobOpeningManagementService jobOpeningManagementService = new JobOpeningManagementService(repo);
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ListInterviewModelService listInterviewModelService = new ListInterviewModelService();

    public Iterable<InterviewModel> findInterviewModels() {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.CUSTOMER_MANAGER);

        return listInterviewModelService.allInterviewModels();
    }

    public List<JobOpening> findJobOpenings() {

        return jobOpeningManagementService.findAllAvailable();

    }

    public void assignInterviewModel(JobOpening jobReference, InterviewModel interviewModel) {
        //Optional<JobOpening> jobOpeningOpt = jobOpeningManagementService.findJobOpeningById(jobReference);
//        Iterable<InterviewModel> interviewModelOpt = listInterviewModelService.findInterviewModelById(interviewName);
//        System.out.println("interviewModelOpt: " + interviewModelOpt);
//        JobOpening jobOpening = null;
//        if (jobOpeningOpt.isPresent()) jobOpening = jobOpeningOpt.get();
//        assert jobOpening != null;
        //InterviewModel interviewModel = interviewModelOpt.iterator().next();
        jobOpeningManagementService.assignInterviewModel(jobReference, interviewModel);
    }


    public InterviewModel findInterviewModelByDesignation(Designation interviewName) {
        return listInterviewModelService.findInterviewModelById(interviewName).iterator().next();
    }
}
