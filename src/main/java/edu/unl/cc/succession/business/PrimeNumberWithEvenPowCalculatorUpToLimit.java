package edu.unl.cc.succession.business;

import edu.unl.cc.succession.model.Printable;
import edu.unl.cc.succession.model.Successionable;

/**
 * Representa del calculo de la Serie de primos elevados por pares hasta un limite (S = 1^2 + 3^4 + 5^6 + 7^8 + 11^10 + 13^(12) ... + N):
 * Autores:
 * William Granda
 * Hector Guerrero
 * Matias Labanda
 * Ricardo Ochoa
 * Gabriel Suarez
 */

public class PrimeNumberWithEvenPowCalculatorUpToLimit implements Successionable, Printable {

    private Integer limit;
    private Integer currentTerm;
    private StringBuilder printableTerms;


    public PrimeNumberWithEvenPowCalculatorUpToLimit(Integer limit) {
        this(1, limit);
    }

    public PrimeNumberWithEvenPowCalculatorUpToLimit(Integer start, Integer limit) {
        start = validateLimit(start, "Downn limit");
        setLimit(limit);
        this.currentTerm = nextTerm(start-1).intValue();
        printableTerms = new StringBuilder("S = ");
    }


    private Integer validateLimit(Number value, String label) {
        if  (value == null) {
            throw new IllegalArgumentException(label + " cannot be null");
        }
        if  (value instanceof Integer) {
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
        this.limit = validateLimit(limit, "Upper limit");
    }


    @Override
    public String print() {
        return this.printableTerms.toString();
    }

    @Override
    public Number calculate() {
        double result = 0;
        int exponent = 2;
        while (currentTerm <= limit) {
            this.printableTerms.append(String.valueOf(currentTerm)).append("^").append(String.valueOf(exponent)).append(" + ");
            result = result + Math.pow(currentTerm, exponent);//result += currentTerm;
            currentTerm = this.nextTerm(currentTerm).intValue();
            exponent+=2;
        }
        return result;
    }

    @Override
    public Number nextTerm(Number current) {
        current = current.intValue() + 1;
        if (current.intValue() == 2){
            current = 3;
        }

        boolean isPrime = false;
        while (!isPrime){
            isPrime = isPrime(current.intValue());
            if (!isPrime){
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
}
