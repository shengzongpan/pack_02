package com.example.myapplication2;

import android.app.AlertDialog;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class File_out {
    private String filepath;
    private int n;

    public File_out(String filepath, int n){
        this.filepath = filepath;
        this.n = n;
    }

    public void write(ArrayList strs, File file){
        try(FileWriter writer = new FileWriter(file)){
            for (int i=0; i<n; i++){
                writer.write(strs.get(i) + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
