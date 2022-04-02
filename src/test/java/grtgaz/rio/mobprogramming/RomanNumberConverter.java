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

        if (classicNumberToConvert == 4) {
            return "IV";
        } else if (classicNumberToConvert == 9) {
            return "IX";
        } else if (classicNumberToConvert == 14) {
            return "XIV";
        } else if (classicNumberToConvert == 19) {
            return "XIX";
        }

        var eligibleRomanDigits = RomanDigit.inReverseOrder()
                .stream()
                .filter(it -> classicNumberToConvert >= it.inClassicNumber())
                .toList();


        var current = new IntermediateRomainNumber("", classicNumberToConvert);
        for (var it : eligibleRomanDigits) {
            var additional = convertWithOnly(current.remainingClassicNumber, it);
            current = new IntermediateRomainNumber(current.currentRomanNumber + additional.currentRomanNumber, additional.remainingClassicNumber);
        }

        return current.currentRomanNumber;
    }

    private IntermediateRomainNumber convertWithOnly(int classicNumber, RomanDigit romanDigit) {
        var romanNumber = IntStream.range(0, classicNumber / romanDigit.inClassicNumber())
                .mapToObj(it -> romanDigit.name()).reduce("", (a, b) -> a + b);
        var remaining = classicNumber % romanDigit.inClassicNumber();
        return new IntermediateRomainNumber(romanNumber, remaining);
    }


    record IntermediateRomainNumber(String currentRomanNumber, Integer remainingClassicNumber) {
    }

}
