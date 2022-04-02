package grtgaz.rio.mobprogramming;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class MobProgrammingApplicationTests {

    RomanNumberConverter romanNumberConverter = new RomanNumberConverter();

    @ParameterizedTest
    @MethodSource("romainNumberExamples")
    void convert_number_to_roman_number(int number, String romainNumber) {
        assertThat(romanNumberConverter.convert(number)).isEqualTo(romainNumber);
    }

    private static Stream<Arguments> romainNumberExamples() {
        return Stream.of(
                Arguments.of(1, "I"),
                Arguments.of(2, "II"),
                Arguments.of(3, "III"),
                Arguments.of(5, "V"),
                Arguments.of(10, "X"),
                Arguments.of(20, "XX"),
                Arguments.of(30, "XXX")
        );
    }
}
