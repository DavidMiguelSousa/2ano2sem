package tcp_chat;

import domain.MessageCode;
import eapli.base.Application;
import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.candidatemanagement.repositories.CandidateRepository;
import eapli.base.clientusermanagement.domain.Customer;
import eapli.base.clientusermanagement.domain.CustomerCode;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.domain.jobopening.JobReference;
import eapli.base.clientusermanagement.domain.jobopening.Phase;
import eapli.base.clientusermanagement.domain.jobopening.Status;
import eapli.base.clientusermanagement.repositories.CustomerRepository;
import eapli.base.clientusermanagement.repositories.JobApplicationRepository;
import eapli.base.clientusermanagement.repositories.JobOpeningRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class TcpChatSrv {

    private static final HashMap<Socket, DataOutputStream> cliList = new HashMap<>();
    private static boolean running = true;

    private static final int port = 8080;

    public static synchronized void sendToAll(int len, byte[] data) throws Exception {
        for (DataOutputStream cOut : cliList.values()) {
            cOut.write(len);
            cOut.write(data, 0, len);
        }
    }

    public static synchronized void addCli(Socket s) throws Exception {
        cliList.put(s, new DataOutputStream(s.getOutputStream()));
    }

    public static synchronized void remCli(Socket s) throws Exception {
        if (cliList.containsKey(s)) {
            if (s != null && !s.isClosed()) {
                cliList.get(s).write(0);
                cliList.remove(s);
                s.close();
            }
        }
    }

    private static ServerSocket sock;

    public static void run(CustomerRepository customerRepository, JobApplicationRepository jobApplicationRepository, CandidateRepository candidateRepository, JobOpeningRepository jobOpeningRepository) throws Exception {
        try {
            sock = new ServerSocket(port);
            System.out.println("IP Address: " + InetAddress.getLocalHost().getHostAddress());
            System.out.println("Server started on port " + sock.getLocalPort());
        } catch (IOException ex) {
            System.out.println("Local port number not available.");
            System.exit(1);
        }

        new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                while (running) {
                    System.out.println("Press enter to stop the server.");
                    reader.readLine();
                    System.out.println("Stopping server...");
                    try {
                        stopServer();
                    } catch (Exception e) {
                        System.out.println("Failed to stop server: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        while (running) {
            try {
                Socket s = sock.accept(); //wait for a new client connection request
                System.out.println("Client connected.");
                addCli(s);
                Thread cli = new TcpChatSrvClient(s, customerRepository, jobApplicationRepository, candidateRepository, jobOpeningRepository);
                cli.start();
            } catch (IOException e) {
                if (!running) {
                    System.out.println("Server stopped.");
                } else {
                    System.out.println("Failed to accept client connection: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }

    }

    public static void stopServer() throws Exception {
        running = false;
        for (Socket s : cliList.keySet()) {
            remCli(s);
        }
        if (sock != null && !sock.isClosed()) {
            sock.close();
        }
    }
}

class TcpChatSrvClient extends Thread {
    private final Socket myS;

    private Customer customer;
    private Candidate candidate;

    private final CustomerRepository customerRepository;
    private final CandidateRepository candidateRepository;
    private final JobApplicationRepository jobApplicationRepository;

    private final JobOpeningRepository jobOpeningRepository;
    private volatile boolean running = true;

    public TcpChatSrvClient(Socket s, CustomerRepository customerRepo, JobApplicationRepository jobRepo, CandidateRepository candidateRepo, JobOpeningRepository JobOpeningRepository) {
        myS = s;
        customerRepository = customerRepo;
        jobApplicationRepository = jobRepo;
        candidateRepository = candidateRepo;
        jobOpeningRepository = JobOpeningRepository;
    }

    public void run() {
        try {
            DataInputStream sIn = new DataInputStream(myS.getInputStream());
            DataOutputStream sOut = new DataOutputStream(myS.getOutputStream());

            while (running) {
                try {
                    int version = sIn.read();
                    int code = sIn.read();
                    int data1LenL = sIn.read();
                    int data1LenM = sIn.read();
                    int data1Len = data1LenL + (data1LenM << 8);
                    byte[] data1 = new byte[data1Len];
                    sIn.readFully(data1, 0, data1Len);

                    int data2LenL = sIn.read();
                    int data2LenM = sIn.read();
                    int data2Len = data2LenL + (data2LenM << 8);
                    byte[] data2 = new byte[data2Len];
                    sIn.readFully(data2, 0, data2Len);

                    //read the end of message bytes
                    sIn.read();
                    sIn.read();

                    byte[] response;

                    MessageCode messageCode = MessageCode.withCode(code);
                    if (messageCode != null) {
                        switch (messageCode) {
                            case COMMTEST:
                                response = ackResponse((byte) version, "Communication test successful.".getBytes(), null);
                                break;
                            case DISCONN:
                                response = ackResponse((byte) version, "Disconnected.".getBytes(), null);

                                sOut.write(response);
                                sOut.flush(); //send the response immediately

                                //end the loop
                                TcpChatSrv.stopServer();
                                break;
                            case AUTH:
                                Optional<UserSession> userSession = AuthzRegistry.authenticationService().authenticate(new String(data1), new String(data2));
                                if (userSession.isPresent()) {
                                    Optional<Customer> customerOptional = customerRepository.findByUsername(Username.valueOf(new String(data1)));
                                    Optional<Candidate> candidateOptional = candidateRepository.ofIdentity(Username.valueOf(new String(data1)));
                                    if (customerOptional.isPresent()) {
                                        customer = customerOptional.get();
                                        String customerEmail = customer.user().email().toString();
                                        response = ackResponse((byte) version, "Authentication successful.".getBytes(), ("Logged customer email: " + customerEmail).getBytes());
                                    } else if (candidateOptional.isPresent()) {
                                        candidate = candidateOptional.get();
                                        String candidateEmail = candidate.email().toString();
                                        response = ackResponse((byte) version, "Authentication successful.".getBytes(), ("Logged candidate email: " + candidateEmail).getBytes());
                                    } else {
                                        response = errorResponse((byte) version, "Authentication failed.".getBytes());
                                    }
                                } else {
                                    response = errorResponse((byte) version, "Authentication failed.".getBytes());
                                }
                                break;
                            case LIST_CANDIDATE_APPLICATIONS:
                                if (candidate == null) {
                                    if (!System.getProperty("environment").equalsIgnoreCase("production")) {
                                        candidate = candidateRepository.ofIdentity(Username.valueOf(new String(data1))).get();
                                    } else {
                                        response = errorResponse((byte) version, "Candidate authentication required".getBytes());
                                        break;
                                    }
                                }
                                Iterable<JobApplication> jobApplications = jobApplicationRepository.applicationsByCandidate(candidate);
                                if (!jobApplications.iterator().hasNext()) {
                                    response = ackResponse((byte) version, "No applications found".getBytes(), null);
                                } else {
                                    StringBuilder builder = new StringBuilder();
                                    builder.append("CANDIDATE ").append(candidate.name().toString().toUpperCase()).append(" APPLICATIONS\n\n");
                                    builder.append(String.format("%-30s %-30s %-30s %-30s %-30s\n", "APPLICATION REFERENCE", "STATUS", "DATE OF SUBMISSION", "JOB OPENING REFERENCE", "NUMBER OF APPLICANTS"));
                                    AtomicInteger counter = new AtomicInteger(1);
                                    for (JobApplication jobApplication : jobApplications) {
                                        builder.append(String.format("%-5s %-30s %-30s %-30s %-30s %-30d\n", counter.getAndIncrement() + ".", jobApplication.identity().toString(), jobApplication.status().toString(), jobApplication.dateOfSubmission().toString(), jobApplication.jobOpening().identity().toString(), jobApplicationRepository.numberOfApplicants(jobApplication)));
                                    }
                                    response = ackResponse((byte) version, builder.toString().getBytes(), null);
                                }
                                break;

                            case LIST_CUSTOMER_ALL_JOB_OPENING:
                                if (customer == null) {
                                    if (!System.getProperty("environment").equalsIgnoreCase("production")) {
                                        customer = customerRepository.ofIdentity(CustomerCode.valueOf(new String(data1))).get();
                                    } else {
                                        response = errorResponse((byte) version, "Customer authentication required".getBytes());
                                        break;
                                    }
                                }
                                Iterable<JobOpening> jobOpenings = jobOpeningRepository.jobOpeningsByCustomer(customer);
                                if (!jobOpenings.iterator().hasNext()) {
                                    response = ackResponse((byte) version, "No Job Openings found".getBytes(), null);
                                } else {
                                    StringBuilder builder = new StringBuilder();
                                    builder.append("CUSTOMER ").append(customer.user().name().toString().toUpperCase()).append(" JOB OPENINGS\n\n");
                                    builder.append(String.format("%-5s %-30s %-30s %-30s %-30s\n"," ", "JOB REFERENCE", "TITLE", "ACTIVE SINCE", "NUMBER OF APPLICANTS"));
                                    AtomicInteger counter = new AtomicInteger(1);
                                    for (JobOpening jobOpening : jobOpenings) {
                                        builder.append(String.format("%-5s %-30s %-30s %-30s %-30d\n", counter.getAndIncrement() + ".", jobOpening.identity().toString(), jobOpening.jobTitle().toString(), jobOpening.phases().get(Phase.APPLICATION).dateInterval().start().getTime().toString(), jobOpeningRepository.numberOfApplicants(jobOpening)));
                                    }
                                    response = ackResponse((byte) version, builder.toString().getBytes(), null);
                                }
                                break;
                            case CUSTOMER_NOTIFICATION:
                                if (customer == null) {
                                    if (!System.getProperty("environment").equalsIgnoreCase("production")) {
                                        customer = customerRepository.ofIdentity(CustomerCode.valueOf(new String(data1))).get();
                                    } else {
                                        response = errorResponse((byte) version, "Customer authentication required".getBytes());
                                        break;
                                    }
                                }
                                JobOpening jobOpening = jobOpeningRepository.findByJobReference(JobReference.valueOf(new String(data2))).get();
                                String phasesString="";
                                for(Phase phase : jobOpening.phases().keySet()) {
                                    if (jobOpening.phases().get(phase).status().equals(Status.IN_PROGRESS)) {
                                        phasesString = phase.phase();
                                        break;
                                    }
                                }
                                 response = ackResponse((byte) version, ("The current phase of job opening "+new String(data2)+"  has changed to "+ phasesString).getBytes(), null);
                                 break;
                            default:
                                response = errorResponse((byte) version, "Unknown request".getBytes());
                                break;
                        }
                    } else {
                        response = errorResponse((byte) version, "Unknown request code".getBytes());
                    }

                    sOut.write(response);
                    sOut.flush();

                } catch (Exception e) {
                    System.out.println("Client disconnected.");
                    running = false; //end the loop on IOException
                }
            }

            //ensure the client socket is closed outside the loop
            myS.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private byte[] ackResponse(byte version, byte[] data1, byte[] data2) {
        return createResponse(version, (byte) MessageCode.ACK.code(), data1, data2);
    }

    private byte[] errorResponse(byte version, byte[] data1) {
        return createResponse(version, (byte) MessageCode.ERR.code(), data1, null);
    }

    private byte[] createResponse(byte version, byte code, byte[] data1, byte[] data2) {
        ByteArrayOutputStream message = new ByteArrayOutputStream();
        try {
            message.write(version);
            message.write(code);

            if (data1 != null) {
                message.write(data1.length & 0xFF); //DATA1_LEN_L
                message.write((data1.length >> 8) & 0xFF); //DATA1_LEN_M
                message.write(data1);
            } else {
                message.write(0);
                message.write(0);
            }

            if (data2 != null) {
                message.write(data2.length & 0xFF); //DATA2_LEN_L
                message.write((data2.length >> 8) & 0xFF); //DATA2_LEN_M
                message.write(data2);
            } else {
                message.write(0);
                message.write(0);
            }

            message.write(0); //end of message (first zero)
            message.write(0); //end of message (second zero)
        } catch (IOException e) {
            e.printStackTrace();
        }

        return message.toByteArray();
    }
}
