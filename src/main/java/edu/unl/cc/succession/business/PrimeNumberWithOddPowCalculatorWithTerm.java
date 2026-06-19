package edu.unl.cc.succession.business;

import edu.unl.cc.succession.model.Printable;
import edu.unl.cc.succession.model.Successionable;

/**
 * Representa el calculo de la Serie de primos elevados a impares hasta n térmimos (S = S = 1^1 + 3^3 + 5^5 + 7^7 + 11^9 + 13^11 ..):
 * Autores:
 * William Granda
 * Hector Guerrero
 * Matias Labanda*
 * Ricardo Ochoa
 * Gabriel Suarez
 */

public class PrimeNumberWithOddPowCalculatorWithTerm implements Successionable, Printable {

    private Integer nTerm;
    private Integer currentTerm;
    private StringBuilder printableTerms;

    public PrimeNumberWithOddPowCalculatorWithTerm(Integer limit) {
        this(1, limit);
    }

    public PrimeNumberWithOddPowCalculatorWithTerm(Integer start, Integer limit) {
        start = validateLimit(start, "Downn limit");
        setLimit(limit);
        this.currentTerm = nextTerm(start - 1).intValue();
        printableTerms = new StringBuilder("S = ");
    }

    private Integer validateLimit(Number value, String label) {
        if (value == null) {
            throw new IllegalArgumentException(label + " cannot be null");
        }
        if (value instanceof Integer) {
            if (value.intValue() < 0) {
                throw new IllegalArgumentException(label + " cannot be negative");
            }
            return value.intValue();
        } else {
            throw new IllegalArgumentException(label + " must be an integer");
        }
    }

    @Override
    public void setLimit(Number limit) {
        this.nTerm = validateLimit(limit, "nTerm");
    }

    @Override
    public Number calculate() {
        double result = 0;
        int countTerm = 0;
        int exponent = 1;

        while (countTerm < nTerm) {

            this.printableTerms
                    .append(currentTerm)
                    .append("^")
                    .append(exponent)
                    .append(" + ");

            result += Math.pow(currentTerm, exponent);

            currentTerm = nextTerm(currentTerm).intValue();

            exponent += 2;
            countTerm++;
        }

        return result;
    }

    @Override
    public Number nextTerm(Number current) {
        current = current.intValue() + 1;

        if (current.intValue() == 2) {
            current = 3;
        }

        boolean isPrime = false;
        while (!isPrime) {
            isPrime = isPrime(current.intValue());
            if (!isPrime) {
                current = current.intValue() + 1;
            }
        }
        return current;
    }

    private boolean isPrime(Integer number) {
        if (number < 1) {
            return false;
        }

        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String print() {
        return this.printableTerms.toString();
    }
}
