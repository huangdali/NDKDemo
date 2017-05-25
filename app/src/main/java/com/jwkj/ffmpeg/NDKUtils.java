package com.jwkj.ffmpeg;


public class NDKUtils {
    static {
        System.loadLibrary("ndkDemo");
    }
    public static native int getSum(int a, int b);
}
