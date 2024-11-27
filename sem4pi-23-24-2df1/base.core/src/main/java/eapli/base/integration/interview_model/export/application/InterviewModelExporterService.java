/*
 * Copyright (c) 2013-2024 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.integration.interview_model.export.application;

import eapli.base.interviewmanagement.domain.InterviewModel;
import eapli.framework.application.ApplicationService;
import eapli.framework.util.TemplateMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 *
 * @author Paulo Gandra de Sousa 28/04/2020
 *
 */
@ApplicationService
public class InterviewModelExporterService {
    private static final Logger logger = LogManager.getLogger(InterviewModelExporterService.class);

    /**
     * Exports dishes. This a "template method" working in conjunction with a Strategy.
     * If the {@link InterviewModelExporter} interface had just the export method we would be repeating the
     * logic of traversing the dish list in every implementation!
     *
     * <p>
     * Note that the exporter receives an Iterable and as such you should take attention to the
     * volume of data to export. If you need to export a large volume of data you should provide
     * some kind of cursor-based iterable and not a pure in-memory collection.
     *
     * @param interviewModels
     * @param filename
     * @param exporter
     * @throws IOException
     */
    @TemplateMethod
    public void export(final Iterable<InterviewModel> interviewModels, final String filename, final InterviewModelExporter exporter)
            throws IOException {
        try {
            exporter.begin(InterviewModelExporter.filePath + filename);

            boolean hasPrevious = false;
            for (final InterviewModel e : interviewModels) {
                if (hasPrevious) {
                    exporter.elementSeparator();
                }

                exporter.element(e);
                hasPrevious = true;
            }

            exporter.end();
        } catch (final IOException e) {
            logger.error("Problem exporting interview models", e);
            throw e;
        } finally {
            exporter.cleanup();
        }
    }
}
