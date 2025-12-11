import utils.StringToNumberConverter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringToNumberConverterTest {

    StringToNumberConverter converter = new StringToNumberConverter();

    @Test
    void testValidNumberConversion() {
        assertEquals(42, converter.convert("42"));
    }

    @Test
    void testNegativeNumberConversion() {
        assertEquals(-15, converter.convert("-15"));
    }

    @Test
    void testInvalidNumberThrowsException() {
        assertThrows(RuntimeException.class, () -> {
            converter.convert("abc");
        });
    }
}