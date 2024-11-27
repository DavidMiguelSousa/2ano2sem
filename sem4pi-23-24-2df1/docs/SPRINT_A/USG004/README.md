# US G004

## 1. Context

Integrating a Continuous Integration (CI) server, like those offered through GitHub Actions, into your development
process offers many advantages that significantly improve the efficiency and reliability of software development
projects.
CI is a development practice where developers frequently merge their code changes into a central repository,
after which automated builds and tests are run.

## 2. Requirements

**US G004** As Project Manager, I want the team to set up a continuous integration server.

**Acceptance Criteria:**

- G004.1.: GitHub Actions/Workflows should be used.

**Dependencies/References:**

* This US is directly related to US G002.
  We first need a project management tool to be able to set up a
  continuous integration server, and USG001 because in that continuous integration server we need to follow the
  technical constraints and concerns of the project.

## 3. Analysis

* It was decided that the CI server to be used is GitHub Actions.

## 4. Design

* The CI server will be set up using GitHub Actions.

## 5. Implementation

* The workflow includes steps for building the project, running tests, and generating reports.
* The workflow is triggered on every push to the main branch and pull requests.

## 6. Integration/Demonstration

*Ensure the CI process aligns with the team’s development workflow, adjusting the pipeline as necessary.

## 7. Observations

* Efficiency: Monitor how the CI server affects the development cycle time and identify any bottlenecks.
* Reliability: Track the incidence of integration issues or bugs that are caught early by the CI process.
* Adoption: Gather feedback from the team on the CI process and tools to understand their impact on day-to-day
  development work.
* Improvements: Based on observations, continually refine the CI setup to better serve the project’s needs.