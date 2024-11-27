package eapli.base.app.common.console.presentation.authz;

import eapli.base.app.common.console.CommonMainMenu;
import eapli.framework.actions.Action;

public class TcpChatSrvLoginAction implements Action {

    private final CommonMainMenu mainMenu;

    public TcpChatSrvLoginAction(CommonMainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    @Override
    public boolean execute() {
        if (new TcpChatSrvLoginUI().doShow()) {
            mainMenu.loop();
            return false;
        }
        return true;
    }
}
