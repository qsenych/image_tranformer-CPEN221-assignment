package ca.ubc.ece.cpen221.ip.mp;

public class Complex {
    private final double re;
    private final double im;

    public Complex(double real, double complex) {
        re = real;
        im = complex;
    }

    public double getRe() { return this.re; }
    public double getIm() { return this.im; }

    public Complex add(Complex that) {
        return new Complex(this.re + that.re, this.im + that.im);
    }

    public Complex subtract(Complex that) {
        return new Complex(this.re - that.re, this.im - that.im);
    }

    public Complex times(Complex that) {
        double real = this.re * that.re - this.im * that.im;
        double comp = this.re * that.im + this.im * that.re;
        return new Complex(real, comp);
    }

    public double magnitude() {
        return Math.hypot(this.re, this.im);
    }

    public static Complex realToComplex(double val) {
        return new Complex(val, 0.0);
    }
}
