package itc8190;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class AKS {

    /**
     * Using Pascal triangle, find coefficients for (x-1)^n.
     * I believe in this use case the sign (+/-) of the coefficients does not play any role, however adding the correct
     * signs does not change the complexity in O notation, so adding them seemed reasonable in this case.
     * The big O complexity of this method is O(n^2/2), which for large inputs can be noted as O(n^2)
     *
     * @param testValue - integer we are testing to be prime or not
     * @return list of coefficients
     */
    static List<BigInteger> findCoefficients(Integer testValue) {
        List<BigInteger> previousRow = new ArrayList<>();
        List<BigInteger> currentRow = new ArrayList<>();

        for (int row = 1; row <= testValue; row++) {
            boolean lastRow = row == testValue;

            currentRow = new ArrayList<>();
            for (int column = 0; column <= row; column++) {
                if (column == 0) {
                    currentRow.add(BigInteger.ONE);
                } else if (row == column) {
                    // Adding the correct sign to last value of the current row
                    currentRow.add(lastRow && column % 2 == 1 ? BigInteger.ONE.negate() : BigInteger.ONE);
                } else {
                    BigInteger rowValue = previousRow.get(column - 1).add(previousRow.get(column));
                    if (lastRow && column % 2 == 1) {
                        // Adding the negative sign to every second value of the current row
                        rowValue = rowValue.negate();
                    }
                    currentRow.add(rowValue);
                }
            }
            previousRow = currentRow;
        }

        return currentRow;
    }

    /**
     * Complexity O(n^2/2) + O(n), which for large inputs can be noted as O(n^2)
     *
     * @param testValue - integer we are testing to be prime or not
     * @return true if integer is prime
     */
    static Boolean isPrimeByAKS(Integer testValue) {
        if (testValue == null || testValue < 0) {
            System.out.println("Only natural numbers are allowed as input");
            return null;
        }
        // coefficients for (x-1)^n
        List<BigInteger> coefficients = findCoefficients(testValue);
        Integer coefficientListSize = coefficients.size();

        // Loop over the coefficients and check one-by-one if they are divisible by the input number
        // while adjusting coefficients for (x-1)^n - (x^n-1) = (x-1)^n - x^n + 1
        // this means skipping the first value and adding 1 to the last value
        for (int i = 1; i < coefficientListSize; i++) {
            BigInteger coefficient = coefficients.get(i);
            // add 1 to the last value
            if (i == coefficientListSize - 1) {
                coefficient = coefficient.add(BigInteger.ONE);
            }
            if (coefficient.remainder(BigInteger.valueOf(testValue)).compareTo(BigInteger.ZERO) != 0) {
                return false;
            }
        }

        return true;
    }


    public static void main(String[] args) {
        // Input value to test here
        Boolean result = isPrimeByAKS(6131);
        if (result == null) {
            System.out.println("Unexpected input");
        } else if (result) {
            System.out.println("Prime");
        } else {
            System.out.println("Not Prime");
        }
    }
}