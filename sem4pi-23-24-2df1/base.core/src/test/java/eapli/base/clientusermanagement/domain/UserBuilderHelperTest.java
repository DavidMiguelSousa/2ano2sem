package eapli.base.clientusermanagement.domain;


import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.*;
import eapli.framework.infrastructure.authz.repositories.impl.inmemory.InMemoryUserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserBuilderHelperTest {

    private PasswordPolicy policy;
    private InMemoryUserRepository repo;
    private SystemUser user1;
    private SystemUser user2;
    private SystemUser user3;

    @BeforeEach
    void setUp() {
        policy = new BasePasswordPolicy();
        repo = new InMemoryUserRepository();
        repo.forEach(u -> repo.delete(u));
        SystemUserBuilder builder = UserBuilderHelper.builder();
        user1 = builder.with("test@domain.com", "Password123!", "Test", "Test", "test@domain.com")
                .withRoles(BaseRoles.ADMIN)
                .build();
        user2 = builder.with("test@domain.com", "PassPass123!", "Name", "Name", "test@domain.com")
                .withRoles(BaseRoles.OPERATOR)
                .build();
        user3 = builder.with("test2@domain.com", "PassPass123!", "Names", "Names", "test2@domain.com")
                .withRoles(BaseRoles.CUSTOMER)
                .build();
    }

    @Test
    void checkValidPasswordTest() {
        String password = "Password123!";
        assertTrue(policy.isSatisfiedBy(password));
    }

    @Test
    void checkNoSpecialCharacterTest() {
        String password = "Password123";
        assertFalse(policy.isSatisfiedBy(password));
    }

    @Test
    void checkNoDigitTest() {
        String password = "Password!";
        assertFalse(policy.isSatisfiedBy(password));
    }

    @Test
    void checkNoUpperCaseLetterTest() {
        String password = "password123!";
        assertFalse(policy.isSatisfiedBy(password));
    }

    @Test
    void checkNoEightCharactersTest() {
        String password = "Pass1!";
        assertFalse(policy.isSatisfiedBy(password));
    }

    @Test
    void createUserSuccessTest() {
        Username username = Username.valueOf("test@domain.com");
        EmailAddress email = EmailAddress.valueOf("test@domain.com");

        repo.save(user1);

        assertEquals(username, user1.username());
        assertEquals(email, user1.email());

        assertTrue(repo.ofIdentity(user1.username()).isPresent());
    }

    @Test
    void createSameUserInsuccessTest() {
        repo.save(user1);
        repo.save(user2);

        assertEquals(1, repo.count());
    }

    @Test
    void createDifferentUsersSuccess() {
        repo.save(user1);
        repo.save(user3);

        assertEquals(2, repo.count());
    }

}
