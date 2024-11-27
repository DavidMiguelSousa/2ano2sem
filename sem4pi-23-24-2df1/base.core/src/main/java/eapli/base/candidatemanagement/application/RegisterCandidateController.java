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
package eapli.base.candidatemanagement.application;

import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.services.CandidateManagementService;
import eapli.base.services.PasswordGenerator;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.*;
import eapli.framework.time.util.CurrentTimeCalendars;

import java.util.Calendar;
import java.util.Optional;
import java.util.Set;

/**
 *
 * Created by nuno on 21/03/16.
 */
@UseCaseController
public class RegisterCandidateController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final UserManagementService userSvc = AuthzRegistry.userService();
    private final CandidateManagementService candidateSvc = new CandidateManagementService(PersistenceContext.repositories().candidates());

    public SystemUser addUser(final String username, final String password, final String firstName,
                              final String lastName,
                              final String email, final Set<Role> roles, final Calendar createdOn) {

        return userSvc.registerNewUser(username, password, firstName, lastName, email, roles,
                createdOn);
    }

    public SystemUser addUser(final String password, final String firstName,
                              final String lastName, final String email, final Set<Role> roles) {
        SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        userBuilder.withPassword(new PlainTextEncoder().encode(password));

        return addUser(email, password, firstName, lastName, email, roles, CurrentTimeCalendars.now());
    }

    public Candidate registerCandidate(final SystemUser user) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER, BaseRoles.OPERATOR);
        return candidateSvc.registerCandidate(user);
    }

    public String generatePassword(String email) {
        return PasswordGenerator.generatePassword(email);
    }

    public Optional<Candidate> candidateWithUsername(String email) {
        return candidateSvc.candidateWithUsername(Username.valueOf(email));
    }
}
