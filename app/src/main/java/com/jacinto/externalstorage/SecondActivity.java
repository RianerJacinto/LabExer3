package com.jacinto.externalstorage;

/**
 * Created by Rianer on 07/11/2017.
 */
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    private TextView text;
    private TextView text2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.second_activity);
        text = (TextView)findViewById(R.id.text);
        text2 = (TextView)findViewById(R.id.text2);
    }


    public void LoadFromInternalStorage(View view) {
        text2 = (TextView)findViewById(R.id.text2);
        File dir = getFilesDir();
        File file = new File(dir, text2 + ".txt");

        readData(file);

}
    public void LoadFromInternalCacheStorage(View view) {
        text2 = (TextView)findViewById(R.id.text2);
        File dir = getCacheDir();
        File file = new File(dir, text2 + ".txt");
             readData(file);
}


    public void LoadFromExternalCacheStorage(View view) {
        File dir = getExternalCacheDir();
        File file = new File(dir, String.valueOf(text2));
        readData(file);
    }

    public void LoadFromExternalPrivateStorage(View view) {
        File dir = getExternalFilesDir("MyDir");
        File file = new File(dir, String.valueOf(text2));

    readData(file);
}
    public void LoadFromExternalPublicStorage(View view) {
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(dir, String.valueOf(text2));
        readData(file);
    }


    public void Back(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }

    public void readData(File file)

    {
        FileInputStream fileInputStream=null;

        try {
            fileInputStream = new FileInputStream(file);
            fileInputStream = openFileInput(text2 + ".txt");
            int read=-1;
            StringBuffer stringBuffer = new StringBuffer();
            while ((read=fileInputStream.read())!=-1)
            {
                stringBuffer.append((char)read);
            }
            text.setText(stringBuffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileInputStream!=null)
            {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
