package tw.edu.hk.db_list;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private SQLiteDatabase db;
    public static final int VERSION = 1;

    public static final String NAME_COLUMN = "name";
    public static final String PHONE_COLUMN = "phone";
    public static final String CODE_COLUMN = "code";
    public static  final String TABLE_NAME = "aaa";
    public static final String KEY_ID = "_id";

    public UserDAO(Context context){
        db = MyDBHelper.getDatabase(context);
    }

    public void close(){
        db.close();
    }

    public UserItem insert(UserItem uu){
        ContentValues cv = new ContentValues();

        cv.put(NAME_COLUMN, uu.getName());
        cv.put(PHONE_COLUMN, uu.getPhone());
        cv.put(CODE_COLUMN, uu.getCode());

        long id = db.insert(TABLE_NAME, null, cv);

        uu.setId((int) id);

        return uu;
    }

    public boolean update(UserItem uu){
        ContentValues cv = new ContentValues();

        cv.put(NAME_COLUMN, uu.getName());
        cv.put(PHONE_COLUMN, uu.getPhone());
        cv.put(CODE_COLUMN, uu.getCode());

        String where = KEY_ID + "=" + uu.getId();
        long id = db.insert(TABLE_NAME, null, cv);

        uu.setId((int)id);

        return db.update(TABLE_NAME, cv, where, null) > 0;
    }

    public boolean delete(int id){
        String where = KEY_ID + "=" + id;

        return db.delete(TABLE_NAME, where , null) > 0;
    }

    public List<UserItem> getAll() {
        List<UserItem> result = new ArrayList<>();
        Cursor cursor = db.query(
                TABLE_NAME, null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }

        cursor.close();
        return result;
    }

    public UserItem get(long id) {
        UserItem item = null;
        String where = KEY_ID + "=" + id;

        Cursor result = db.query(
                TABLE_NAME, null, where, null, null, null, null, null);

        if (result.moveToFirst()){
            item = getRecord(result);
        }

        result.close();

        return item;
    }


    public UserItem getRecord(Cursor cursor) {
        UserItem result = new UserItem("");

        result.setId(cursor.getInt(0));
        result.setName(cursor.getString(1));
        result.setPhone(cursor.getString(2));
        result.setCode(cursor.getInt(3));

        return result;
    }

    public int getCount() {
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }

        return result;
    }
}
