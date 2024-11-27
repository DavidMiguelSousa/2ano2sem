package tcp_chat;

import domain.MessageCode;
import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.candidatemanagement.repositories.CandidateRepository;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.repositories.JobApplicationRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

class TcpChatSrvSingleThread {

	private static final int SO_TIMEOUT = 100;

	private static final HashMap<Socket, DataOutputStream> cliListOut = new HashMap<>();
	private static final HashMap<Socket, DataInputStream> cliListIn = new HashMap<>();

	private static ServerSocket sock;
	private static JobApplicationRepository jobApplicationRepository;
	private static CandidateRepository candidateRepository;

	public static void startServer(JobApplicationRepository jobRepo, CandidateRepository candidateRepo) throws Exception {
		jobApplicationRepository = jobRepo;
		candidateRepository = candidateRepo;

		try {
			sock = new ServerSocket(8080);
			System.out.println("Server started on port 8080.");
		} catch (IOException ex) {
			System.out.println("Local port number not available.");
			System.exit(1);
		}

		sock.setSoTimeout(SO_TIMEOUT); // set the socket timeout

		while (true) {
			try { // check for new client connection requests
				Socket cliS = sock.accept();
				cliS.setSoTimeout(SO_TIMEOUT); // set the connected socket timeout
				cliListOut.put(cliS, new DataOutputStream(cliS.getOutputStream()));
				cliListIn.put(cliS, new DataInputStream(cliS.getInputStream()));
			} catch (SocketTimeoutException ignored) {
			} // no new connections

			for (Socket s : cliListIn.keySet()) {   // all connected clients
				DataInputStream sIn = cliListIn.get(s);
				try {                // try reading the line size
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

					// read the end of message bytes
					sIn.read();
					sIn.read();

					byte[] response;
					if (code == MessageCode.LIST_CANDIDATE_APPLICATIONS.code()) {
						Candidate candidate = candidateRepository.ofIdentity(Username.valueOf(new String(data1))).get();
						Iterable<JobApplication> jobApplications = jobApplicationRepository.applicationsByCandidate(candidate);
						if (!jobApplications.iterator().hasNext()) {
							response = createResponse((byte) version, (byte) MessageCode.ACK.code(), "No applications found".getBytes(), null);
						} else {
							StringBuilder builder = new StringBuilder();
							builder.append("\nCANDIDATE ").append(candidate.name().toString().toUpperCase()).append(" APPLICATIONS\n\n");
							builder.append(String.format("%-30s %-30s %-30s %-30s %-30s\n\n", "APPLICATION REFERENCE", "STATUS", "DATE OF SUBMISSION", "JOB OPENING REFERENCE", "NUMBER OF APPLICANTS"));
							AtomicInteger counter = new AtomicInteger(1);
							for (JobApplication jobApplication : jobApplications) {
								builder.append(String.format("%-5d. %-30s %-30s %-30s %-30s %-30d\n", counter.getAndIncrement(), jobApplication.identity().toString(), jobApplication.status().toString(), jobApplication.dateOfSubmission().toString(), jobApplication.jobOpening().identity().toString(), jobApplicationRepository.numberOfApplicants(jobApplication)));
							}
							response = createResponse((byte) version, (byte) MessageCode.ACK.code(), builder.toString().getBytes(), null);
						}
					} else {
						response = createResponse((byte) version, (byte) MessageCode.ERR.code(), "Unknown request".getBytes(), null);
					}

					for (DataOutputStream sOut : cliListOut.values()) {
						sOut.write(response);
					}

					// close the client socket after sending the response
					cliListIn.remove(s);
					cliListOut.remove(s);
					s.close();
				} catch (SocketTimeoutException ignored) {
				} // no text line from client
			}
		}
	}

	private static byte[] createResponse(byte version, byte code, byte[] data1, byte[] data2) {
		ByteArrayOutputStream message = new ByteArrayOutputStream();
		try {
			message.write(version);
			message.write(code);

			if (data1 != null) {
				message.write(data1.length & 0xFF); // DATA1_LEN_L
				message.write((data1.length >> 8) & 0xFF); // DATA1_LEN_M
				message.write(data1);
			} else {
				message.write(0);
				message.write(0);
			}

			if (data2 != null) {
				message.write(data2.length & 0xFF); // DATA2_LEN_L
				message.write((data2.length >> 8) & 0xFF); // DATA2_LEN_M
				message.write(data2);
			} else {
				message.write(0);
				message.write(0);
			}

			message.write(0); // end of message (first zero)
			message.write(0); // end of message (second zero)
		} catch (IOException e) {
			e.printStackTrace();
		}

		return message.toByteArray();
	}
}