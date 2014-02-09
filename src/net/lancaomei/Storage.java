package net.lancaomei;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Storage {
	private DictionaryOpenHelper sqlHelper;
	SQLiteDatabase sqliteReader;
	SQLiteDatabase sqliteWriter;

			
	
	Storage(Context context) {
		sqlHelper = new DictionaryOpenHelper(context);
		sqliteReader = sqlHelper.getReadableDatabase();

	}
	
	public void save(ContactDetail detail) {
		sqliteWriter = sqlHelper.getWritableDatabase();
		String sql = "insert into "+ 
				DictionaryOpenHelper.DICTIONARY_TABLE_NAME +
				" (NAME, PHONE, IMAGETAG) "+
				" VALUES (" + detail.getName() + ", " +
				detail.getPhone() + ", " +
				detail.getImageTagPath() + ");";
		Log.i("Insert", sql);
		sqliteWriter.execSQL(sql);
	}
	
	public void get(ArrayList<ContactDetail> details) {
		Cursor curser = sqliteReader.query(DictionaryOpenHelper.DICTIONARY_TABLE_NAME, null, null, null, null, null, null);
		if (null != curser) {
			while (curser.moveToNext()) {
				ContactDetail detail = new ContactDetail();
				detail.setName(curser.getString(0));
				detail.setPhone(curser.getString(1));
				detail.setImageTagPath(curser.getString(2));
				details.add(detail);
			}
		}
	}
}



class DictionaryOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    public static final String DICTIONARY_TABLE_NAME = "LANCAOMEI";
    private static final String DICTIONARY_TABLE_CREATE =
                "CREATE TABLE IF NOT EXISTS " + DICTIONARY_TABLE_NAME + " (" +
                "NAME TEXT, " +
                "PHONE TEXT, " +
                "IMAGETAG TEXT, " +
                "OTHER TEXT);";

    DictionaryOpenHelper(Context context) {
        super(context, DICTIONARY_TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DICTIONARY_TABLE_CREATE);
    }

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
	}
}