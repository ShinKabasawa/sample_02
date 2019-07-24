package haiming.co.jp.sample_02.Activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

import haiming.co.jp.sample_02.Data.prefectures_data;
import haiming.co.jp.sample_02.Manager.FileManager;
import haiming.co.jp.sample_02.R;

public class Main2Activity extends AppCompatActivity {

    private Spinner spinner_1;
    private Spinner spinner_2;
    private Spinner spinner_3;
    private ArrayList<prefectures_data> prefe_data_array;
    private ArrayList<String> pref_name_array;
    private String place;
    private String pref;
    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }

    protected void onStart(){
        super.onStart();
        init();

        read_file();

        String[] spinneritem_1 = {"自宅","勤務先","旅行先1","旅行先"};

        ArrayAdapter<String> adapter_1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinneritem_1);
        adapter_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_1.setAdapter(adapter_1);

        ArrayAdapter<String> adapter_2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pref_name_array);
        adapter_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_2.setAdapter(adapter_2);
    }


    private void init(){
        spinner_1 = (Spinner)findViewById(R.id.spinner_1);
        spinner_2 = (Spinner)findViewById(R.id.spinner_2);
        spinner_3 = (Spinner)findViewById(R.id.spinner_3);

        spinner_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                place = (String)spinner_1.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                place = (String)spinner_1.getSelectedItem();
            }
        });

        spinner_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pref = (String)spinner_2.getSelectedItem();

                ArrayList<String> arrayList = new ArrayList<>();

                for (int i = 0 ; i< prefe_data_array.size(); i++){
                    if (pref.equals(prefe_data_array.get(i).prefectures)){
                        prefectures_data prefectures_data = new prefectures_data();
                        arrayList.add(prefe_data_array.get(i).city);
                    }
                }
                ArrayAdapter<String> adapter_3 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
                adapter_3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_3.setAdapter(adapter_3);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                pref = (String)spinner_2.getSelectedItem();

                ArrayList<String> arrayList = new ArrayList<>();

                for (int i = 0 ; i< prefe_data_array.size(); i++){
                    if (pref.equals(prefe_data_array.get(i).prefectures)){
                        prefectures_data prefectures_data = new prefectures_data();
                        arrayList.add(prefe_data_array.get(i).city);
                    }
                }
                ArrayAdapter<String> adapter_3 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
                adapter_3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_3.setAdapter(adapter_3);
            }
        });

        spinner_3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city = (String)spinner_3.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                city = (String)spinner_3.getSelectedItem();
            }
        });

        prefe_data_array = new ArrayList<>();
        pref_name_array = new ArrayList<>();
        place = "";
        pref = "";
        city = "";
    }


    private void read_file(){
        prefe_data_array = (ArrayList<prefectures_data>) FileManager.prefectures_read_file(this);
        Log.v("Main2Activity","prefe_data_array.size = " + prefe_data_array.size());

        //TextView textView = findViewById(R.id.textViewconfirm);
        //textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        //StringBuilder value = new StringBuilder();
        //for (int i= 0; i < prefe_data_array.size();i++){
        //    value.append(prefe_data_array.get(i).prefectures);
        //    value.append(" ");
        //    value.append(prefe_data_array.get(i).city);
        //    value.append("\n");
        //}
        //textView.setText(value.toString());

        Resources res = getResources();
        String[] ta = res.getStringArray(R.array.ken_name_array);
        pref_name_array = new ArrayList<>(Arrays.asList(ta));
    }



}
