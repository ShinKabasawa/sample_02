package haiming.co.jp.sample_02.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

public class DaoCreate extends SQLiteOpenHelper {

    final static String DB_NAME ="weather.db";  // DB名
    final static int	DB_VERSION = 2;             // バージョン

    private final String create_todo_table = "CREATE TABLE TBL_todo (" +
                                             "id INTEGER PRIMARY KEY,"+
                                             "title TEXT,"+
                                             "content TEXT,"+
                                             "fin_flg INTEGER,"+
                                             "date TEXT)";


    static  final String CREATE_TABLE_S = "CREATE TABLE SCHDULE_TABLE ( " +
                                          "schdule_id  TEXT PRIMARY KEY, " +
                                          "schdule_date  TEXT NOT NULL, " +
                                          "plan_id  TEXT"+
                                          ")";

    static final String CREATE_TABLE_P = "CREATE TABLE PLAN_TABLE ( " +
                                         "plan_id TEXT PRIMARY KEY, " +
                                         "schdule_id TEXT," +
                                         "plan_detal TEXT," +
                                         "plan_check INTEGER,"+
                                         "plan_date TEXT" +
                                         ")";


    public DaoCreate(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreateTable(){
        SQLiteDatabase db = getWritableDatabase();
        db.close();
    }

    //データベースヘルパーのコンストラクタ
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.v("onCreate","create");
            db.execSQL(create_todo_table);
            db.execSQL(CREATE_TABLE_P);
            db.execSQL(CREATE_TABLE_S);
        }catch (Exception e){
            e.printStackTrace();
            Log.v("onCreate","a = " + e);
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(CREATE_TABLE_P);
            db.execSQL(CREATE_TABLE_S);
        }catch (Exception e){
            Log.v("onUpgrade","Exception = " + e);
        }
    }
}
