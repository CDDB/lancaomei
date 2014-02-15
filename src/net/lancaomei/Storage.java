package net.lancaomei;

import java.util.ArrayList;

import android.content.ContentValues;
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
	}
	
	public void save(ContactDetail detail) {
		sqliteWriter = sqlHelper.getWritableDatabase();
		ContentValues cValues = new ContentValues();
		cValues.put("NAME", detail.getName());
		cValues.put("IMAGETAG", detail.getImageTagPath());
		cValues.put("TAG", detail.getTag());
		cValues.put("DESC", detail.getDesc());
		cValues.put("PHONE", detail.getPhone());
		sqliteWriter.insert(DictionaryOpenHelper.DICTIONARY_TABLE_NAME, null, cValues);		
		Log.i("Insert", cValues.toString());
	}
	
	public void get(ArrayList<ContactDetail> details) {
		sqliteReader = sqlHelper.getReadableDatabase();
		Cursor curser = sqliteReader.query(DictionaryOpenHelper.DICTIONARY_TABLE_NAME, null, null, null, null, null, null);
		if (null != curser) {
			while (curser.moveToNext()) {
				ContactDetail detail = new ContactDetail();
				detail.setName(curser.getString(0));
				detail.setImageTagPath(curser.getString(1));
				detail.setTag(curser.getString(2));
				detail.setDesc(curser.getString(3));
				detail.setPhone(curser.getString(4));
				details.add(detail);
			}
		}
	}
}



class DictionaryOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    public static final String DICTIONARY_TABLE_NAME = "LANCAOMEI_DETAIL";
    private static final String DICTIONARY_TABLE_CREATE =
                "CREATE TABLE IF NOT EXISTS " + DICTIONARY_TABLE_NAME + " (" +
                "NAME TEXT, " +
                "IMAGETAG TEXT, " +
                "TAG TEXT, " +
                "DESC TEXT, " +
                "PHONE TEXT);";

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