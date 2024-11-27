package eapli.base.clientusermanagement.domain.jobopening.dto;

import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;

public class JobRequirementsDTO {

    private Designation designation;
    private Description description;

    public JobRequirementsDTO() {
    }

    public JobRequirementsDTO(Designation designation, Description description) {
        this.designation = designation;
        this.description = description;
    }

    public Designation designation() {
        return designation;
    }

    public Description description() {
        return description;
    }

    public void updateDesignation(Designation designation) {
        this.designation = designation;
    }

    public void updateDescription(Description description) {
        this.description = description;
    }
}
