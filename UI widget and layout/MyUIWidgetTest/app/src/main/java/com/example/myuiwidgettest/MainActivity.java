package com.example.myuiwidgettest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);//声明Button类型对象，通过finViewById方法与在xml注册的id为button的控件相关联。
        final EditText editText = (EditText) findViewById(R.id.edit_text);
        final ImageView imageView = (ImageView) findViewById(R.id.image_view);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setTitle("Dialog Box");//设置对话框标题
//                builder.setMessage("Hello,teacher shengyunYang!");//设置对话框内容
//                builder.setPositiveButton("OK", null);
//                builder.show();

                switch (view.getId()) {
                    case R.id.button:
//                        String inputText = editText.getText().toString();
//                        Toast.makeText(MainActivity.this, inputText, Toast.LENGTH_SHORT).show();
                        imageView.setImageResource(R.drawable.img_2);
                        break;
                    default:
                        break;
                }

            }
        });


    }


}