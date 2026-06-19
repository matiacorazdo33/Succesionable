package edu.unl.cc.succession.business;

import edu.unl.cc.succession.model.Printable;
import edu.unl.cc.succession.model.Successionable;

/**
 * Clase de dominio: contiene toda la lógica de negocio
 * para generar números primos y calcular la serie de
 * raíces cúbicas: S = 1^(1/3) + 3^(1/3) + 5^(1/3) + 7^(1/3) + ...
 * Autores:
 * William Granda
 * Hector Guerrero
 * Matias Labanda
 * Ricardo Ochoa*
 * Gabriel Suarez
 */

public class SeriePrimosElevadosCubica implements Successionable, Printable {

    private Integer limit;
    private Integer currentTerm;
    private Integer countTerms;
    private StringBuilder printableTerms;

    public SeriePrimosElevadosCubica(Integer limit) {
        this(0, limit);
    }

    public SeriePrimosElevadosCubica(Integer start, Integer limit) {
        start = validateLimit(start, "Down limit");
        setLimit(limit);
        this.countTerms = 0;
        this.currentTerm = 1;
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
        this.limit = validateLimit(limit, "Upper limit");
    }

    @Override
    public Number calculate() {
        double result = 0.0;
        while (this.countTerms < this.limit) {
            this.printableTerms.append(this.currentTerm)
                    .append("^(1/3)")
                    .append("  + ");
            result = result + raizCubica(this.currentTerm);
            this.countTerms++;
            this.currentTerm = this.nextTerm(this.currentTerm).intValue();
        }
        return result;
    }

    @Override
    public Number nextTerm(Number current) {
        if (current.intValue() == 1) {
            return 3;
        }
        return siguientePrimo(current.intValue());
    }

    @Override
    public String print() {
        return printableTerms.toString();
    }

    /**
     * Determina si un número es primo.
     */
    public boolean esPrimo(int numero) {
        if (numero < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Encuentra el siguiente número primo después de "actual".
     */
    private int siguientePrimo(int actual) {
        int candidato = actual + 1;
        while (!esPrimo(candidato)) {
            candidato++;
        }
        return candidato;
    }

    /**
     * Calcula la raíz cúbica de un número.
     */
    public double raizCubica(double numero) {
        return Math.cbrt(numero);
    }
}