package itc8190;

public class SimplePrimality {

    static Boolean isPrime(Integer testValue) {
        if (testValue == null || testValue < 0) {
            System.out.println("Only natural numbers are allowed as input");
            return null;
        }

        for (int i = 2; i < testValue; i++) {
            if (testValue % i == 0) {
                return false;
            }
        }

        return true;
    }


    public static void main(String[] args) {
        // Input value to test here
        Boolean result = isPrime(33469);
        if (result == null) {
            System.out.println("Unexpected input");
        } else if (result) {
            System.out.println("Prime");
        } else {
            System.out.println("Not Prime");
        }
    }
    // 490ms for 13127
    // 1.2sec for 33469
}