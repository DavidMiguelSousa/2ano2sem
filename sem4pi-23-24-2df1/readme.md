# Project Jobs4U

## 1. Description of the Project

* Jobs4U is a company specialized in talent acquisition. The company provides recruitment services for job positions in its clients.
* The aim of this project is to develop, in an exploratory way, a solution that allows automating the main activities of the company. Therefore, a minimum viable product should be developed in 3 months.
* The company’s clients are other companies or entities that need to recruit human resources.
* In response to requests from its clients, Jobs4U develops all activities that allow it to select a set of candidates for job offers (from its clients). At the end of the process, Jobs4U must deliver to its client an ordered list of candidates for each job offer.
* The final recruitment decision is the responsibility of the client.

## 2. Planning and Technical Documentation

[Planning and Technical Documentation](docs/README.md)

## 3. How to Build

* Make sure Maven is installed and on the PATH

* If using an Oracle database, you will need to change your maven settings for
downloading the Oracle drivers. see <https://blogs.oracle.com/dev2dev/entry/how_to_get_oracle_jdbc#settings> for more information.

Run script

    rebuild-all.bat

## 4. How to Execute Tests

Run script

    build-all.bat

The tests are included in the build process.

## 5. How to Run

* Make sure JRE 11 is installed and on the PATH

For example, if you want to run the backoffice app, run script

    run-backoffice.bat

or

    run-user.bat

## 6. How to Install/Deploy into Another Machine (or Virtual Machine)

For example, if you want to run the backoffice app, run script

    rebuild-all.bat

followed by

    run-backoffice.bat

or

    rebuild-all.sh

followed by

    run-backoffice.sh

## 7. How to Generate PlantUML Diagrams

To generate plantuml diagrams for documentation execute the script (for the moment, only for linux/unix/macos):

    ./generate-plantuml-diagrams.sh

## 8. Project structure

- base.app.backoffice.console
    - presentation using console
    - Main class
    - application properties in resource folder

- base.app.bootstrap
    - bootstrap data. should be ignored on a "real" instalation

- base.app.candidate.console
    - presentation using console
    - Main class
    - application properties in resource folder

- base.app.common.console
    - presentation using console
    - Main class
    - application properties in resource folder

- base.app.customer.console
    - presentation using console
    - Main class
    - application properties in resource folder

- base.app.other.console
    - presentation using console
    - Main class
    - application properties in resource folder

- base.app.user.console
    - presentation using console
    - Main class
    - application properties in resource folder

- base.bootstrappers
    - bootstrappers for the application
    - should be ignored on a "real" instalation

- base.core
    - use case controllers, model, and persistence

- base.infrastructure.application
    - application settings

- base.persistence.impl
    - persistence implementation

- jobs4u.util.ci
    - continuous integration scripts