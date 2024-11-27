package eapli.base.clientusermanagement.domain.jobopening;

import java.util.Comparator;

public class JobOpeningComparator implements Comparator<JobOpening> {
    @Override
    public int compare(JobOpening o1, JobOpening o2) {
        return o1.identity().compareTo(o2.identity());
    }
}
