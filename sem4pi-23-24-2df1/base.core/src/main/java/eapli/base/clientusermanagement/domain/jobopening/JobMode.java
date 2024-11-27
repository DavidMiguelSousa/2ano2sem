package eapli.base.clientusermanagement.domain.jobopening;

public enum JobMode {
    REMOTE("Remote"),
    HYBRID("Hybrid"),
    ONSITE("Onsite");

    private String mode;

    JobMode(String mode) {
    }

    public String mode() {
        return this.mode;
    }
}
