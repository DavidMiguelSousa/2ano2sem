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
package eapli.base.usermanagement.application;

import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.counter.domain.TopWords;
import eapli.base.counter.domain.WordAggregator;
import eapli.base.counter.services.CountTopWordsService;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.services.CandidateManagementService;
import eapli.base.services.JobApplicationManagementService;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author losa
 */
@UseCaseController
public class ListUsersController{

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final UserManagementService userSvc = AuthzRegistry.userService();
    private final JobApplicationManagementService applicationSvc = new JobApplicationManagementService(PersistenceContext.repositories().jobApplications());
    private final CandidateManagementService candidateSvc = new CandidateManagementService(PersistenceContext.repositories().candidates());
    private final CountTopWordsService countTopWordsService = new CountTopWordsService();

    public Iterable<SystemUser> activeUsers() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN);

        List<SystemUser> users = new ArrayList<>();
        userSvc.activeUsers().forEach(users::add);

        users.sort(Comparator.comparing(u -> u.name().toString()));

        return users;
    }

    public Iterable<SystemUser> activeBackofficeUsers() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN);

        List<SystemUser> activeBackofficeUsers = new ArrayList<>();
        for (SystemUser u : activeUsers()) {
            for (Role role : u.roleTypes()) {
                if (BaseRoles.isCollaborator(role)) {
                    activeBackofficeUsers.add(u);
                    break;
                }
            }
        }
        return activeBackofficeUsers;
    }

    public Iterable<JobApplication> applicationByCandidate(Candidate candidate) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.CUSTOMER_MANAGER, BaseRoles.ADMIN);

        return applicationSvc.applicationsByCandidate(candidate);
    }

    public Optional<SystemUser> find(final Username u) {
        return userSvc.userOfIdentity(u);
    }

    public Iterable<Candidate> findCandidates() {
        return candidateSvc.allCandidates();
    }

    public TopWords countTopWords(JobApplication jobApplication) {
        return countTopWordsService.countTopWords(applicationSvc.files(jobApplication, jobApplication.jobOpening()));
    }

    public WordAggregator filesWhereWordIsPresent(String word) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);

        return countTopWordsService.filesWhereWordIsPresent(word);
    }
}
