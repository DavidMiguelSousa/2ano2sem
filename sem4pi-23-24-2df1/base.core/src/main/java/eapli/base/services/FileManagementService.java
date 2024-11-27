package eapli.base.services;

import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.clientusermanagement.domain.ApprovalStatus;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplicationBuilder;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.domain.jobopening.JobReference;
import eapli.base.clientusermanagement.repositories.JobApplicationRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.application.ApplicationService;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationService
public class FileManagementService {

    private final JobApplicationRepository repo = PersistenceContext.repositories().jobApplications();

    public boolean existsCandidateFiles(Path sharedFolder, JobReference jobReference, Username candidateUsername) {
        return Files.exists(sharedFolder.resolve(jobReference.reference()).resolve(candidateUsername.toString()));
    }

    public File[] allApplicationFiles(Path sharedFolder, Candidate candidate, JobOpening jobOpening) {
        if (existsCandidateFiles(sharedFolder, jobOpening.jobReference(), candidate.user().username())) {
            return sharedFolder.resolve(jobOpening.jobReference().reference()).resolve(candidate.user().username().toString()).toFile().listFiles();
        }
        return new File[0];
    }

    public File findCandidateFile(Path sharedFolder, JobReference jobReference, Username candidateUsername, String fileNameRegex) {
        Path candidateDirectory = sharedFolder.resolve(jobReference.reference()).resolve(candidateUsername.toString());
        Pattern pattern = Pattern.compile(fileNameRegex);

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(candidateDirectory)) {
            for (Path filePath : stream) {
                if (Files.isRegularFile(filePath)) {
                    Matcher matcher = pattern.matcher(filePath.getFileName().toString());
                    if (matcher.matches()) {
                        return filePath.toFile();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    private String[] readCandidateDataFile(Path path) throws IOException {
        return Files.readString(path).split("\n");
    }

    public JobApplication registerApplication(Path sharedFolder, Candidate candidate, JobOpening jobOpening) throws IOException {

        JobApplicationBuilder builder = new JobApplicationBuilder();
        builder = builder.withJobOpening(jobOpening).
                withCandidate(candidate).
                withJobApplicationReference(jobOpening.jobReference()).
                withJobApplicationStatus(ApprovalStatus.PENDING);

        JobApplication jobApplication = builder.build();

        File[] files = sharedFolder.resolve(jobOpening.jobReference().reference()).resolve(candidate.user().username().toString()).toFile().listFiles();
        if (files != null) {
            for (File file : files) {
                String[] fileName = file.getName().split("-");
                if (fileName.length == 3 && fileName[1].equalsIgnoreCase("candidate") && fileName[2].equalsIgnoreCase("data.txt")) {
                    jobApplication.addPhoneNumber(readCandidateDataFile(file.toPath())[3]);
                } else if (fileName.length == 2 && fileName[1].equalsIgnoreCase("cv.txt")) {
                    jobApplication.addCV(file);
                } else if (fileName.length > 1) {
                    jobApplication.addAdditionalFile(file);
                }
            }
        }

        if (repo.containsOfIdentity(jobApplication.identity())) repo.removeOfIdentity(jobApplication.identity());
        return repo.save(jobApplication);
    }

    public SystemUser candidateData(Path sharedFolder, Username candidateEmail, JobReference jobReference) {
        SystemUserBuilder builder = UserBuilderHelper.builder();
        File candidateDataFile = findCandidateFile(sharedFolder, jobReference, candidateEmail, "^\\d+-candidate-data\\.txt");
        try {
            String[] lines = readCandidateDataFile(candidateDataFile.toPath());
            String[] nameLine = lines[2].split(" ");
            EmailAddress email = EmailAddress.valueOf(candidateEmail.toString());
            Name name = Name.valueOf(nameLine[0], nameLine[1]);
            Password password = Password.encodedAndValid(PasswordGenerator.generatePassword(candidateEmail.toString()), new BasePasswordPolicy(), new PlainTextEncoder()).get();
            builder = builder.with(email, password, name).withRoles(BaseRoles.CANDIDATE);
            if (!candidateEmail.toString().equalsIgnoreCase(lines[1])) {
                throw new IllegalArgumentException("Candidate data file read email does not match the candidate's username");
            }
        } catch (IOException ex) {
            throw new IllegalArgumentException("Error reading candidate data file");
        }
        return builder.build();
    }
}
