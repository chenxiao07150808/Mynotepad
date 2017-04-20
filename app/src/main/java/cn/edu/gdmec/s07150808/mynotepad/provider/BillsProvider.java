package cn.edu.gdmec.s07150808.mynotepad.provider;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import cn.edu.gdmec.s07150808.mynotepad.database.DBOpenHelper;

public class BillsProvider extends ContentProvider{

	private static UriMatcher matcher=new UriMatcher(UriMatcher.NO_MATCH);
	
	private static final int BIllS =1;
	private static final int BIll =2;
	
	static{
		matcher.addURI("com.zhidisoft.mynotepad.billsprovider", "bill", BIllS);
		matcher.addURI("com.zhidisoft.mynotepad.billsprovider", "bill/#", BIll);
	}
	private DBOpenHelper dbopenhelper;
	@Override
	public boolean onCreate() {
		dbopenhelper=new DBOpenHelper(this.getContext(), "", null, 1);
		
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db=dbopenhelper.getWritableDatabase();
		switch (matcher.match(uri)) {
		case BIllS:
			return db.query("bill", null, null, null, null, null, null);
			
		case BIll:
			Long id=ContentUris.parseId(uri);
			String where="id= "+id;
			return db.query("bill", null, where, null, null, null, null);
		

		default:
			throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());
		}
		
	}

	@Override
	public String getType(Uri uri) {
		switch (matcher.match(uri)) {
		case BIllS:
			return "vnd.android.cursor.dir/bill";
		case BIll:
			return "vnd.android.cursor.item/bill";
		default:
		 throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());  
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db=dbopenhelper.getWritableDatabase();
		switch (matcher.match(uri)) {
		case BIllS:
			Long row=db.insert("bill", null, values);
			return ContentUris.withAppendedId(uri, row);
		case BIll:
			Long row2=db.insert("bill", null, values);
			String newuri=uri.toString().substring(0, uri.toString().lastIndexOf("/"))+row2;
			return Uri.parse(newuri);

		default:
			 throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());  
		}
		
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
