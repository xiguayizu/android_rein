package com.iluyinji.c23;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.iluyinji.c23.code_cCreateObject_2_5.cCreateJavaObj;
import com.iluyinji.c23.code_callParent_2_4.Polymorphism;
import com.iluyinji.c23.code_operArray_2_6.cOperArray;

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

    public void cCreateJavaObj(View view) {
        cCreateJavaObj.test1();
        cCreateJavaObj.test2();
    }
    public void cOperJavaString(View view) {
        cCreateJavaObj.cOperJavaString();
    }

    public void cOperJavaArray(View view) {
        cOperArray.cOperArray();
    }
}
