package cn.edu.gdmec.s07150808.mynotepad.adapter;

import java.util.List;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import cn.edu.gdmec.s07150808.mynotepad.R;
import cn.edu.gdmec.s07150808.mynotepad.entity.GridViewEntity;

public class ListViewAdapterOfDialogActivity extends BaseAdapter{

	private Context context;
	private List<GridViewEntity> list;
	
	public ListViewAdapterOfDialogActivity(Context context, List<GridViewEntity> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(list!=null){
			return list.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		HodView hod;
		if(convertView==null){
			hod=new HodView();
		    convertView=LayoutInflater.from(context).inflate(R.layout.listviewitem_dialogactivity, null);
		    hod.text_at_dialoglistview=(TextView) convertView.findViewById(R.id.text_at_dialoglistview);
		    convertView.setTag(hod);
		}else {
			hod=(HodView) convertView.getTag();
		}
		hod.text_at_dialoglistview.setText(list.get(position).getAccountName());
		return convertView;
	}

	private class HodView{
		TextView text_at_dialoglistview;
	}
}
