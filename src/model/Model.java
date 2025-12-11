package model;

import exception.DivisionByZeroException;
import utils.NumberToStringConverter;
import utils.StringToNumberConverter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class Model {

    private String current = "";
    private String display = "0";
    private ArrayList<Double> numbers = new ArrayList<>();
    private ArrayList<String> operations = new ArrayList<>();

    private final StringToNumberConverter stringToNumberConverter = new StringToNumberConverter();
    private final NumberToStringConverter numberToStringConverter = new NumberToStringConverter();

    private static final String DECIMAL_SEPARATOR = ",";
    private static final String GROUP_SEPARATOR = ".";

    private static final String ERROR = "Error";

    public Model() {}

    public void updateCurrent(String numberPressed) {
        // Handle decimal separator
        if (numberPressed.equals(DECIMAL_SEPARATOR)) {
            if (!this.current.contains(DECIMAL_SEPARATOR)) {
                this.current += DECIMAL_SEPARATOR;
            }
            updateDisplay();
            return;
        }

        // If decimal part exists → DO NOT FORMAT
        if (this.current.contains(DECIMAL_SEPARATOR)) {
            this.current += numberPressed;
            updateDisplay();
            return;
        }

        // Integer part only
        String noGroup = this.current.replace(GROUP_SEPARATOR, "");

        if (noGroup.isEmpty() || noGroup.equals("0")) {
            noGroup = numberPressed;
        } else {
            noGroup += numberPressed;
        }

        // Format ONLY integer part
        this.current = formatNumber(noGroup);

        updateDisplay();
    }

    public void updateOperation(String operationPressed) {
        double numberToAdd = stringToNumberConverter.convert(this.current);
        this.numbers.add(numberToAdd);
        this.current = "";

        this.operations.add(operationPressed);

        updateDisplay();
    }

    public void goBack() {
        if (this.display.equals("0")){
            return;
        }

        if (!(this.current.isEmpty() || this.current.equals("0"))) {
            // Remove last character
            this.current = this.current.substring(0, this.current.length() - 1);

            // Remove trailing grouping separator if left alone
            if (this.current.endsWith(GROUP_SEPARATOR)) {
                this.current = this.current.substring(0, this.current.length() - 1);
            }

            updateDisplay();
            return;
        }

        if (!this.operations.isEmpty()) {
            // Remove last operation
            this.operations.remove(this.operations.size() - 1);

            // Restore last number into current
            if (!this.numbers.isEmpty()) {
                double lastNumber = this.numbers.remove(this.numbers.size() - 1);
                this.current = numberToStringConverter.convert(lastNumber);
            }

            updateDisplay();
        }
    }

    public void clear() {
        this.current = "";
        this.display = "0";
        this.numbers = new ArrayList<>();
        this.operations = new ArrayList<>();
    }

    public void calculatePercentage(){
        double result = executeOperations();
        result /= 100;
        this.current = numberToStringConverter.convert(result);
        this.display = this.current;
    }

    public void calculateNegative() {
        double result = executeOperations();
        result = -result;
        this.current = numberToStringConverter.convert(result);
        this.display = this.current;
    }

    public void getResult() {
        double result = executeOperations();
        this.current = numberToStringConverter.convert(result);
        this.display = this.current;
    }

    private double executeOperations() {
        double numberToAdd = stringToNumberConverter.convert(this.current);
        this.numbers.add(numberToAdd);

        double result = this.numbers.get(0);

        for (int i = 1; i < this.numbers.size(); i++) {
            double number = this.numbers.get(i);
            String operationToExecute = this.operations.get(i - 1);
            switch (operationToExecute) {
                case "+" -> result += number;
                case "-" -> result -= number;
                case "X" -> result *= number;
                case "/" -> {
                    if (number == 0) {
                        throw new DivisionByZeroException(ERROR);
                    }
                    result /= number;
                }
            }
        }

        this.numbers = new ArrayList<>();
        this.operations = new ArrayList<>();

        return result;
    }

    public String getDisplay() {
        return this.display;
    }

    private void updateDisplay() {
        StringBuilder sb = new StringBuilder();

        int count = Math.min(numbers.size(), operations.size());

        for (int i = 0; i < count; i++) {
            sb.append(numberToStringConverter.convert(numbers.get(i)));
            sb.append(operations.get(i));
        }

        // If there is an extra number (last number before operator)
        if (numbers.size() > operations.size()) {
            sb.append(numberToStringConverter.convert(
                    numbers.get(numbers.size() - 1)
            ));
        }

        // Append currently typed value (live input)
        if (this.current.isEmpty()) {
            this.current = "0";
        }
        sb.append(this.current);

        this.display = sb.toString();
    }

    private String formatNumber(String raw) {
        if (raw.endsWith(DECIMAL_SEPARATOR)) {
            return raw;
        }

        double value = Double.parseDouble(
                raw.replace(DECIMAL_SEPARATOR, ".")
        );

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');

        DecimalFormat df = new DecimalFormat("#,##0.##########", symbols);
        df.setGroupingUsed(true);

        return df.format(value);
    }
}
