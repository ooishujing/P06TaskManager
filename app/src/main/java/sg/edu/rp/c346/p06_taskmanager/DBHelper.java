package sg.edu.rp.c346.p06_taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "task.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TASK = "tasks";
    private static final String COLUMN_ID = "_id";
    private static final String COLOUMN_NAME = "name";
    private static final String COLOUMN_DESC = "description";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_TASK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLOUMN_NAME + " TEXT," + COLOUMN_DESC + "TEXT ) ";
        db.execSQL(createTableSql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        onCreate(db);

    }

    public void insertTask(String title, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLOUMN_NAME, title);
        values.put(COLOUMN_DESC, description);
        db.insert(TABLE_TASK,null,values);
        db.close();
    }



    public ArrayList<Task> getAllTasks(String keyword) {
        ArrayList<Task> task = new ArrayList<Task>();

        ArrayList<Task> tasks = new ArrayList<Task>();
        String selectQuery = "SELECT *" + " FROM " + TABLE_TASK;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String description = cursor.getString(2);

                Task obj = new Task( title, description);
                obj.set_id(id);
                tasks.add(obj);
            }while(cursor.moveToNext());

        }
        cursor.close();
        return tasks;
    }


}
