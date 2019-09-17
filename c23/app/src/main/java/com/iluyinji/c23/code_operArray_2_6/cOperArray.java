package com.iluyinji.c23.code_operArray_2_6;

import com.iluyinji.c23.code_callParent_2_4.Father;

public class cOperArray {
    static{
        System.loadLibrary("cOperJavaArray-lib");
    }
    // 定义一个int型数组
    int[] arrays = {4,3,12,56,1,23,45,67};
    // 定义Father对象数组
    Father[] objArrays = {new Father(), new Father(), new Father()};
    // 定义一个本地方法
    public native void callCppFunction();
    public static void cOperArray(){
        cOperArray coper = new cOperArray();
        coper.callCppFunction();
    }
}
