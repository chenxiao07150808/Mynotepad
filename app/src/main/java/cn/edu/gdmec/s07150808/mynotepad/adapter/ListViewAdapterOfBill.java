package cn.edu.gdmec.s07150808.mynotepad.adapter;

import java.util.List;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cn.edu.gdmec.s07150808.mynotepad.R;
import cn.edu.gdmec.s07150808.mynotepad.entity.GridViewEntity;

public class ListViewAdapterOfBill extends BaseAdapter{

	private Context context;
	private List<GridViewEntity> list;
	
	public ListViewAdapterOfBill(Context context,
			List<GridViewEntity> list) {
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
		HodView hod;
		if(convertView==null){
			hod=new HodView();
			convertView=LayoutInflater.from(context).inflate(R.layout.xianitem, null);
		    hod.billshow_text=(TextView) convertView.findViewById(R.id.billshow_text);
		    hod.imgbill=(ImageView) convertView.findViewById(R.id.imgbill);
		    hod.zuobillshow_text=(TextView) convertView.findViewById(R.id.zuobillshow_text);
		    hod.xian=(View) convertView.findViewById(R.id.xian);
		    convertView.setTag(hod);
		}else{
			hod=(HodView)convertView.getTag();
		}
	   
		
		hod.imgbill.setImageResource(list.get(position).getImg());
		int state=Integer.parseInt(list.get(position).getBillState());
		if(state==0){
			
			hod.billshow_text.setText(list.get(position).getBillType()+"  "+list.get(position).getBill());
			hod.billshow_text.setVisibility(View.VISIBLE);
			hod.zuobillshow_text.setVisibility(View.GONE);
		}
		if(state==1){
			hod.zuobillshow_text.setText(list.get(position).getBillType()+"  "+list.get(position).getBill());
		    hod.zuobillshow_text.setVisibility(View.VISIBLE);
		    hod.billshow_text.setVisibility(View.GONE);
		}
		
		return convertView;
	}

	private class HodView{
		TextView billshow_text,zuobillshow_text;
		ImageView imgbill;
		View xian;
	}
}