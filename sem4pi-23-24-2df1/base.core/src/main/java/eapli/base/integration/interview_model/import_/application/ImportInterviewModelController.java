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

package eapli.base.integration.interview_model.import_.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.integration.domain.FileExtension;
import eapli.base.integration.interview_model.import_.repositories.InterviewModelImporterPluginRepository;
import eapli.base.interviewmanagement.application.viadto.InterviewModelDTOParser;
import eapli.base.interviewmanagement.domain.InterviewModel;
import eapli.base.interviewmanagement.dto.InterviewModelDTO;
import eapli.base.interviewmanagement.repositories.InterviewModelRepository;
import eapli.framework.application.UseCaseController;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Paulo Gandra de Sousa 2024.04.30
 */
@UseCaseController

public class ImportInterviewModelController {
    private static final Logger LOGGER = LogManager.getLogger(ImportInterviewModelController.class);

//    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final InterviewModelImporterPluginRepository pluginRepo = PersistenceContext.repositories().interviewModelImporterPlugins();
    private final InterviewModelRepository interviewModelRepository = PersistenceContext.repositories().interviewModels();
    /**
     * Import an interview model from a file. It uses the file extension to determine which
     * import plugin to activate.
     * <p>
     * If there is an error parsing the file no interview model will be imported.
     *
     * @param filename
     * @return the imported interview model
     * @throws IOException
     */
    public InterviewModel importInterviewModel(final String filename) throws IOException {
//        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);

        // TODO refactor this method to move logic from the controller into a service class

        // prepare the result variable
        InterviewModel interviewModel;

        // get the content of the file to import
        InputStream content = null;
        try {
            // get the content of the file to import
            content = inputStreamFromResourceOrFile(filename);

            // get the right plugin for the file
            final var fileExt = FilenameUtils.getExtension(filename);
            final var plugin = pluginRepo.findByFileExtension(FileExtension.valueOf(fileExt)).orElseThrow(
                    () -> new IllegalStateException("There is no plugin associated with that file extension"));

            // load the plugin
            final var importer = plugin.buildImporter();

            // parse the content
            final var interviewModelToRegister = importer.importFrom(content);

            // do the import
            interviewModel = doTheImport(interviewModelToRegister);
        } finally {
            if (content != null) {
                try {
                    content.close();
                } catch (final IOException e) {
                    LOGGER.error("Error closing the file {}", filename);
                }
            }
        }

        return interviewModel;
    }

    private InterviewModel doTheImport(final InterviewModelDTO interviewModelToRegister) {
        // TODO begin transaction
        final var newInterviewModel = new InterviewModelDTOParser().valueOf(interviewModelToRegister);
        if (interviewModelRepository.containsOfIdentity(newInterviewModel.identity())) {
            LOGGER.info("Repository already contains the interview model {}", newInterviewModel.identity());
            return newInterviewModel;
        } else {
        LOGGER.info("Registering interview model {}", newInterviewModel.identity());
    }
        // TODO commit transaction
        return interviewModelRepository.save(newInterviewModel);
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
