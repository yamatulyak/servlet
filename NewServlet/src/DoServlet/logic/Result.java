package DoServlet.logic;

public class Result {

    private  double result=10;
    public  Result() {
    }
    public void chet(double a, double b, char math){

        if(math == '+') {
            this.result = a + b;
        }
        else if(math == '-') {
            this.result = a - b;
        }
        else if(math == '*') {
            this.result = a * b;
        }
        else if(math == '/') {
            this.result = a / b;
        }

    }
    public double getRes() {
        return this.result;
    }

}
