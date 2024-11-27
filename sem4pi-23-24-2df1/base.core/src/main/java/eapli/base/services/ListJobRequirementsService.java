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
package eapli.base.services;

import eapli.base.clientusermanagement.domain.jobopening.JobRequirements;
import eapli.base.clientusermanagement.repositories.JobRequirementsRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.interviewmanagement.domain.InterviewModel;
import eapli.base.interviewmanagement.repositories.InterviewModelRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

/**
 * An application service to avoid code duplication since getting the list of all dishes is needed
 * in several use cases.
 */
@ApplicationService
public class ListJobRequirementsService {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final JobRequirementsRepository jobRequirementsRepository = PersistenceContext.repositories().jobRequirements();

    public Iterable<JobRequirements> allJobRequirements() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN,
                BaseRoles.CUSTOMER_MANAGER);

        return jobRequirementsRepository.findAll();
    }

    public Iterable<JobRequirements> findJobRequirements(Designation id) {
        return jobRequirementsRepository.findByDesignation(id);
    }
}
