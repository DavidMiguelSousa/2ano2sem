# UC031 - As Customer Manager, I want to upload a text file with the candidate responses for an interview

## 1. Context

> The Customer Manager will select a job opening and the respective applications according to the chosen job opening.
>
> This manager is responsible for administering the interviews of candidates for the available job offers.
> 
> It is essential that the Customer Manager has the capability to import a text file containing the candidates' responses during the interviews.

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
        - `5` to job opening interview management.
    - Then, select the option:
        - `3` to import Interview file with candidate responses
    - Fill in the required fields.

## 7. Observations

* The job opening interview management system is essential for enabling the customer manager to oversee the system's interviews.
* This system follows the EAPLI framework provided by the course.
* It is implemented within the backoffice system.