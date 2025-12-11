package utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public class StringToNumberConverter {

    DecimalFormatSymbols symbols;
    DecimalFormat decimalFormat;

    public StringToNumberConverter() {
        this.symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        decimalFormat = new DecimalFormat("#,###.##########", symbols);
    }

    public double convert(String s) {
        if (this.decimalFormat == null) {
            return 0.0;
        }

        try {
            return this.decimalFormat.parse(s).doubleValue();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
