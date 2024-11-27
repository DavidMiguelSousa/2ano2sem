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
package eapli.base.clientusermanagement.domain;

import jakarta.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
@Embeddable
public class CustomerCode implements ValueObject, Comparable<CustomerCode> {

    private static final long serialVersionUID = 1L;

    private String code;

    public CustomerCode(final String customerCode) {
        if (StringPredicates.isNullOrEmpty(customerCode)) {
            throw new IllegalArgumentException(
                    "Mecanographic Number should neither be null nor empty");
        }
        // TODO validate invariants, i.e., customerCode regex
        // Só pode conter uma palavra toda junta e, se não estiver em maiúsculas, deve passar a estar
        this.code = customerCode.trim().toUpperCase();
    }

    protected CustomerCode() {
        // for ORM
    }

    public static CustomerCode valueOf(final String customerCode) {
        return new CustomerCode(customerCode);
    }

    public String code() {
        return this.code;
    }
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomerCode)) {
            return false;
        }

        final CustomerCode that = (CustomerCode) o;
        return this.code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return this.code.hashCode();
    }

    @Override
    public String toString() {
        return this.code;
    }

    @Override
    public int compareTo(final CustomerCode arg0) {
        return code.compareTo(arg0.code);
    }
}
