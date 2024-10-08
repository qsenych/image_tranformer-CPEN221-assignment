package ca.ubc.ece.cpen221.ip.mp;

/**
 * An immutable datatype representing a complex number (re + im * j) where j is sqrt(-1)
 *
 */
public class Complex {
    private final double re;
    private final double im;

    /**
     * Creates a representation of a complex number.
     *
     * @param real: Any double representing the real part
     * @param complex: Any double representing the complex part of a number
     */
    public Complex (double real, double complex) {
        re = real;
        im = complex;
    }

    /**
     * @return the real part of the number.
     */
    public double getRe () { return this.re; }

    /**
     * @return the complex part of the number.
     */
    public double getIm () { return this.im; }

    /**
     * Adds 2 Complex numbers
     *
     * @param that: A non-null instance of a Complex Number.
     * @return A new Complex instance of the sum.
     */
    public Complex add (Complex that) {
        return new Complex(this.re + that.re, this.im + that.im);
    }

    /**
     * Subtracts 2 Complex numbers
     *
     * @param that: A non-null instance of a Complex Number.
     * @return A new Complex instance of the difference of this - that.
     */
    public Complex subtract (Complex that) {
        return new Complex(this.re - that.re, this.im - that.im);
    }

    /**
     * Multiplies 2 Complex numbers
     *
     * @param that: A non-null instance of a Complex Number.
     * @return A new Complex instance of the product.
     */
    public Complex times (Complex that) {
        double real = this.re * that.re - this.im * that.im;
        double comp = this.re * that.im + this.im * that.re;
        return new Complex(real, comp);
    }

    /**
     * @return the magnitude or absolute value of this instance.
     */
    public double magnitude () {
        return Math.hypot(this.re, this.im);
    }

    /**
     * A static method which converts a real double to a complex instance;
     *
     * @param val: Any double to be converted into a Complex instance.
     * @return A new Complex instance where re = val and im = 0.0
     */
    public static Complex realToComplex (double val) {
        return new Complex(val, 0.0);
    }
}
