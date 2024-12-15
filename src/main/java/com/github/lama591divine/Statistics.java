package com.github.lama591divine;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Statistics {

    long intCount;
    long floatCount;
    long stringCount;
    BigInteger minInt;
    BigInteger maxInt;
    BigInteger sumInt;
    BigDecimal sumFloat;
    double minFloat;
    double maxFloat;
    int minStringLength;
    int maxStringLength;

    public Statistics() {
        intCount = 0;
        floatCount = 0;
        stringCount = 0;
        minInt = null;
        maxInt = null;
        sumInt = BigInteger.ZERO;
        sumFloat = BigDecimal.ZERO;
        minFloat = Double.MAX_VALUE;
        maxFloat = Double.MIN_VALUE;
        minStringLength = Integer.MAX_VALUE;
        maxStringLength = Integer.MIN_VALUE;
    }

    public void addInteger(BigInteger value) {
        intCount++;
        sumInt = sumInt.add(value);
        if (minInt == null || value.compareTo(minInt) < 0) {
            minInt = value;
        }
        if (maxInt == null || value.compareTo(maxInt) > 0) {
            maxInt = value;
        }
    }

    public void addFloat(double value) {
        floatCount++;
        BigDecimal bd = BigDecimal.valueOf(value);
        sumFloat = sumFloat.add(bd);
        if (value < minFloat) {
            minFloat = value;
        }
        if (value > maxFloat) {
            maxFloat = value;
        }
    }

    public void addString(String str) {
        stringCount++;
        int len = str.length();
        if (len < minStringLength) {
            minStringLength = len;
        }
        if (len > maxStringLength) {
            maxStringLength = len;
        }
    }

    public void printStatistics(boolean shortStats, boolean fullStats, boolean hasIntegers, boolean hasFloats, boolean hasStrings) {
        if (hasIntegers) {
            if (shortStats) {
                System.out.println("Statistics for integers:");
                System.out.println("Count: " + intCount + "\n");
            } else if (fullStats) {
                System.out.println("Statistics for integers:");
                System.out.println("Count: " + intCount);
                System.out.println("Minimum value: " + minInt);
                System.out.println("Maximum value: " + maxInt);
                System.out.println("Sum: " + sumInt);
                System.out.println("Average: " + (intCount > 0 ? sumInt.divide(BigInteger.valueOf(intCount)) : 0) + "\n");
            }
        }

        if (hasFloats) {
            if (shortStats) {
                System.out.println("Statistics for floating-point numbers:");
                System.out.println("Count: " + floatCount + "\n");
            } else if (fullStats) {
                System.out.println("Statistics for floating-point numbers:");
                System.out.println("Count: " + floatCount);
                System.out.println("Minimum value: " + minFloat);
                System.out.println("Maximum value: " + maxFloat);
                System.out.println("Sum: " + sumFloat);
                System.out.println("Average: " + (floatCount > 0 ? sumFloat.divide(BigDecimal.valueOf(floatCount), 10, RoundingMode.HALF_UP) : 0) + "\n");
            }
        }

        if (hasStrings) {
            if (shortStats) {
                System.out.println("Statistics for strings:");
                System.out.println("Count: " + stringCount + "\n");
            } else if (fullStats) {
                System.out.println("Statistics for strings:");
                System.out.println("Count: " + stringCount);
                System.out.println("Minimum string length: " + minStringLength);
                System.out.println("Maximum string length: " + maxStringLength + "\n");
            }
        }

        if (!hasIntegers && !hasFloats && !hasStrings) {
            System.out.println("Cant find data: ");
        }
    }
}