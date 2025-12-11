package utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class NumberToStringConverter {

    DecimalFormatSymbols symbols;
    DecimalFormat decimalFormat;

    public NumberToStringConverter() {
        this.symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        decimalFormat = new DecimalFormat("#,###.##########", symbols);
    }

    public String convert(Double n) {
        if (this.decimalFormat == null) {
            return "0";
        }

        return this.decimalFormat.format(n);
    }
}
