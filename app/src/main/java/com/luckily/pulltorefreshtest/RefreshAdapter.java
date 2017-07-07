package com.luckily.pulltorefreshtest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/6 0006.
 */

class RefreshAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String[]> data;

    public RefreshAdapter(Context context, ArrayList<String[]> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        if (data.size() == 1) {
            return data.get(0).length;
        } else {
            return data.get(0).length + data.get(1).length;
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_product, null);
            holder.tv_type = (TextView) convertView.findViewById(R.id.tv_type);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_type.setText("第" + (position + 1) + "个");
        return convertView;
    }

    class ViewHolder {
        TextView tv_type;
    }
}
