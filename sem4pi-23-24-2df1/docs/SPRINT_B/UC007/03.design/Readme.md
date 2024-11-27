# UC007 - List Job Openings

## 3. Design - User Story Realization 

### 3.1. Rationale


|                                    Interaction ID                                    |       Question: Which class is responsible for...       |          Answer          |                                            Justification (with patterns)                                            |
|:------------------------------------------------------------------------------------:|:-------------------------------------------------------:|:------------------------:|:-------------------------------------------------------------------------------------------------------------------:|
|                         Step 1: Login (as Customer Manager)                          |         ... running the application's console?          |     BaseApplication      |   Pure Fabrication: BaseApplication is a class created with the purpose of running a console in our application.    |
|                                                                                      |     ... showing the backoffice console to do login?     |      BaseBackoffice      |       Pure Fabrication: BaseBackoffice is a class created with the purpose of showing the backoffice console.       |
|                                                                                      |               ... showing the login menu?               |         LoginUI          |              Pure Fabrication: LoginUI is a class created with the purpose of showing the login menu.               |
|                     Step 2: Validate login (as Customer Manager)                     |          ... validating the login credentials?          |    CredentialHandler     |          Service: CredentialHandler is a class that provides the service of validating login credentials.           |
|                                                                                      |       ... showing the login (in)success message?        |         LoginUI          |         Pure Fabrication: LoginUI is a class created with the purpose of showing the login success message.         |
| Step 3: Allow access to the backoffice application with customer manager permissions | ... giving customer manager permissions to logged user? |    CredentialHandler     |          Service: CredentialHandler is a class that provides the service of validating login credentials.           |
|                           Step 4: Ask to list Job Openings                           |             ... interacting with the actor?             |     ListJobOpeningUI     |        Pure Fabrication: AddUserUI is a class created with the purpose of showing the user creation console         |
|                                                                                      |        ... controlling the flow of the use case?        | ListJobOpeningController |     Controller: AddUserController is a class created with the purpose of controlling the flow of the use case.      |
|                             Step 5: Search personal data                             |    ... record the customer instance in the database?    |   JobOpeningRepository   |        Pure Fabrication: AddUserUI is a class created with the purpose of showing the user creation console         |
|                             Step 7: Display Job Openings                             |             ... show list with Job Openings             |     ListJbOpeningUI      |    Pure Fabrication: AddUserUI is a class created with the purpose of showing the user creation success message.    |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* BaseApplication
* BaseBackoffice
* CredentialHandler
* AuthorizationService
* JobOpening
* JobOpeningManagementService

Other software classes (i.e. Pure Fabrication) identified: 

* LoginUI
* ListJobOpeningUI
* ListJobOpeningController
* JobOpeningRepository

## 3.2. Sequence Diagram (SD)

![uc007-sequence-diagram-full.svg](svg%2Fuc007-sequence-diagram-full.svg)


## 3.3. Class Diagram (CD)

![uc007-class-diagram.svg](svg%2Fuc007-class-diagram.svg)