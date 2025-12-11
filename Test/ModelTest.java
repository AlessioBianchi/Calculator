import model.Model;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import utils.NumberToStringConverter;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class ModelTest {

    Model model = new Model();
    NumberToStringConverter numberToStringConverter = new NumberToStringConverter();

    @ParameterizedTest
    @CsvSource({
            "+", "-", "X", "/"
    })
    void anyOperation_ShouldReturn(String operation) {
        model.clear();
        int a = new Random().nextInt();
        model.updateCurrent(String.valueOf(a));
        model.updateOperation(operation);
        int b = new Random().nextInt();
        model.updateCurrent(String.valueOf(b));
        model.getResult();
        String result = switch (operation) {
            case "+" -> numberToStringConverter.convert((double) a + b);
            case "-" -> numberToStringConverter.convert((double) a - b);
            case "X" -> numberToStringConverter.convert((double) a * b);
            case "/" -> numberToStringConverter.convert((double) a / b);
            default -> null;
        };

        assertEquals(result, model.getDisplay());
    }

    @ParameterizedTest
    @CsvSource({
            "+", "-", "X", "/"
    })
    void shouldCalculatePercentage(String operation) {
        model.clear();
        int a = new Random().nextInt();
        model.updateCurrent(String.valueOf(a));
        model.updateOperation(operation);
        int b = new Random().nextInt();
        model.updateCurrent(String.valueOf(b));
        model.calculatePercentage();
        String result = switch (operation) {
            case "+" -> numberToStringConverter.convert(((double) a + b)/100);
            case "-" -> numberToStringConverter.convert(((double) a - b)/100);
            case "X" -> numberToStringConverter.convert(((double) a * b)/100);
            case "/" -> numberToStringConverter.convert(((double) a / b)/100);
            default -> null;
        };

        assertEquals(result, model.getDisplay());
    }

    @ParameterizedTest
    @CsvSource({
            "+", "-", "X", "/"
    })
    void shouldCalculateNegative(String operation) {
        model.clear();
        int a = new Random().nextInt();
        model.updateCurrent(String.valueOf(a));
        model.updateOperation(operation);
        int b = new Random().nextInt();
        model.updateCurrent(String.valueOf(b));
        model.calculateNegative();
        String result = switch (operation) {
            case "+" -> numberToStringConverter.convert(-((double) a + b));
            case "-" -> numberToStringConverter.convert(-((double) a - b));
            case "X" -> numberToStringConverter.convert(-((double) a * b));
            case "/" -> numberToStringConverter.convert(-((double) a / b));
            default -> null;
        };

        assertEquals(result, model.getDisplay());
    }

    @Test
    public void shouldUpdateCurrent() {
        model.clear();
        List<String> pool = List.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ",");
        String nextPressed = pool.get(new Random().nextInt(0, pool.size()));
        model.updateCurrent(nextPressed);

        assertEquals(nextPressed, model.getDisplay());
    }

    @Test
    public void shouldUpdateOperation() {
        model.clear();
        List<String> poolNumbers = List.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        List<String> poolOperations = List.of("+", "-", "X", "/");
        String nextNumberPressed = poolNumbers.get(new Random().nextInt(0, poolNumbers.size()));
        model.updateCurrent(nextNumberPressed);
        String nextOperationPressed = poolOperations.get(new Random().nextInt(0, poolOperations.size()));
        model.updateOperation(nextOperationPressed);
        String result = nextNumberPressed + nextOperationPressed + "0";

        assertEquals(result, model.getDisplay());
    }

    @Test
    public void shouldGoBack() {
        model.clear();
        List<String> poolNumbers = List.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        List<String> poolOperations = List.of("+", "-", "X", "/");
        String nextNumberPressed = poolNumbers.get(new Random().nextInt(0, poolNumbers.size()));
        model.updateCurrent(nextNumberPressed);
        String nextOperationPressed = poolOperations.get(new Random().nextInt(0, poolOperations.size()));
        model.updateOperation(nextOperationPressed);
        model.goBack();

        assertEquals(nextNumberPressed, model.getDisplay());
    }

    @Test
    public void shouldClear() {
        model.clear();
        List<String> poolNumbers = List.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        List<String> poolOperations = List.of("+", "-", "X", "/");
        String nextNumberPressed = poolNumbers.get(new Random().nextInt(0, poolNumbers.size()));
        model.updateCurrent(nextNumberPressed);
        String nextOperationPressed = poolOperations.get(new Random().nextInt(0, poolOperations.size()));
        model.updateOperation(nextOperationPressed);
        model.clear();

        assertEquals("0", model.getDisplay());
    }

    @Test
    public void shouldFormatDisplay() {
        String valueFormatted = "1.234,56";
        model.updateCurrent("1");
        model.updateCurrent("2");
        model.updateCurrent("3");
        model.updateCurrent("4");
        model.updateCurrent(",");
        model.updateCurrent("5");
        model.updateCurrent("6");

        assertEquals(valueFormatted, model.getDisplay());
    }
}