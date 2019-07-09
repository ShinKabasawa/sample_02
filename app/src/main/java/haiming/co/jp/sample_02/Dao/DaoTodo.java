package haiming.co.jp.sample_02.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

public class DaoTodo extends SQLiteOpenHelper {

    // INSERT文
    private final String insert_todo = "";

    // SELECT文
    private final String sel_all = "";

    // DELETE文
    private final String delete_todo = "";

    public DaoTodo(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DaoCreate.DB_NAME, null, DaoCreate.DB_VERSION);
        Log.v("DaoTodo","DaoTodo");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v("DaoTodo","onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v("DaoTodo","onUpgrade");
    }

    // Todoの追加
    public boolean add_todo(String title, String content, String date, int check_val){
        boolean res = false;
        return res;
    }

    // Todoの全取得
    public ArrayList sel_all_todo(){
        ArrayList arrayList = new ArrayList();
        return arrayList;
    }

    // Todoの削除
    public boolean del_todo(String title, String date){
        boolean res = false;
        return res;
    }
}
