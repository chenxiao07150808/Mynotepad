package cn.edu.gdmec.s07150808.mynotepad.fg;


import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import cn.edu.gdmec.s07150808.mynotepad.R;
import cn.edu.gdmec.s07150808.mynotepad.adapter.ListViewAdapter;
import cn.edu.gdmec.s07150808.mynotepad.dbservice.UserService;
import cn.edu.gdmec.s07150808.mynotepad.entity.ListViewEntity;
import cn.edu.gdmec.s07150808.mynotepad.otheresactivity.AddNoteBook;
import cn.edu.gdmec.s07150808.mynotepad.otheresactivity.LookNote;
import cn.edu.gdmec.s07150808.mynotepad.xlistviewutil.XListView;


public class FgNoteBook extends Fragment implements OnClickListener ,XListView.IXListViewListener {

	//无关变量
	private static final String LOG_TAG="<<<<<<日志>>>>>>";

	private ListView listview;
	private Button add_btn_of_notebook;
	private List<ListViewEntity> list;

	private ListViewAdapter adapter;
	private View view;
	private long e;

	private MyItemOnClick myItemOnClick;
	/**
	 * 获得数据库note表相关数据所用变量
	 */
	private UserService service;
	/**
	 * 通讯相关的变量定义
	 */
	private static final int Code_Note = 1;
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		Log.e(LOG_TAG, "这是fg的onAttach1=======");
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.e(LOG_TAG, "这是fg的onCreate2=======");
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.e(LOG_TAG, "这是fg的onActivityCreated4=======");
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.e(LOG_TAG, "这是fg的onResume6=======");
	}
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.e(LOG_TAG, "这是fg的onPause7=======");
	}
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.e(LOG_TAG, "这是fg的onStop8=======");
	}
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		Log.e(LOG_TAG, "这是fg的onDestroyView9=======");
	}
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		init();
		list=findNote();
		adapter = new ListViewAdapter(getActivity(), list);
		listview.setAdapter(adapter);
		Log.e(LOG_TAG, "这是fg的onstart5=======");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fgnotebook, container, false);
		Log.e(LOG_TAG, "这是fg的onCreateView3=======");
		return view;
	}

	private void init() {

		listview = (ListView) view.findViewById(R.id.listview);
		add_btn_of_notebook = (Button) view
				.findViewById(R.id.add_btn_of_notebook);
		add_btn_of_notebook.setOnClickListener(this);
		myItemOnClick=new MyItemOnClick();
		listview.setOnItemClickListener(myItemOnClick);


	}
	private class MyItemOnClick implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
								long id) {
			// TODO Auto-generated method stub
			Intent intent =new Intent(getActivity(),LookNote.class);
			Bundle bundle=new Bundle();
			bundle.putSerializable("data", list.get(position));
			intent.putExtras(bundle);
			getActivity().startActivity(intent);

		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.add_btn_of_notebook:
				Intent intent = new Intent(getActivity(), AddNoteBook.class);
				getActivity().startActivity(intent);

				break;

			default:
				break;
		}
	}

	private List<ListViewEntity> findNote(){
		service=new UserService(getActivity());
		list=service.findAll();
		return list;

	}
	private long delNote(String id){
		service=new UserService(getActivity());
		e=service.delect(id);

		return e;

	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub

	}
	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub

	}
}
