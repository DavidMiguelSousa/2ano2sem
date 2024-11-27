# UC018 - As Customer Manager, I want to select the job requirements of a job opening 

## 3. Design - User Story Realization

### 3.1. Rationale

|                      Interaction ID                      |          Question: Which class is responsible for...          |             Answer              |                                                    Justification (with patterns)                                                     |
|:--------------------------------------------------------:|:-------------------------------------------------------------:|:-------------------------------:|:------------------------------------------------------------------------------------------------------------------------------------:|
|           Step 1: Login (as Customer Manager)            |            ... running the application's console?             |         BaseApplication         |            Pure Fabrication: BaseApplication is a class created with the purpose of running a console in our application.            |
|                                                          |        ... showing the backoffice console to do login?        |         BaseBackoffice          |               Pure Fabrication: BaseBackoffice is a class created with the purpose of showing the backoffice console.                |
|                                                          |                  ... showing the login menu?                  |             LoginUI             |                       Pure Fabrication: LoginUI is a class created with the purpose of showing the login menu.                       |
|       Step 2: Validate login (as Customer Manager)       |             ... validating the login credentials?             |        CredentialHandler        |                   Service: CredentialHandler is a class that provides the service of validating login credentials.                   |
|                                                          |          ... showing the login (in)success message?           |             LoginUI             |                 Pure Fabrication: LoginUI is a class created with the purpose of showing the login success message.                  |
| Step 3: Choosing the job requirements for a job opening. |    ... giving customer manager permissions to logged user?    |        CredentialHandler        |                   Service: CredentialHandler is a class that provides the service of validating login credentials.                   |
|              Step 4: List with job openings              |                ... interacting with the actor?                |     SelectJobRequirementsUI     |   Pure Fabrication: SelectInterviewModelUI is a class created with the purpose of displaying the console for the job opening list.   |
|                                                          |                                                               | SelectJobRequirementsController |                                                                                                                                      |
|                                                          |                                                               |   JobOpeningManagementService   |                                                                                                                                      |
|      Step 4: Enter the job opening reference number      |            ... showing the user creation console?             |     SelectJobRequirementsUI     |          Pure Fabrication: SelectInterviewModelUI is a class created with the purpose of showing the user creation console           |
|                                                          | ... ensure the new customer is valid by checking its details? |        SystemUserBuilder        |                         Factor: SystemUserBuilder is a class that provides the service of validating users.                          | 
|                                                          |                ... keep the customer's details                |           SystemUser            |                                         Information Expert: SystemUser has its own details.                                          |
|            Step 5: List with job requirements            |                ... interacting with the actor?                |     SelectJobRequirementsUI     |                                                                                                                                      |
|                                                          |                                                               | SelectJobRequirementsController |                                                                                                                                      |
|                                                          |                                                               |    ListJobRequirementsService   |                                                                                                                                      |
|              Step 5: Enter job requirements              |            ... showing the user creation console?             |     SelectJobRequirementsUI     |                 Pure Fabrication: AddUserUI is a class created with the purpose of showing the user creation console                 |
|                                                          | ... ensure the new customer is valid by checking its details? |        SystemUserBuilder        |                         Factor: SystemUserBuilder is a class that provides the service of validating users.                          | 
|                                                          |                ... keep the customer's details                |           SystemUser            |                                         Information Expert: SystemUser has its own details.                                          |
| Step 7: Show job requirements was successfully selected  |        ... show the user creation (in)success message?        |            AddUserUI            |            Pure Fabrication: AddUserUI is a class created with the purpose of showing the user creation success message.             |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* BaseApplication
* BaseBackoffice
* CredentialHandler
* AuthorizationService
* SystemUserBuilder
* SystemUser
* JobOpeningManagementService
* ListJobRequirementsService

Other software classes (i.e. Pure Fabrication) identified:

* LoginUI
* SelectJobRequirementsUI
* SelectJobRequirementsController
* UserRepository

## 3.2. Sequence Diagram (SD)

![uc018-sequence-diagram-full.svg](svg%2Fuc018-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![uc018-class-diagram.svg](svg%2Fuc018-class-diagram.svg)