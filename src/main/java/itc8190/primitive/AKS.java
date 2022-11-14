package itc8190.primitive;

public class AKS {

    // https://www.jstor.org/stable/3597229?seq=1#metadata_info_tab_contents
    // https://en.wikipedia.org/wiki/AKS_primality_test
    // https://link.springer.com/content/pdf/10.1007/b12334.pdf
    public static boolean isPrime(long input) {
        // Validations
        if (input < 2) {
            throw new RuntimeException("Invalid input!");
        }

        // Step 1
        if (LongMath.isPerfectPower(input)) {
            return false;
        }

        // Step 2
        long r = LongMath.findR(input);

        // Step 3
        long minVal = Math.min(r, input - 1);
        for (long a = 2; a <= minVal; a++) {
            if (LongMath.findGcd(a, input) > 1) {
                return false;
            }
        }

        // Step 4
        if (input <= r) {
            return true;
        }

        // Step 5
        long limit = LongMath.findLimit(input, r);
        for (long a = 1L; a <= limit; a++) {
            if (!LongMath.arePolynomialsDivisible(a, input, r)) {
                return false;
            }
        }

        // Step 6
         return true;
    }

    public static void main(String[] args) {
        System.out.println(isPrime(29717813L));
    }
}