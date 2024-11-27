# UC022 - As Customer Manager, I want to rank the candidates for a job opening

# 4. Tests

In this project, a Test-Driven Development (TDD) approach was used.

```java
import eapli.framework.general.domain.model.Description;
import org.springframework.cglib.core.Local;

import java.util.concurrent.Phaser;

class RankTest {
    private PasswordPolicy policy;

    private SystemUserBuilder systemUserBuilder;
    private CustomerBuilder customerBuilder;
    private JobApplicationBuilder jobApplicationBuilder;
    private JobOpeningBuilder jobOpeningBuilder;

    private SystemUser candidate1;
    private SystemUser candidate2;
    private SystemUser customerUser;
    private Customer customer;
    private JobApplication jobApplication1;
    private JobApplication jobApplication2;
    private JobOpening jobOpening;

    private Address address;
    private Map<Phase, PhaseDetails> phases;
    private InterviewModel interviewModel;
    private JobRequirements jobRequirements;
    private Map<Rank, JobApplication> ranks;

    @BeforeEach
    void setUp() {
        //builders
        systemUserBuilder = new SystemUserBuilder();
        customerBuilder = new CustomerBuilder();
        jobApplicationBuilder = new JobApplicationBuilder();
        jobOpeningBuilder = new JobOpeningBuilder();

        //needed parameters
        address = new Address("Street", "123", "City", "PostalCode");
        phases = new HashMap<>();
        phases.put(Phase.APPLICATION, new PhaseDetails(new DateInterval(), Status.COMPLETED));
        phases.put(Phase.SCREENING, new PhaseDetails(new DateInterval(), Status.COMPLETED));
        phases.put(Phase.INTERVIEW, new PhaseDetails(new DateInterval(), Status.COMPLETED));
        phases.put(Phase.ANALYSIS, new PhaseDetails(new DateInterval(), Status.COMPLETED));
        phases.put(Phase.RESULT, new PhaseDetails(new DateInterval(), Status.IN_PROGRESS));
        interviewModel = new InterviewModel("Interview Model", "Description");
        jobRequirements = new JobRequirements("Job Requirements", "Description");
        ranks = new HashMap<>();

        //users
        candidate1 = systemUserBuilder.with("candidate1@job4u.com",
                "Password1!", "candidate", "one", "candidate1@job4u.com").build();

        candidate2 = systemUserBuilder.with("candidate2@jobs4u.com",
                "Password2!", "candidate", "two", "candidate2@jobs4u.com").build();

        customerUser = systemUserBuilder.with("customer@jobs4.com",
                "Password3!", "customer", "customer", "customer@jobs4u.com").build();

        customer = customerBuilder.with(customerUser, CustomerCode.valueOf("CODE"), address).build();

        //job opening
        jobOpening = jobOpeningBuilder.with(JobReference.valueOf("JOB-REF"),
                Designation.valueOf("Job Opening"), ContractType.FULL_TIME,
                JobMode.HYBRID, address, customer, NumberOfVacancies.valueOf(1),
                Description.valueOf("description"), phases, interviewModel,
                jobRequirements, ranks).build();

        //job application
        jobApplication1 = jobApplicationBuilder.with(JobApplicationReference.valueOf("JOBAPPREF-1"),
                jobOpening, LocalData.now(), candidate1, CurriculumVitae.valueOf("designation1", "description1")).build();

        jobApplication2 = jobApplicationBuilder.with(JobApplicationReference.valueOf("JOBAPPREF-2"),
                jobOpening, LocalData.now(), candidate2, CurriculumVitae.valueOf("designation2", "description2")).build();
    }
}
```

**Test 1:** Check that the map is empty before ranking candidates.

```java
class RankTest {
    @Test
    void ensureMapIsEmptyBeforeRankingCandidates() {

        Map<Rank, JobApplication> expected = new HashMap<>();

        assertEquals(expected, jobOpening.ranks());
    }
}
```

**Test 2:** Check that the rank is empty for a candidate.

```java
class RankTest {
    @Test
    void ensureCandidateRankIsEmptyBeforeRanking() {
        Rank expected = new Rank();

        assertEquals(expected, jobApplication1.rank());
        assertEquals(expected, jobApplication2.rank());
    }
}
```

**Test 3:** Check that the map size is equal to the number of candidates.

```java
class RankTest {
    @Test
    void ensureMapSizeEqualsCandidatesNumber() {
        Rank rank1 = new Rank(0);
        Rank rank2 = new Rank();

        () -> jobOpening.ranks().put(rank1, jobApplication1);
        () -> jobOpening.ranks().put(rank2, jobApplication2);

        assertEquals(2, jobOpening.ranks().size());
    }
}
```

**Test 4:** Check that the number of ranked candidates is equal to the number of vacancies.

```java
class RankTest {
    @Test
    void ensureNumberOfRankedCandidatesEqualsNumberOfVacancies() {
        Integer expected = jobOpening.numberOfVacancies().value();
        Integer actual = 0;

        Rank rank1 = new Rank(0);
        Rank rank2 = new Rank();

        () -> jobOpening.ranks().put(rank1, jobApplication1);
        () -> jobOpening.ranks().put(rank2, jobApplication2);

        for (Map.Entry<Rank, JobApplication> entry : jobOpening.ranks().entrySet()) {
            if (entry.getKey().ranked()) {
                actual++;
            }
        }

        assertEquals(actual, expected);
    }
}
```

**Test 5:** Check that the rank is unique for each application.

```java
class RankTest {
    @Test
    void ensureOneUniqueRankForEachApplication() {
        Rank rank1 = new Rank(0);
        Rank rank2 = new Rank(1);

        () -> jobOpening.ranks().put(rank1, jobApplication1);
        () -> jobOpening.ranks().put(rank2, jobApplication2);

        assertNotEquals(jobApplication1.rank(), jobApplication2.rank());
        assertEquals(jobApplication1.rank(), rank1);

        () -> jobOpening.ranks().put(rank1, jobApplication2);

        assertNotEquals(jobApplication1.rank(), jobApplication2.rank());
        assertEquals(jobApplication2.rank(), rank1);
    }
}
```

# 5. Construction (Implementation)

To help achieve this Use Case's requirements, some EAPLI framework classes were used.

## Class RankJobApplicationsAction

```java

@Override
public boolean execute() {
    return new RankJobApplicationsUI().show();
}
```

## Class RankJobApplicationsUI

```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankJobApplicationsUI extends AbstractUI {
    private final RankJobApplicationsController controller = new RankJobApplicationsController();
  
    @Override
    public String headline() {
      return "Rank Job Opening's Applications";
    }
  
    protected boolean doShow() {
      Iterable<JobOpening> jobOpenings = controller.jobOpeningsWithAnalysisPhaseOn();
      List<JobOpening> jobOpeningsList = new ArrayList<>();
  
      jobOpenings.forEach(jobOpeningsList::add);
  
      if (jobOpeningsList.isEmpty()) {
        System.out.print("No job openings with analysis phase on available.\n");
        return false;
      }
  
      JobOpening jobOpening;
  
      do {
        SelectWidget<JobOpening> selector = new SelectWidget<>("Select Job Opening", jobOpeningsList, new JobOpeningPrinter());
        selector.show();
        jobOpening = selector.selectedElement();
  
        if (!jobOpening.applicationsRanked().isEmpty())
          System.out.print("""
                  ##Candidates were already ranked.##
  
                  """);
  
      } while (!jobOpening.applicationsRanked().isEmpty());
  
      int numberOfVacancies = jobOpening.numberOfVacancies().value();
  
      Iterable<JobApplication> jobApplications = controller.jobApplications(jobOpening);
      List<JobApplication> jobApplicationsList = new ArrayList<>();
  
      jobApplications.forEach(jobApplicationsList::add);
  
      Map<Rank, JobApplication> ranks = rankJobApplications(jobApplicationsList, numberOfVacancies);
  
      saveJobApplicationRanks(jobOpening, ranks, jobApplicationsList);
  
      return false;
    }
  
    private Map<Rank, JobApplication> rankJobApplications(List<JobApplication> jobApplications, int numberOfVacancies) {
      Map<Rank, JobApplication> ranks = new HashMap<>();
      int position;
      Rank rank;
  
      do {
        JobApplication jobApplication = selectJobApplication(jobApplications);
  
        boolean display = Console.readBoolean("\nDo you want to display the candidate's information? (Y/N): ");
  
        // if (display) displayJobApplicationInfo(jobApplication.information());
        if (display) displayJobApplicationInfo();
  
        position = Console.readInteger("Rank the candidate [0, 1, 2, ...]: ");
  
        rank = new Rank(position, RankStatus.RANKED);
  
        while (position < -1 || ranks.containsKey(rank)) {
            if (position < -1) System.out.println("\nInvalid rank. Please try again.\n");
            if (ranks.containsKey(rank)) System.out.println("\nRank already in use. Please try again.\n");
            position = Console.readInteger("Rank the candidate [0, 1, 2, ...]: ");
            rank = new Rank(position, RankStatus.RANKED);
        }
  
            ranks.put(rank, jobApplication);
  
            System.out.printf("Candidate %s ranked...\n", jobApplication.candidate().toString());
  
            jobApplications.remove(jobApplication);
  
    } while (ranks.size() < numberOfVacancies && !jobApplications.isEmpty());
  
        if (jobApplications.isEmpty()) {
            System.out.println("All candidates were ranked.\n");
        } else {
            System.out.println("Number of vacancies filled.\n");
        }
        
    return ranks;
}
  
    private JobApplication selectJobApplication(List<JobApplication> jobApplications) {
        int i = 0;
        
        for (JobApplication jobApplication : jobApplications) {
            System.out.printf("%d. %s\n", i++, jobApplication.candidate().toString());
        }
  
        int option = Console.readInteger("Choose an application: ");
  
        while (option < 0 || option >= jobApplications.size()) {
            System.out.print("Invalid option. Please try again.\n");
            option = Console.readInteger("Choose an application: ");
        }
        
        return jobApplications.get(option);
    }
  
  //    private void displayJobApplicationInfo(Information info) {
  //        int option;
  //
  //        do {
  //            if (jobApplication.interviewGrade.exists())
  //                printf("Interview Grade: %d\n", jobApplication.interviewGrade.grade());
  //
  //            for (int i = 1; i < 6; i++) {
  //                printf("%s. %s\n", i, info.get(i).toString());
  //            }
  //
  //            printf("0. Return 0\n");
  //
  //            option = Console.readInteger("Choose an option: ");
  //
  //            if (option < 1 || option > 6) {
  //                printf("Invalid option. Please try again.\n");
  //            } else {
  //                controller.printFileInformation(info.get(option));
  //            }
  //
  //            printf("\n");
  //
  //        } while (option != 0);
  //    }
  
    private void displayJobApplicationInfo() {
      System.out.println("Implement class Information\n");
    }
  
        private void saveJobApplicationRanks(JobOpening jobOpening, Map<Rank, JobApplication> ranks, List<JobApplication> jobApplications) {
        Rank notRanked = new Rank();
  
        for (JobApplication jobApplication : jobApplications) {
            if (!ranks.containsValue(jobApplication)) {
            controller.saveJobApplicationRank(jobApplication, notRanked);
            System.out.printf("Candidate %s rank saved...\n", jobApplication.candidate().toString());
            } else {
            for (Map.Entry<Rank, JobApplication> entry : ranks.entrySet()) {
                    if (entry.getValue().equals(jobApplication)) {
                        controller.updateJobOpeningRankList(jobApplication, jobOpening);
                        controller.saveJobApplicationRank(jobApplication, entry.getKey());
                        System.out.printf("Candidate %s rank saved...\n", jobApplication.candidate().toString());
                    }
                }
            }
        }
    }
}
```

## Class RankJobApplicationsController

```java
public class RankJobApplicationsController {
    private final JobOpeningManagementService jobOpeningManagementService = new JobOpeningManagementService();
    private final JobApplicationManagementService jobApplicationManagementService = new JobApplicationManagementService();

    public Iterable<JobOpening> jobOpeningsWithAnalysisPhaseOn() {
        return jobOpeningManagementService.jobOpeningsWithPhaseOn(Phase.ANALYSIS);
    }

    public Iterable<JobApplication> jobApplications(JobOpening jobOpening) {
        return jobApplicationManagementService.jobApplications(jobOpening);
    }

    public void saveJobApplicationRank(JobApplication jobApplication, Rank notRanked) {
        jobApplicationManagementService.saveJobApplicationRank(jobApplication, notRanked);
    }

    public void updateJobOpeningRankList(JobApplication jobApplication, JobOpening jobOpening) {
        jobOpeningManagementService.updateJobOpeningRanksList(jobApplication, jobOpening);
    }
}
```

## Class JobOpeningManagementService

```java
public class JobOpeningManagementService {
    private final JobOpeningRepository jobOpeningRepository = PersistenceContext.repositories().jobOpenings();

    public Iterable<JobOpening> jobOpeningsWithPhaseOn(Phase phase) {
        Iterable<JobOpening> allJobOpenings = jobOpeningRepo.findAll();
        List<JobOpening> jobOpenings = new ArrayList<>();

        for (JobOpening jobOpening : allJobOpenings) {
            if (jobOpening.phases().get(phase).status().equals(Status.IN_PROGRESS)) {
                jobOpenings.add(jobOpening);
            }
        }

        return jobOpenings;
    }

    public void updateJobOpeningRanksList(JobApplication jobApplication, JobOpening jobOpening) {
        List<JobApplication> list = jobOpening.applicationsRanked();
        list.add(jobApplication);

        list.sort(comparator);

        jobOpening.updateRanks(list);

        jobOpeningRepo.save(jobOpening);
    }
}
```

## Class JobApplicationManagementService

```java
public class JobApplicationManagementService {
    private final JobApplicationRepository jobApplicationRepository = PersistenceContext.repositories().jobApplications();

    public Iterable<JobApplication> jobApplications(JobOpening jobOpening) {
        return jobApplicationRepo.findJobApplicationsByJobOpening(jobOpening);
    }

    public void saveJobApplicationRank(JobApplication jobApplication, Rank rank) {
        jobApplication.defineRank(rank);

        jobApplicationRepo.save(jobApplication);
    }
}
```

## Class Rank

```java
public class Rank implements ValueObject {
    @Getter
    private final boolean ranked;
    @OneToOne(optional = false)
    private int rank;

    private Map<Integer, Integer> map = new HashMap<>();

    public Rank(int rank) {
        this.ranked = true;
        this.rank = rank;
    }

    public Rank() {
        this.ranked = false;
    }

    public int rank() {
        return rank;
    }

    public boolean ranked() {
        return ranked;
    }
}
```

### Class JobApplicationsRankComparator

```java
public class Rank implements ValueObject {
  @Getter
  private final boolean ranked; //false
  @OneToOne(optional = false)
  private int rank;

  public Rank(int rank) {
    this.ranked = true;
    this.rank = rank;
  }

  public Rank() {
    this.ranked = false;
  }

  public int rank() {
    return rank;
  }

  public boolean ranked() {
    return ranked;
  }
}
```

## Class JobOpening

```java
import java.sql.Array;import java.util.ArrayList;public class JobOpening implements AggregateRoot<JobReference> {

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<JobApplication> applicationsRanked;

    JobOpening(/* some parameteres */) {

        //some code
        this.ranks = new ArrayList<>();

        return this;
    }

    public void updateRanks(List<JobApplication> applicationsRanked) {
      this.applicationsRanked = applicationsRanked;
    }
  
    public List<JobApplication> applicationsRanked() {
      return this.applicationsRanked;
  }
}
```

## Class JobApplication

```java
public class JobApplication implements AggregateRoot<JobApplicationReference> {

    private Rank rank;

    JobApplication(JobApplicationReference jobApplicationReference, JobOpening jobOpening,
                   LocalDate applicationDate, SystemUser candidate, CurriculumVitae curriculumVitae,
                   Rank rank) {

        this.jobApplicationReference = jobApplicationReference;
        this.jobOpening = jobOpening;
        this.applicationDate = applicationDate;
        this.candidate = candidate;
        this.curriculumVitae = curriculumVitae;
        this.rank = new Rank();
    }

    public Rank rank() {
        return this.rank;
    }
}
```

# 6. Integration and Demonstration

* A new option in the backoffice users menu was added. However, to _rank candidates_, the authenticated user must have
  customer manager permissions.

# 7. Observations

* To run this Use Case, check the Integration and Demonstration section in this Use Case [Read Me](../README.md) file.