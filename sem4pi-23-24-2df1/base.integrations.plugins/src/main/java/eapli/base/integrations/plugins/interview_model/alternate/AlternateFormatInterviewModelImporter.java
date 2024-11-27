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
package eapli.base.integrations.plugins.interview_model.alternate;

import eapli.base.integration.interview_model.import_.application.InterviewModelImporter;
import eapli.base.interviewmanagement.dto.InterviewModelDTO;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * An example plugin for importing dishes.
 *
 * @author Paulo Gandra de Sousa 2024.04.30
 */
public class AlternateFormatInterviewModelImporter implements InterviewModelImporter {

    public AlternateFormatInterviewModelImporter() {
    }

    @Override
    public InterviewModelDTO importFrom(final InputStream filename) throws IOException {
        InterviewModelDTO interviewModel = new InterviewModelDTO();
        BufferedReader reader = new BufferedReader(new InputStreamReader(filename));
        StringBuilder descriptionBuilder = new StringBuilder();

        String designation = "";
        String line;
        boolean firstLine = true;

        while ((line = reader.readLine()) != null) {
            if (firstLine) {
                designation = line.split("\"")[1];
                firstLine = false;
            } else {
                descriptionBuilder.append(line).append("\n");
            }
        }

        reader.close();

        interviewModel.setName(Designation.valueOf(designation));
        interviewModel.setDescription(Description.valueOf(descriptionBuilder.toString()));

        return interviewModel;
    }

}
