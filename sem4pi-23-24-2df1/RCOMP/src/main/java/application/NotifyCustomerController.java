package application;

import domain.MessageCode;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.framework.application.UseCaseController;
import tcp_chat.TcpService;

@UseCaseController
public class NotifyCustomerController {

    private final TcpService tcpService;

    public NotifyCustomerController() {
        this.tcpService = new TcpService();
    }

    public String receiveNotification(JobOpening jobOpening) throws Exception {
        return tcpService.communicate(MessageCode.CUSTOMER_NOTIFICATION, "", jobOpening.jobReference().reference());
    }
}
