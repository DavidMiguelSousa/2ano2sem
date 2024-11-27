package eapli.base.clientusermanagement.domain.rank;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.*;

public class Rank implements ValueObject {
    private final RankStatus status;
    @OneToOne(optional = false)
    private final int rank;

    public Rank(int rank, RankStatus status) {
        this.rank = rank;
        this.status = status;
    }

    public Rank() {
        this.rank = 0;
        this.status = RankStatus.PENDING;
    }

    public int rank() {
        return this.rank;
    }

    public RankStatus status() {
        return this.status;
    }
}
