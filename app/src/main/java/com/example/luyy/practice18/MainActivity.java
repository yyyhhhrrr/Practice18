package com.example.luyy.practice18;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText EditText;
    private EditText EditText2;
    private TextView showTextView;
    // 要保存的文件名
    private String fileName = "yanghaoran_java.txt";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获取页面中的组件
        EditText = (EditText) findViewById(R.id.editText);
        EditText2 =(EditText) findViewById(R.id.editText2) ;
        showTextView = (TextView) findViewById(R.id.showText);
        Button addButton = (Button) this.findViewById(R.id.addButton);
        Button showButton = (Button) this.findViewById(R.id.showButton);
        // 绑定单击事件
        addButton.setOnClickListener(listener);
        showButton.setOnClickListener(listener);

    }

    // 声明监听器
    private View.OnClickListener listener = new OnClickListener() {
        public void onClick(View v) {
            Button view = (Button) v;
            switch (view.getId()) {
                case R.id.addButton:
                    save();
                    break;
                case R.id.showButton:
                    read();
                    break;

            }

        }

    };

    /**
     *保存用户输入的内容到文件
     */
    private void save() {

        String content = EditText.getText().toString();
        String content2 = EditText2.getText().toString();

        try {

            FileOutputStream outputStream = openFileOutput(fileName,
                    Activity.MODE_PRIVATE);
            String temp="学号："+content+"\n"+"密码：" + content2+"\n";
            outputStream.write(temp.getBytes());
            outputStream.flush();
            outputStream.close();
            Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @author chenzheng_java
     * 读取刚才用户保存的内容
     */
    private void read() {
        try {
            FileInputStream inputStream = this.openFileInput(fileName);
            byte[] bytes = new byte[1024];
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            while (inputStream.read(bytes) != -1) {
                arrayOutputStream.write(bytes, 0, bytes.length);
            }
            inputStream.close();
            arrayOutputStream.close();
            String content = new String(arrayOutputStream.toByteArray());
            showTextView.setText(content);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
