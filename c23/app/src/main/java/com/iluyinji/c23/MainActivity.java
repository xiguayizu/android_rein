package com.iluyinji.c23;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.iluyinji.c23.code_callParent_2_4.Polymorphism;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public void nativeCallJava(View view) {
        Hello hello = new Hello();
        hello.test();
    }

    public void nativeChangeJavaVal(View view) {
        JNIDemo jniDemo = new JNIDemo();
        jniDemo.JNIDemo();
    }

    public void cppAndJavaExtendsCall(View view) {
        Polymorphism polymorphism = new Polymorphism();
        polymorphism.javaCallOrder();
        polymorphism.cppCallOrder();
    }

    public void cppCallNonvirtual(View view) {
        Polymorphism polymorphism = new Polymorphism();
        polymorphism.cppCallJava(); // Nonvirtual
    }
}
