package itc8190;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Sources used for help/inspiration:
 * https://www.cse.iitk.ac.in/users/manindra/algebra/primality_v6.pdf
 * https://github.com/kisileno/CryptoMethods/blob/master/ProbablePrimeTest/src/main/java/com/kpi/aks/AKS.java
 */
public class AKSComplex {

    /**
     * Step 1 - check if the number is a perfect power
     * @param n
     * @return
     */
    static boolean isPerfectPower(int n) {
        if (n == 1) {
            return true;
        }
        int sqrtN = (int) Math.sqrt(n);
        for (int a = 2; a <= sqrtN; a++) {
            for (int b = 2; b <= n; b++) {
                long calculatedPower = (int) Math.pow(a, b);
                System.out.println("Calculated " + calculatedPower + " using a=" + a + " and b=" + b + ".");
                if (calculatedPower == n) {
                    return true;
                }
                if (calculatedPower > n) {
                    break;
                }
                // Handle integer overflow
                if (calculatedPower < 0) {
                    throw new RuntimeException("Integer overflow, @isPerfectPower");
                }
            }
        }
        return false;
    }

    // loga b = loge b / loge a
    static int findSmallestR(int n) {
        int result = logBaseTwo(n) * logBaseTwo(n);
        return result;
    }

    static int logBaseTwo(int n) {
        return (int)(Math.log(n) / Math.log(2));
    }

    public static void main(String[] args) {
        // Input value to test here
    }
}