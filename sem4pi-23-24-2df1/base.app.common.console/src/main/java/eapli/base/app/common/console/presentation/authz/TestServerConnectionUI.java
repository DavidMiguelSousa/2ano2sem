package eapli.base.app.common.console.presentation.authz;

import application.TestServerConnectionController;
import eapli.framework.presentation.console.AbstractUI;

public class TestServerConnectionUI extends AbstractUI {

    private final TestServerConnectionController controller = new TestServerConnectionController();

    @Override
    protected boolean doShow() {
        try {
            System.out.println(controller.testConnection());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return true;
        }
        return false;
    }

    @Override
    public String headline() {
        return "Test Client-Server Connection";
    }
}
