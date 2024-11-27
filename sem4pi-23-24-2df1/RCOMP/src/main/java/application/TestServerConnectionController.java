package application;

import domain.MessageCode;
import eapli.framework.application.UseCaseController;
import tcp_chat.TcpService;

@UseCaseController
public class TestServerConnectionController {

    private final TcpService tcpService = new TcpService();

    public String testConnection() throws Exception {
        return tcpService.communicate(MessageCode.COMMTEST, "", "");
    }
}