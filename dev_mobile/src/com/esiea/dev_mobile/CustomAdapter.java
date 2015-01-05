package com.esiea.dev_mobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter{
	
	private String[] mDataSet;
	
	public CustomAdapter(String[] dataSet){
		mDataSet = dataSet;
	}
	
	@Override
	public int getCount() {
		return mDataSet.length;
	}

	@Override
	public String getItem(int pos) {
		return mDataSet[pos];
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public View getView(int el_pos, View recy_view, ViewGroup parent) {
		if(recy_view == null)
			recy_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_row_item, parent,false);
		TextView mtv = (TextView) recy_view.findViewById(R.id.tv);
		mtv.setText(getItem(el_pos));
		
		return recy_view;
	}
	
	
}
