# UC005

## 1. Requirements Engineering

### 1.1. User Story Description

> As Customer Manager I want to register a customer and that the system automatically creates a user for that customer.

---

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

> The Customer Manager (CM) is an employee of the company Jobs4U who is responsible for managing the company's
> customers. Each CM will have a group of Customers that it will handle. The Customers will follow a Job Opening into
> the system, so the CM should register it after validating all the criteria needed. The customer manager is responsible
> to setup the recruitment process, defining the dates for the phases and if the process includes interviews.

**From the client clarifications:**

* **2024-03-14**
 
> **Question:** Por cada Customer, apenas existe um “representante” que acede à conta (i.e., Customer App)?
>
> **Answer:** Sim, parece-me suficiente.

> **Question:** No enunciado não está explicita a informação a recolher para os Customers? Qual a informação necessária?
> E quando aos funcionários da empresa?
>
> **Answer:** De facto isso não está explicito. No entanto, são referidos no nome da empresa e o seu endereço no âmbito
> de um job opening. Quanto aos utilizadores (representante da empresa que acede à Customer App) eu diria que serão
> dados
> similares ao do Candidate. Quando aos funcionários da empresa, eu diria que é importante garantir que é usado o email
> para identificar qualquer utilizador do sistema. Penso que será importante para cada utilizador termos o nome completo
> assim como um short user name (que deverá ser único). **[Atualização em 2024-03-21:]** O Product Owner reconsiderou e
> decidiu
> que o short user name é dispensável uma vez que para autenticação dos utilizadores se deve usar apenas o email e a
> password.

> **Question:** Um customer manager pode gerir vários clientes?
>
> **Answer:** Sim.

* **2024-03-21**

> **Question:** Na criação de um utilizador no sistema o nome é definido pelo utilizador ou é o nome da pessoa (primeiro
> e último) e se a password é definida pelo utilizador ou gerada pelo sistema?
>
> **Answer:** (...). Neste contexto é necessário ter uma password para esse novo utilizador. Uma vez que essa informação 
> não é transmitida pelo candidato, suponho que a solução mais “aconselhada” será o sistema gerar uma password para esse 
> utilizador. Como o utilizador/candidato irá receber essa informação (a forma de autenticação na app) está out of scope, 
> no sentido em que não existe nenhuma US que remete para isso. As US 1000 e 1001 também remetem para criação de utilizadores. 
> Aqui, eventualmente poderia-se pensar em introduzir manualmente as passwords, mas pode ser pelo mesmo mecanismo de 
> definição automática de password, descrito anteriormente.

* **2024-04-20**

> **Question:** A criação de uma conta para o customer é feita ao inserir manualmente o email e nome do customer ou é 
> expectavel ter uma lista de customers não registados no sistema para usar?
> 
> **Answer:** Não percebi bem o conceito de customers não registados. Penso que esse conceito não existe. A US 1001 
> indica que se pretende registar um customer. Ao registar-se o customer deve-se também criar um utilizador para que o 
> customer possa aceder à App de customers.
 
* **2024-04-21**

> **Question:** Regras de negócio para endereço do customer- Quais são as regras de negócio do endereço do customer?
>
> **Answer:** (...). Se me perguntar se é suficiente, nesta fase, que o sistema aceite apenas endereços nacionais, a 
> minha resposta é afirmativa. (...).

> **Question:** É correto assumir que o customer fica automaticamente atribuido ao customer manager que o registou ?
>
> **Answer:** Sim. No âmbito deste projeto vamos assumir que isso deve acontecer.
---

### 1.3. Acceptance Criteria

**AC005.1:** When creating a Customer, a User should be created first, then the Customer.

**AC005.2:** The User should have a name, an email, a password and a role, which will be 'Customer'.

**AC005.3:** The User can be created automatically via bootstrap.

---

### 1.4. Found out Dependencies

* no dependencies were found.

---

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * name
    * email

* Automatic data:
    * password

* Selected data:
    * role

**Output Data:**

* Terminal:
    * (In)Success of the operation

---

### 1.6. System Sequence Diagram (SSD)

![uc005-system-sequence-diagram.svg](svg%2Fuc005-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks