package eapli.base.clientusermanagement.domain.jobopening;

public enum Status {
    COMPLETED ("Completed"),
    IN_PROGRESS ("In progress"),
    PENDING ("Pending");

    private final String status;
    Status(String status) {
        this.status = status;
    }

    public String status() {
        return status;
    }

    public Status of(String status) {
        for (Status s : Status.values()) {
            if (s.status().equals(status)) {
                return s;
            }
        }
        return null;
    }
}
