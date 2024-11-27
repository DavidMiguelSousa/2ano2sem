# UC024 - As Operator, I want to enable candidates

## 2. Analysis

### 2.1. Relevant Domain Model Excerpt

Part of the following diagram was extracted from the EAPLI framework (provided by the course's teachers).

![UC024 - Domain Model](svg/uc024-domain-model.svg)

### 2.2. Process Specification

#### 2.2.1. Normal Flow
1. **Authenticate Operator**: Verify that the Operator is logged in with appropriate permissions.
2. **Access User Management Interface**: Operator navigates to the user management section of the backoffice.
3. **Select the Option to Enable Candidates**: Operator selects the option to enable candidates.
4. **List Inactive Candidates**: The system lists all inactive candidates (users who cannot access, at that moment, the Candidate App).
5. **Select Candidate to Enable**: Operator selects the candidate to enable.
6. **Enable Candidate**: The system grants the selected candidate access to the Candidate App (by activating him/her).

#### 2.2.2. Exceptional Flows
- **EF024.1**: If there is no data, the system shall display a message indicating that there are no inactive candidates to list.
- **EF024.2**: If there is an error retrieving the data, the system shall display an error message and prompt the Operator to try again later.

### 2.3. Functional Requirements Reevaluation
- **FR024.1**: The system shall give the operator the option to enable candidates.
- **FR024.2**: Only inactive candidaes should be listed to be enabled.
- **FR024.3**: The system shall grant the selected candidate access to the Candidate App.

### 2.4. Non-functional Requirements Specification
- **Security**: Assess encryption standards for transmitting user credentials.
- **Performance**: Ensure user listing processes complete within acceptable time limits, maintaining system responsiveness.
- **Usability**: Interface should be intuitive, guiding the Operator smoothly through the listing process with clear instructions and error handling.

### 2.5. Data Integrity and Security
- **Authorized Access**: Ensure that user data is stored securely and that only authorized users (in this case, the operator) can access it.

### 2.6. Interface Design
- The interface will follow the EAPLI framework's design patterns, providing a user-friendly experience for the Operator.

### 2.7. Risk Analysis
- **R024.1**: Unauthorized Access to User Data
  - **Mitigation**: Implement validation checks within the system to ensure that only the Operator can list inactive candidates and enable them.

### 2.8. Decisions
- **D024.1**: Create a "Candidate" entity to represent the users who are candidates (contrary to what was previously decided, where the "User" entity was used for all users who were candidates).