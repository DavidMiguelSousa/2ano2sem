# UC006
## 1. Requirements Engineering

### 1.1. User Case Description

> As Customer Manager, I want to register a job opening.

---

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

> Alternatively this task has to  be achieved by a bootstrap process.

**From the client clarifications:**


> **Question:** 
> No job opening é tudo de preenchimento obrigatório ou existem opcionais?
> 
> **Answer:** 
> Os campos referidos na secção 2.2.2 são de preenchimento obrigatório. Os requirements vão ser dinâmicos uma vez que dependem do requirements specification selecionado para aquele job opening (que se baseia numa linguagem).



> **Question:**
> Sobre a job specification, deve ser o cliente a enviar os requisitos ou é a responsabilidade do customer manager? Qual o conceito de uma job specification?
> 
> **Answer:**
> Tipicamente será o customer que informa o custerm manager dos requisitos mínimos para uma oferta de emprego. O Customer manager verifica se existe já um requirements specification adequado. Caso não existe, com a ajuda do Language Engineer é criado um novo.



> **Question:**
> No job opening (secção 2.2.2), no campo company, deve ser o customer name ou o customer code, uma vez que o customer code é único e introduzido manualmente?
> 
> **Answer:**
> A informação relativa ao job opening que aparece no final da página 5 deve ser vista como algo a ser usado na divulgação de uma oferta de emprego. Nesse contexto, para a Company faz mais sentido divulgar o nome da company e não o seu código. Dito isto, em termos de armazenamento numa base de dados poderá ficar o código.



> **Question:**
> No contexto em que o Customer Manager regista uma oferta de emprego, como são selecionados/definidos os requisitos para essa job offer?
>
> **Answer:**
> O Customer manager regista a job opening (US 1002) e de seguida (normalmente) seleciona qual o requirements specification que é adequado a esse job opening. O requirements specification será um dos que foi “criado” pelo language engineer e registado no sistema.



> **Question:**
>Numa job opening, o tipo de contrato e o modo de trabalho são elementos fixos, na medida em que os tipos apresentados na documentação são estáticos e não mudam, ou poderão haver mais tipos deste dois elementos?
> **Answer:**
>Os mais “normais” serão os que aparecem na documentação. Mas penso que faz sentido o sistema aceitar outros diferentes caso o Customer Manager assim o entender.



> **Question:**
> Um customer manager tem a responsabilidade de criar job openings para os customers em que é responsável. Na hora da criação da job opening, como é que ele refere qual é o customer daquela job opening? Seleciona um customer dentro dos que está responsável?
> **Answer:**
>Sim, pode ser como indica.


---

### 1.3. Acceptance Criteria

*AC006.1* Verify the job opening before register.

*AC006.2* Every job opening needs to be unique.

*AC006.3* Alternatively this task has to  be achieved by a bootstrap process.



---

### 1.4. Found out Dependencies

* There's a dependencies to UC001 - "As Administrator, I want to be able to register users of the
  backoffice.", because if the costumer manager doesn't exist, we can't register anything.

---

### 1.5 Input and Output Data
**Input Data:**

* Typed data:
	* Title or function
    * Address
    * Number of vacancies
    * Description
    

* Automatic data:
	* Job Reference

* Selected data:
    * Contract Type
    * Mode
    * Customer

**Output Data:**
* (In)Success of the operation

---

### 1.6. System Sequence Diagram (SSD)

![uc006-system-sequence-diagram.svg](svg%2Fuc006-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks

* The Job Reference is a unique identifier of the job opening (generated by the system, for
instance based on the customer code followed by a sequential number).
* Title or function, such as "front end programmer".
* Contract Type, such as full-time or part-time.
* Mode Such as remote, hybrid, onsite.
* Address, Address for job.
* Company, Customer name.

