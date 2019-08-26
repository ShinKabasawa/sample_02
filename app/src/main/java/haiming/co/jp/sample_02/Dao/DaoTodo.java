package haiming.co.jp.sample_02.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

import haiming.co.jp.sample_02.Data.TodoData;

public class DaoTodo extends SQLiteOpenHelper {

    // INSERT文
    public static final String inserat    =   "INSERT INTO TBL_todo (" +
                                                    "title, content, fin_flg, date) " +
                                                    "VALUES (?, ?, ?, ?)";

    // SELECT文
    private static final String sel_all =           "SELECT * FROM TBL_todo";
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
        SQLiteDatabase db = this.getWritableDatabase();

        try{
            SQLiteStatement stmt = db.compileStatement(inserat);
            stmt.bindString(1, title);
            stmt.bindString(2, content);
            stmt.bindLong(3, check_val);
            stmt.bindString(4, date);
            int guidanceId = (int) stmt.executeInsert();
        }catch (SQLException e) {
            e.printStackTrace();
            Log.d("add_todo", e.getMessage());
            return res;
        }
        db.close();
        res = true;
        return res;
    }

    // Todoの全取得
    public ArrayList<TodoData> sel_all_todo(){
        ArrayList arrayList = new ArrayList();
        TodoData data = new TodoData();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery(sel_all, new String[]{});
        c.moveToFirst();

        if (c.moveToFirst()){
            do {
                data = new TodoData();
                data.todo_title = c.getString(1);
                data.todo_content = c.getString(2);
                data.setting_date = c.getString(3);
                data.fin_flg = c.getInt(4);

                arrayList.add(data);
            }while (c.moveToNext());
        }

        return arrayList;
    }

    // Todoの削除
    public boolean del_todo(String title, String date){
        boolean res = false;
        return res;
    }
}
