# UC039 — As a Candidate, I want to be notified in my application when the state of one of my applications changes.

# 4. Tests 

In this project, a Test-Driven Development (TDD) approach was used.

```java

```

**Test 1:** Check the validity of the password policy (with a valid password).

```java

```

**Test 2:** Check the validity of the password policy (with an invalid password because of the absence of a special character).

```java

```

**Test 3:** Check the validity of the password policy (with an invalid password because of the absence of a digit).

```java

```

**Test 4:** Check the validity of the password policy (with an invalid password because of the absence of an uppercase letter).

```java

```

**Test 5:** Check the validity of the password policy (with an invalid password because of the absence of at least eight characters).

```java

```

**Test 6:** Check the creation of a user is successfull.
```java

```

**Test 7:** Check the creation of users with the same email fails for the second one.
```java

```

**Test 8:** Check the creation of users with different email is successfull.
```java

```

# 5. Construction (Implementation)

To help achieve this Use Case's requirements, some EAPLI framework classes were used.

## Class AddUserAction
```java

```

## Class AddUserUI
```java

```

## Class AddUserController
```java

```

## Class SystemUserBuilder
```java

```

# 6. Integration and Demo 

* A new option in the backoffice users menu was added. 

# 7. Observations

* To run this Use Case, check the Integration and Demonstration section in this Use Case [Read Me](../README.md) file.