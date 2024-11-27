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

import eapli.base.clientusermanagement.domain.address.Address;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

/**
 * A factory for User entities.
 *
 * This class demonstrates the use of the factory (DDD) pattern using a fluent
 * interface. it acts as a Builder (GoF).
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public class CustomerBuilder implements DomainFactory<Customer> {

    private SystemUser systemUser;
    private CustomerCode customerCode;
    private Address address;

    public CustomerBuilder with(final SystemUser systemUser, final CustomerCode customerCode, final Address address) {
        this.systemUser = systemUser;
        this.customerCode = customerCode;
        this.address = address;

        return this;
    }

    public CustomerBuilder withSystemUser(final SystemUser systemUser) {
        this.systemUser = systemUser;
        return this;
    }

    public CustomerBuilder withCustomerCode(final CustomerCode customerCode) {
        this.customerCode = customerCode;
        return this;
    }

    public CustomerBuilder withCustomerCode(final String customerCode) {
        this.customerCode = new CustomerCode(customerCode);
        return this;
    }

    public CustomerBuilder withAddress(final Address address) {
        this.address = address;
        return this;
    }

    @Override
    public Customer build() {
        // since the factory knows that all the parts are needed it could throw
        // an exception. however, we will leave that to the constructor
        return new Customer(this.systemUser, this.customerCode, this.address);
    }
}
