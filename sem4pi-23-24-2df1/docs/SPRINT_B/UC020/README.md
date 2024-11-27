# UC020 - As Customer Manager, I want to generate and export a template text file with candidate answers during an interview.

## 1. Context

> A template text file should be produced to provide a starting point for the customer manager to register the candidate's answers during an interview.
> The customer manager should be able to generate this template for a specific interview model (set of questions used to get the candidate's answers).
> The interview's answers will be essential to evaluate the candidate's answers with a grade, which will be used to rank candidates.

## 2. Requirements

* The requirements engineering related to this use case can be found in the [Requirements Engineering](01.requirements-engineering/README.md) file.

## 3. Analysis

* The analysis related to this use case can be found in the [Analysis](02.analysis/README.md) file.
 
## 4. Design

* The design related to this use case can be found in the [Design](03.design/README.md) file.

## 5. Implementation

* The implementation related to this use case can be found in the [Tests and Implementation](04.test-and-implementation/README.md) file.

## 6. Integration/Demonstration

* To run this Use Case, the customer manager must:
    - Run the `BackofficeApp` configuration.
    - Log in as customer manager with the following credentials:
        - Username: customermanager@jobs4u.com
        - Password: CustomerManager1!
    - Select the option `Settings`.
    - Select the desired option:
        - `4` to generate and export a template text file with candidate answers during an interview.
    - Follow the instructions on the screen.

## 7. Observations

* The customer manager must have an interview model created (UC017 and UC019) to generate the template text file with candidate answers during an interview.