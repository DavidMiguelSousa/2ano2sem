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
package eapli.base.app.backoffice.console.presentation.operator;

import eapli.base.app.backoffice.console.presentation.printer.CandidatePrinter;
import eapli.base.candidatemanagement.application.EnableCandidateController;
import eapli.base.candidatemanagement.domain.Candidate;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fernando
 */
@SuppressWarnings("squid:S106")
public class EnableCandidateUI extends AbstractUI {
    private static final Logger LOGGER = LoggerFactory.getLogger(EnableCandidateUI.class);

    private final EnableCandidateController theController = new EnableCandidateController();

    @Override
    protected boolean doShow() {
        final Iterable<Candidate> iterable = this.theController.inactiveCandidates();
        final List<Candidate> candidates = new ArrayList<>();
        iterable.forEach(candidates::add);
        if (!iterable.iterator().hasNext()) {
            System.out.println("There are no registered inactive candidates.");
        } else {
            SelectWidget<Candidate> selector = new SelectWidget<>("Select Candidate to Enable", candidates, new CandidatePrinter());
            selector.show();
            int option = selector.selectedOption();
            if (option == 0) {
                System.out.println("No candidate selected");
            } else {
                try {
                    this.theController.enableCandidate(selector.selectedElement());
                } catch (IntegrityViolationException | ConcurrencyException ex) {
                    LOGGER.error("Error performing the operation", ex);
                    System.out.println(
                            "Unfortunatelly there was an unexpected error in the application. Please try again and if the problem persists, contact your system admnistrator.");
                }
            }
        }
        return true;
    }

    @Override
    public String headline() {
        return "Enable Candidate";
    }
}
