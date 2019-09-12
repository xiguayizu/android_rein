package com.iluyinji.c23.code_callParent_2_4;


// 多态， java性质
public class Polymorphism {


    static{
        System.loadLibrary("Polymorphism-lib");
    }

    Child cppCallPoint;


    // java 调用顺序
    public void javaCallOrder(){
        Child child = new Child();
        child.function();
        // 输出: “java 子类被调用”
    }
    // c++ 调用顺序
    public native void cppCallOrder();

    // c++ call java 调用顺序
    // 失败。。。
    public native void cppCallJava();

}
