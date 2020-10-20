package com.orzmo.calc.controller;

import android.telecom.Call;

import com.orzmo.calc.utils.CallBack;

import java.math.BigDecimal;
import java.util.Arrays;

public class Calc {
    private static final String TAG = "Calc";
    private String num1 = "";
    private String num2 = "";
    private String op = "";
    private Boolean canSetDot = true;
    private CallBack cba;


    public Calc() {

    }


    public Calc(CallBack cb) {
        this.cba = cb;
    }

    /**
     * @description 设置数字
     * @param c
     */
    public void setNum(char c) {
        // 如果操作符为空 则放入num1 否则放入num2
        if (this.op.equals("")) {
            this.num1 = this.num1 + c;
        } else {
            this.num2 = this.num2 + c;
        }

    }

    public void setOp(char c, CallBack cb) {
        String temp = String.valueOf(c);
        // 当num2不为空
        if(this.num1.equals("")) {
            cb.run("你不能先输入符号");
            return;
        }
        this.op = String.valueOf(c);
        this.canSetDot = true;

        if (temp.equals("u")) {
            if (Double.parseDouble(this.num1) < 0){
                cb.run("您输入不能为负数");
            }else {
                this.formatNum(Math.sqrt(Double.parseDouble(this.num1)));
            }

        }

        if (temp.equals("i")) {
            this.formatNum(Math.sin(Double.parseDouble(this.num1)));
        }

//        if(!num2.equals("")){
//            String temp = this.equals();
//            this.resetCalc();
//            this.num1 = temp;
//            this.op = String.valueOf(c);
//        } else {
//            this.op = String.valueOf(c);
//            this.canSetDot = true;
//        }
    }

    /**
     * @description 设置小数点
     */
    public void setDot(CallBack cb) {
        // 判断是否可以使用Dot
        if (this.canSetDot) {
            // 如果操作符为空则放入num1 否则放入num2
            if (this.op.equals("")) {
                // 判断num1是否为空，如果为空则增加0.
                if(this.num1.equals("") || this.num1.equals("-")) {
                    this.num1 = this.num1 + "0.";
                } else {
                    this.num1 = this.num1 + ".";
                }
            } else {
                // 判断num2是否为空，如果为空则增加0.
                if(this.num2.equals("") || this.num2.equals("-")) {
                    this.num2 = this.num2 + "0.";
                } else {
                    this.num2 = this.num2 + ".";
                }
            }

            this.canSetDot = false;
        } else {
            cb.run("已经按过小数点了");
        }

    }

    /**
     * @description 获取现在的操作数文字内容
     * @return
     */
    public String getNowNum() {
        if (this.op.equals("")){
            return this.num1;
        } else {
            return this.num2;
        }
    }

    /**
     * @description 数组转字符串
     * @param o
     * @param flag
     * @return
     */
    private String join( Object[] o , String flag ){
        StringBuffer str_buff = new StringBuffer();

        for(int i=0 , len=o.length ; i<len ; i++){
            str_buff.append( String.valueOf( o[i] ) );
            if(i<len-1)str_buff.append( flag );
        }

        return str_buff.toString();
    }

    /**
     * 删除最后一位文本
     */
    public void backInput() {
        if (this.op.equals("")) {
            String[] temp = this.num1.split("");
            String[] end = Arrays.copyOf(temp, temp.length-1);
            this.num1 = this.join(end, "");

            this.canSetDot = !this.num1.contains(".");
        } else {
            String[] temp = this.num2.split("");
            String[] end = Arrays.copyOf(temp, temp.length-1);
            this.num2 = this.join(end, "");
            this.canSetDot = !this.num1.contains(".");
        }
    }

    /**
     * @description 取反
     */
    public void revers(CallBack cb) {
        // 如果都是空则不执行
        if(this.num1.equals("") && this.num2.equals("")) {
            cb.run("输入为空不能取反！");
            return;
        }

        if (this.op.equals("")) {
            String[] temp = this.num1.split("");

            String[] end;

            if(temp[1].equals("-")){
                end = Arrays.copyOfRange(temp,2,temp.length);
                this.num1 = this.join(end, "");
            }else{
                end = Arrays.copyOf(temp, temp.length);
                this.num1 = this.join(end, "");
                this.num1 = "-" + this.num1;
            }
        } else {
            String[] temp = this.num2.split("");

            String[] end;
            if(temp[1].equals("-")){
                end = Arrays.copyOfRange(temp,2,temp.length);
                this.num2 = this.join(end, "");
            }else{
                end = Arrays.copyOf(temp, temp.length);
                this.num2 = this.join(end, "");
                this.num2 = "-" + this.num2;
            }
        }
    }

    /**
     * @description 重设计算器
     */
    public void resetCalc() {
        this.num1 = "";
        this.num2 = "";
        this.op = "";
        this.canSetDot = true;
    }

    /**
     * @description 等于运算
     * @return
     */
    public String equals() {
        if (this.num1.equals("") || this.num2.equals("") || this.op.equals("")) {
            return this.getNowNum();
        }

        switch (this.op) {
            case "m":
                return formatNum(this.getNum1() % this.getNum2());
            case "d":
                if (this.num2.equals("0")){
                    this.cba.run("除数不为0");
                } else{
                    return formatNum(this.getNum1() / this.getNum2());
                }

            case "t":
                return formatNum(this.getNum1() * this.getNum2());
            case "s":
                return formatNum(this.getNum1() - this.getNum2());
            case "p":
                return formatNum(this.getNum1() + this.getNum2());
            default:
                return "";
        }
    }

    /**
     *
     * @param d
     * @return
     */
    private String formatNum(Double d) {
        System.out.println(d);
        this.resetCalc();
        BigDecimal bg = new BigDecimal(d).setScale(8, BigDecimal.ROUND_HALF_UP);
        double num = bg.doubleValue();
        if (Math.round(d) - d == 0) {
            this.num1 = String.valueOf(Math.round(d));
            return String.valueOf(Math.round(d));
        }
        this.num1 = String.valueOf(num);
        this.canSetDot = false;
        return String.valueOf(d);
    }

    /**
     * @description 获取double类型的num1
     * @return
     */
    private Double getNum1() {
        return Double.valueOf(this.num1);
    }

    /**
     * @description 获取double类型的num2
     * @return
     */
    private Double getNum2() {
        return Double.valueOf(this.num2);
    }

}
