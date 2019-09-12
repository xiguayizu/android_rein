package com.iluyinji.c23;

public class JNIDemo {
    public int number = 0;
    public native void sayHello();
    static{
        System.loadLibrary("JNIDemo-lib");
    }
    public void JNIDemo(){
        sayHello();
        System.out.println(number);
    }


    // c++中调用java方法1 & 2
    boolean function (int a, double b, char c){
        return true;
    }
    /*
    // 1:
        env->CallBooleanMethod(
            instance,        // 该类对象
            env->GetFieldID(
                env->GetObjectClass(instance),
                "function", "I"
            ),               // 获取偏移
            10L, 3.4, L'a'   // 参数，  L'a' 因为Java是Unicode 双字节, c++是单字节的
        );

    // 2:
        jvalue* args = new jvalue[3]; // 定义jvalue 数组
        args[0].i = 10L;
        args[1].d = 3.44;
        args[2].c = L'a';
        env->CallBooleanMethod(obj, id_function, args);
        delete[] args; // 是否指针堆内存
    * */

    // ...
}
