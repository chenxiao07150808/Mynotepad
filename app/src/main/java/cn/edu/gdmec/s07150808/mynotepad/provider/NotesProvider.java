package cn.edu.gdmec.s07150808.mynotepad.provider;

import java.util.regex.Matcher;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import cn.edu.gdmec.s07150808.mynotepad.database.DBOpenHelper;

public class NotesProvider extends ContentProvider{

	//定义一个urimatcher匹配对象
	public  static UriMatcher matcher=new UriMatcher(UriMatcher.NO_MATCH);
	//定义获取全部记事薄内容成功的标示
	public static final int NOTES=1;
	//定义获取单个记事簿内容成功的标示
	public static final int NOTE=2;
	//定义静态代码块，完成注册所需匹配的uri
	/**
	 * 参数1：建议写成要开放的标准名
	 * 参数2：标准对应的表名
	 * 参数3：匹配成功后返回的标示，#代表通配符
	 */
	static{
		matcher.addURI("com.zhidisoft.mynotepad.noteprovider", "note", NOTES);
		matcher.addURI("com.zhidisoft.mynotepad.noteprovider", "note/#", NOTE);
	}

	private DBOpenHelper dbopenhelper;
	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		dbopenhelper=new DBOpenHelper(this.getContext(), "", null, 1);
		return false;
	}

	/**
	 * 5-4、查询记录
	 * 注：db.query()方法对应参数解释
	 * 其中各种参数意思如下（如果其中某个参数不设置，可以指定为null）：
	 　　   	table：表名。相当于select语句from关键字后面的部分。如果是多表联合查询，可以用逗号将两个表名分开。<br>
	 　　    	columns：要查询出来的列名。相当于select语句select关键字后面的部分。<br>
	 　　		selection：查询条件子句，相当于select语句where关键字后面的部分，在条件子句允许使用占位符"?"<br>
	 　　		selectionArgs：对应于selection语句中占位符的值，值在数组中的位置与占位符在语句中的位置必须一致，否则就会有异常。<br>
	 　　		groupBy：相当于select语句groupby关键字后面的部分<br>
	 having：相当于select语句having关键字后面的部分<br>
	 orderBy：相当于select语句orderby关键字后面的部分<br>
	 limit：指定偏移量和获取的记录数，相当于select语句limit关键字后面的部分<br>
	 *
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
						String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db=dbopenhelper.getWritableDatabase();
		switch (matcher.match(uri)) {
			case NOTES:
				return db.query("note", null, null, null, null, null, null);

			case NOTE:
				long id=ContentUris.parseId(uri);
				String where="id= "+id;
				return db.query("note", null, where, null, null, null, null);



			default:
				throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());
		}

	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		switch (matcher.match(uri)) {
			case NOTES:
				return "vnd.android.cursor.dir/note";
			case NOTE:
				return "vnd.android.cursor.item/note";

			default:
				throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());
		}

	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db=dbopenhelper.getWritableDatabase();
		switch (matcher.match(uri)) {
			case NOTES:
				long row=db.insert("note", null, values);
				Uri newUri=ContentUris.withAppendedId(uri, row);
				return newUri;
			case NOTE:
				long row2=db.insert("note", null, values);
				String stUri=uri.toString();
				stUri=stUri.substring(0, stUri.lastIndexOf("/"))+row2;
				return Uri.parse(stUri);
			default:
				throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());
		}

	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		SQLiteDatabase db=dbopenhelper.getWritableDatabase();
		switch (matcher.match(uri)) {
			case NOTES:
				return db.delete("note", null, null);
			case NOTE:
				long id=ContentUris.parseId(uri);
				String where="id= "+id;
				int e=db.delete("note", where, null);
				return e;

			default:
				throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());
		}

	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
					  String[] selectionArgs) {
		SQLiteDatabase db=dbopenhelper.getWritableDatabase();
		switch (matcher.match(uri)) {
			case NOTES:
				return db.update("note", values, null, null);
			case NOTE:
				String where="id= "+ContentUris.parseId(uri);
				int e=db.update("note", values, where, null);
				return e;
			default:
				throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());
		}

	}

}
