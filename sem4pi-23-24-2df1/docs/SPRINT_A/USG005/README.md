# US G005

## 1. Context

This is the first time this task is being developed in this project.

## 2. Requirements

**US G005** As Project Manager, I want the team to add to the project the necessary scripts, so that build/executions/deployments/... can be executed effortlessly.

**Acceptance Criteria:**

- G005.1. The project must be read, reviewed and understood by the team.
- G005.2. The project must have a README file with the necessary instructions to build, execute, deploy, ... it.
- G005.3. The project must have a script to (re)build it.
- G005.4. The project must have a script to execute its different modules.

**Dependencies/References:**

* [US G001](../USG001/README)

## 3. Analysis

* The scope of this project is briefly described in the Project Description section in the following file: [Project Description](../../../readme.md).

## 4. Design

The design of this User Story is described, mainly, by the project's Domain Model.

### 4.1. Realization

The realization of this User Story is based on the following tasks:

- **Task 1:** Read, review and understand the project.
- **Task 2:** Create a README file with the necessary instructions to build, execute, deploy, ... the project.
- **Task 3:** Create a script to (re)build the project.
- **Task 4:** Create a script to execute the project's different modules.
- **Task 5:** Update the project's README file with the necessary instructions to build, execute, deploy, ... it.

### 4.2. Domain Model

[Domain Model](../USG006/DOMAIN_MODEL/domain_model.jpg).

### 4.3. Applied Patterns

The patterns we applied to this User Story (and project) are:

- **Pattern 1**: *DDD* - Domain Driven Design.
- **Pattern 2**: *TDD* - Test Driven Development.

### 4.4. Tests

* This User Story has no specific tests related to it.
* To verify the project structure is correct, run script
    
    __build-all.bat__ or __build-all.sh__

## 5. Implementation

* The necessary scripts to build and execute the project are available in the project root folder (e.g.: __build-all.bat__ and __build-all.sh__).

## 6. Integration/Demonstration

* Running scripts __build-all.bat__ or __build-all.sh__ will build the project and run the tests.

## 7. Observations

* In Sprint A, the project's structure was defined, but it will be improved in the next sprints.
* The project's structure is described in the following file: [Project Description](../../../readme.md).