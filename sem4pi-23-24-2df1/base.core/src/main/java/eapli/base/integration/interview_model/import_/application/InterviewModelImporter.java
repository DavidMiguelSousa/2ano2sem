package eapli.base.integration.interview_model.import_.application;

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

import eapli.base.interviewmanagement.dto.InterviewModelDTO;

import java.io.IOException;
import java.io.InputStream;

/**
 * The interface for all dish importer plugins.
 * <p>
 * The plugin does not actually perform the import into the domain. Instead it
 * reads from an input stream and returns a list of DTOs. That way, the plugin
 * is not coupled to actually reading from a physical file on disk and inserting
 * the data in the database. This follows the Single Responsibility Principle
 * and the general principle of "small tools that can be chained together".
 * Everything related to how the input stream is obtained (e.g., from a physical
 * disk file or from a text box in the UI) is handled outside of the plugin in
 * the application or presentation layer. Likewise, everything related to what
 * should be done with the data read from the stream is outside of the plugin's
 * scope.
 *
 * @author Paulo Gandra de Sousa 2024.04.30
 */
public interface InterviewModelImporter {

    /**
     * Imports dishes from a stream (provably read from a file) according to the
     * format.
     *
     * @param filename
     * @return
     * @throws IOException
     */
    InterviewModelDTO importFrom(InputStream filename) throws IOException;

}