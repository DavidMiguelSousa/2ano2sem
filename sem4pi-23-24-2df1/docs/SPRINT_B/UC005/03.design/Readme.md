# UC005 - As Customer Manager I want to register a customer and that the system automatically creates a user for that customer.

## 3. Design - User Story Realization

### 3.1. Rationale

|                                    Interaction ID                                    |                      Question: Which class is responsible for...                       |        Answer         |                                            Justification (with patterns)                                            |
|:------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------:|:---------------------:|:-------------------------------------------------------------------------------------------------------------------:|
|                         Step 1: Login (as Customer Manager)                          |                         ... running the application's console?                         |    BaseApplication    |   Pure Fabrication: BaseApplication is a class created with the purpose of running a console in our application.    |
|                                                                                      |                    ... showing the backoffice console to do login?                     |    BaseBackoffice     |       Pure Fabrication: BaseBackoffice is a class created with the purpose of showing the backoffice console.       |
|                                                                                      |                              ... showing the login menu?                               |        LoginUI        |              Pure Fabrication: LoginUI is a class created with the purpose of showing the login menu.               |
|                     Step 2: Validate login (as Customer Manager)                     |                         ... validating the login credentials?                          |   CredentialHandler   |          Service: CredentialHandler is a class that provides the service of validating login credentials.           |
|                                                                                      |                       ... showing the login (in)success message?                       |        LoginUI        |         Pure Fabrication: LoginUI is a class created with the purpose of showing the login success message.         |
| Step 3: Allow access to the backoffice application with customer manager permissions |                ... giving customer manager permissions to logged user?                 |   CredentialHandler   |          Service: CredentialHandler is a class that provides the service of validating login credentials.           |
|                             Step 4: Create new customer                              |                         ... showing the user creation console?                         |       AddUserUI       |        Pure Fabrication: AddUserUI is a class created with the purpose of showing the user creation console         |
|                                                                                      |                       ... controlling the flow of the use case?                        |   AddUserController   |     Controller: AddUserController is a class created with the purpose of controlling the flow of the use case.      |
|                                                                                      | ... ensure customer manager has permissions to create a new user with 'Customer' role? | AuthorizationService  |         Service: AuthorizationService is a class that provides the service of validating login credentials.         |
|                            Step 5: Enter customer details                            |                              ... communicating with user?                              |       AddUserUI       |        Pure Fabrication: AddUserUI is a class created with the purpose of showing the user creation console         |
|                                                                                      |             ... ensure the new customer is valid by checking its details?              |   SystemUserBuilder   |                 Factor: SystemUserBuilder is a class that provides the service of validating users.                 |
|                                                                                      |                            ... keep the customer's details                             |      SystemUser       |                                 Information Expert: SystemUser has its own details.                                 |
|                            Step 6: Register new customer                             |  ... ensure the new customer is not already registered and create a new user for it?   | UserManagementService |              Service: UserManagementService is a class that provides the service of registering users.              |
|                                                                                      |                   ... record the customer instance in the database?                    |    UserRepository     | Information Expert: UserRepository is a class created with the purpose of recording user instances in the database. |
|                 Step 7: Show (in)success of the operation's message                  |                    ... show the user creation (in)success message?                     |       AddUserUI       |    Pure Fabrication: AddUserUI is a class created with the purpose of showing the user creation success message.    |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* BaseApplication
* BaseBackoffice
* CredentialHandler
* AuthorizationService
* SystemUserBuilder
* SystemUser
* UserManagementService

Other software classes (i.e. Pure Fabrication) identified:

* LoginUI
* AddUserUI
* AddUserController
* UserRepository

## 3.2. Sequence Diagram (SD)

![uss003-sequence-diagram-full.svg](svg%2Fuc005-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![us003-class-diagram.svg](svg%2Fuc005-class-diagram.svg)