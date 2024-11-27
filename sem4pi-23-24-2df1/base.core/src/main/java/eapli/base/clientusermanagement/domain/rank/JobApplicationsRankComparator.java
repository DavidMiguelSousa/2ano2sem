package eapli.base.clientusermanagement.domain.rank;


import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;

import java.util.*;

public class JobApplicationsRankComparator implements Comparator<JobApplication> {
    @Override
    public int compare(JobApplication a1, JobApplication a2) {
        if (a1.rank() != null && a1.rank().rank() > -1 &&
                a2.rank() != null && a2.rank().rank() > -1)
            return Integer.compare(a1.rank().rank(), a2.rank().rank());
        else {
            System.out.print("Error: Ranking with null variables!.\n");
            if (a1.rank() == null)
                System.out.print("Error: %s has null rank " +
                        a1.identity().jobApplicationReference() + "\n");
            if (a1.rank().rank() < 0)
                System.out.print("Error: %s has negative rank " +
                        a1.identity().jobApplicationReference() + "\n");
            if (a2.rank() == null)
                System.out.print("Error: %s has null rank " +
                        a2.identity().jobApplicationReference() + "\n");
            if (a2.rank().rank() < 0)
                System.out.print("Error: %s has negative rank " +
                        a2.identity().jobApplicationReference() + "\n");

            return 0;
        }
    }
}
