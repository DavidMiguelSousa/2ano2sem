package eapli.base.clientusermanagement.domain.jobopening;

public enum Phase {
    APPLICATION("Application"),
    SCREENING("Screening"),
    INTERVIEW("Interview"),
    ANALYSIS("Analysis"),
    RESULT("Result");

    private final String phase;

    Phase(String phase) {
        this.phase = phase;
    }

    public String phase() {
        return this.phase;
    }

    public static Phase[] orderedPhases() {
        return new Phase[]{APPLICATION, SCREENING, INTERVIEW, ANALYSIS, RESULT};
    }

    public Phase of(String phase) {
        for (Phase p : Phase.values()) {
            if (p.phase().equals(phase)) {
                return p;
            }
        }
        return null;
    }

    public Phase nextPhase() {
        Phase[] phases = Phase.values();
        int currentIndex = this.ordinal();
        if (currentIndex < phases.length - 1) {
            return phases[currentIndex + 1];
        } else {
            return null;
        }
    }



}
