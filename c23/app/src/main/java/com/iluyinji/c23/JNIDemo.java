package com.iluyinji.c23;

public class JNIDemo {
    public int number = 0;
    public native void sayHello();
    static{
        System.loadLibrary("JNIDemo-lib");
    }
    public JNIDemo(){
        sayHello();
        System.out.println(number);
    }
}
