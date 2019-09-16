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
Java_com_iluyinji_c23_code_1cCreateObject_12_15_cCreateJavaObj_test1(JNIEnv *env,
                                                                     jobject instance) {
    // 获取Java中Date对象的Class对象
    jclass clazz_date = env->FindClass("java/util/Date");
    // 获取构造方法的id
    jmethodID mid_date = env->GetMethodID(clazz_date, "<init>", "()V");

    // 生成Date对象
    jobject now = env->NewObject(clazz_date, mid_date);

    // 获取Date对象中的getTime方法的id
    jmethodID mid_date_getTime = env->GetMethodID(clazz_date, "getTime", "()J");
    // 调用getTime方法返回时间
    jlong time = env->CallLongMethod(now, mid_date_getTime);
    // 打印时间，这里要注意的是不能使用cout输出， 因为cout并没对 __int64 的输出进行重载
    __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, "%i64d", time);
}



extern "C"
JNIEXPORT void JNICALL
Java_com_iluyinji_c23_code_1cCreateObject_12_15_cCreateJavaObj_test2(JNIEnv *env, jclass type) {

    // TODO
    // 获取java中的Date对象
    jclass clazz_date = env->FindClass("java/util/Date");
    jmethodID jmethodID1 = env->GetMethodID(clazz_date, "<init>", "()V");
    jobject now = env->AllocObject(clazz_date);
    // 调用构造方法
    env->CallNonvirtualVoidMethod(now, clazz_date, jmethodID1);
    //获取Date对象中的getTime方法id
    jmethodID mid_date_getTime = env->GetMethodID(clazz_date, "getTime", "()J");
    // 调用getTime方法返回时间
    jlong time = env->CallLongMethod(now, mid_date_getTime);
    // 打印时间，这里要注意是不能用cout输出
    __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, "%i64d", time);
}


extern "C"
JNIEXPORT void JNICALL
Java_com_iluyinji_c23_code_1cCreateObject_12_15_cCreateJavaObj_callCppFunction(JNIEnv *env,
                                                                               jobject instance) {
    // TODO
    // 获取java中的属性 :msg
    jfieldID fid_msg = env->GetFieldID(
            env->GetObjectClass(instance),
            "msg",
            "Ljava/lang/String;"
            );
    // 获取属性msg的对象
    jstring j_msg = (jstring)env->GetObjectField(instance, fid_msg);

    /* 第一种方式 start----------------------------------------------------------------
     * */
    // 获得字符串指针
    const jchar* jstr = env->GetStringChars(j_msg, NULL);
    // 转换成宽字符串
    (std::wstring((const wchar_t*)jstr));
    // 释放指针
    env->ReleaseStringChars(j_msg, jstr);
    // 第一种方式 end

    // 第二种方式 start----------------------------------------------------------------
    const jchar* jstr2 = env->GetStringCritical(j_msg, NULL);
    (std::wstring((const wchar_t*)jstr2));
    // 释放指针
    env->ReleaseStringCritical(j_msg, jstr2);
    // 第二种方式 end

    // 第三种方式 start----------------------------------------------------------------
    // 获取字符串的长度
    jsize len = env->GetStringLength(j_msg);
    // 生成长度位len的字符串指针
    jchar* jstr3 = new jchar[len+1];
    // c++中字符串以 "\0" 结尾,
    jstr3[len] = L'\0';
    // 将字符串j_msg复制到jstr中
    env->GetStringRegion(j_msg, 0, len, jstr3);
    // 转换成宽字符串
    std::wstring wstr((const wchar_t*)jstr);
    // 释放指针
    delete[] jstr3;
    // 第三种方式 end

    // 将字符串进行倒叙----------------------------------------------------------------
    reverse(wstr.begin(), wstr.end());
    // 获取倒叙后的新字符串
    jstring j_new_str = env->NewString((const jchar*)wstr.c_str(), (jint)wstr.size());
    // 将新的字符串设置变量中
    env->SetObjectField(instance, fid_msg, j_new_str);
}







