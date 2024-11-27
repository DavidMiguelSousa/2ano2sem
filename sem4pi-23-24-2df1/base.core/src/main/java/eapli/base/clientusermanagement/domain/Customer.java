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
package eapli.base.clientusermanagement.domain;

import eapli.base.clientusermanagement.domain.address.Address;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;

import java.io.Serial;
import java.util.Calendar;

/**
 * A Customer User.
 * This class represents customer users. It follows a DDD approach where User
 * is the root entity of the Base User Aggregate and all of its properties
 * are instances of value objects. A Costumer User references a System User
 * This approach may seem a little more complex than just having String or
 * native type attributes but provides for real semantic of the domain and
 * follows the Single Responsibility Pattern
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
@Entity
@Table (name = "CUSTOMER")
public class Customer implements AggregateRoot<CustomerCode> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @EmbeddedId
    private CustomerCode customerCode;

    private Address address;

    /**
     * cascade = CascadeType.NONE as the systemUser is part of another aggregate
     */
    @OneToOne(optional = false)
    private SystemUser systemUser;

     Customer(final SystemUser systemUser, final CustomerCode customerCode, Address address) {
        Preconditions.noneNull(systemUser, customerCode, address);

        this.systemUser = systemUser;
        this.customerCode = customerCode;
        this.address = address;
        //this.jobOpeningList = new HashSet<>();
    }

    protected Customer() {
        // for ORM only
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    public CustomerCode customerCode() {
        return customerCode;
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    public SystemUser user() {
        return this.systemUser;
    }

    public boolean active() {
        return this.systemUser.isActive();
    }

    public void activate() {
        this.systemUser.activate();
    }

    public void deactivate() {
        this.systemUser.deactivate(Calendar.getInstance());
    }

    public Address address() {
        return this.address;
    }

    @Override
    public CustomerCode identity() {
        return this.customerCode;
    }

//    @Override
//    public boolean equals(final Object o) {
//        if (!this.customerCode.equals(((Customer) o).customerCode)) {
//            return false;
//        } else if (!this.address.equals(((Customer) o).address)) {
//            return false;
//        } else {
//            return super.equals(o);
//        }
//    }
}

