package cn.edu.gdmec.s07150808.mynotepad.otheresactivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import cn.edu.gdmec.s07150808.mynotepad.R;
import cn.edu.gdmec.s07150808.mynotepad.adapter.GridViewAdapter;
import cn.edu.gdmec.s07150808.mynotepad.dbservice.UserService;
import cn.edu.gdmec.s07150808.mynotepad.entity.GridViewEntity;
import cn.edu.gdmec.s07150808.mynotepad.otheresactivity.DialogActivity;

public class AddBill extends Activity implements OnClickListener {

	/**
	 * adapter相关变量
	 */
	private GridViewEntity ent;
	private GridViewAdapter adapter;
	private GridView gridview;
	private Integer[] img;
	private String[] st;
	private List<GridViewEntity> list;

	/**
	 * addBill相关变量
	 */

	private Button pay_btn, income_btn;
	private TextView changedshow_text;
	private ImageView changedshow_jpg;
	private Button cash_btn;
	/**
	 * 添加数据到库相关变量
	 */
	private Intent intent;
	private static final int REQCODE = 1;
	private String accountname ;
	private String inbill;
	private int indeximg;
	private int instate=0;
	private String intype;
	private String intime;
	private GridViewEntity inent;
	private UserService service;
	private GridViewEntity dbent;
	private Long e;// 判断数据是否插入成功

	/**
	 * 自定义监听器
	 */
	private MyGridLister grlister;

	/**
	 * 计算器相关变量
	 */
	private TextView one, four, seven, ac, two, five, eight, zero, three, six, nine, point, del, add, ok;
	private TextView changedshow_number;
	private boolean isChoicked = false;
	private String intext1 = null;
	private String intext2 = null;
	private String intext3 = null;
	private String intext4 = null;
	private String intext5 = null;
	private String intext6 = null;
	private String intext7 = null;
	private String intext8 = null;
	private String intext9 = null;
	private String intext0 = null;
	private String intextpoint = null;

	private double result = 0;
	private double in1 = 0;
	private double in2 = 0;
	private int opcount = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addbill_layout);
		init();
		initView();
		initSelectColor();
		initCalculator();

	}

	private void initView() {
		img = new Integer[] { R.drawable.type_big_1, R.drawable.type_big_2, R.drawable.type_big_3,
				R.drawable.type_big_4, R.drawable.type_big_5, R.drawable.type_big_6, R.drawable.type_big_7,
				R.drawable.type_big_8, R.drawable.type_big_9, R.drawable.type_big_10, R.drawable.type_big_11,
				R.drawable.type_big_12 };
		st = new String[] { "一般", "用餐", "零食", "交通", "充值", "购物", "娱乐", "住房", "约会", "网购", "日用品", "香烟" };
		ent = new GridViewEntity(img, st);
		list = new ArrayList<GridViewEntity>();
		list.add(ent);
		list.add(ent);
		list.add(ent);
		list.add(ent);
		list.add(ent);
		list.add(ent);
		list.add(ent);
		list.add(ent);
		list.add(ent);
		list.add(ent);
		list.add(ent);
		list.add(ent);
		adapter = new GridViewAdapter(list, this);
		gridview.setAdapter(adapter);
	}

	private void incomeIsClick() {
		img = new Integer[] { R.drawable.type_big_13, R.drawable.type_big_14, R.drawable.type_big_15,
				R.drawable.type_big_16, R.drawable.type_big_17, R.drawable.type_big_18, R.drawable.type_big_19 };
		st = new String[] { "工资", "外快兼职", "奖金", "借入", "零花钱", "投资收入", "礼物红包" };
		ent = new GridViewEntity(img, st);
		list = new ArrayList<GridViewEntity>();
		list.add(ent);
		list.add(ent);
		list.add(ent);
		list.add(ent);
		list.add(ent);
		list.add(ent);
		list.add(ent);

		adapter = new GridViewAdapter(list, this);
		adapter.notifyDataSetChanged();
		gridview.setAdapter(adapter);
	}

	private void init() {
		gridview = (GridView) findViewById(R.id.gridview);
		income_btn = (Button) findViewById(R.id.income_btn);
		pay_btn = (Button) findViewById(R.id.pay_btn);
		income_btn.setOnClickListener(this);
		pay_btn.setOnClickListener(this);
		cash_btn = (Button) findViewById(R.id.cash_btn);
		cash_btn.setOnClickListener(this);
		changedshow_text = (TextView) findViewById(R.id.changedshow_text);
		changedshow_jpg = (ImageView) findViewById(R.id.changedshow_jpg);
		grlister = new MyGridLister();
		gridview.setOnItemClickListener(grlister);
	}

	/**
	 * 实例化计算器相关
	 */
	private void initCalculator() {
		one = (TextView) findViewById(R.id.one);
		one.setOnClickListener(this);
		two = (TextView) findViewById(R.id.two);
		two.setOnClickListener(this);
		three = (TextView) findViewById(R.id.three);
		three.setOnClickListener(this);
		four = (TextView) findViewById(R.id.four);
		four.setOnClickListener(this);
		five = (TextView) findViewById(R.id.five);
		five.setOnClickListener(this);
		six = (TextView) findViewById(R.id.six);
		six.setOnClickListener(this);
		seven = (TextView) findViewById(R.id.seven);
		seven.setOnClickListener(this);
		eight = (TextView) findViewById(R.id.eight);
		eight.setOnClickListener(this);
		nine = (TextView) findViewById(R.id.nine);
		nine.setOnClickListener(this);
		zero = (TextView) findViewById(R.id.zero);
		zero.setOnClickListener(this);
		ac = (TextView) findViewById(R.id.ac);
		ac.setOnClickListener(this);
		add = (TextView) findViewById(R.id.add);
		add.setOnClickListener(this);
		del = (TextView) findViewById(R.id.del);
		del.setOnClickListener(this);
		point = (TextView) findViewById(R.id.point);
		point.setOnClickListener(this);
		ok = (TextView) findViewById(R.id.ok);
		ok.setOnClickListener(this);
		changedshow_number = (TextView) findViewById(R.id.changedshow_number);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
			case R.id.pay_btn:
				initView();
				pay_btn.setBackgroundColor(pay_btn.getResources().getColor(R.color.blue));
				income_btn.setBackgroundColor(pay_btn.getResources().getColor(R.color.white));
				pay_btn.setTextColor(pay_btn.getResources().getColor(R.color.white));
				income_btn.setTextColor(income_btn.getResources().getColor(R.color.black));
				instate = 0;
				break;
			case R.id.income_btn:
				incomeIsClick();
				income_btn.setBackgroundColor(income_btn.getResources().getColor(R.color.blue));
				pay_btn.setBackgroundColor(pay_btn.getResources().getColor(R.color.white));
				income_btn.setTextColor(income_btn.getResources().getColor(R.color.white));
				pay_btn.setTextColor(pay_btn.getResources().getColor(R.color.black));
				instate = 1;
				break;
			case R.id.cash_btn:
				intent = new Intent(AddBill.this, DialogActivity.class);
				AddBill.this.startActivityForResult(intent, REQCODE);
				break;

			case R.id.one:
				if (isChoicked) {
					changedshow_number.setText(null);
					isChoicked = false;

				}
				intext1 = changedshow_number.getText().toString();
				intext1 += "1";
				changedshow_number.setText(intext1);
				break;
			case R.id.two:
				if (isChoicked) {
					changedshow_number.setText(null);
					isChoicked = false;
				}
				intext2 = changedshow_number.getText().toString();
				intext2 += "2";
				changedshow_number.setText(intext2);
				break;
			case R.id.three:
				if (isChoicked) {
					changedshow_number.setText(null);
					isChoicked = false;
				}
				intext3 = changedshow_number.getText().toString();
				intext3 += "3";
				changedshow_number.setText(intext3);
				break;
			case R.id.four:
				if (isChoicked) {
					changedshow_number.setText(null);
					isChoicked = false;
				}
				intext4 = changedshow_number.getText().toString();
				intext4 += "4";
				changedshow_number.setText(intext4);
				break;
			case R.id.five:
				if (isChoicked) {
					changedshow_number.setText(null);
					isChoicked = false;
				}
				intext5 = changedshow_number.getText().toString();
				intext5 += "5";
				changedshow_number.setText(intext5);
				break;
			case R.id.six:
				if (isChoicked) {
					changedshow_number.setText(null);
					isChoicked = false;
				}
				intext6 = changedshow_number.getText().toString();
				intext6 += "6";
				changedshow_number.setText(intext6);
				break;
			case R.id.seven:
				if (isChoicked) {
					changedshow_number.setText(null);
					isChoicked = false;
				}
				intext7 = changedshow_number.getText().toString();
				intext7 += "7";
				changedshow_number.setText(intext7);
				break;
			case R.id.eight:
				if (isChoicked) {
					changedshow_number.setText(null);
					isChoicked = false;
				}
				intext8 = changedshow_number.getText().toString();
				intext8 += "8";
				changedshow_number.setText(intext8);
				break;
			case R.id.nine:
				if (isChoicked) {
					changedshow_number.setText(null);
					isChoicked = false;
				}
				intext9 = changedshow_number.getText().toString();
				intext9 += "9";
				changedshow_number.setText(intext9);
				break;
			case R.id.zero:
				if (isChoicked) {
					changedshow_number.setText(null);
					isChoicked = false;
				}
				intext0 = changedshow_number.getText().toString();
				intext0 += "0";
				changedshow_number.setText(intext0);
				break;

			case R.id.point:

				intextpoint = changedshow_number.getText() + ".";
				changedshow_number.setText(intextpoint);
				break;
			case R.id.add:
				if (changedshow_number.getText().toString().equals(null)
						|| changedshow_number.getText().toString().equals("")) {
					return;
				}
				in1 = Double.parseDouble(changedshow_number.getText().toString());
				changedshow_number.setText(null);
				opcount = 1;
				isChoicked = false;
				break;
			case R.id.ac:
				if (changedshow_number.getText().toString().equals(null)
						|| changedshow_number.getText().toString().equals("")) {
					return;
				}

				changedshow_number.setText(null);
				opcount = 2;
				isChoicked = false;
				break;
			case R.id.del:
				if (changedshow_number.getText().toString().equals(null)
						|| changedshow_number.getText().toString().equals("")) {
					return;
				}
				String st = changedshow_number.getText().toString();
				changedshow_number.setText(st.subSequence(0, st.length() - 1));
				opcount = 3;
				isChoicked = false;
				break;

			case R.id.ok:

				if (changedshow_number.getText().toString().equals(null)
						|| changedshow_number.getText().toString().equals("")) {
					return;
				}
				in2 = Double.parseDouble(changedshow_number.getText().toString());
				changedshow_number.setText(null);
				result = in1 + in2;
				changedshow_number.setText(Double.toString(result));
				isChoicked = true;
				inbill = changedshow_number.getText().toString().trim();
				in1=0;
				in2=0;
				intype = changedshow_text.getText().toString().trim();
				intime = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
				inent = new GridViewEntity();
				inent.setBill(inbill);
				inent.setBillState(instate+"");
				inent.setBillType(intype);
				inent.setImg(indeximg);
				inent.setTime(intime);
				inent.setAccountName(cash_btn.getText().toString().trim());
				insertBill(inent);
				UpdataAccount(inent);
				break;
			default:
				break;
		}
	}

	private void initSelectColor() {
		pay_btn.setBackgroundColor(pay_btn.getResources().getColor(R.color.blue));
		income_btn.setBackgroundColor(pay_btn.getResources().getColor(R.color.white));
		pay_btn.setTextColor(pay_btn.getResources().getColor(R.color.white));
		income_btn.setTextColor(income_btn.getResources().getColor(R.color.black));

	}

	/**
	 * gridview监听
	 *
	 * @author Administrator
	 *
	 */

	private class MyGridLister implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

			ent = list.get(position);
			changedShow(ent, position);

			indeximg = position;
		}

	}

	/**
	 * 此方法可展示的gridview单击结果
	 */
	private void changedShow(GridViewEntity ent, int position) {

		Integer[] img = ent.getImgs();
		int path = img[position];
		changedshow_jpg.setImageResource(path);
		String[] st = ent.getTypes();
		String text = st[position];
		changedshow_text.setText(text);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQCODE) {
			switch (resultCode) {
				case RESULT_OK:
					accountname = data.getStringExtra("data");
					cash_btn.setText(accountname);
					break;

				default:
					break;
			}
		}
	}

	private long insertBill(GridViewEntity inent) {
		service = new UserService(AddBill.this);

		dbent = service.findGve(inent.getBillType(),inent.getTime());
		if (dbent != null) {
			Double dbbill = Double.valueOf(dbent.getBill());
			Double newbill = Double.valueOf(inent.getBill());
			String bill=Double.toString(dbbill + newbill);
			String id = Integer.toString(dbent.getId());
			ContentValues values = new ContentValues();
			values.put("bill", bill);
			e=service.updateBill(id, values);

			/**
			 * 当要插入账单记录时间和账单类型相同时
			 */
		} else {
			e = service.insertBill(inent);

		}
		return e;

	}

	private void UpdataAccount(GridViewEntity gve) {
		service = new UserService(AddBill.this);
		service.updateAccount(gve);

	}
}
