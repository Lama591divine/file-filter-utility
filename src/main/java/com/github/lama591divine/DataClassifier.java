package com.github.lama591divine;

import java.math.BigInteger;

public class DataClassifier {

    public enum DataType {
        INTEGER, FLOAT, STRING, UNKNOWN
    }

    public DataType classify(String line) {
        if (line.matches("-?\\d+")) {
            return DataType.INTEGER;
        }

        if (line.matches("-?\\d*\\.\\d*([eE][-+]?\\d+)?")) {
            return DataType.FLOAT;
        }

        return DataType.STRING;
    }

    public BigInteger parseInteger(String line) {
        return new BigInteger(line);
    }

    public double parseFloat(String line) {
        return Double.parseDouble(line);
    }
}