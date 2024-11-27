package serverInit;

import eapli.base.Application;
import eapli.base.candidatemanagement.repositories.CandidateRepository;
import eapli.base.clientusermanagement.repositories.CustomerRepository;
import eapli.base.clientusermanagement.repositories.JobApplicationRepository;
import eapli.base.clientusermanagement.repositories.JobOpeningRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import tcp_chat.TcpChatSrv;

public class Server {
    public static void main(String[] args) {
        CustomerRepository customerRepo = null;
        JobApplicationRepository jobRepo = null;
        CandidateRepository candidateRepo = null;
        JobOpeningRepository jobOpeningRepo = null;

        String environment = System.getProperty("environment");
        if (environment != null && environment.equalsIgnoreCase("production")) {
            Application.settings().setDefaultProperties();
            AuthzRegistry.configure(PersistenceContext.repositories().users(), new BasePasswordPolicy(), new PlainTextEncoder());
            customerRepo = PersistenceContext.repositories().customers();
            jobRepo = PersistenceContext.repositories().jobApplications();
            candidateRepo = PersistenceContext.repositories().candidates();
            jobOpeningRepo = PersistenceContext.repositories().jobOpenings();
        }

        try {
            TcpChatSrv.run(customerRepo, jobRepo, candidateRepo, jobOpeningRepo);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
