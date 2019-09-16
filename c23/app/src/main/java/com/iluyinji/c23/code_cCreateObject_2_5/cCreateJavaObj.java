package com.iluyinji.c23.code_cCreateObject_2_5;

import android.util.Log;

import com.iluyinji.c23.JNIDemo;

public class cCreateJavaObj {
    static{
        System.loadLibrary("cCreateJavaObject-lib");
    }

    // 第一种方法创建对象
    public static native void test1();

    // 第二种方法创建对象
    public static native void test2();

    //
    /* native 中字符串的操作
       java 是宽字节

    *  获取字符串的长度
       jsize GetStringLength(jstring j_msg)
       参数j_msg是一个jstring对象

    * 将jstring 对象拷贝到const jchar* 指针字符串:
        utf-8 编码存入jstr
       env->GetStringRegion(jstring j_msg, jsize start, jsize len, jchar* jstr);
       utf-16 编码存入jstr
       env->GetStringUTFRegion(jstring j_msg, jsize start, jsize len , char* jstr);

    *  生成一个jstirng对象
       jobject NewString(const jchar* jstr, int size)
       这个方法可以认为是将字符串指针jstr转换成字符串对象 jstring

    *  将jstring对象转换成const jchar*字符串指针。 有两个方法： GetStringCchars 和 GetStringUTFChars 方法
       GetStringChars:
        const * jchar* GetStringChars(jstring j_msg, jboolean* copied)

       返回一个utf-16编码的宽字符串 (jchar*)
       参数如下：
    * */

    // 定义一个 String 属性
    public String msg = null;
    public static void cOperJavaString(){
        String str = "123abc";
        cCreateJavaObj jniDemo = new cCreateJavaObj();
        jniDemo.msg = str;
        jniDemo.callCppFunction();
        Log.d("javazp", jniDemo.msg);
    }

    native void callCppFunction();


}
