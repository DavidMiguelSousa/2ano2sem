package eapli.base.clientusermanagement.domain.jobapplication;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.File;
import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class CurriculumVitae implements ValueObject, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String cvFileName;

    public CurriculumVitae(File file) {
        this.cvFileName = file.exists() ? file.getName() : "";
    }

    public CurriculumVitae(String name) {
        this.cvFileName = name;
    }

    protected CurriculumVitae() {
        // for ORM
    }

    public String cvFileName() {
        return this.cvFileName;
    }

    public String updateCvFileName(File file) {
        return this.cvFileName = file.exists() ? file.getName() : "";
    }

    public String updateCvFileName(String name) {
        return this.cvFileName = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (this.getClass() != obj.getClass())) {
            return false;
        }

        final CurriculumVitae other = (CurriculumVitae) obj;
        return this.cvFileName.equals(other.cvFileName);
    }
}