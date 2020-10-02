package com.example.activitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.d("SecondActivity", this.toString());
        Log.d("SecondActivity", "Task id is " + getTaskId());//该方法用于打印出返回栈的id
        setContentView(R.layout.second_layout);
//        Intent intent = getIntent();
//        String data = intent.getStringExtra("extra_data");
//        Log.d("SecondActivity", data);

        Button button2 = (Button) findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.putExtra("data_return", "Hello FirstActivity");
//                setResult(RESULT_OK, intent);
//                finish();

//                Intent intent = new Intent(SecondActivity.this, FirstActivity.class);//在SecondActivity基础上启动FirstActivity
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);//在SecondActivity基础上启动ThirdActivity
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("SecondActivity", "onDestroy");
    }

}