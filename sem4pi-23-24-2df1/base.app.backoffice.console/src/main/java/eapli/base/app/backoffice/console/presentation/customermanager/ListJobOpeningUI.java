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
package eapli.base.app.backoffice.console.presentation.customermanager;

import eapli.base.app.backoffice.console.presentation.printer.JobOpeningPrinter;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.usermanagement.application.AddJobOpeningController;
import eapli.base.usermanagement.application.ListJobOpeningController;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author losa
 */
@SuppressWarnings({ "squid:S106" })
public class ListJobOpeningUI extends AbstractListUI<JobOpening> {
    private final ListJobOpeningController theController = new ListJobOpeningController();

    @Override
    public String headline() {
        return "List Job Opening";
    }

    @Override
    protected String emptyMessage() {
        return "No data.";
    }

    @Override
    protected Iterable<JobOpening> elements() {
        return theController.allJobOpening();
    }

    @Override
    protected Visitor<JobOpening> elementPrinter() {
        return new JobOpeningPrinter();
    }

    @Override
    protected String elementName() {
        return "Job Opening";
    }

    @Override
    protected String listHeader() {
        return String.format("#%-15s%-30s%-15s%-12s%-45s%-20s%-15s%-30s", "Job Reference", "Title", "Contract Type", "Job Mode", "Address", "Customer", "Vacancies", "Description");
    }
}
