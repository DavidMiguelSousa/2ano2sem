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
package eapli.base.infrastructure.bootstrapers;

import eapli.base.integration.job_requirements.import_.application.RegisterJobRequirementsImporterPluginController;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Paulo Gandra de Sousa 2024.04.30
 */
public class JobRequirementsImporterPluginsBootstrapper implements Action {

    private static final Logger LOGGER = LogManager.getLogger(JobRequirementsImporterPluginsBootstrapper.class);

    RegisterJobRequirementsImporterPluginController controller = new RegisterJobRequirementsImporterPluginController();

    @Override
    public boolean execute() {
        // just to showcase two plugins
        register("Standard JR", "Standard job requirements format", TestDataConstants.PLUGIN_FILE_EXTENSION_STANDARD,
                "eapli.base.integrations.plugins.job_requirements.standard.StandardFormatJobRequirementsImporter");
        register("Alternate JR", "Alternate job requirements format", TestDataConstants.PLUGIN_FILE_EXTENSION_ALTERNATE,
                "eapli.base.integrations.plugins.job_requirements.alternate.AlternateFormatJobRequirementsImporter");
        return true;
    }

    private void register(final String name, final String description, final String fileExtension,
                          final String fqClassName) {
        try {
            controller.registerJobRequirementsImporterPlugin(name, description, fileExtension, fqClassName);
            LOGGER.info(name);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated plugin during bootstrap
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", name);
            LOGGER.trace("Assuming existing record", e);
        }
    }
}
