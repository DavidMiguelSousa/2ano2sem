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
package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.integration.domain.FileExtension;
import eapli.base.integration.interview_model.import_.domain.InterviewModelImporterPlugin;
import eapli.base.integration.interview_model.import_.repositories.InterviewModelImporterPluginRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
class JpaInterviewModelImporterPluginRepository extends JpaAutoTxRepository<InterviewModelImporterPlugin, Designation, Designation>
        implements InterviewModelImporterPluginRepository {

    public JpaInterviewModelImporterPluginRepository(TransactionalContext autoTx) {
        super(autoTx, "name");
    }

    public JpaInterviewModelImporterPluginRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "name");
    }

    @Override
    public Optional<InterviewModelImporterPlugin> findByFileExtension(FileExtension fileExt) {
        final TypedQuery<InterviewModelImporterPlugin> query = entityManager().createQuery(
                "SELECT d1 FROM InterviewModelImporterPlugin d1 WHERE d1.fileExtension = :fileExt", InterviewModelImporterPlugin.class);
        query.setParameter("fileExt", fileExt);

        return query.getResultList().stream().findFirst();
    }
}
