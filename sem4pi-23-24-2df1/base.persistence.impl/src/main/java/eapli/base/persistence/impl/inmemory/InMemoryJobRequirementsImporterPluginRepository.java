package eapli.base.persistence.impl.inmemory;

import eapli.base.integration.domain.FileExtension;
import eapli.base.integration.job_requirements.import_.domain.JobRequirementsImporterPlugin;
import eapli.base.integration.job_requirements.import_.repositories.JobRequirementsImporterPluginRepository;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

public class InMemoryJobRequirementsImporterPluginRepository
        extends InMemoryDomainRepository<JobRequirementsImporterPlugin, Designation>
        implements JobRequirementsImporterPluginRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Optional<JobRequirementsImporterPlugin> findByFileExtension(FileExtension fileExt) {
        return matchOne(e -> e.fileExtension().equals(fileExt));
    }

}
