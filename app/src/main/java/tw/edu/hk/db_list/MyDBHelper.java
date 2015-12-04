package tw.edu.hk.db_list;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lin on 12/3/2015.
 */
public class MyDBHelper extends SQLiteOpenHelper {

    public static  final String TABLEAAA_NAME = "aaa";

    public static final String S_ID = "_id";
    public static final String NAME_COLUMN = "name";
    public static final String PHONE_COLUMN = "phone";
    public static final String CODE_COLUMN = "code";

    //使用上面宣告的變數建立表格的SQL指令
    public static final String CREATE_TABLE_AAA =
            "CREATE TABLE " + TABLEAAA_NAME + " (" +
                    S_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME_COLUMN + " TEXT, " +
                    PHONE_COLUMN + " INTEGER, " +
                    CODE_COLUMN + " INTEGER)";

    // DB name
    public static final String DATABASE_NAME = "hk001.db";
    // 資料庫版本，資料結構改變的時候要更改這個數字，通常是加一
    public static final int VERSION = 1;
    //  資料庫物件，固定的欄位變數
    private static SQLiteDatabase database;


    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static SQLiteDatabase getDatabase(Context context) {
        if (database == null || !database.isOpen()) {
            database = new MyDBHelper(context, DATABASE_NAME,
                    null, VERSION).getWritableDatabase();
        }

        return database;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_AAA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLEAAA_NAME);
        onCreate(db);
    }
}
