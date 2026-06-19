package edu.unl.cc.succession.business;

import edu.unl.cc.succession.model.Printable;
import edu.unl.cc.succession.model.Successionable;

/**
 * Representa el calculo de la Serie de primos elevados a la raiz de numeros pares
 * hasta un limite (S = 1^(1/2) + 3^(1/4) + 5^(1/6) + 7^(1/8) + 11^(1/10) + 13^(1/12) ... + N):
 * Autores:
 * William Granda
 * Hector Guerrero
 * Matias Labanda
 * Ricardo Ochoa
 * Gabriel Suarez
 */


public class PrimeRootEvenCalculatorUpToLimit implements Successionable, Printable {

    private Number limit;

    public PrimeRootEvenCalculatorUpToLimit(Number limit) {
        this.limit = limit;
    }

    @Override
    public void setLimit(Number limit) {
        this.limit = limit;
    }

    @Override
    public Number calculate() {
        double sum = 0;
        int even = 2;

        for (int n = 1; n <= limit.intValue(); n++) {
            if (n == 1 || isPrime(n)) {
                sum += Math.pow(n, 1.0 / even);
                even += 2;
            }
        }

        return sum;
    }

    @Override
    public Number nextTerm(Number current) {
        int next = current.intValue() + 1;

        while (!isPrime(next)) {
            next++;
        }

        return next;
    }

    @Override
    public String print() {
        return "Serie de primos elevados a la raiz de numeros pares hasta un limite";
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