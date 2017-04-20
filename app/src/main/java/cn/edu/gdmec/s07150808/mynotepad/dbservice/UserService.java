package cn.edu.gdmec.s07150808.mynotepad.dbservice;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import cn.edu.gdmec.s07150808.mynotepad.database.DBOpenHelper;
import cn.edu.gdmec.s07150808.mynotepad.entity.GridViewEntity;
import cn.edu.gdmec.s07150808.mynotepad.entity.ListViewEntity;


public class UserService {
	
	
	public static final String TOTE_TITLE="title";
	public static final String TOTE_CONTENT="content";
	public static final String TOTE_DTIMES="dtimes";
	
	public static final String BILL_BILL="bill";
	public static final String BILL_IMG="img";
	public static final String BILL_BILLTYPE="billtype";
	public static final String BILL_SATTE="billstate";
	public static final String BILL_TIME="logtime";
	public static final String BILL_ID="id";

	public static final String ACCOUNT_NAME="accountname";
	public static final String ACCOUNT_MEMORY="money";
	

	private GridViewEntity gve;
    private String accountmoney;
	DBOpenHelper dbOpenHelper;
	
 public UserService(Context context){
		 
		 
	 dbOpenHelper=new DBOpenHelper(context, "", null, 1);
		 
	 }

 public Long insertNote( ListViewEntity ete){
	 SQLiteDatabase db= dbOpenHelper.getWritableDatabase();
	 ContentValues values=new ContentValues();
	 values.put(TOTE_TITLE, ete.getText_title_of_item());
	 values.put(TOTE_CONTENT, ete.getText_content_of_item());
	 values.put(TOTE_DTIMES, ete.getDtimes());
	 long e=db.insert("note", null, values);
	 db.close();
	 
	return e;
	 
 }

 public Long insertBill( GridViewEntity gve){
	 SQLiteDatabase db= dbOpenHelper.getWritableDatabase();
	 ContentValues values=new ContentValues();
	 values.put(BILL_BILL, gve.getBill());
	 values.put(BILL_IMG, gve.getImg());
	 values.put(BILL_BILLTYPE,gve.getBillType());
	 values.put(BILL_SATTE,gve.getBillState());
	 values.put(BILL_TIME, gve.getTime());
	 long e=db.insert("bill", null, values);
	 db.close();
	 
	 return e;
	 
 }
 

 public Long delect(String id){
	 SQLiteDatabase db= dbOpenHelper.getWritableDatabase();
	 long e=db.delete("note", "id = ?", new String[]{id});
	 db.close();
	return e;
	 
 }
 /**
  * ����note���е�����
  * @param id �����Ϣid
  * @param values Ҫ���ĵ�����
  * @return
  */
 public long updateNote(String id, ContentValues values){
	 SQLiteDatabase db= dbOpenHelper.getWritableDatabase();
	  long e= db.update("note", values," id = ? ", new String[]{id});
	  db.close();
	return e;
	 
 }
 /**
  * ����bill��������
  * @param id ���Ѽ�¼��Ӧ��id,
  * @param values ���µ�����
  * @return
  */
 public long updateBill(String id, ContentValues values){
	 SQLiteDatabase db= dbOpenHelper.getWritableDatabase();
	 long e= db.update("bill", values," id = ? ", new String[]{id});
	 db.close();
	 return e;
	 
 }
 public long updateAccount(GridViewEntity gve){
	 SQLiteDatabase db= dbOpenHelper.getWritableDatabase();
	   Double oldmoney=Double.valueOf(findAccount(gve.getAccountName()).getBill().toString().trim());
		Double newmoney=Double.valueOf(gve.getBill().toString().trim());
		int state=Integer.parseInt(gve.getBillState().toString().trim());
		
		if(state==0){
			accountmoney=Double.toString(oldmoney-newmoney);
			
		}else if(state==1){
			accountmoney=Double.toString(oldmoney+newmoney);
			
		}
		ContentValues values=new ContentValues();
		values.put("money", accountmoney);
	 long e= db.update("account", values," accountname = ? ", new String[]{gve.getAccountName()});
	 db.close();
	 return e;
	 
 }
 public List<ListViewEntity> findAll(){
	 SQLiteDatabase db= dbOpenHelper.getWritableDatabase();
	 Cursor cursor=db.query("note", null, null, null, null, null, "id desc");
	 List<ListViewEntity> list=new ArrayList<ListViewEntity>();
	 while (cursor.moveToNext()) {
		 
		String title=cursor.getString(cursor.getColumnIndex(TOTE_TITLE));
		String content=cursor.getString(cursor.getColumnIndex(TOTE_CONTENT));
		String dtimes=cursor.getString(cursor.getColumnIndex(TOTE_DTIMES));
		ListViewEntity entity=new ListViewEntity(title,content,dtimes);
	    list.add(entity);
	 }
	 return list;
	 
 }
 public List<GridViewEntity> findAllBill(){
	 SQLiteDatabase db= dbOpenHelper.getWritableDatabase();
	 Cursor cursor=db.query("bill", null, null, null, null, null, "id desc");
	 List<GridViewEntity> list=new ArrayList<GridViewEntity>();
	 while (cursor.moveToNext()) {
		 
		 String dbill=cursor.getString(cursor.getColumnIndex(BILL_BILL));
		 String dimg=cursor.getString(cursor.getColumnIndex(BILL_IMG));
		 String dbstate=cursor.getString(cursor.getColumnIndex(BILL_SATTE));
		 String dbtype=cursor.getString(cursor.getColumnIndex(BILL_BILLTYPE));
		 GridViewEntity entity=new GridViewEntity();
		 entity.setBillType(dbtype);
		 entity.setBillState(dbstate);
		 entity.setBill(dbill);
		 entity.setImg(Integer.parseInt(dimg));
		 list.add(entity);
	 }
	 return list;
 }
	 public List<GridViewEntity> findAllAccount(){
		 SQLiteDatabase db= dbOpenHelper.getWritableDatabase();
		 Cursor cursor=db.query("account", null, null, null, null, null, "id asc");
		 List<GridViewEntity> list=new ArrayList<GridViewEntity>();
		 while (cursor.moveToNext()) {
			 
			 String dbmoney=cursor.getString(cursor.getColumnIndex(ACCOUNT_MEMORY));
			 
			 String dbaccountname=cursor.getString(cursor.getColumnIndex(ACCOUNT_NAME));
			 GridViewEntity entity=new GridViewEntity();
			 entity.setAccountName(dbaccountname);
			 entity.setBill(dbmoney);
			 list.add(entity);
		 }
		 return list;
		 
 }
 public ListViewEntity find(String id){
	 SQLiteDatabase db= dbOpenHelper.getWritableDatabase();
	Cursor cursor=db.query("note", new String[]{TOTE_TITLE,TOTE_CONTENT,TOTE_DTIMES}, "id = ? ",new String[]{id}, null, null, null);
	ListViewEntity entity=null;
	if(cursor.moveToFirst()){
		String title=cursor.getString(cursor.getColumnIndex(TOTE_TITLE));
		String content=cursor.getString(cursor.getColumnIndex(TOTE_CONTENT));
		String dtimes=cursor.getString(cursor.getColumnIndex(TOTE_DTIMES));
		
		entity=new ListViewEntity(title, content, dtimes);
	}
	return entity;
	
 }
 public GridViewEntity findAccount(String accountname){
	 SQLiteDatabase db= dbOpenHelper.getWritableDatabase();
	 Cursor cursor=db.query("account", null, "accountname = ? ",new String[]{accountname}, null, null, null);
	 GridViewEntity entity=null;
	 if(cursor.moveToFirst()){
		 String dbmoney=cursor.getString(cursor.getColumnIndex(ACCOUNT_MEMORY));
		 entity=new GridViewEntity();
		 entity.setBill(dbmoney);
	 }
	 return entity;
	 
 }
 public GridViewEntity findGve(String billtype,String time){
	 SQLiteDatabase db= dbOpenHelper.getWritableDatabase();
	 Cursor cursor=db.query("bill", null,"billtype=? and logtime=? ",new String[]{billtype,time}, null, null, null);
	
	 while(cursor.moveToNext()){
		 String dbill=cursor.getString(cursor.getColumnIndex(BILL_BILL));
		 String dimg=cursor.getString(cursor.getColumnIndex(BILL_IMG));
		 int img=Integer.parseInt(dimg);
		 String dtype=cursor.getString(cursor.getColumnIndex(BILL_BILLTYPE));
		 String dstate=cursor.getString(cursor.getColumnIndex(BILL_SATTE));
		 String dtime=cursor.getString(cursor.getColumnIndex(BILL_TIME));
		 String did=cursor.getString(cursor.getColumnIndex(BILL_ID));
		 int id=Integer.parseInt(did);
		 gve=new GridViewEntity();
		 gve.setBill(dbill);
	     gve.setBillState(dstate);
	     gve.setBillType(dtype);
	     gve.setId(id);
	     gve.setImg(img);
	     gve.setTime(dtime);
		
	 }
	 return gve;
	 
 }

}
