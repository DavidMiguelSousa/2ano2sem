package eapli.base.services;

import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Name;
import eapli.framework.infrastructure.authz.domain.model.Password;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordGeneratorTest {

    private SystemUser user;
    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String NON_ALPHA_CHARS = "!@#$%^&*()-_+=<>?{}[]|/\\";

    @BeforeEach
    void setUp() {
        user = UserBuilderHelper.builder().with(EmailAddress.valueOf("test@domain.com"), Password.encodedAndValid("Password1!", new BasePasswordPolicy(), new PlainTextEncoder()).get(), Name.valueOf("Test", "Name")).build();
    }

    @Test
    void generatePassword() {
        //expected: 4 first characters of the email + 1 random uppercase letter + 1 random digit + 1 random non-alphanumeric character
        String password = PasswordGenerator.generatePassword(user.email().toString());

        assertEquals(user.email().toString().substring(0,3), password.substring(0,3));

        assertTrue(UPPERCASE_LETTERS.contains(password.substring(4, 5)));
        assertTrue(DIGITS.contains(password.substring(5, 6)));
        assertTrue(NON_ALPHA_CHARS.contains(password.substring(6, 7)));
    }
}