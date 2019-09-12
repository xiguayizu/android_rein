package com.iluyinji.c23;

import java.util.Date;


/*
查看签名:
# 先编译为 class
    javac -encoding UTF-8  .\Hello.java
# 再通过 javap 查看
    javap -s -p .\Hello.class
#*# 这里可以写成小工具勒
* */
public class Hello {
    public int property;
    public int function(int foo, Date date, int[] arr){
        System.out.println("function");
        return 0;
    }
    // native 中调用 java 方法
    public native void test();
}
