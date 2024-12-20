/*
 * Copyright (c) 2013-2024 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eapli.base.app.other.console;

import eapli.base.app.other.console.presentation.MainMenu;
import eapli.base.app.common.console.presentation.authz.TcpChatSrvLoginAction;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.io.util.Console;
import tcp_chat.TcpService;

/**
 *
 * @author Paulo Gandra Sousa
 */
@SuppressWarnings("squid:S106")
public final class OtherApp {

    /**
     * Empty constructor is private to avoid instantiation of this class.
     */
    private OtherApp() {
    }

    public static void main(final String[] args) {
        System.out.println("=====================================");
        System.out.println("Base POS");
        System.out.println("(C) 2016, 2017, 2018");
        System.out.println("=====================================");

        AuthzRegistry.configure(PersistenceContext.repositories().users(),
                new BasePasswordPolicy(), new PlainTextEncoder());

        try {
            TcpService.connectClient(Console.readNonEmptyLine("Enter the server IP address: ", "Invalid IP address."));
        } catch (Exception e) {
            System.out.println("Error connecting to server: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        // login and go to main menu
        if (new TcpChatSrvLoginAction(new MainMenu()).execute()) {
            final MainMenu menu = new MainMenu();
            menu.mainLoop();
        }

        try {
            TcpService.disconnectClient();
        } catch (Exception e) {
            System.out.println("Error disconnecting from server: " + e.getMessage());
            e.printStackTrace();
        }

        // exiting the application, closing all threads
        System.exit(0);
    }
}
