/*
 * Copyright (c) 2013-2024 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eapli.base.integration.job_requirements.import_.application;

import eapli.base.clientusermanagement.domain.jobopening.JobRequirements;
import eapli.base.clientusermanagement.domain.jobopening.dto.JobRequirementsDTO;
import eapli.base.clientusermanagement.repositories.JobRequirementsRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.integration.domain.FileExtension;
import eapli.base.integration.job_requirements.import_.application.viadto.JobRequirementsDTOParser;
import eapli.base.integration.job_requirements.import_.repositories.JobRequirementsImporterPluginRepository;
import eapli.framework.application.UseCaseController;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * @author Paulo Gandra de Sousa 2024.04.30
 */
@UseCaseController
public class ImportJobRequirementsController {
    private static final Logger LOGGER = LogManager.getLogger(ImportJobRequirementsController.class);

//    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final JobRequirementsImporterPluginRepository pluginRepo = PersistenceContext.repositories().jobRequirementsImporterPlugins();
    private final JobRequirementsRepository jobRequirementsRepository = PersistenceContext.repositories().jobRequirements();

    /**
     * Import dishes from a file. It uses the file extension to determine which
     * import plugin to activate.
     * <p>
     * If there is an error parsing the file no dish will be imported.
     *
     * @param filename
     * @return the list of imported dishes
     * @throws IOException
     */
    public JobRequirements importJobRequirements(final String filename) throws IOException {
//        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.OPERATOR);

        // TODO refactor this method to move logic from the controller into a service class

        // prepare the result variable
        JobRequirements jobRequirements;

        // get the content of the file to import
        InputStream content = null;
        try {
            // get the content of the file to import
            content = inputStreamFromResourceOrFile(filename);

            // get the right plugin for the file
            final var fileExt = FilenameUtils.getExtension(filename);
            final var plugin = pluginRepo.findByFileExtension(FileExtension.valueOf(fileExt)).orElseThrow(
                    () -> new IllegalStateException("There is no plugin associated with that file extension"));

//             load the plugin
            final var importer = plugin.buildImporter();

//             parse the content
            final var jobRequirementsToRegister = importer.importFrom(content);

//             do the import
            jobRequirements = doTheImport(jobRequirementsToRegister);
        } finally {
            if (content != null) {
                try {
                    content.close();
                } catch (final IOException e) {
                    LOGGER.error("Error closing the file {}", filename);
                }
            }
        }

        return jobRequirements;
    }

    private JobRequirements doTheImport(final JobRequirementsDTO jobRequirementsToRegister) {
        // TODO begin transaction
        final JobRequirements newJobRequirements = new JobRequirementsDTOParser().valueOf(jobRequirementsToRegister);
        if (jobRequirementsRepository.containsOfIdentity(newJobRequirements.identity())) {
            LOGGER.info("Repository already contains job requirements specification {}", newJobRequirements.identity());
            return newJobRequirements;
        } else {
            LOGGER.info("Registering job requirements specification {}", newJobRequirements.identity());
        }
        // TODO commit transaction
        return jobRequirementsRepository.save(newJobRequirements);
    }

//    private InputStream inputStreamFromResourceOrFile(final String filename) throws FileNotFoundException {
//        InputStream content;
//        final var classLoader = this.getClass().getClassLoader();
//        final var resource = classLoader.getResource(filename);
//        if (resource != null) {
//            final var file = new File(resource.getFile());
//            content = new FileInputStream(file);
//        } else {
//            content = new FileInputStream(filename);
//        }
//        return content;
//    }
    private InputStream inputStreamFromResourceOrFile(final String filename) throws IOException {
        InputStream content;
        final var classLoader = this.getClass().getClassLoader();
        final var resource = classLoader.getResource(filename);
        if (resource != null) {
            content = resource.openStream();
        } else {
            content = new FileInputStream(filename);
        }
        return content;
    }
}
