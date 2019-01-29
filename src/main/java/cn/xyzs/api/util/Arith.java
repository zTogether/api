package cn.xyzs.api.util;

import java.math.BigDecimal;

/**
 * 加，减，乘，除，四舍五入，去尾，进一
 * @Author: zhouchao
 * @Date: 2019/1/29 0029 10:31
 */
public class Arith {
    //默认除法运算精度
    private static final int DEF_DIV_SCANLE = 10;
    private Arith(){}

    /**
     * 加法运算
     * @param v1
     * @param v2
     * @return
     */
    public static double add(double v1,double v2){
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.add(b2).doubleValue();
    }

    /**
     * 减法运算
     * @param v1
     * @param v2
     * @return
     */
    public static double sub(double v1,double v2){
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 乘法运算
     * @param v1
     * @param v2
     * @return
     */
    public static double mul(double v1,double v2){
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 除法运算
     * @param v1
     * @param v2
     * @return
     */
    public static double div(double v1,double v2){
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.divide(b2,DEF_DIV_SCANLE,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 保留N位小数,进行四舍五入
     * @param d
     * @return
     */
    public static Double saveOneBitRound(Double d,Integer n){
        BigDecimal bd = new BigDecimal(d);
        Double tem = bd.setScale(n,BigDecimal.ROUND_HALF_UP).doubleValue();
        return tem;
    }
    /**
     * 保留N位小数,进行去尾
     * @param d
     * @return
     */
    public static Double saveOneBitRoundDown(Double d,Integer n){
        BigDecimal bd = new BigDecimal(d);
        Double tem = bd.setScale(n,BigDecimal.ROUND_DOWN).doubleValue();
        return tem;
    }
    /**
     * 保留N位小数,进行进一
     * @param d
     * @return
     */
    public static Double saveOneBitRoundUp(Double d,Integer n){
        BigDecimal bd = new BigDecimal(d);
        Double tem = bd.setScale(n,BigDecimal.ROUND_UP).doubleValue();
        return tem;
    }

    public static void main(String[] args) {
        double d = Arith.saveOneBitRound(2.338933333,2);
        System.err.println(d);
    }
}
