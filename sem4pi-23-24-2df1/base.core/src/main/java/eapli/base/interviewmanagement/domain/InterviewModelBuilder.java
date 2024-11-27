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
package eapli.base.interviewmanagement.domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;

/**
 * A Dish builder. Since there are two optional components of Dish, i.e.,
 * nutricional info and allergens, we are using a builder to simplify the class
 * and avoid overloading constructors or too much conditional logic on the
 * constructor.
 *
 * @author Paulo Gandra de Sousa 2021/05/04
 */
public class InterviewModelBuilder implements DomainFactory<InterviewModel> {

    private InterviewModel theModel;

    private Designation name;

    private Description description;

    public InterviewModelBuilder withName(final Designation name) {
        this.name = name;
        return this;
    }

    public InterviewModelBuilder withDescription(final Description description) {
        this.description = description;
        return this;
    }

    private InterviewModel buildOrThrow() {
        if (theModel != null) {
            return theModel;
        }
        if (name != null) {
            if (description != null) theModel = new InterviewModel(name, description);
            else theModel = new InterviewModel(name);
            return theModel;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public InterviewModel build() {
        final InterviewModel ret = buildOrThrow();
        // make sure we will create a new instance if someone reuses this builder and do
        // not change
        // the previously built interview model.
        theModel = null;
        return ret;
    }
}