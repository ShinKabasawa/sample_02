package haiming.co.jp.sample_02.Activity;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import haiming.co.jp.sample_02.R;

/**
 * 権限
 */
public class PermissionActivity extends AppCompatActivity {

    private BluetoothManager manager;
    private MaterialDialog dialogBluetooth;
    private MaterialDialog dialogLocation;
    private MaterialDialog dialogcamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
    }

    @Override
    protected void onStart(){
        super.onStart();
        manager = (BluetoothManager) this.getSystemService(Context.BLUETOOTH_SERVICE);
    }


    // BLUETOOTHの権限付与
    public void Bluetooth_permission_Click(View v){
        // final BluetoothManager manager = BleUtil.getManager(this);
        final BluetoothAdapter ba = BluetoothAdapter.getDefaultAdapter();
        if (!ba.isEnabled()) {
            // ダイアログ表示して選択させる
            // int REQUEST_ENABLE_BT = 1;
            // int REQUEST_STATE_CHANGE_BT = 2;

            bluetoothAlertDialog(ba);
        }
    }

    // 位置情報の権限付与
    public void GPS_permission_Click(View v){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            LocationManager LocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            boolean gpsflg = LocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean networkflg = LocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            // 位置情報使用許可
            if(!gpsflg && !networkflg) {
                locationAlertDialog();
            }
        }
    }

    // アプリ内でのカメラの使用権原付与
    public void Camera_permission_Click(View v){
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //MaterialDialog.Builder builder = new MaterialDialog.Builder(this);                                                                   //
        //builder.title("カメラ使用許可");                                                                                                     //
        //builder.content("このアプリケーションでカメラ使用しますか");                                                                         //
        //builder.positiveText("はい");                                                                                                        //
        //builder.negativeText("いいえ");                                                                                                      //
        //final Context context = this;                                                                                                        //
        //final Activity activity = this;                                                                                                      //
        //builder.onPositive(new MaterialDialog.SingleButtonCallback() {                                                                       //
        //    @Override                                                                                                                        //
        //    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {                                               //
        //        dialogcamera.dismiss();                                                                                                      //
        //        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, 1);                                    //
        //        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {                                                                   //
        //            // Android 6.0以降のみ、該当パーミッションが許可されていない場合                                                         //
        //            int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);                            //
        //            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {                                                              //
        //                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {                     //
        //                    // パーミッションが必要であることを明示するアプリケーション独自のUIを表示                                        //
        //                    final int REQUEST_CODE = 1;                                                                                      //
        //                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE);             //
        //                }else{}                                                                                                              //
        //            } else {                                                                                                                 //
        //                // 許可済みの場合                                                                                                    //
        //                Toast.makeText(context,"許可済",Toast.LENGTH_SHORT).show();                                                          //
        //            }                                                                                                                        //
        //        } else{                                                                                                                      //
        //            // Android 6.0以前の場合                                                                                                 //
        //            Toast.makeText(context,"ANdroid 6以前",Toast.LENGTH_SHORT).show();                                                       //
        //        }                                                                                                                            //
        //    }                                                                                                                                //
        //});                                                                                                                                  //
        //builder.onNegative(new MaterialDialog.SingleButtonCallback() {                                                                       //
        //    @Override                                                                                                                        //
        //    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {                                               //
        //        dialogcamera.dismiss();                                                                                                      //
        //    }                                                                                                                                //
        //});                                                                                                                                  //
        //dialogcamera = builder.build();                                                                                                      //
        //dialogcamera.setCancelable(false);                                                                                                   //
        //dialogcamera.show();                                                                                                                 //
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        permission_camaera();

    }

    private void permission_camaera(){
        Context context = this;
        Activity activity = this;
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            // Android 6.0以降のみ、該当パーミッションが許可されていない場合
            int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {
                    // パーミッションが必要であることを明示するアプリケーション独自のUIを表示
                    final int REQUEST_CODE = 1;
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE);
                }else{

                }
            } else {
                // 許可済みの場合
                Toast.makeText(context,"許可済",Toast.LENGTH_SHORT).show();
            }
        } else{
            // Android 6.0以前の場合
            Toast.makeText(context,"Android 6以前",Toast.LENGTH_SHORT).show();
        }
    }

    private void bluetoothAlertDialog(final BluetoothAdapter ba)
    {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(this);

        builder.title("Bluetooth設定");
        builder.content("この端末でアプリを使用するにはBluetoothをONにする必要があります。");
        builder.positiveText("はい");
        builder.negativeText("いいえ");

        final BluetoothAdapter[] mBTAdapter = new BluetoothAdapter[1];

        builder.onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
                ba.enable();

                if (manager != null) {
                    mBTAdapter[0] = manager.getAdapter();
                }

                if (mBTAdapter[0] == null) {
                    Toast.makeText(PermissionActivity.this, "Bluetoothが使えません", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
            }
        });

        dialogBluetooth = builder.build();
        dialogBluetooth.setCancelable(false);
        dialogBluetooth.show();
    }


    private void locationAlertDialog() {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(this);
        builder.title("位置情報設定");
        builder.content("この端末でアプリを使用するには位置情報をONにする必要があります。");
        builder.positiveText("許可");
        builder.negativeText("許可しない");
        final Activity activity = this;
        builder.onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
                startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0);
            }
        });
        builder.onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
            }
        });

        dialogLocation = builder.build();
        dialogLocation.setCancelable(false);
        dialogLocation.show();
    }
}
