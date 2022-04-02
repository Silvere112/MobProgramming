package grtgaz.rio.mobprogramming;

import java.util.stream.IntStream;

public class RomanNumberConverter {
    public String convert(int numberToConvert) {
        if (hasOnly(numberToConvert, 10)) {
            return convertWithOnlyX(numberToConvert);
        } else if (hasOnly(numberToConvert, 5)) {
            return convertWithOnlyV(numberToConvert);
        } else if (hasOnly(numberToConvert, 1)) {
            return convertWithOnlyI(numberToConvert);
        }

        throw new RuntimeException("Unsupported number");
    }

    private boolean hasOnly(int number, int i) {
        return number % i == 0;
    }

    private String convertWithOnlyV(int number) {
        return "V";
    }

    private String convertWithOnlyX(int number) {
        return IntStream.range(0, number / 10).mapToObj(it -> "X").reduce(
                "",
                (a, b) -> a + b
        );
    }

    private String convertWithOnlyI(int number) {
        return IntStream.range(0, number).mapToObj(it -> "I").reduce(
                "",
                (a, b) -> a + b
        );
    }
}
