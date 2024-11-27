/*
 * Copyright (c) 2013-2024 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and
 * associated documentation files (the "Software"), to deal in the Software
 * without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish,
 * distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom
 * the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.infrastructure.smoketests;

import eapli.base.integration.interview_model.import_.application.ImportInterviewModelController;
import eapli.base.interviewmanagement.domain.InterviewModel;
import eapli.framework.actions.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 *
 * @author Paulo Gandra de Sousa 30/04/2024
 *
 */
public class InterviewModelImportThruPluginSmokeTester implements Action {
    private static final Logger LOGGER = LogManager.getLogger(InterviewModelImportThruPluginSmokeTester.class);

    private final ImportInterviewModelController importInterviewModelController = new ImportInterviewModelController();

    @Override
    public boolean execute() {
        // import one file in standard format
//        testImportFrom("bootstrap1.jar");
        // import one file in alternate format
        testImportFrom("interviewModelBootstrap2.txt");

        // nothing else to do
        return true;
    }

    private void testImportFrom(final String file) {
        try {
            final var r = importInterviewModelController.importInterviewModel(file);
            outputImportedContent(file, r);
        } catch (final IOException e) {
            LOGGER.error("Error while importing interview model from {}", file, e);
        }
    }

    private void outputImportedContent(final String filename, final InterviewModel interviewModel) {
        // output the imported content
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("-- IMPORT INTERVIEW MODEL FROM {} USING A PLUGIN --", filename);
            LOGGER.info(interviewModel.identity());
            LOGGER.info("-- END IMPORT --");
        }
    }
}
