package eapli.base.clientusermanagement.domain.jobopening;

public enum ContractType {
    FULL_TIME ("Full-Time"),
    PART_TIME ("Part-Time");

    private String contractType;

    ContractType(String contractType) {
    }

    public String contractType() {
        return this.contractType;
    }
}
