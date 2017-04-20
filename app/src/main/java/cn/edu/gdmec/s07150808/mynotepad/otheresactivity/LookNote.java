package cn.edu.gdmec.s07150808.mynotepad.otheresactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import cn.edu.gdmec.s07150808.mynotepad.R;
import cn.edu.gdmec.s07150808.mynotepad.entity.ListViewEntity;


public class LookNote extends Activity {
	
	private TextView re_title,re_context;
	private ListViewEntity ent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	   setContentView(R.layout.looknote);
	   init();
	   Intent intent=getIntent();
	   ent=(ListViewEntity) intent.getSerializableExtra("data");
	   re_context.setText(ent.getText_content_of_item());
	   re_title.setText(ent.getText_title_of_item());
	 
	}

	private void init(){
		re_title=(TextView) findViewById(R.id.re_title);
		re_context=(TextView) findViewById(R.id.re_context);
	}
	
}
