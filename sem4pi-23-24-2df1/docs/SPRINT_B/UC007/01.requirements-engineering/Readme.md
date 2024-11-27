# UC007
## 1. Requirements Engineering

### 1.1. User Case Description

>  As Customer Manager, I want to list job openings.

---

### 1.2. Customer Specifications and Clarifications


**From the client clarifications:**

> **Question:** 
> Na us1003 é pedido que se liste job openings, há algum critério para definir quais listar? Ou são as do sistema inteiro?

> > **Answer:** 
> Suponho que poder filtrar por Customer e data seja útil. Também poder filtrar apenas as activas ou todas parece-me útil.


> **Question:**
>Em relação à listagem dos jobs openings, um customer manager pode listar todos os jobs openings ou apenas os que lhe foram atribuídos. Posto de outra forma, os job openings são atribuídos a um customer manager específico, e o mesmo só pode ter acesso à sua lista de job openings?

> > **Answer:**
>Penso que faz sentido listar apenas os “seus” job openings.



> **Question:**
>Relativamente a uma questão já colocada foi referido que "pode-se filtrar por Customer" nesta US. Nesta caso qual será a forma que o Customer Manager utilizará para filtrar as Job Openings por Costumer (nome, email,...)? E quando se refere a "poder filtrar por data" significa que é uma determinada data ou um intervalo de tempo?
> > **Answer:**
>O Customer é tipicamente uma empresa e tem um nome. Também já foi referida a existência de um customer code. Quanto ao filtro por data se estiverem no papel do customer manager que tem de consultar job openings faz sentido ser para um dia? Ou seja ele teria de sabe em que dia é que registou o job opening que está a pesquisar…
---

### 1.3. Acceptance Criteria

*AC007.1* The Costumer Manager should only be able to list the job openings that are assigned to him.


---

### 1.4. Found out Dependencies

* There's a dependency to UC006- "As Customer Manager, I want to register a job opening.", as to lis job opennings we need to have them registered first.

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

![us1001-system-sequence-diagram.svg](svg%2Fuc007-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks

* n\a