package edu.unl.cc.succession.business;

import edu.unl.cc.succession.model.Printable;
import edu.unl.cc.succession.model.Successionable;

/**
 * Representa el calculo de la Serie de primos elevados a la raiz cuadrada
 * hasta un limite (S = 1^(1/2) + 3^(1/2) + 5^(1/2) + 7^(1/2) + 11^(1/2) + 13^(1/2)+ .. + N^(1/2):
 * @authors
 * William Granda
 * Hector Guerrero*
 * Matias Labanda
 * Ricardo Ochoa
 * Gabriel Suarez
 */

public class PrimeSquareRootCalculatorUpToLimit implements Successionable, Printable {

    private Number limit;

    public PrimeSquareRootCalculatorUpToLimit(Number limit) {
        this.limit = limit;
    }

    public void setLimit(Number limit) {
        this.limit = limit;
    }

    @Override
    public Number calculate() {
        double sum = 0.0;

        for (int n = 1; n <= limit.intValue(); n++) {
            if (n == 1 || (isPrime(n) && n != 2)) {
                sum += Math.pow(n, 1.0 / 2);
            }
        }

        return sum;
    }

    @Override
    public Number nextTerm(Number current) {
        if (current.intValue() == 1) {
            return 3;
        }

        int next = current.intValue() + 1;

        while (!isPrime(next)) {
            next++;
        }

        return next;
    }

    @Override
    public String print() {
        StringBuilder serie = new StringBuilder("S = ");
        boolean first = true;

        for (int n = 1; n <= limit.intValue(); n++) {
            if (n == 1 || (isPrime(n) && n != 2)) {
                if (!first) {
                    serie.append(" + ");
                }

                serie.append(n).append("^(1/2)");
                first = false;
            }
        }

        return serie.toString();
    }

    private boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}