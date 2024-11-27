package application;

import domain.MessageCode;
import eapli.framework.application.UseCaseController;
import tcp_chat.TcpService;

@UseCaseController
public class ListCustomerJobOpeningController {

    private final TcpService tcpService;

    public ListCustomerJobOpeningController() {
        this.tcpService = new TcpService();
    }

    public String retrieveCustomerJobOpeningsFromServer() throws Exception {
        return tcpService.communicate(MessageCode.LIST_CUSTOMER_ALL_JOB_OPENING, "", "");
    }
}
