package cn.edu.gdmec.s07150808.mynotepad.otheresactivity;

import java.util.ArrayList;
import java.util.List;







import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import cn.edu.gdmec.s07150808.mynotepad.R;
import cn.edu.gdmec.s07150808.mynotepad.adapter.ListViewAdapterOfDialogActivity;
import cn.edu.gdmec.s07150808.mynotepad.dbservice.UserService;
import cn.edu.gdmec.s07150808.mynotepad.entity.GridViewEntity;

public class DialogActivity extends Activity{

	private ListViewAdapterOfDialogActivity adapter;
	private List<GridViewEntity> list;
	private UserService service;
	

	private ListView listview_of_diact;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialogactivity);
		getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		initView();
		init();
		listview_of_diact.setOnItemClickListener( new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stu
				String st=list.get(position).getAccountName();
				Toast.makeText(DialogActivity.this, st, Toast.LENGTH_SHORT).show();
				DialogActivity.this.getIntent().putExtra("data", st);
				DialogActivity.this.setResult(RESULT_OK,DialogActivity.this.getIntent());
				DialogActivity.this.finish();
			}
		});
		
	}
	private void initView(){
		listview_of_diact=(ListView)findViewById(R.id.listview_of_diact);
	}

	private void init(){
		service=new UserService(DialogActivity.this);
		list=service.findAllAccount();
		
		adapter=new ListViewAdapterOfDialogActivity(DialogActivity.this, list);
		listview_of_diact.setAdapter(adapter);
	}
	
}
