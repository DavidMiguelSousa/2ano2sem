# UC027 - As Customer Manager, I want to close phases of the process for a job opening

## 1. Context

> The Customer Manager will select a job opening.
> 
> The Customer Manager sees the current phase of the job opening.
> 
> The Customer Manager will close the phase of the job opening on the following conditions:
> - The phase is not closed.
> - The phase is status `In progress`.
> The phases have 3 states: 'In progress', 'Completed' and 'Pending'.
> 
> The Customer Manager sees, if all phases are complete, the status of the job opening is changed to "completed".

## 2. Requirements

* The requirements engineering related to this use case can be found in the [Requirements Engineering](01.requirements-engineering/README.md) file.

## 3. Analysis

* The analysis related to this use case can be found in the [Analysis](02.analysis/README.md) file.
 
## 4. Design

* The design related to this use case can be found in the [Design](03.design/README.md) file.

## 5. Implementation

* The implementation related to this use case can be found in the [Tests and Implementation](04.test-and-implementation/README.md) file.

## 6. Integration/Demonstration

* To run this Use Case, the Customer Manager must:
    - Run the `BackofficeApp` configuration.
    - Log in as system administrator with the following credentials:
        - Username: customermanager@jobs4u.com
        - Password: CustomerManager1!
    - Select the option `Settings`.
    - Select the desired option:
        - `1` to job opening management.
    - Then, select the option:
        - `4` to job opening phases management.
    - Then, select the option:
        - `2` to close job opening phases.
    - Fill in the required fields.

## 7. Observations

* The job opening management system is essential for the system to allow the customer manager to manage the job openings, as well as job applications, customers and candidates of the system.
* The job opening management system follows the EAPLI framework provided by the course.
* The job opening management system is implement ed in the backoffice system.