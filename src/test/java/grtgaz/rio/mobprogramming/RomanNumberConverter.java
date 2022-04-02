package grtgaz.rio.mobprogramming;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RomanNumberConverter {

    enum RomanDigit {
        V(5), I(1), X(10);

        private final int classicNumber;

        RomanDigit(int i) {
            this.classicNumber = i;
        }

        public int inClassicNumber() {
            return classicNumber;
        }

        public static List<RomanDigit> inReverseOrder() {
            return Arrays.stream(RomanDigit.values())
                    .sorted(Comparator.comparingInt(RomanDigit::inClassicNumber).reversed())
                    .collect(Collectors.toList());
        }
    }

    public String convert(int classicNumberToConvert) {
        var eligibleRomainDigits = RomanDigit.inReverseOrder()
                .stream()
                .filter(it -> hasOnly(classicNumberToConvert, it))
                .findFirst()
                .orElseThrow();

        return convertWithOnly(classicNumberToConvert, eligibleRomainDigits);
    }

    private String convertWithOnly(int classicNumber, RomanDigit romanDigit) {
        return IntStream.range(0, classicNumber / romanDigit.inClassicNumber())
                .mapToObj(it -> romanDigit.name()).reduce("", (a, b) -> a + b);
    }

    private boolean hasOnly(int classicNumber, RomanDigit romainDigitValue) {
        return classicNumber % romainDigitValue.inClassicNumber() == 0;
    }

}
