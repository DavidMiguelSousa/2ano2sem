package eapli.base.persistence.impl.inmemory;

import eapli.base.integration.domain.FileExtension;
import eapli.base.integration.interview_model.import_.domain.InterviewModelImporterPlugin;
import eapli.base.integration.interview_model.import_.repositories.InterviewModelImporterPluginRepository;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

public class InMemoryInterviewModelImporterPluginRepository
        extends InMemoryDomainRepository<InterviewModelImporterPlugin, Designation>
        implements InterviewModelImporterPluginRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Optional<InterviewModelImporterPlugin> findByFileExtension(FileExtension fileExt) {
        return matchOne(e -> e.fileExtension().equals(fileExt));
    }

}
