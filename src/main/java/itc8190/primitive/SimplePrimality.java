package itc8190.primitive;

public class SimplePrimality {

    static boolean isPrime(long input) {
        long maxTestValue = (long) Math.sqrt(input);

        for (long i = 2L; i <= maxTestValue; i++) {
            if (input % i == 0) {
                return false;
            }
        }

        return true;
    }


    public static void main(String[] args) {
        // Input value to test here
        boolean result = isPrime(2971781927L);
        if (result) {
            System.out.println("Prime");
        } else {
            System.out.println("Not Prime");
        }
    }
    // ~350ms for 13127
    // ~400ms for 33469
    // ~440ms for 1312783547
    // ~16sec for 306680863762905727
}