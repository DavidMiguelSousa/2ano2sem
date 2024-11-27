package eapli.base.clientusermanagement.domain.rank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RankTest {
    @Test
    void ensureRankConstructorWorksForPending() {
        Rank expected = new Rank();

        assertEquals(0, expected.rank());
        assertEquals(RankStatus.PENDING, expected.status());
    }

    @Test
    void ensureRankConstructorWorksForRanked() {
        Rank expected = new Rank(1, RankStatus.RANKED);

        assertEquals(1, expected.rank());
        assertEquals(RankStatus.RANKED, expected.status());
    }

    @Test
    void ensureRankConstructorWorksForUnranked() {
        Rank expected = new Rank(-1, RankStatus.UNRANKED);

        assertEquals(-1, expected.rank());
        assertEquals(RankStatus.UNRANKED, expected.status());
    }
}