package application;

import domain.MessageCode;
import eapli.framework.application.UseCaseController;
import org.antlr.v4.runtime.misc.Pair;
import tcp_chat.TcpService;

@UseCaseController
public class TcpChatSrvLoginController {

    private final TcpService tcpService = new TcpService();

    public Pair<MessageCode, String> login(String username, String password) throws Exception {
        return tcpService.responseCode(MessageCode.AUTH, username, password);
    }
}