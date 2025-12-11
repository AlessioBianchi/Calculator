import utils.NumberToStringConverter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NumberToStringConverterTest {

    NumberToStringConverter converter = new NumberToStringConverter();

    @Test
    void testIntegerToString() {
        assertEquals("10", converter.convert((double) 10));
    }

    @Test
    void testNegativeNumberToString() {
        assertEquals("-5", converter.convert((double) -5));
    }
}
