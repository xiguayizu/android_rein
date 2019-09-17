#include <jni.h>
#include <string>
#include "stdio.h"
#include <iostream>
#include <string>
#include <algorithm>

#include <android/log.h>
#include <locale>

#define LOG_TAG  "NDK_OUTPUT"
#define LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)



extern "C"
JNIEXPORT void JNICALL
Java_com_iluyinji_c23_code_1operArray_12_16_cOperArray_callCppFunction(JNIEnv *env,
                                                                       jobject instance) {


    // 获取java中数组属性arrays的id
    jfieldID fid_arrays = env->GetFieldID(env->GetObjectClass(instance), "arrays", "[I");
    // 获取Java中数组属性arrays的对象
    jintArray jint_arr = (jintArray)env->GetObjectField(instance, fid_arrays);
    // 获取arrays对象的指针
    jint* int_arr = env->GetIntArrayElements(jint_arr, NULL);
    // 获取数组长度
    jsize len = env->GetArrayLength(jint_arr);
    // 打印数组中的值
    LOGD("数组的值为：");
    for( int s =0;s<len;s++ ){
        LOGD("%d ,", int_arr[s]);
    }



    // 新建一个 jintArray 对象
    jintArray jint_arr_temp = env->NewIntArray(len);
    // 获取jint_arr_temp 对象的指针
    jint* int_arr_temp = env->GetIntArrayElements(jint_arr_temp, NULL);

    // 计数
    jint count = 0;
    // 偶数位存入到int_arr_temp 内存中
    for( jsize j=0;j<len;j++ ){
        if(j%2 == 0){
            int_arr_temp[count++] = int_arr[j];
        }
    }
    // 打印int_arr_temp内存中的数组
    LOGD("数组中偶数的值为：");
    for( int s =0;s<count;s++ ){
        LOGD("%d ,", int_arr_temp[s]);
    }

    // 将数组中的一段(0-2)数据拷贝到内存中, 并打印出来
    jint* buffer = new jint[len];
    // 获取数组中从0开始长度为3的一段数据值
    env->GetIntArrayRegion(jint_arr, 0, 3, buffer);
    LOGD("打印数组中0-3一段值：");
    for( int l=0;l<3;l++ ){
        LOGD("%d, ", buffer[1]);
    }

    // 将数组中的一段(3-7)设置成一定的值， 并且打印
    jint* buffers = new jint[4];
    for( int n=0;n<4;n++ ){
        buffers[n] = n+1;
    }
    // 将buffers这个数组中值设置到数组从3开始长度是4的值中
    env->SetIntArrayRegion(jint_arr, 3, 4, buffers);
    // 从新获取数组指针
    int_arr = env->GetIntArrayElements(jint_arr, NULL);
    LOGD("数组中37-这段值变成了：");
    for( int m=0;m<len;m++ ){
        LOGD("%d, ", int_arr[m]);
    }

    // 调用c++标准库中的排序方法 sort(...), 传递一个数组的开始指针和结束指针
    std::sort(int_arr, int_arr+len);
    // 迭代打印数组中的元素
    LOGD("排序后：");
    for( jsize i=0;i<len;i++ ){
        LOGD("%d, ", int_arr[i]);
    }
    // 释放数组指针
    env->ReleaseIntArrayElements(jint_arr, int_arr, JNI_ABORT);



    // 获取Java中对象Father数组属性的id
    jfieldID fid_obj_arrays =
            env->GetFieldID(env->GetObjectClass(instance), "objArrays", "[Lcom/iluyinji/c23/code_callParent_2_4/Father;");
    // 获取Java中对象数组Father属性objArrays的对象
    jobjectArray  jobj_arr = (jobjectArray)env->GetObjectField(instance, fid_obj_arrays);
    // 从对象数组中获取索引为1的对象Father
    jobject jobj = env->GetObjectArrayElement(jobj_arr, 1);
    // 获取Father对象的class对象
    jclass clazz_father = env->GetObjectClass(jobj);
    // 获取Father对象中的function方法的id
    jmethodID id_father_function = env->GetMethodID(clazz_father, "function", "()V");
    // 调用Father对象中的function方法
    env->CallVoidMethod(jobj, id_father_function);

    // 在本地创建一个大小为10的对象数组，对象的初始化都是jobj
    // 也就是方法的第三个参数
    jobjectArray jobj_arr_temp = env->NewObjectArray(10, env->GetObjectClass(jobj), jobj);
    // 获取本地对象数组中第4个对象
    jobject jobj_temp = env->GetObjectArrayElement(jobj_arr_temp, 3);
    // 调用Father对象中的function方法
    env->CallVoidMethod(jobj_temp, id_father_function);

}








