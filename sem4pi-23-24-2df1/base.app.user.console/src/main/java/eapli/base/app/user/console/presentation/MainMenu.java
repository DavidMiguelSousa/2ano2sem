/*
 * Copyright (c) 2013-2024 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.app.user.console.presentation;

import eapli.base.app.common.console.presentation.authz.TestServerConnectionAction;
import eapli.base.app.common.console.CommonMainMenu;
import eapli.base.app.user.console.presentation.myuser.ListCandidateApplicationsAction;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * @author Paulo Gandra Sousa
 */
class MainMenu extends ClientUserBaseUI implements CommonMainMenu {

    private static final String SEPARATOR_LABEL = "--------------";

    private static final int EXIT_OPTION = 0;

    // MAIN MENU
//    private static final int MY_USER_OPTION = 1;
    private static final int TEST_CLIENT_SERVER_CONNECTION = 1;
    private static final int LIST_CANDIDATE_APPLICATIONS = 2;

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
        boolean exit = false;
        while (!exit) {
            final Menu menu = buildMenu();
            final MenuRenderer renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
            exit = renderer.render();
        }
        return true;
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

//        final Menu myUserMenu = new MyUserMenu();
//        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);
        mainMenu.addItem(TEST_CLIENT_SERVER_CONNECTION, "Test Client-Server Connection", new TestServerConnectionAction());
        mainMenu.addItem(LIST_CANDIDATE_APPLICATIONS, "List Candidate Applications", new ListCandidateApplicationsAction());

        mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Exiting..."));

        return mainMenu;
    }

    @Override
    public Menu buildMenu() {
        return buildMainMenu();
    }

    @Override
    public void loop() {
        mainLoop();
    }
}
