package eapli.base.app.common.console;

import eapli.framework.actions.menu.Menu;

public interface CommonMainMenu {

    Menu buildMenu();

    void loop();
}