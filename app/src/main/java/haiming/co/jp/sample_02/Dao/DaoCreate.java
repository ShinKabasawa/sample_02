package haiming.co.jp.sample_02.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DaoCreate extends SQLiteOpenHelper {

    private final static String DB_NAME ="weather.db";  // DB名
    public final static int	DB_VERSION = 1;           // バージョン

    private final String create_todo_table = "CREATE TABLE TBL_todo (" +
                                             "id INTEGER PRIMARY KEY,"+
                                             "title TEXT,"+
                                             "content TEXT,"+
                                             "fin_flg INTEGER,"+
                                             "date TEXT)";

    public DaoCreate(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreateTable(){
        SQLiteDatabase db = getWritableDatabase();
        db.close();
    }

    // todoメモの追加
    //public boolean ineart_data(String title, String content, String date){
    //    boolean res = false;
    //    return res;
    //}

    // todoメモの全取得
    //public ArrayList sel_all_todo(){
    //    ArrayList arrayList = new ArrayList();
    //    return arrayList;
    //}

    // todoメモの削除
    //public boolean del_todo(String date){
    //    boolean res = false;
    //    return res;
    //}

    //データベースヘルパーのコンストラクタ
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(create_todo_table);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
