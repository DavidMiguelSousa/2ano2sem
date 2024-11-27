# UC013
## 1. Requirements Engineering

### 1.1. User Case Description

> As Customer Manager, I want to list all applications for a job opening.

---

### 1.2. Customer Specifications and Clarifications


**From the client clarifications:**

> **Question:** 
> Relativamente aos critérios para a listagem das candidaturas: Devem aparecer candidaturas que estão a decorrer ou podem aparecer candidaturas feitas no passado? Podem aparecer quaisquer candidaturas ou apenas as que tenham sido aceites? Que informação deverá ser mostrada em cada candidatura?

> > **Answer:** 
> Tal como refere a descrição da US, devem ser listadas todas as candidaturas para um job opening. Faz sentido mostrar todas as candidaturas, independentemente do seu estado. Assim, para cada cada candidatura deve ser identificado o candidato e o estado da sua candidatura


> **Question:**
>A lista deve conter applications que ainda não concluíram todo o processo de seleção definido no setup da Job Opening ? Se sim, a lista deve conter o estado/fase de cada application?
> > **Answer:**
>O processo de seleção/recrutamento termina apenas no “fim”, pelo que não percebo muito bem a primeira pergunta. Quanto à segunda pergunta penso que faz sentido incluir o estado da aplicação, até para o Customer Manager perceber as applications que foram rejeitadas por não passarem os requisitos.




### 1.3. Acceptance Criteria

*AC013.1* It should appear a list of all applications for a job opening.
*AC013.2* The list should contain the candidate and the state of the application.


---

### 1.4. Found out Dependencies

* There's a dependency to UC006- "As Customer Manager, I want to register a job opening.", as to list the applications, there must be job openings to list the applications from.
* There's a dependencies to UC001 - "As Administrator, I want to be able to register users of the
  backoffice.", because if the costumer manager doesn't exist, we can't register anything.
---

### 1.5 Input and Output Data
**Input Data:**

* Typed data:
  * n\a
	
* Automatic data:
	* list

* Selected data:
	* n\a

**Output Data:**
* (In)Success of the operation

---

### 1.6. System Sequence Diagram (SSD)

![uc013-system-sequence-diagram.svg](svg%2Fuc013-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks

* n\a