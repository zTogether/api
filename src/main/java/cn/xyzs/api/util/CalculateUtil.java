package cn.xyzs.api.util;

import java.math.BigDecimal;

public class CalculateUtil {
    public static double GetResult(double numA, double numB, String operate){
        double res = 0;
        BigDecimal bigA = new BigDecimal(Double.toString(numA));
        BigDecimal bigB = new BigDecimal(Double.toString(numB));
        switch (operate) {

            case "+":
                res = bigA.add(bigB).doubleValue();
                break;
            case "-":
                res = bigA.subtract(bigB).doubleValue();
                break;
            case "*":
                res = bigA.multiply(bigB).doubleValue();
                break;
            case "/":
                res = bigA.divide(bigB).doubleValue();
                break;
            default :
                System.out.println("运算符不合法~");
                break;
        }
        return res;
    }
}
