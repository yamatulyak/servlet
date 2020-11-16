package DoServlet.logic;

public class Calc {

    private String math;

    private double a;

    private double b;

    public Calc(String math, double a, double b) {
        this.math = math;
        this.a = a;
        this.b = b;
    }

    public String getMath() {
        return math;
    }

    public void setMath(String math) {
        this.math = math;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }
}



