//
// Created by Administrator on 2019/9/12 0012.
//

/*
    cmake 中添加正确的配置
*/
#include <jni.h>
#include <string>
#include <iostream>


extern "C"
JNIEXPORT void JNICALL
Java_com_iluyinji_c23_JNIDemo_sayHello(JNIEnv *env, jobject instance) {
    // 获取obj中的对象的class对象
    jclass clazz = env->GetObjectClass(instance);
    // 获取java中的number字段的id（最后一个参数是number的签名）
    jfieldID id_number = env->GetFieldID(clazz, "number", "I");
    // 获取number的值
    jint number = env->GetIntField(instance, id_number);
    // 输出到控制台
    std::cout << number << std::endl;
    // 修改number的值为100, 这里要注意的是 jint 对应的 c++ 是long类型， 所以后面呀偶家一个L
    env->SetIntField(instance, id_number, 100L);
}

