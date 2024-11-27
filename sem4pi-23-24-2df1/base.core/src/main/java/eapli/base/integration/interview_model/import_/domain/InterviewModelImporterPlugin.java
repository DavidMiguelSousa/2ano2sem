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
package eapli.base.integration.interview_model.import_.domain;

import eapli.base.integration.domain.FQClassName;
import eapli.base.integration.domain.FileExtension;
import eapli.base.integration.interview_model.import_.application.InterviewModelImporter;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/**
 * The configuration entry of an importer plugin.
 *
 * @author Paulo Gandra de Sousa 2024.04.30
 */
@Entity
//@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class InterviewModelImporterPlugin implements AggregateRoot<Designation>, Serializable {
    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = LogManager.getLogger(InterviewModelImporterPlugin.class);

    @Id
    @GeneratedValue
    private Long pk;

    @Version
    private Long version;

    //business id
    @Column(nullable = false)
    private final Designation name;

    private final Description description;

    private final FileExtension fileExtension;

    private final FQClassName className;

    protected InterviewModelImporterPlugin() {
        // for ORM only
        name = null;
        description = null;
        fileExtension = null;
        className = null;
    }

    public InterviewModelImporterPlugin(final String name2, final String description2, final String fileExtension2,
                              final String fqClassName) {
        Preconditions.noneNull(name2, description2, fileExtension2, fqClassName);

        name = Designation.valueOf(name2);
        description = Description.valueOf(description2);
        fileExtension = FileExtension.valueOf(fileExtension2);
        className = FQClassName.valueOf(fqClassName);
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Designation identity() {
        return name;
    }

    /**
     * Dynamically loads and builds the plugin importer.
     *
     * @return
     */
    public InterviewModelImporter buildImporter() {
        try {
            return (InterviewModelImporter) Class.forName(className.toString()).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | IllegalArgumentException
                 | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            LOGGER.error("Unable to dynamically load the Plugin", ex);
            throw new IllegalStateException("Unable to dynamically load the Plugin: " + className.toString(), ex);
        }
    }

    public FileExtension fileExtension() {
        return fileExtension;
    }

    public Designation name() {
        return name;
    }

    public Description description() {
        return description;
    }

    public FQClassName className() {
        return className;
    }
}