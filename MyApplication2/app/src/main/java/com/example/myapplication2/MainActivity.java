package com.example.myapplication2;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    //计数
    int n = 0;
    //用AraayList集合来存储
    ArrayList<String> strs = new ArrayList<>(); ;
    //文件路径
    String filepath = "vocabulary.txt";
    //控件
    TextView tx2;
    Button b_add;
    Words words;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        //载入，即从文件读出--还没写
        //读取
        File externfile = getExternalFilesDir(null);
        if(externfile == null){
            externfile =getFilesDir();
        }
        File file = new File(externfile, filepath);
        File_in file_in = new File_in(filepath);
        try {
            strs = file_in.readx(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        n = file_in.getN();
        //更新record数量
        tx2 = (TextView) findViewById(R.id.text2);
        tx2.setText("Record数量: "+ n);



        //创建ArrayAdapter
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,strs);
        //获取ListView对象，通过调用setAdapter方法为ListView设置Adapter设置适配器
        ListView list_test = (ListView) findViewById(R.id.list_item);
        list_test.setAdapter(adapter);

        b_add = findViewById(R.id.b_add);
        b_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //增添 --- 代码功能不足---没写完
                //dialog 窗口建立并且按下“确定需要更新”
                showdialog();



            }
        });
    }

    protected void showdialog(){

        AlertDialog.Builder dialog1 = new AlertDialog.Builder(this);
        //设置dialog标题
        dialog1.setTitle("添加");
        //加载xml布局
        View dialogView = getLayoutInflater().inflate(R.layout.dialog1, null);
        dialog1.setView(dialogView);
        dialog1.setCancelable(false);
        //获取布局中的输入框
        final EditText input1 = dialogView.findViewById(R.id.input1);
        final EditText input2 = dialogView.findViewById(R.id.input2);
        final EditText input3 = dialogView.findViewById(R.id.input3);

        //设置按钮
        dialog1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //获取输入框中的文字
                String text1 = input1.getText().toString();
                String text2 = input2.getText().toString();
                String text3 = input3.getText().toString();
                //传入words对象
                words = new Words(text1, text3, text2);
                //增加集合
                strs.add(words.getEn_word() + "  --  " + words.getCixing() + "  --  " + words.getCn_mean());
                n++;

                //通知适配器数据已经更改
                adapter.notifyDataSetChanged();

                //更新record数量
                tx2.setText("Record数量: " + n);

                File externfile = getExternalFilesDir(null);
                if(externfile == null){
                    externfile =getFilesDir();
                }
                File file = new File(externfile, filepath);
                //存入文件
                File_out file_out = new File_out(filepath, n);
                file_out.write(strs, file);

            }
        });
        dialog1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog1.show();
    }
}