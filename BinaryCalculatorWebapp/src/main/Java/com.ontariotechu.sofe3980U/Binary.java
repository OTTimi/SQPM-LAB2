package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 */
public class Binary {
    private String number = "0"; // String containing the binary value "0" or "1"

    /**
     * Performs bitwise OR operation between two binary numbers.
     *
     * @param other Another Binary object.
     * @return A new Binary object representing the OR operation.
     */
    public Binary OR(Binary other) {
        StringBuilder result = new StringBuilder();
        String num1 = this.number;
        String num2 = other.getNumber();

        // Pad the shorter number with leading zeros
        int maxLength = Math.max(num1.length(), num2.length());
        num1 = String.format("%" + maxLength + "s", num1).replace(' ', '0');
        num2 = String.format("%" + maxLength + "s", num2).replace(' ', '0');

        for (int i = 0; i < maxLength; i++) {
            result.append(num1.charAt(i) == '1' || num2.charAt(i) == '1' ? '1' : '0');
        }

        return new Binary(result.toString());
    }

    /**
     * Performs bitwise AND operation between two binary numbers.
     *
     * @param other Another Binary object.
     * @return A new Binary object representing the AND operation.
     */
    public Binary AND(Binary other) {
        StringBuilder result = new StringBuilder();
        String num1 = this.number;
        String num2 = other.getNumber();

        // Pad the shorter number with leading zeros
        int maxLength = Math.max(num1.length(), num2.length());
        num1 = String.format("%" + maxLength + "s", num1).replace(' ', '0');
        num2 = String.format("%" + maxLength + "s", num2).replace(' ', '0');

        for (int i = 0; i < maxLength; i++) {
            result.append(num1.charAt(i) == '1' && num2.charAt(i) == '1' ? '1' : '0');
        }

        return new Binary(result.toString());
    }

    /**
     * Multiplies two binary numbers using repeated addition.
     *
     * @param other Another Binary object.
     * @return A new Binary object representing the multiplication.
     */
    public Binary Multiply(Binary other) {
        Binary result = new Binary("0");
        Binary multiplicand = this;

        for (int i = other.getNumber().length() - 1; i >= 0; i--) {
            if (other.getNumber().charAt(i) == '1') {
                result = result.add(multiplicand);
            }
            multiplicand = multiplicand.shiftLeft();
        }

        return result;
    }
}

