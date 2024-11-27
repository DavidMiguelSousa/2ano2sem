package eapli.base.app.backoffice.console.presentation;

import eapli.base.Application;
import eapli.base.app.backoffice.console.presentation.admin.ActivateUserAction;
import eapli.base.app.backoffice.console.presentation.admin.DeactivateUserAction;
import eapli.base.app.backoffice.console.presentation.admin.ListActiveBackofficeUsersAction;
import eapli.base.app.backoffice.console.presentation.authz.AddUserAction;
import eapli.base.app.backoffice.console.presentation.authz.SelectInterviewModelAction;
import eapli.base.app.backoffice.console.presentation.authz.SelectJobRequirementsAction;
import eapli.base.app.backoffice.console.presentation.candidate.AcceptRefuseSignupRequestAction;
import eapli.base.app.backoffice.console.presentation.customermanager.*;
import eapli.base.app.backoffice.console.presentation.operator.*;
import eapli.base.app.common.console.presentation.authz.MyUserMenu;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

public class MainMenu extends AbstractUI {

    // COMMON SETTINGS
    private static final String RETURN_LABEL = "Return ";
    private static final int EXIT_OPTION = 0;
    private static final String SEPARATOR_LABEL = "--------------";
    private static final int ADD_USER_OPTION = 1;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int SETTINGS_OPTION = 2;

    // ADMIN MENU
    private static final int LIST_ACTIVE_BACKOFFICE_USERS_OPTION = 2;
    private static final int ACTIVATE_USER_OPTION = 3;
    private static final int DEACTIVATE_USER_OPTION = 4;
    private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 5;

    // CUSTOMER MANAGER MENU
    private static final int COUNT_TOP_WORDS = 2;
    private static final int JOBOPENING_MANAGEMENT = 3;
    private static final int JOBAPPLICATION_MANAGEMENT = 4;

    // OPERATOR MENU
    private static final int GENERATE_JOB_REQUIREMENTS = 1;
    private static final int IMPORT_CANDIDATE_FILES = 2;
    private static final int REGISTER_CANDIDATE = 3;
    private static final int LIST_CANDIDATES = 4;
    private static final int ENABLE_CANDIDATE = 5;
    private static final int DISABLE_CANDIDATE = 6;

    // JOB OPENING MANAGEMENT
    private static final int ADD_JOBOPENING = 1;
    private static final int LIST_JOBOPENING = 2;
    private static final int EDIT_JOB_OPENING = 3;
    private static final int JOBOPENING_PHASES_MANAGEMENT = 4;
    private static final int JOBOPENING_INTERVIEW_MANAGEMENT = 5;
    private static final int JOBOPENING_REQUIREMENTS_MANAGEMENT = 6;
    private static final int JOBAPPLICATION_RELATED = 7;


    // JOB APPLICATION RELATED
    private static final int RANK_JOB_APPLICATIONS = 1;
    private static final int EVALUATE_JOB_APPLICATIONS = 2;
    private static final int DISPLAY_INTERVIEW_GRADES = 3;
    private static final int VERIFY_REQUIREMENTS = 4;
    private static final int DISPLAY_JOB_APPLICATION_DATA = 5;
    private static final int RECORD_INTERVIEW_TIMESTAMP = 6;

    // JOB OPENING PHASES MANAGEMENT
    private static final int SETUP_JOBOPENING_PHASES = 1;
    private static final int OPEN_CLOSE_PHASES = 2;

    // JOB OPENING INTERVIEW MANAGEMENT
    private static final int GENERATE_INTERVIEW_MODEL = 1;
    private static final int SELECT_INTERVIEW_MODEL = 2;
    private static final int UPLOAD_INTERVIEW_FILE = 3;

    // JOB APPLICATION MANAGEMENT
    private static final int LIST_DATA_APPLICATIONS_JOBOPENING = 1;
    private static final int CANDIDATE_MANAGEMENT = 2;

    // CANDIDATE MANAGEMENT
    private static final int LIST_PERSONAL_DATA_CANDIDATE = 1;
    private static final int LIST_PERSONAL_DATA_APPLICATIONS_CANDIDATE = 2;


    //-----------------------//
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return renderer.render();
    }

    @Override
    public String headline() {

        return authz.session().map(s -> "Base [ @" + s.authenticatedUser().identity() + " ]")
                .orElse("Base [ ==Anonymous== ]");
    }

    protected void drawFormTitle() {
        System.out.println("* * *  B A S E  * * *\n");
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.ADMIN)) {
            final Menu settingsMenu = buildAdminSettingsMenu();
            mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.CUSTOMER_MANAGER)) {
            final Menu settingsMenu = buildCustomerManagerSettingsMenu();
            mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.OPERATOR)) {
            final Menu settingsMenu = buildOperatorSettingsMenu();
            mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Closing..."));

        return mainMenu;
    }

    private Menu buildAdminSettingsMenu() {
        final Menu menu = new Menu("Settings >");

        menu.addItem(ADD_USER_OPTION, "Register User", new AddUserAction());
        menu.addItem(LIST_ACTIVE_BACKOFFICE_USERS_OPTION, "List Active Backoffice Users", new ListActiveBackofficeUsersAction());
        menu.addItem(ACTIVATE_USER_OPTION, "Enable User", new ActivateUserAction());
        menu.addItem(DEACTIVATE_USER_OPTION, "Disable User", new DeactivateUserAction());
        menu.addItem(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, "Accept/Refuse Signup Request", new AcceptRefuseSignupRequestAction());

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildCustomerManagerSettingsMenu() {
        final Menu menu = new Menu("Settings >");
        final Menu jobOpeningMenu = buildJobOpeningManagementMenu();
        final Menu jobApplicationMenu = buildJobApplicationManagementMenu();

        menu.addItem(ADD_USER_OPTION, "Register Customer", new AddUserAction());
        menu.addItem(COUNT_TOP_WORDS, "Count Top 20 Words from Applications' Files", new CountTopWordsAction());
        menu.addSubMenu(JOBOPENING_MANAGEMENT, jobOpeningMenu);
        menu.addSubMenu(JOBAPPLICATION_MANAGEMENT, jobApplicationMenu);

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildOperatorSettingsMenu() {
        final Menu menu = new Menu("Settings >");

        menu.addItem(GENERATE_JOB_REQUIREMENTS, "Generate Job Requirements", new GenerateJobRequirementsAction());
        menu.addItem(IMPORT_CANDIDATE_FILES, "Import Candidate Files", new ImportCandidateFilesAction());
        menu.addItem(REGISTER_CANDIDATE, "Register Candidate", new RegisterCandidateAction());
        menu.addItem(LIST_CANDIDATES, "List Candidates", new ListCandidatesAction());
        menu.addItem(ENABLE_CANDIDATE, "Enable Candidate", new EnableCandidateAction());
        menu.addItem(DISABLE_CANDIDATE, "Disable Candidate", new DisableCandidateAction());

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildCandidateManagementMenu() {
        final Menu menu = new Menu("Candidate Management >");

        menu.addItem(LIST_PERSONAL_DATA_CANDIDATE, "List Candidate's Personal Data", new ListPersonalDataCandidateAction());
        menu.addItem(LIST_PERSONAL_DATA_APPLICATIONS_CANDIDATE, "List Candidate's Personal Data with Applications", new ListDetailsApplicationCandidateAction());

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildJobApplicationManagementMenu() {
        final Menu menu = new Menu("Job Application Management >");
        final Menu candidateMenu = buildCandidateManagementMenu();

        menu.addItem(LIST_DATA_APPLICATIONS_JOBOPENING, "List Job Applications with Specific Job Reference", new ListApplicationJobOpeningAction());
        menu.addSubMenu(CANDIDATE_MANAGEMENT, candidateMenu);

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildJobOpeningManagementMenu() {
        final Menu menu = new Menu("Job Opening Management >");
        final Menu jobOpeningPhasesMenu = buildJobOpeningPhasesManagementMenu();
        final Menu jobOpeningInterviewMenu = buildJobOpeningInterviewManagementMenu();
        final Menu jobOpeningRequirementsMenu = buildJobOpeningRequirementsManagementMenu();
        final Menu jobApplicationRelatedMenu = buildJobApplicationRelatedMenu();

        menu.addItem(ADD_JOBOPENING, "Add Job Opening", new AddJobOpeningAction());
        menu.addItem(LIST_JOBOPENING, "List Job Opening", new ListJobOpeningAction());
        menu.addItem(EDIT_JOB_OPENING, "Edit Job Opening", new EditJobOpeningAction());
        menu.addSubMenu(JOBOPENING_PHASES_MANAGEMENT, jobOpeningPhasesMenu);
        menu.addSubMenu(JOBOPENING_INTERVIEW_MANAGEMENT, jobOpeningInterviewMenu);
        menu.addSubMenu(JOBOPENING_REQUIREMENTS_MANAGEMENT, jobOpeningRequirementsMenu);
        menu.addSubMenu(JOBAPPLICATION_RELATED, jobApplicationRelatedMenu);

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildJobApplicationRelatedMenu(){
        final Menu menu = new Menu("Job Application Related Content >");

        menu.addItem(RANK_JOB_APPLICATIONS, "Rank Job Opening's Applications", new RankJobApplicationsAction());
        menu.addItem(EVALUATE_JOB_APPLICATIONS, "Evaluate Job Opening's Applications", new EvaluateJobOpeningsInterviewsAction());
        menu.addItem(DISPLAY_INTERVIEW_GRADES, "Display Interview Grades", new ListJobOpeningsInterviewGradesAction());
        menu.addItem(VERIFY_REQUIREMENTS, "Verify Requirements Job Opening's Applications", new VerifyJobOpeningsRequirementsAction());
        menu.addItem(DISPLAY_JOB_APPLICATION_DATA, "Display All Data of a Job Application", new DisplayJobApplicationDataAction());
        menu.addItem(RECORD_INTERVIEW_TIMESTAMP, "Record Timestamp for and Interview with a Candidate", new RecordInterviewTimestampAction());

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildJobOpeningInterviewManagementMenu() {
        final Menu menu = new Menu("Job Opening Interview Management >");

        menu.addItem(GENERATE_INTERVIEW_MODEL, "Generate Interview Model", new GenerateInterviewModelAction());
        menu.addItem(SELECT_INTERVIEW_MODEL, "Select Interview Model", new SelectInterviewModelAction());
        menu.addItem(UPLOAD_INTERVIEW_FILE, "Import Interview file with candidate responses", new UploadTextInterviewModelAction());

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }
    private Menu buildJobOpeningRequirementsManagementMenu() {
        final Menu menu = new Menu("Job Opening Requirements Management >");

        menu.addItem(GENERATE_INTERVIEW_MODEL, "Generate Job Requirements", new GenerateJobRequirementsAction());
        menu.addItem(SELECT_INTERVIEW_MODEL, "Select Job Requirements", new SelectJobRequirementsAction());
        menu.addItem(UPLOAD_INTERVIEW_FILE, "Import Job Requirements file with candidate responses", new UploadTextJobRequirementsAction());

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildJobOpeningPhasesManagementMenu() {
        final Menu menu = new Menu("Job Opening Phases Management >");

        menu.addItem(SETUP_JOBOPENING_PHASES, "Setup Job Opening Phases", new SetupJobOpeningPhasesAction());
        menu.addItem(OPEN_CLOSE_PHASES, "Open/Close Job Opening Phases", new OpenClosePhasesJobOpeningAction());

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

}
