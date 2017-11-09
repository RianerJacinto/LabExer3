package com.jacinto.externalstorage;

import java.io.File;
import java.io.FileWriter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.logging.FileHandler;

import static com.jacinto.externalstorage.R.id.text;
import static com.jacinto.externalstorage.R.id.text2;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);

}


    public void Next(View view) {
        startActivity(new Intent(this,SecondActivity.class));
    }

    public void SaveInInternalStorage(View view) {


        String text=editText.getText().toString();
        String text2=editText2.getText().toString();
        File dir = getFilesDir();
        File file = new File(dir, text2 + ".txt");


        try {
            FileWriter fw = new FileWriter(text2 +".txt", true);
            fw.write(text + "\n\n");
            fw.close();
        } catch (IOException ioe) {
        }
        writeData(file, text2);
    }




    public void SaveInInternalCacheStorage(View view) {
        String text=editText.getText().toString();;
        String text2=editText2.getText().toString();
        File dir = getExternalCacheDir();
        File file = new File(dir,text);
        writeData(file, text2);



    }

    public void SaveInExternalCacheStorage(View view) {
        String text=editText.getText().toString();
        String text2=editText2.getText().toString();
        File dir = getExternalCacheDir();
        File file = new File(dir,text);
        writeData(file, text2);


}
    public void SaveInExternalPrivateStorage(View view) {
        String text=editText.getText().toString();
        String text2=editText2.getText().toString();
        File dir = getExternalFilesDir("MyDir");
        File file = new File(dir,text);
        writeData(file, text2);
    }

    public void SaveInExternalPublicStorage(View view) {
        String text=editText.getText().toString();
        String text2=editText2.getText().toString();
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(dir,text);
        writeData(file, text2);
    }


    public void writeData(File file,String text)
    {
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream = new FileOutputStream(file);

            fileOutputStream.write(text.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fileOutputStream!=null)
            {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Toast.makeText(this,"Data saved to "+file.getAbsolutePath(),Toast.LENGTH_LONG).show();

    }
}
