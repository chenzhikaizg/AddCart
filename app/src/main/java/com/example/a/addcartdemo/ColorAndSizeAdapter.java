package com.example.a.addcartdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenzhikai on 2017/11/6.
 */

public class ColorAndSizeAdapter extends BaseAdapter {
    private List<String> values;
    private Context mContext;
    private LayoutInflater layoutInflater;
    private ViewHolder viewHolder;

    public ColorAndSizeAdapter(Context mContext){
        this.mContext = mContext;
        values = new ArrayList<String>();
        layoutInflater=LayoutInflater.from(mContext);
    }
    public void addData(List<String> values) {
        this.values.addAll(values);
    }
    public void clear() {
        values.clear();
    }
    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public String getItem(int i) {
        return values.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            view = layoutInflater.inflate(R.layout.adapter_color_and_size, null);
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView)view.findViewById(R.id.tv_zhishi_title);
            view.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
     viewHolder.tvTitle.setText(values.get(i));




        return view;
    }
    private class ViewHolder{
        private TextView tvTitle;


    }
}
