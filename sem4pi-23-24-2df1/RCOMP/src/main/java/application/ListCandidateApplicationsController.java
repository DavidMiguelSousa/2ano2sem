package application;

import domain.MessageCode;
import eapli.framework.application.UseCaseController;
import tcp_chat.TcpService;

@UseCaseController
public class ListCandidateApplicationsController {

    private final TcpService tcpService;

    public ListCandidateApplicationsController() {
        this.tcpService = new TcpService();
    }

    public String retrieveCandidateApplicationsFromServer() throws Exception {
        return tcpService.communicate(MessageCode.LIST_CANDIDATE_APPLICATIONS, "", "");
    }
}
