package mos.com.jalase2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

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

public class ScrollingActivity extends AppCompatActivity {



    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String name = getIntent().getStringExtra("name");
        getSupportActionBar().setTitle(name);
        

        sharedPref = this.getSharedPreferences(name, Context.MODE_PRIVATE);

        final CheckBox cb_hozor1 = (CheckBox) findViewById(R.id.hozor1);
        cb_hozor1.setOnClickListener(Listener);
        final CheckBox cb_hozor2 = (CheckBox) findViewById(R.id.hozor2);
        cb_hozor2.setOnClickListener(Listener);
        final CheckBox cb_hozor3 = (CheckBox) findViewById(R.id.hozor3);
        cb_hozor3.setOnClickListener(Listener);
        final CheckBox cb_hozor4 = (CheckBox) findViewById(R.id.hozor4);
        cb_hozor4.setOnClickListener(Listener);
        final CheckBox cb_hozor5 = (CheckBox) findViewById(R.id.hozor5);
        cb_hozor5.setOnClickListener(Listener);
        final CheckBox cb_mosbat1 = (CheckBox) findViewById(R.id.mosbat1);
        cb_mosbat1.setOnClickListener(Listener);
        final CheckBox cb_mosbat2 = (CheckBox) findViewById(R.id.mosbat2);
        cb_mosbat2.setOnClickListener(Listener);
        final CheckBox cb_mosbat3 = (CheckBox) findViewById(R.id.mosbat3);
        cb_mosbat3.setOnClickListener(Listener);
        final CheckBox cb_mosbat4 = (CheckBox) findViewById(R.id.mosbat4);
        cb_mosbat4.setOnClickListener(Listener);
        final CheckBox cb_mosbat5 = (CheckBox) findViewById(R.id.mosbat5);
        cb_mosbat5.setOnClickListener(Listener);
        final CheckBox cb_manfi1 = (CheckBox) findViewById(R.id.manfi1);
        cb_manfi1.setOnClickListener(Listener);
        final CheckBox cb_manfi2 = (CheckBox) findViewById(R.id.manfi2);
        cb_manfi2.setOnClickListener(Listener);
        final CheckBox cb_manfi3 = (CheckBox) findViewById(R.id.manfi3);
        cb_manfi3.setOnClickListener(Listener);
        final CheckBox cb_manfi4 = (CheckBox) findViewById(R.id.manfi4);
        cb_manfi4.setOnClickListener(Listener);
        final CheckBox cb_manfi5 = (CheckBox) findViewById(R.id.manfi5);
        cb_manfi5.setOnClickListener(Listener);


        if (sharedPref.contains("hozor1")) {
            cb_hozor1.setChecked(sharedPref.getBoolean("hozor1", false));
            cb_hozor2.setChecked(sharedPref.getBoolean("hozor2", false));
            cb_hozor3.setChecked(sharedPref.getBoolean("hozor3", false));
            cb_hozor4.setChecked(sharedPref.getBoolean("hozor4", false));
            cb_hozor5.setChecked(sharedPref.getBoolean("hozor5", false));
            cb_manfi1.setChecked(sharedPref.getBoolean("manfi1", false));
            cb_manfi2.setChecked(sharedPref.getBoolean("manfi2", false));
            cb_manfi3.setChecked(sharedPref.getBoolean("manfi3", false));
            cb_manfi4.setChecked(sharedPref.getBoolean("manfi4", false));
            cb_manfi5.setChecked(sharedPref.getBoolean("manfi5", false));
            cb_mosbat1.setChecked(sharedPref.getBoolean("mosbat1", false));
            cb_mosbat2.setChecked(sharedPref.getBoolean("mosbat2", false));
            cb_mosbat3.setChecked(sharedPref.getBoolean("mosbat3", false));
            cb_mosbat4.setChecked(sharedPref.getBoolean("mosbat4", false));
            cb_mosbat5.setChecked(sharedPref.getBoolean("mosbat5", false));
        }

        int position_clicked = getIntent().getIntExtra("position",0);


        String json = null;
        json = readFromFile();
        try {
            JSONObject jObject = new JSONObject(json);
            JSONArray jsonArray = jObject.getJSONArray("bache");
            JSONObject farde_morede_nazar = jsonArray.getJSONObject(position_clicked);
            if (farde_morede_nazar != null) {
                JSONObject hozor = farde_morede_nazar.getJSONObject("hozor");
                cb_hozor1.setChecked(hozor.getBoolean("jalase1"));
                cb_hozor2.setChecked(hozor.getBoolean("jalase2"));
                cb_hozor3.setChecked(hozor.getBoolean("jalase3"));
                cb_hozor4.setChecked(hozor.getBoolean("jalase4"));
                cb_hozor5.setChecked(hozor.getBoolean("jalase5"));
                JSONObject manfi = farde_morede_nazar.getJSONObject("manfi");
                cb_manfi1.setChecked(manfi.getBoolean("jalase1"));
                cb_manfi2.setChecked(manfi.getBoolean("jalase2"));
                cb_manfi3.setChecked(manfi.getBoolean("jalase3"));
                cb_manfi4.setChecked(manfi.getBoolean("jalase4"));
                cb_manfi5.setChecked(manfi.getBoolean("jalase5"));
                JSONObject mosbat = farde_morede_nazar.getJSONObject("mosbat");
                cb_mosbat1.setChecked(mosbat.getBoolean("jalase1"));
                cb_mosbat2.setChecked(mosbat.getBoolean("jalase2"));
                cb_mosbat3.setChecked(mosbat.getBoolean("jalase3"));
                cb_mosbat4.setChecked(mosbat.getBoolean("jalase4"));
                cb_mosbat5.setChecked(mosbat.getBoolean("jalase5"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    @Override
    protected void onStop(){
        super.onStop();

        final CheckBox cb_hozor1 = (CheckBox) findViewById(R.id.hozor1);
        final CheckBox cb_hozor2 = (CheckBox) findViewById(R.id.hozor2);
        final CheckBox cb_hozor3 = (CheckBox) findViewById(R.id.hozor3);
        final CheckBox cb_hozor4 = (CheckBox) findViewById(R.id.hozor4);
        final CheckBox cb_hozor5 = (CheckBox) findViewById(R.id.hozor5);
        final CheckBox cb_mosbat1 = (CheckBox) findViewById(R.id.mosbat1);
        final CheckBox cb_mosbat2 = (CheckBox) findViewById(R.id.mosbat2);
        final CheckBox cb_mosbat3 = (CheckBox) findViewById(R.id.mosbat3);
        final CheckBox cb_mosbat4 = (CheckBox) findViewById(R.id.mosbat4);
        final CheckBox cb_mosbat5 = (CheckBox) findViewById(R.id.mosbat5);
        final CheckBox cb_manfi1 = (CheckBox) findViewById(R.id.manfi1);
        final CheckBox cb_manfi2 = (CheckBox) findViewById(R.id.manfi2);
        final CheckBox cb_manfi3 = (CheckBox) findViewById(R.id.manfi3);
        final CheckBox cb_manfi4 = (CheckBox) findViewById(R.id.manfi4);
        final CheckBox cb_manfi5 = (CheckBox) findViewById(R.id.manfi5);

        int position_clicked = getIntent().getIntExtra("position",0);

        String json = readFromFile();
        JSONObject jObject=null;
        try {
            jObject = new JSONObject(json);
            JSONArray jsonArray = jObject.getJSONArray("bache");
            JSONObject farde_morede_nazar = jsonArray.getJSONObject(position_clicked);
            if (farde_morede_nazar != null) {
                JSONObject hozor = farde_morede_nazar.getJSONObject("hozor");
                hozor.put("jalase1",cb_hozor1.isChecked());
                hozor.put("jalase2",cb_hozor2.isChecked());
                hozor.put("jalase3",cb_hozor3.isChecked());
                hozor.put("jalase4",cb_hozor4.isChecked());
                hozor.put("jalase5",cb_hozor5.isChecked());
                JSONObject mosbat = farde_morede_nazar.getJSONObject("mosbat");
                mosbat.put("jalase1",cb_mosbat1.isChecked());
                mosbat.put("jalase2",cb_mosbat2.isChecked());
                mosbat.put("jalase3",cb_mosbat3.isChecked());
                mosbat.put("jalase4",cb_mosbat4.isChecked());
                mosbat.put("jalase5",cb_mosbat5.isChecked());
                JSONObject manfi = farde_morede_nazar.getJSONObject("manfi");
                manfi.put("jalase1",cb_manfi1.isChecked());
                manfi.put("jalase2",cb_manfi2.isChecked());
                manfi.put("jalase3",cb_manfi3.isChecked());
                manfi.put("jalase4",cb_manfi4.isChecked());
                manfi.put("jalase5",cb_manfi5.isChecked());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String outputJSONstr = jObject.toString();
        writeToFile(outputJSONstr);
    }

    final View.OnClickListener Listener = new View.OnClickListener() {
        public void onClick(final View v) {

        }

    };


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
