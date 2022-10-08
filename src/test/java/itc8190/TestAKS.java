package itc8190;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestAKS {

    @Test
    void testCoefficientFinding_4() {
        List<BigInteger> expectedResult = List.of(
                BigInteger.valueOf(1L), BigInteger.valueOf(-4L), BigInteger.valueOf(6L), 
                BigInteger.valueOf(-4L), BigInteger.valueOf(1L)
        );
        List<BigInteger> result = AKS.findCoefficients(4);
        for (int i = 0; i < expectedResult.size(); i++) {
            assertThat(result.get(i)).isEqualByComparingTo(expectedResult.get(i));
        }
    }

    @Test
    void testCoefficientFinding_7() {
        List<BigInteger> expectedResult = List.of(
                BigInteger.valueOf(1L), BigInteger.valueOf(-7L), BigInteger.valueOf(21L), BigInteger.valueOf(-35L), 
                BigInteger.valueOf(35L), BigInteger.valueOf(-21L), BigInteger.valueOf(7L), BigInteger.valueOf(-1L)
        );
        List<BigInteger> result = AKS.findCoefficients(7);
        for (int i = 0; i < expectedResult.size(); i++) {
            assertThat(result.get(i)).isEqualByComparingTo(expectedResult.get(i));
        }
    }

    @Test
    void testCoefficientFinding_14() {
        List<BigInteger> expectedResult = List.of(
                BigInteger.valueOf(1), BigInteger.valueOf(-14L), BigInteger.valueOf(91L),
                BigInteger.valueOf(-364L), BigInteger.valueOf(1001L), BigInteger.valueOf(-2002L), 
                BigInteger.valueOf(3003L), BigInteger.valueOf(-3432L), BigInteger.valueOf(3003L), 
                BigInteger.valueOf(-2002L), BigInteger.valueOf(1001L), BigInteger.valueOf(-364L), 
                BigInteger.valueOf(91L), BigInteger.valueOf(-14L), BigInteger.valueOf(1L)
        );
        List<BigInteger> result = AKS.findCoefficients(14);
        for (int i = 0; i < expectedResult.size(); i++) {
            assertThat(result.get(i)).isEqualByComparingTo(expectedResult.get(i));
        }
    }

    // not prime numbers according to http://compoasso.free.fr/primelistweb/page/prime/liste_online_en.php
    @ParameterizedTest
    @ValueSource(ints = {4, 6, 9, 21, 169, 1207})
    void testIsPrimeByAKS_notPrimeNumbers(int number) {
        assertThat(AKS.isPrimeByAKS(number)).isFalse();
    }

    // prime numbers according to http://compoasso.free.fr/primelistweb/page/prime/liste_online_en.php
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 73, 127, 233, 283, 467, 661, 877, 1087, 1223})
    void testIsPrimeByAKS_withPrimeNumbers(int number) {
        assertThat(AKS.isPrimeByAKS(number)).isTrue();
    }
}
