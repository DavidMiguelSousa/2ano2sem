# UC002 - As Admin, I want to enable users of the backoffice

## 2. Analysis

### 2.1. Relevant Domain Model Excerpt

The following diagram was extracted from the EAPLI framework (provided by the course's teachers).

![UC001 - Domain Model](svg/uc002-domain-model.svg)

### 2.2. Process Specification

#### 2.2.1. Normal Flow
1. **Authenticate Admin**: Verify that the Admin is logged in with appropriate permissions.
2. **Access User Management Interface**: Admin navigates to the user management section of the backoffice.
3. **Select the Option to Enable Users**: Admin selects the option to enable users.
4. **List Inactive Users**: The system lists all inactive users.
5. **Select User to Enable**: Admin selects the user to enable.
6. **Enable User**: The system enables the selected user.

#### 2.2.2. Exceptional Flows
- **EF002.1**: If there is no data, the system shall display a message indicating that there are no users to list.
- **EF002.2**: If there is an error retrieving the data, the system shall display an error message and prompt the Admin to try again later.

### 2.3. Functional Requirements Reevaluation
- **FR002.1**: The system shall give the admin the option to enable users.
- **FR002.2**: The system shall list all unactive users (backoffice, customer and candidates).
- **FR002.3**: Only inactive users should be listed to be enabled.
- **FR002.4**: The system shall enable the selected user.

### 2.4. Non-functional Requirements Specification
- **Security**: Assess encryption standards for transmitting user credentials.
- **Performance**: Ensure user listing processes complete within acceptable time limits, maintaining system responsiveness.
- **Usability**: Interface should be intuitive, guiding the Admin smoothly through the listing process with clear instructions and error handling.

### 2.5. Data Integrity and Security
- **Authorized Access**: Ensure that user data is stored securely and that only authorized users (in this case, the system administrator) can access it.

### 2.6. Interface Design
- The interface will follow the EAPLI framework's design patterns, providing a user-friendly experience for the Admin.

### 2.7. Risk Analysis
- **R003.1**: Unauthorized Access to User Data
    - **Mitigation**: Implement validation checks within the system to ensure that only admin can list active users.

### 2.8. Decisions
- **D003.1**: Utilize role-based access control for user management, assigning to each user specific permissions based on their role.