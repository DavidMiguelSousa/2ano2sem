# UC036 — As Customer Manager, I want to display all the data of an application.

## 3. Design - Use Case Realization

### 3.1. Rationale

| Interaction ID                                            | Question: Which class is responsible for...                 | Answer                           | Justification (with patterns)                                                                                                                  |
|:----------------------------------------------------------|:------------------------------------------------------------|:---------------------------------|:-----------------------------------------------------------------------------------------------------------------------------------------------|
| Step 1: Display all job openings                          | ... running the application's console?                      | DisplayApplicationDataUI         | Pure Fabrication: DisplayApplicationDataUI is a class created with the purpose of running the console in our application.                      |
|                                                           | ... controlling the flow of displaying application data?    | DisplayApplicationDataController | Controller: DisplayApplicationDataController is a class created with the purpose of controlling the flow of displaying application data.       |
|                                                           | ... ensuring the user is authenticated?                     | AuthorizationService             | Service: AuthorizationService is a class that provides the service of validating the user’s authentication.                                    |
|                                                           | ... fetching the authenticated user session?                | UserSession                      | Session: UserSession is a class responsible for managing the user's session and returning the authenticated user.                              |
|                                                           | ... fetching all job openings?                              | JobOpeningManagementService      | Service: JobOpeningManagementService is a class that provides the service of fetching all job openings.                                        |
|                                                           | ... interacting with the job opening repository?            | JobOpeningRepository             | Information Expert: JobOpeningRepository is a class created with the purpose of interacting with the job opening data in the database.         |
| Step 2: Display job applications for selected job opening | ... controlling the flow of displaying job applications?    | DisplayApplicationDataController | Controller: DisplayApplicationDataController is a class created with the purpose of controlling the flow of displaying job applications.       |
|                                                           | ... fetching job applications for the selected job opening? | JobApplicationManagementService  | Service: JobApplicationManagementService is a class that provides the service of fetching job applications for the selected job opening.       |
|                                                           | ... interacting with the job application repository?        | JobApplicationRepository         | Information Expert: JobApplicationRepository is a class created with the purpose of interacting with the job application data in the database. |
| Step 3: Display selected job application data             | ... running the application's console?                      | DisplayApplicationDataUI         | Pure Fabrication: DisplayApplicationDataUI is a class created with the purpose of running the console in our application.                      |
|                                                           | ... showing the details of the selected job application?    | DisplayApplicationDataUI         | Pure Fabrication: DisplayApplicationDataUI is a class created with the purpose of displaying the details of the selected job application.      |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* DisplayApplicationDataUI
* DisplayApplicationDataController
* AuthorizationService
* UserSession
* JobOpeningManagementService
* JobOpeningRepository
* JobApplicationManagementService
* JobApplicationRepository
* JobApplication
* JobOpening

Other software classes (i.e. Pure Fabrication) identified:

* DisplayApplicationDataUI

## 3.2. Sequence Diagram (SD)

![Sequence Diagram](svg/uc035-sequence-diagram.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/uc035-class-diagram.svg)