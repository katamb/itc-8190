package itc8190;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class TestAKSComplex {

    @ParameterizedTest
    @ValueSource(ints = {4, 8, 9, 16, 25, 27, 32, 36, 49, 64, 81, 100, 121, 125, 128, 144, 169, 196, 216, 225, 243, 256, 289, 324, 343, 361, 400, 441, 484, 512, 529, 576, 625, 676, 729, 784, 841, 900, 961, 1000})
    void testIsPerfectPower_perfectPowers(int number) {
        assertThat(AKSComplex.isPerfectPower(number)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 7, 10, 15, 24, 101, 120, 126, 129, 145, 168, 197, 212, 519, 556, 624, 674, 724, 785, 842, 901, 960, 1001, 33469})
    void testIsPerfectPower_notPerfectPowers(int number) {
        assertThat(AKSComplex.isPerfectPower(number)).isFalse();
    }
}
