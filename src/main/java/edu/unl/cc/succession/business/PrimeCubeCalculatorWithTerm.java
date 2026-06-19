package edu.unl.cc.succession.business;

import edu.unl.cc.succession.model.Printable;
import edu.unl.cc.succession.model.Successionable;

/**
 * Representa el cálculo de la Serie números Primos elevados al cubo hasta N términos
 * S = 1^3 + 3^3 + 5^3 + 7^3 + 11^3 + 13^3 + ...
 * @authors
 * William Granda
 * Hector Guerrero
 * Matias Labanda
 * Ricardo Ochoa
 * Gabriel Suarez
 */
public class PrimeCubeCalculatorWithTerm implements Successionable, Printable {

    private Integer nTerm;
    private Integer currentTerm;
    private StringBuilder printableTerms;

    public PrimeCubeCalculatorWithTerm(Integer limit) {
        this(1, limit);
    }

    public PrimeCubeCalculatorWithTerm(Integer start, Integer limit) {
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
        long result = 0;
        int countTerm = 0;
        while (countTerm < nTerm) {
            long cube = (long) Math.pow(currentTerm, 3);
            this.printableTerms.append(currentTerm).append("^3 + ");
            result = result + cube;
            currentTerm = nextTerm(currentTerm).intValue();
            countTerm++;
        }
        return result;
    }

    @Override
    public Number nextTerm(Number current) {
        current = current.intValue() + 1;
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
        if (number <= 1) {
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
