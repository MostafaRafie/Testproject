package mos.com.jalase2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AddMember extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText et_nam = (EditText) findViewById(R.id.editText3);
                EditText et_fon = (EditText) findViewById(R.id.editText4);
                EditText et_bab = (EditText) findViewById(R.id.editText5);

                if (et_nam.getText()!=null) {
                    String json = readFromFile();
                    JSONObject jObject = null;
                    String newMemberFormat = "{\"name\": \"\",\n" +
                            "\t\t\"bab\": \"\",\n" +
                            "\t\t\"tel\": \"\",\n" +
                            "\t\t\"hozor\": {\n" +
                            "\t\t\t\"jalase1\": \"false\",\n" +
                            "\t\t\t\"jalase2\": \"false\",\n" +
                            "\t\t\t\"jalase3\": \"false\",\n" +
                            "\t\t\t\"jalase4\": \"false\",\n" +
                            "\t\t\t\"jalase5\": \"false\"\n" +
                            "\t\t},\n" +
                            "\t\t\"mosbat\": {\n" +
                            "\t\t\t\"jalase1\": \"false\",\n" +
                            "\t\t\t\"jalase2\": \"false\",\n" +
                            "\t\t\t\"jalase3\": \"false\",\n" +
                            "\t\t\t\"jalase4\": \"false\",\n" +
                            "\t\t\t\"jalase5\": \"false\"\n" +
                            "\t\t},\n" +
                            "\t\t\"manfi\": {\n" +
                            "\t\t\t\"jalase1\": \"false\",\n" +
                            "\t\t\t\"jalase2\": \"false\",\n" +
                            "\t\t\t\"jalase3\": \"false\",\n" +
                            "\t\t\t\"jalase4\": \"false\",\n" +
                            "\t\t\t\"jalase5\": \"false\"\n" +
                            "\t\t}}";
                    try {
                        jObject = new JSONObject(json);
                        JSONArray jsonArray = jObject.getJSONArray("bache");
                        JSONObject newMemberObject = new JSONObject(newMemberFormat);

                        newMemberObject.put("name", et_nam.getText());

                        if (et_fon != null) {
                            newMemberObject.put("tel", et_fon.getText());
                        }
                        if (et_bab != null) {
                            newMemberObject.put("bab", et_bab.getText());
                        }
                        jsonArray.put(newMemberObject);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    String outputJSONstr = jObject.toString();
                    writeToFile(outputJSONstr);

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }else{
                    //toast someting
                }
            }

        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }


    private String readFromFile() {
        BufferedReader input = null;
        File file = null;
        String output="";
        try {
            file = new File(getExternalFilesDir(null), "data.json"); // Pass getFilesDir() and "MyFile" to read file

            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr =new InputStreamReader(fis);
            input = new BufferedReader(isr);
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = input.readLine()) != null) {
                buffer.append(line);
            }
            output = buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }


    private void writeToFile(String newData) {
        try {
            File file = new File(getExternalFilesDir(null), "data.json");
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(newData);
            osw.flush();
            osw.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
