
/*
* dvm直接跑dex文件
* */
public class construct_dex_widthRun {
    public static void main(String[] argc){
        System.out.println("Hello, Android!\n");
    }
}

/*
* 1)
* 编译java class 文件：  执行  javac -encoding utf-8 construct_dex_widthRun.java
*                       生成  construct_dex_widthRun.class
*                       测试  java construct_dex_widthRun
*
* 2)
* 编译成dex文件。 编译工具 Android SDK: /build-tools/19.0.1/dx
*                       执行  dx --dex --output=construct_dex_widthRun.dex construct_dex_widthRun.class
*                       生成  construct_dex_widthRun.dex
* 3)
* adb root
* adb push construct_dex_widthRun.dex /sdcard/
* adb shell
* dalvikvm -cp /sdcard/construct_dex_widthRun.dex construct_dex_widthRun
*          -cp : class path
*
*
* */
