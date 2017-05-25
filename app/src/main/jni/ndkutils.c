#include "stdio.h"
#include "jni.h"

JNIEXPORT jint JNICALL Java_com_jwkj_ffmpeg_NDKUtils_getSum
        (JNIEnv *env, jclass jclass, jint a, jint b) {
    return a+b;
}