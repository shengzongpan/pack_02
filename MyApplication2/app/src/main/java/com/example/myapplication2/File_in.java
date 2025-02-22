package com.example.myapplication2;


import android.app.AlertDialog;
import android.app.Dialog;
import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class File_in {
    private String filepath;
    private int n;
    public File_in(String filepath){
        this.filepath = filepath;
    }

    public ArrayList<String> readx(File file) throws IOException{
        ArrayList<String> strs = new ArrayList<>();
        //new AlertDialog.Builder(new MainActivity()).setTitle("1").setMessage("找不到文件").setPositiveButton("ok",null).show();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
               String line;
               while((line = reader.readLine()) != null){
                   strs.add(line);
                   n++;
               }
        }catch (Exception e){

        }
        return strs;
    }

    public int getN() {
        return n;
    }
}
