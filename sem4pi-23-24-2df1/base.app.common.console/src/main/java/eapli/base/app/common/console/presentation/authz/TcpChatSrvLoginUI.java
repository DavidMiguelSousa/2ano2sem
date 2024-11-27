package eapli.base.app.common.console.presentation.authz;

import application.TcpChatSrvLoginController;
import domain.MessageCode;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.antlr.v4.runtime.misc.Pair;

public class TcpChatSrvLoginUI extends AbstractUI {

    private final TcpChatSrvLoginController controller = new TcpChatSrvLoginController();

    @Override
    protected boolean doShow() {
        String username = Console.readNonEmptyLine("Username: ", "Please provide a username");
        String password = Console.readNonEmptyLine("Password: ", "Please provide a password");

        try {
            Pair<MessageCode, String> response = controller.login(username, password);
            System.out.println(response.b);
            return response.a == MessageCode.ACK;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public String headline() {
        return "Tcp Chat Server - Login";
    }

}