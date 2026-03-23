package edu.norcocollege.cis18b.weekx.mini10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AlertValidatorTest 
{
    @Test
    void shouldAcceptValidAlert() 
    {
        AlertValidator validator = new AlertValidator();
        Alert alert = new Alert(1, "CPU critical", AlertLevel.CRITICAL);

        assertDoesNotThrow(() -> validator.validate(alert));
    }

    @Test
    void shouldRejectBlankMessage() 
    {
        AlertValidator validator = new AlertValidator();
        Alert alert = new Alert(1, "   ", AlertLevel.WARNING);

        assertThrows(InvalidAlertException.class, () -> validator.validate(alert));
    }

    @Test
    void shouldRejectNullLevel() 
    {
        AlertValidator validator = new AlertValidator();
        Alert alert = new Alert(1, "Disk usage high", null);

        assertThrows(InvalidAlertException.class, validator.validate(alert));
    }
}
