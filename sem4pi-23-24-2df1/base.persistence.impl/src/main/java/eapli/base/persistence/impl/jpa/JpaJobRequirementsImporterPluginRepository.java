package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.integration.domain.FileExtension;
import eapli.base.integration.job_requirements.import_.domain.JobRequirementsImporterPlugin;
import eapli.base.integration.job_requirements.import_.repositories.JobRequirementsImporterPluginRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

public class JpaJobRequirementsImporterPluginRepository
        extends JpaAutoTxRepository<JobRequirementsImporterPlugin, Designation, Designation>
        implements JobRequirementsImporterPluginRepository {

    public JpaJobRequirementsImporterPluginRepository(TransactionalContext autoTx) {
        super(autoTx, "name");
    }

    public JpaJobRequirementsImporterPluginRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "name");

    }

    @Override
    public Optional<JobRequirementsImporterPlugin> findByFileExtension(FileExtension fileExt) {
        final TypedQuery<JobRequirementsImporterPlugin> query = entityManager().createQuery(
                "SELECT d1 FROM JobRequirementsImporterPlugin d1 WHERE d1.fileExtension = :fileExt", JobRequirementsImporterPlugin.class);
        query.setParameter("fileExt", fileExt);

        return query.getResultList().stream().findFirst();
    }
}
