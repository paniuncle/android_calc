package com.orzmo.calc.controller;

import java.util.Arrays;

public class Calc {

    private String num1 = "";
    private String num2 = "";
    private String op = "";
    private Boolean canSetDot = true;

    public Calc() {

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

    /**
     * @description 设置小数点
     */
    public void setDot() {
        // 判断是否可以使用Dot
        if (this.canSetDot) {
            // 如果操作符为空则放入num1 否则放入num2
            if (this.op.equals("")) {
                // 判断num1是否为空，如果为空则增加0.
                if(this.num1.equals("")) {
                    this.num1 = "0.";
                } else {
                    this.num1 = this.num1 + ".";
                }
            } else {
                // 判断num2是否为空，如果为空则增加0.
                if(this.num2.equals("")) {
                    this.num2 = "0.";
                } else {
                    this.num2 = this.num2 + ".";
                }
            }

            this.canSetDot = false;
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

}
