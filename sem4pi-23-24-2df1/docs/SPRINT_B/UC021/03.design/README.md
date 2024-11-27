# UC021 - As Operator, I want to generate and export a template text file to help collect data fields for candidates of a job opening (so the data is used to verify the requirements of the job opening).

## 3. Design - Use Case Realization

### 3.1. Rationale

| Interaction ID                    | Question: Which class is responsible for...                          | Answer                            | Justification (with patterns)                                                                                                  |
|-----------------------------------|----------------------------------------------------------------------|-----------------------------------|--------------------------------------------------------------------------------------------------------------------------------|
| Step 1: Generate Job Requirements | ... initializing the job requirements generation process?            | GenerateJobRequirementsUI         | Pure Fabrication: GenerateJobRequirementsUI is the class responsible for interfacing with the user to start the process.       |
|                                   | ... asking the user for the interview model name?                    | GenerateJobRequirementsUI         | Pure Fabrication: GenerateJobRequirementsUI directly interacts with the user to obtain necessary details like model name.      |
| Step 2: Processing File Creation  | ... creating the initial job requirements file?                      | GenerateJobRequirementsController | Controller: GenerateJobRequirementsController manages the control flow, receiving input from the UI to process file creation.  |
|                                   | ... processing the creation of the file on the server?               | JobRequirementsManagementService  | Service: JobRequirementsManagementService handles the backend logic to actually create the file based on controller commands.  |
| Step 3: Adding Questions          | ... handling the addition of questions to the job requirements file? | GenerateJobRequirementsController | Controller: GenerateJobRequirementsController coordinates the appending of questions to the file.                              |
|                                   | ... processing the addition of questions in the backend?             | JobRequirementsManagementService  | Service: JobRequirementsManagementService performs the backend operations to append questions as instructed by the controller. |
| Step 4: Finalizing the Operation  | ... displaying the (in)success of the operation to the user?         | GenerateJobRequirementsUI         | Pure Fabrication: GenerateJobRequirementsUI is tasked with communicating the result of the operation back to the user.         |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

**GenerateJobRequirementsController** (Controller)
**JobRequirementsManagementService** (Service)

Other software classes (i.e., Pure Fabrication) identified:

**GenerateJobRequirementsUI** (Pure Fabrication)

## 3.2. Sequence Diagram (SD)

![uc020-sequence-diagram.svg](svg/uc020-sequence-diagram.svg)

## 3.3. Class Diagram (CD)

![uc020-class-diagram.svg](svg/uc020-class-diagram.svg)