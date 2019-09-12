#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_iluyinji_c23_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

// native 中调用 java 方法
extern "C"
JNIEXPORT void JNICALL
Java_com_iluyinji_c23_Hello_test(JNIEnv *env, jobject instance) {
    // TODO
    jclass hello_clazz = env->GetObjectClass(instance);
    jfieldID fieldId_prop = env->GetFieldID(hello_clazz, "property", "I");
    // (int foo, Date date, int[] arr)
    jmethodID  methodId_func = env->GetMethodID(hello_clazz, "function", "(ILjava/util/Date;[I)I");
    env->CallIntMethod(instance, methodId_func, 0L, NULL, NULL);
}