package cn.edu.gdmec.s07150808.mynotepad.otheresactivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import cn.edu.gdmec.s07150808.mynotepad.R;
import cn.edu.gdmec.s07150808.mynotepad.dbservice.UserService;
import cn.edu.gdmec.s07150808.mynotepad.entity.ListViewEntity;


public class AddNoteBook extends Activity implements OnClickListener {

	private Button save_btn, undo_btn, addtime_btn;
	private EditText edit_title, edit_context;


	private String date = null;
	private UserService service;
	private String intext = null;
	private int op = 0;


	public AddNoteBook(UserService service) {
		super();
		this.service = service;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addnote_layout);
		init();

	}

	private void init() {
		edit_title = (EditText) findViewById(R.id.edit_title);
		edit_title.requestFocus();
		edit_context = (EditText) findViewById(R.id.edit_context);
		save_btn = (Button) findViewById(R.id.save_btn);
		undo_btn = (Button) findViewById(R.id.undo_btn);
		addtime_btn = (Button) findViewById(R.id.addtime_btn);
		addtime_btn.setOnClickListener(this);
		undo_btn.setOnClickListener(this);
		save_btn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
			case R.id.save_btn:
				service = new UserService(AddNoteBook.this);
				insertNote(service);

				AddNoteBook.this.finish();
				break;

			case R.id.undo_btn:
				if (!edit_title.getText().toString().trim().isEmpty()) {
					op = 1;
				}
				if (!edit_context.getText().toString().trim().isEmpty()) {
					op = 2;
				}
				switch (op) {
					case 1:
						String sub1=cancalInto(edit_title.getText().toString().trim());

						edit_title.setText(sub1);
						break;
					case 2:
						String sub2=cancalInto(edit_context.getText().toString().trim());
						edit_context.setText(sub2);
						break;

					default:
						break;
				}

				break;
			case R.id.addtime_btn:
				date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				String etext=edit_context.getText().toString().trim();
				edit_context.setText(etext+date);
				break;
			default:
				break;
		}
	}

	/**
	 * 定义方法去获得输入的内容插入数据库；
	 */
	public  void insertNote(UserService service) {
		ListViewEntity ete = new ListViewEntity(
				edit_title.getText().toString(), edit_context.getText()
				.toString(), date);
		service.insertNote(ete);
	}

	/**
	 * 定义一个方法撤销editext输入
	 */
	private String cancalInto(String intext) {
		String subedtext = intext.substring(0, intext.length() - 1);
		return subedtext;

	}

}
