package cn.edu.gdmec.s07150808.mynotepad.otheresactivity;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.edu.gdmec.s07150808.mynotepad.R;

public class RollOutOrIn extends Activity implements OnClickListener {

	private final static int Roll_Code = 1;
	private RelativeLayout inaccount, outaccount;
	private Intent intent;
	private Button back_btn_of_roll;
	private String accountname;
	private TextView out_text,in_text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.roll_out);
		init();
	}

	private void init() {
		outaccount = (RelativeLayout) findViewById(R.id.outaccount);
		inaccount = (RelativeLayout) findViewById(R.id.inaccount);
		back_btn_of_roll = (Button) findViewById(R.id.back_btn_of_roll);
		out_text=(TextView) findViewById(R.id.out_text);
		in_text=(TextView) findViewById(R.id.in_text);
		inaccount.setOnClickListener(this);
		outaccount.setOnClickListener(this);
		back_btn_of_roll.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.outaccount:
			intent = new Intent(RollOutOrIn.this, DialogActivity.class);
			RollOutOrIn.this.startActivityForResult(intent, Roll_Code);
			
			break;
		case R.id.inaccount:
			intent = new Intent(RollOutOrIn.this, DialogActivity.class);
			RollOutOrIn.this.startActivityForResult(intent, Roll_Code);
			break;
		case R.id.back_btn_of_roll:
			RollOutOrIn.this.finish();
			break;

		default:
			break;
		}

	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(data==null){
			return;
		}
		switch (resultCode) {
		case Roll_Code:
			accountname = data.getStringExtra("data");
			in_text.setText(accountname);
			break;

		default:
			break;
		}
	}

}
