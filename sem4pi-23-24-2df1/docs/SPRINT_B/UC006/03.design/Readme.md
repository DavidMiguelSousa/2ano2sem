# UC006 - As Customer Manager, I want to register a job opening.

## 3. Design - User Story Realization 

### 3.1. Rationale


|                                    Interaction ID                                    |                      Question: Which class is responsible for...                       |           Answer            |                                               Justification (with patterns)                                               |
|:------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------:|:---------------------------:|:-------------------------------------------------------------------------------------------------------------------------:|
|                         Step 1: Login (as Customer Manager)                          |                         ... running the application's console?                         |       BaseApplication       |      Pure Fabrication: BaseApplication is a class created with the purpose of running a console in our application.       |
|                                                                                      |                    ... showing the backoffice console to do login?                     |       BaseBackoffice        |          Pure Fabrication: BaseBackoffice is a class created with the purpose of showing the backoffice console.          |
|                                                                                      |                              ... showing the login menu?                               |           LoginUI           |                 Pure Fabrication: LoginUI is a class created with the purpose of showing the login menu.                  |
|                     Step 2: Validate login (as Customer Manager)                     |                         ... validating the login credentials?                          |      CredentialHandler      |             Service: CredentialHandler is a class that provides the service of validating login credentials.              |
|                                                                                      |                       ... showing the login (in)success message?                       |           LoginUI           |            Pure Fabrication: LoginUI is a class created with the purpose of showing the login success message.            |
| Step 3: Allow access to the backoffice application with customer manager permissions |                ... giving customer manager permissions to logged user?                 |      CredentialHandler      |             Service: CredentialHandler is a class that provides the service of validating login credentials.              |
|                             Step 4: Register job opening                             |                         ... showing the user creation console?                         |       AddJobOpeningUI       |    Pure Fabrication: AddRegisterJobOpeningUI is a class created with the purpose of showing the user creation console     |
|                                                                                      |                       ... controlling the flow of the use case?                        |   AddJobOpeningController   |   Controller: RegisterJobOpeningController is a class created with the purpose of controlling the flow of the use case.   |
|                                                                                      | ... ensure customer manager has permissions to create a new user with 'Customer' role? |    AuthorizationService     |            Service: AuthorizationService is a class that provides the service of validating login credentials.            |
|                          Step 5: Enter Job Opening details                           |                              ... communicating with user?                              |       AddJobOpeningUI       |        Pure Fabrication: AddJobOpeningUI is a class created with the purpose of showing the user creation console         |
|                                                                                      |             ... ensure the new customer is valid by checking its details?              |      JobOpeningBuilder      |                    Factor: JobOpeningBuilder is a class that provides the service of validating users.                    |
|                                                                                      |                            ... keep the customer's details                             |         JobOpening          |                                    Information Expert: JobOpening has its own details.                                    |
|                               Step 6: Add job Opening                                |  ... ensure the new customer is not already registered and create a new user for it?   | JobOpeningManagementService |              Service: JobOpeningManagementService is a class that provides the service of registering users.              |
|                                                                                      |                   ... record the customer instance in the database?                    |    JobOpeningRepository     | Information Expert: JobOpeningRepository is a class created with the purpose of recording user instances in the database. |
|                 Step 7: Show (in)success of the operation's message                  |                    ... show the user creation (in)success message?                     |       AddJobOpeningUI       |    Pure Fabrication: AddJobOpeningUI is a class created with the purpose of showing the user creation success message.    |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* BaseApplication
* BaseBackoffice
* CredentialHandler
* AuthorizationService
* JobOpeningBuilder
* JobOpening
* JobOpeningManagementService

Other software classes (i.e. Pure Fabrication) identified: 

* LoginUI
* AddJobOpeningUI
* AddJobOpeningController
* JobOpeningRepository
 * 
## 3.2. Sequence Diagram (SD)

![uc006-sequence-diagram-full.svg](svg%2Fuc006-sequence-diagram-full.svg)


## 3.3. Class Diagram (CD)

![uc006-class-diagram.svg](svg%2uc006-class-diagram.svg)