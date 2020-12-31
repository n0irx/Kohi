#include <jni.h>

extern "C" jint
Java_id_ac_ui_cs_mobileprogramming_nataprawiraf_kohi_ui_create_CreateRecipeViewModel_timeSeconds(JNIEnv *env,
        jobject thiz, jint a) {
    return a * 60;
}

