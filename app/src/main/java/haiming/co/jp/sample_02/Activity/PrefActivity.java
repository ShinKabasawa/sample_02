package haiming.co.jp.sample_02.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import haiming.co.jp.sample_02.R;

/**
 * プリファレンス課題
 */
public class PrefActivity extends AppCompatActivity {

    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pref);
    }

    @Override
    protected void onStart(){
        super.onStart();
        editText = (EditText)findViewById(R.id.res_edit);
        textView = (TextView)findViewById(R.id.result_view);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {

        // DOWNとUPが取得できるのでログの2重表示防止のためif
        if (e.getAction() == KeyEvent.ACTION_DOWN) {
            //キーコード表示
            Log.d("KeyCode","KeyCode:"+ e.getKeyCode());
        }

        return super.dispatchKeyEvent(e);
    }

    // 登録クリック処理
    public void sava_Click(View v){
        String test = new String();
        test = editText.getText().toString();

        if(!test.equals("")) {
            setPreference(this, "test", test, 1);
        }else{
            Toast.makeText(this,"入力してください",Toast.LENGTH_SHORT).show();
        }
    }

    // 取得クリック処理
    public void load_click(View v){
        String s = (String) loadPreference(this,"test",1);
        textView.setText(s);
    }

    // クリアクリック処理
    public void clear_click(View v){
        textView.setText("");
    }

    /**
     * SharedPreferenceを保存する
     * @param con    Context
     * @param key    保存するSharedPreferenceのキー
     * @param obj    保存するSharedPreferenceの値
     * @param spType 保存するSharedPreferenceの値の型 1:String 2:int 3:boolean 4:Long 5:Float
     */
    public static void setPreference(Context con, String key, Object obj, int spType) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(con);
        String str = String.valueOf(obj);

        switch (spType) {
            case 1: // String
                prefs.edit().putString(key, str).apply();
                break;
            case 2: // int
                prefs.edit().putInt(key, Integer.parseInt(str)).apply();
                break;
            case 3: // boolean
                boolean bool = true;
                if (str.equals("false")) {
                    bool = false;
                }
                prefs.edit().putBoolean(key, bool).apply();
                break;
            case 4: // long
                prefs.edit().putLong(key, Long.parseLong(str)).apply();
                break;
            case 5: // float
                prefs.edit().putFloat(key, Float.parseFloat(str)).apply();
                break;
        }
    }

    /**
     * SharedPreferenceをから値を取得する
     * @param con     Context
     * @param key     取得するSharedPreferenceのキー
     * @param spType  取得するSharedPreferenceの値の型
     * @return        取得したSharedPreferenceの値
     */
    public static Object loadPreference(Context con, String key, int spType) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(con);
        Object obj = new Object();

        switch (spType) {
            case 1: // String
                obj = sp.getString(key, "");
                break;
            case 2: // int
                obj = sp.getInt(key, 1);
                break;
            case 3: // boolean
                obj = sp.getBoolean(key, false);
                break;
            case 4: // long
                obj = sp.getLong(key, 1);
                break;
            case 5: // float
                obj = sp.getFloat(key, 1);
                break;
        }
        return obj;
    }




}
