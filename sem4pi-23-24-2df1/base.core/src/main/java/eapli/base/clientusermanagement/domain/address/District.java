package eapli.base.clientusermanagement.domain.address;

public enum District {
    AVEIRO("Aveiro"),
    ACORES("Açores"),
    BEJA("Beja"),
    BRAGA("Braga"),
    BRAGANCA("Bragança"),
    CASTELO_BRANCO("Castelo Branco"),
    COIMBRA("Coimbra"),
    EVORA("Évora"),
    FARO("Faro"),
    GUARDA("Guarda"),
    LEIRIA("Leiria"),
    LISBOA("Lisboa"),
    MADEIRA("Madeira"),
    PORTALEGRE("Portalegre"),
    PORTO("Porto"),
    SANTAREM("Santarém"),
    SETUBAL("Setúbal"),
    VIANA_DO_CASTELO("Viana do Castelo"),
    VILA_REAL("Vila Real"),
    VISEU("Viseu");

    private final String district;

    District(String district) {
        this.district = district;
    }

    public String district() {
        return district;
    }

    @Override
    public String toString() {
        return district;
    }
}
