package eapli.base.candidatemanagement.domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class CandidateBuilder implements DomainFactory<Candidate> {

    private SystemUser user;

    public CandidateBuilder with(final SystemUser user) {
        this.user = user;
        return this;
    }

    @Override
    public Candidate build() {
        return new Candidate(user);
    }
}
