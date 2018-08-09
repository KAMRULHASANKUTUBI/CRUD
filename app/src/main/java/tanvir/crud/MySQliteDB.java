package tanvir.crud;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQliteDB extends SQLiteOpenHelper {

    private static final int DB_VERSION=1;
    private static final String DB_NAME="phonebook.db";

    private static final String TABLE_NAME="contacts";

    private static final String COLUMN1="id";
    private static final String COLUMN2="name";
    private static final String COLUMN3="phone";


    public MySQliteDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query;
        query="CREATE TABLE " + TABLE_NAME + "(id integer primary key,cell text,phone text)";
        db.execSQL(query);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public boolean addToTable(String id,String name,String phone){

        SQLiteDatabase database=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN1,id);
        values.put(COLUMN2,name);
        values.put(COLUMN3,phone);

        long check=database.insert(TABLE_NAME,null,values);
        if (check==-1){
            return false;
        }else{
            return true;
        }

    }
    public Cursor display(){
        SQLiteDatabase db=getReadableDatabase();
        Cursor result;
        result=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return result;
    }

    public int deleteData(String id){
        SQLiteDatabase db=getWritableDatabase();
        return db.delete(TABLE_NAME,"id=?",new String[]{id});
    }
    public boolean updateData(String id,String name,String phone){

        SQLiteDatabase database=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN1,id);
        values.put(COLUMN2,name);
        values.put(COLUMN3,phone);

        long check=database.update(TABLE_NAME,values,"id=?",new String[]{id});
        if (check==-1){
            return false;
        }else{
            return true;
        }

    }
}
