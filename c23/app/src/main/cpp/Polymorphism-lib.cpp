//
// Created by Administrator on 2019/9/12 0012.
//

#include <jni.h>
#include <string>
#include <iostream>
#include <android/log.h>
#define LOG_TAG  "NDK_OUTPUT"
#define LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)


// c++ 中子类父类调用
// 不带 virtual 的
class Father1{
public:
    void function(){
        LOGD("c++ Father1:function");
    }
};
class Child1:public Father1{
public:
    void function(){
        LOGD("c++ Child1:function");
    }
};

// 带 virtual 的
class Father2{
public:
    void virtual function(){  // [*] virtual
        LOGD("c++ Father2:virtual function");
    }
};
class Child2:public Father2{
public:
    void function(){
        LOGD("c++ Child2:virtual function");
    }
};


// c++ 11 的调用顺序
extern "C"
JNIEXPORT void JNICALL
Java_com_iluyinji_c23_code_1callParent_12_14_Polymorphism_cppCallOrder(JNIEnv *env,
                                                                       jobject instance) {
    // TODO
    Child1 c1;
    c1.function();  // c++ !virtual 父类被调用
    Child2 c2;
    c2.function();  // c++ virtual 子类被调用
}


// c++ 调用 java 的调用顺序
extern "C"
JNIEXPORT void JNICALL
Java_com_iluyinji_c23_code_1callParent_12_14_Polymorphism_cppCallJava(JNIEnv *env,
                                                                       jobject instance) {
    // 获取obj中对象的class对象
    jclass clazz = env->GetObjectClass(instance);
    // 获取Java中的father字段的id（最后一个参数是father字段的签名）
    jfieldID id_father = env->GetFieldID(clazz, "cppCallPoint", "Lcom/iluyinji/c23/code_callParent_2_4/Father;");
    // 获取father字段的对象类型
    jobject father = env->GetObjectField(instance, id_father);
    // 获取father对象的class对象
    jclass clazz_father = env->FindClass("com/iluyinji/c23/code_callParent_2_4/Father");
    // 获取father对象中的function方法的id
    jmethodID id_father_function = env->GetMethodID(clazz_father, "function", "()V");
    // 调用父类中的function方法（但是会执行子类的方法）
    env->CallVoidMethod(father, id_father_function);
    // 调用父类中的function方法（执行就是父类中的function方法）
    env->CallNonvirtualVoidMethod(father, clazz_father, id_father_function);
}


