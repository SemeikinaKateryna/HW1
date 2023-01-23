import static java.lang.Math.*;

public class Main {
    double a = 2.7;
    double b = -0.3;
    double c = 4;
    double[] arrayX;
    double[] arrayY;
    public static final double err = 1e-9;
    double func(double x){
        double y = 0;
        if(x < 1.4){
            y = a * pow(x,2) + b * x + c;
        } else if ((abs(x - 1.4)) <= err) {
            y = a/x + sqrt(pow(x,2) + 1);
        } else if (x > 1.4) {
            y = (a + b * x) / sqrt(pow(x,2) + 1);
        }
        return y;
    }
    int getSteps(double begin, double end, double delta){
        int steps = 0;
        for (double i = begin; i < end + err; i += delta) {
            steps++;
        }
        return steps;
    }
    double[] getArrayOfX(double begin, double end, double delta){
        arrayX = new double[getSteps(begin,end,delta)];
        int j = -1;
        for (double i = begin; i <= end + err; i += delta) {
            arrayX[++j] = i;
        }
        return arrayX;
    }
    double[] getArrayOfY(double[] arrayX){
        arrayY = new double[arrayX.length];
        for (int i = 0; i < arrayX.length; i++) {
            arrayY[i] = func(arrayX[i]);
        }
        return arrayY;
    }

    double getMaxY(double[] arrayY){
        double maxY = arrayY[0];
        for (double y: arrayY) {
            if(y > maxY){
                maxY = y;
            }
        }
        return maxY;
    }

    double getMinY(double[] arrayY){
        double minY = arrayY[0];
        for (double y: arrayY) {
            if(y < minY){
                minY = y;
            }
        }
        return minY;
    }

    double getSumY(double[] arrayY){
        double sum = 0.0;
        for (double y: arrayY) {
            sum += y;
        }
        return sum;
    }

    double getAverageY(double[] arrayY){
        double average = getSumY(arrayY)/arrayY.length;
        return average;
    }

    void printMaxY(double[] arrayY){
        double max = getMaxY(arrayY);
        int index = 0;
        for (int i = 0; i < arrayY.length; i++) {
            if(arrayY[i] == max){
                index = i;
            }
        }
        System.out.println("Max Y = " + max + "\tIndex : " + index + " X = " + arrayX[index]);
    }

    void printMinY(double[] arrayY){
        double min = getMinY(arrayY);
        int index = 0;
        for (int i = 0; i < arrayY.length; i++) {
            if(arrayY[i] == min){
                index = i;
            }
        }
        System.out.println("Min Y = " + min + "\tIndex : " + index + " X = " + arrayX[index]);
    }

    public static void main(String[] args) {
        Main main = new Main();
        double[] bufferedArrayOfX = main.getArrayOfX(0.0, 2.0, 0.002);
        double[] bufferedArrayOfY = main.getArrayOfY(bufferedArrayOfX);

        for (int i = 0; i < bufferedArrayOfY.length; i++) {
            System.out.println("[" + i + "] x = " + bufferedArrayOfX[i] + " y = " + bufferedArrayOfY[i]);
        }
        main.printMaxY(bufferedArrayOfY);
        main.printMinY(bufferedArrayOfY);
    }
}
