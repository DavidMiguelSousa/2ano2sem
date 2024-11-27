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
package eapli.base.persistence.impl.inmemory;

import eapli.base.candidatemanagement.repositories.CandidateRepository;
import eapli.base.clientusermanagement.repositories.*;
import eapli.base.infrastructure.bootstrapers.BaseBootstrapper;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.integration.interview_model.import_.repositories.InterviewModelImporterPluginRepository;
import eapli.base.integration.job_requirements.import_.repositories.JobRequirementsImporterPluginRepository;
import eapli.base.interviewmanagement.repositories.InterviewModelRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.inmemory.InMemoryUserRepository;

/**
 *
 * Created by nuno on 20/03/16.
 */
public class InMemoryRepositoryFactory implements RepositoryFactory {

    static {
        // only needed because of the in memory persistence
        new BaseBootstrapper().execute();
    }

    @Override
    public UserRepository users(final TransactionalContext tx) {
        return new InMemoryUserRepository();
    }

    @Override
    public UserRepository users() {
        return users(null);
    }

    @Override
    public CustomerRepository customers(final TransactionalContext tx) {

        return new InMemoryCustomerRepository();
    }

    @Override
    public CustomerRepository customers() {
        return customers(null);
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext tx) {
        return new InMemorySignupRequestRepository();
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return signupRequests(null);
    }

    @Override
    public JobOpeningRepository jobOpenings(final TransactionalContext tx) {
        return new InMemoryJobOpeningRepository();
    }

    @Override
    public JobOpeningRepository jobOpenings() {
        return jobOpenings(null);
    }

    @Override
    public JobApplicationRepository jobApplications(final TransactionalContext tx) {
        return new InMemoryJobApplicationRepository();
    }

    @Override
    public JobApplicationRepository jobApplications() {
        return jobApplications(null);
    }

    @Override
    public InterviewModelImporterPluginRepository interviewModelImporterPlugins(final TransactionalContext tx) {
        return new InMemoryInterviewModelImporterPluginRepository();
    }

    @Override
    public InterviewModelImporterPluginRepository interviewModelImporterPlugins() {
        return interviewModelImporterPlugins(null);
    }

    @Override
    public InterviewModelRepository interviewModels(final TransactionalContext tx) {
        return new InMemoryInterviewModelRepository();
    }

    @Override
    public InterviewModelRepository interviewModels() {
        return interviewModels(null);
    }

    @Override
    public JobRequirementsRepository jobRequirements(final TransactionalContext tx) {
        return new InMemoryJobRequirementsRepository();
    }

    @Override
    public JobRequirementsRepository jobRequirements() {
        return jobRequirements(null);
    }

    @Override
    public JobRequirementsImporterPluginRepository jobRequirementsImporterPlugins(final TransactionalContext tx) {
        return new InMemoryJobRequirementsImporterPluginRepository();
    }

    @Override
    public JobRequirementsImporterPluginRepository jobRequirementsImporterPlugins() {
        return jobRequirementsImporterPlugins(null);
    }

    @Override
    public CandidateRepository candidates(final TransactionalContext tx) {
        return new InMemoryCandidateRepository();
    }

    @Override
    public CandidateRepository candidates() {
        return candidates(null);
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        // in memory does not support transactions...
        return null;
    }

}
