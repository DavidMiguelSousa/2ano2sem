package eapli.base.services;

import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.strings.util.StringPredicates;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static eapli.framework.io.util.Console.*;

public class ConsoleUtils {

    public static LocalTime readTime(final String prompt) {
        return readTime(prompt, "HH:mm");
    }

    public static LocalTime readTime(final String prompt, final String timeFormat) {
        while(true) {
            try {
                String strTime = readLine(prompt);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormat);
                return LocalTime.parse(strTime, formatter);
            } catch (DateTimeParseException ignored) {
            }
        }
    }

    public static int readIntegerInScale(final String prompt, final int min, final int max) {
        while (true) {
            try {
                int value = readInteger(prompt);
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
            }
        }
    }

    public static double readDoubleInScale(final String prompt, final double min, final double max) {
        while (true) {
            try {
                double value = readDouble(prompt);
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
            }
        }
    }

    public static EmailAddress readEmail(final String prompt, final String errorMessage) {
        do {
            String value = readNonEmptyLine(prompt, errorMessage);
            if (StringPredicates.isEmail(value)) {
                return EmailAddress.valueOf(value);
            } else {
                System.out.println(errorMessage);
            }
        } while (true);
    }

}
