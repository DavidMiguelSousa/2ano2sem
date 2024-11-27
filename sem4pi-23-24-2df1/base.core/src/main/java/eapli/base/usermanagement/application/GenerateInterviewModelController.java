package eapli.base.usermanagement.application;

import question.QuestionType;
import eapli.base.services.InterviewModelManagementService;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Map;
import java.util.Set;

public class GenerateInterviewModelController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final InterviewModelManagementService intModSvc = new InterviewModelManagementService();

    public boolean createFile(String fileName) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER, BaseRoles.LANGUAGE_ENGINEER);
        return intModSvc.createFile(fileName);
    }

    public void addTitle(String nameOfModel) {
        intModSvc.addTitle(nameOfModel);
    }

    public void addQuestion(QuestionType questionType, String question, double grade) {
        intModSvc.addQuestion(questionType, question, grade);
    }

    public void addOptions(Set<String> options) {
        intModSvc.addOptions(options);
    }

    public void addSolutions(Set<Map<String, Double>> solutions) {
        intModSvc.addSolutions(solutions);
    }

    public boolean writeInFile() {
        return intModSvc.writeInFile();
    }

    public void validateTotalGrade(Map<String, Double> questionsAndGrades, double totalGrade) {
        intModSvc.validateTotalGrade(questionsAndGrades, totalGrade);
    }
}
