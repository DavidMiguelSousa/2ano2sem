package eapli.base.clientusermanagement.domain.jobapplication;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;

import java.io.File;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class JobApplicationAdditionalData implements ValueObject, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ElementCollection
    private List<String> files;

    public JobApplicationAdditionalData(List<String> files) {
        this.files = files;
    }

    protected JobApplicationAdditionalData() {
        // for ORM
        this.files = new ArrayList<>();
    }

    public List<String> files() {
        return this.files;
    }

    public void addFile(File file) {
        if (file == null) return;
        this.files.add(file.getName());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String file : files) builder.append(file).append(", ");
        if (!builder.isEmpty()) builder.delete(builder.length() - 2, builder.length());
        return builder.toString();
    }
}
