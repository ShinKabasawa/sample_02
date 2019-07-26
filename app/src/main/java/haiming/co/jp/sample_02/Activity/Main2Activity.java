package haiming.co.jp.sample_02.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;

import java.util.ArrayList;
import java.util.Arrays;

import haiming.co.jp.sample_02.Data.Common;
import haiming.co.jp.sample_02.Data.prefectures_data;
import haiming.co.jp.sample_02.Manager.FileManager;
import haiming.co.jp.sample_02.R;

public class Main2Activity extends AppCompatActivity implements LocationListener {

    private Spinner spinner_1;
    private Spinner spinner_2;
    private Spinner spinner_3;
    private ArrayList<prefectures_data> prefe_data_array;
    private ArrayList<String> pref_name_array;
    private String place;
    private String pref;
    private String city;
    private LocationManager locationManager;
    private GoogleApiClient _apiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    protected void onStart() {
        super.onStart();
        init();

        read_file();

        String[] spinneritem_1 = {"自宅", "勤務先", "旅行先1", "旅行先2"};

        ArrayAdapter<String> adapter_1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinneritem_1);
        adapter_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_1.setAdapter(adapter_1);

        ArrayAdapter<String> adapter_2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pref_name_array);
        adapter_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_2.setAdapter(adapter_2);
    }


    /**
     * 初期化
     */
    private void init() {
        place = "";
        pref = "";
        city = "";
        spinner_1 = (Spinner) findViewById(R.id.spinner_1);
        spinner_2 = (Spinner) findViewById(R.id.spinner_2);
        spinner_3 = (Spinner) findViewById(R.id.spinner_3);

        spinner_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                place = (String) spinner_1.getSelectedItem();
                Common.loadPreference(getApplicationContext(), place, 1);
                pref = (String) Common.loadPreference(getApplicationContext(), "自宅pref", 1);
                city = (String) Common.loadPreference(getApplicationContext(), "自宅city", 1);

                //////////////////////////////////////////////////////
                //int cnt = 0;                                      //
                //ArrayList<String> arrayList = new ArrayList<>();  //
                //for (int i = 0 ; i< pref_name_array.size(); i++){ //
                //    if (pref.equals(pref_name_array.get(i))){     //
                //        cnt = i;                                  //
                //        break;                                    //
                //    }                                             //
                //}                                                 //
                //spinner_2.setSelection(cnt);                      //
                //////////////////////////////////////////////////////

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                place = (String) spinner_1.getSelectedItem();
                Common.loadPreference(getApplicationContext(), place, 1);
                pref = (String) Common.loadPreference(getApplicationContext(), "自宅pref", 1);
                city = (String) Common.loadPreference(getApplicationContext(), "自宅city", 1);
            }
        });

        spinner_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pref = (String) spinner_2.getSelectedItem();

                ArrayList<String> arrayList = new ArrayList<>();

                for (int i = 0; i < prefe_data_array.size(); i++) {
                    if (pref.equals(prefe_data_array.get(i).prefectures)) {
                        arrayList.add(prefe_data_array.get(i).city);
                    }
                }

                ArrayAdapter<String> adapter_3 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
                adapter_3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_3.setAdapter(adapter_3);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                pref = (String) spinner_2.getSelectedItem();

                ArrayList<String> arrayList = new ArrayList<>();

                for (int i = 0; i < prefe_data_array.size(); i++) {
                    if (pref.equals(prefe_data_array.get(i).prefectures)) {
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
                city = (String) spinner_3.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                city = (String) spinner_3.getSelectedItem();
            }
        });

        prefe_data_array = new ArrayList<>();
        pref_name_array = new ArrayList<>();

    }

    private void enableLoc() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);

        builder.setAlwaysShow(true);
    }


    // 位置情報許可の確認
    public void checkPermission(View v) {
        // 既に許可している
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {


            // 位置情報取得
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.v("checkSelfPermission", "");
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 1, this);

        }
        // 拒否していた場合
        else {
            requestLocationPermission();
        }
    }

    // 許可を求める
    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(Main2Activity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1000);

        } else {
            Toast toast = Toast.makeText(this, "許可されないとアプリが実行できません", Toast.LENGTH_SHORT);
            toast.show();

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);

        }
    }

    // 結果の受け取り
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1000) {
            // 使用が許可された
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "許可", Toast.LENGTH_SHORT).show();

                _apiClient = new GoogleApiClient.Builder(getApplicationContext())
                        .addApi(LocationServices.API)
                        .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                            @Override
                            public void onConnected( Bundle bundle) {
                                //...
                            }

                            @Override
                            public void onConnectionSuspended(int i) {
                                //...
                            }
                        })
                        .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                            @Override
                            public void onConnectionFailed( ConnectionResult connectionResult) {
                                //...
                            }
                        }).build();

                FusedLocationProviderApi fused = LocationServices.FusedLocationApi;
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }

                LocationRequest request = LocationRequest.create();
                LocationListener _listener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {

                        String msg = "Lat=" + location.getLatitude() + "Lng=" + location.getLongitude();
                        Log.d("onLocationChanged", msg);
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                };


                enableLoc();

                Location location = fused.getLastLocation(_apiClient);
                //enableLoc();

                Log.v("########","lat = "+ location.getLatitude());
                Log.v("########","lon = "+ location.getLongitude());

                startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                // 位置情報取得
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    Log.v("checkSelfPermission","");
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,500, 1, this);
            } else {
                // それでも拒否された時の対応
                Toast toast = Toast.makeText(this, "これ以上なにもできません", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }


    public void regbtn_onClick(final View v) {
        String lat = "";
        String lon = "";

        if (!place.equals("") && !pref.equals("") && !city.equals("")) {
            for (int i = 0; i < prefe_data_array.size(); i++) {
                if (city.equals(prefe_data_array.get(i).city)) {
                    lat = prefe_data_array.get(i).lat;
                    lon = prefe_data_array.get(i).lon;
                }
            }

            Common.setPreference(this, place, city, 1);
            Common.setPreference(this, place + "pref", pref, 1);
            Common.setPreference(this, place + "lat", lat, 1);
            Common.setPreference(this, place + "lon", lon, 1);


        } else {
            Toast.makeText(this, "選択してください", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * CSVファイル読込
     */
    private void read_file(){
        prefe_data_array = (ArrayList<prefectures_data>) FileManager.prefectures_read_file(this);
        Log.v("Main2Activity","prefe_data_array.size = " + prefe_data_array.size());

        Resources res = getResources();
        String[] ta = res.getStringArray(R.array.ken_name_array);
        pref_name_array = new ArrayList<>(Arrays.asList(ta));
    }


    @Override
    public void onLocationChanged(Location location) {
        String msg = "Lat=" + location.getLatitude() + "Lng=" + location.getLongitude();
        Log.d("GPS", msg);
        locationManager.removeUpdates(this);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("GPS", "onStatusChanged");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("GPS", "onProviderEnabled");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("GPS", "onProviderDisabled");
    }
}
