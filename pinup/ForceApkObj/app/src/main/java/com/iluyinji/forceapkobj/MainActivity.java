package com.iluyinji.forceapkobj;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 获取需要被添加控件的Linear布局(方法二)
        setContentView(R.layout.activity_main);
        final LinearLayout lin = (LinearLayout) findViewById(R.id.mainLinearLayout);

        // 添加一个LinearLayout布局，设置成layout_width:wrap_content;layout_height:wrap_content;
        LinearLayout layout = new LinearLayout(this); // 线性布局方式

        //添加一个TextView，设置成layout_width:wrap_content;layout_height:wrap_content;
        TextView tv = new TextView(this); // 普通聊天对话
        tv.setText("我和猫猫是新添加的");
        tv.setBackgroundColor(Color.GRAY);
        LinearLayout.LayoutParams LP_WW = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(LP_WW);
        layout.addView(tv);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                startActivity(intent);
            }
        });

        //将动态增加的布局添加到当前布局中；
        lin.addView(layout);
        setContentView(lin);

    }
}
