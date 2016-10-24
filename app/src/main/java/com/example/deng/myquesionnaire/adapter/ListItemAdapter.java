package com.example.deng.myquesionnaire.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.deng.myquesionnaire.R;
import com.example.deng.myquesionnaire.bean.Qs;

public class ListItemAdapter extends BaseAdapter {

    private List<Qs.QuestionnairesBean> objects = new ArrayList<Qs.QuestionnairesBean>();

    private Context context;
    private LayoutInflater layoutInflater;

    public ListItemAdapter(Context context, List<Qs.QuestionnairesBean> mList) {
        this.context = context;
        this.objects=mList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Qs.QuestionnairesBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((Qs.QuestionnairesBean) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(Qs.QuestionnairesBean object, ViewHolder holder) {
        //TODO implement
        holder.textView.setText(object.getTitle());
    }

    protected class ViewHolder {
        private TextView textView;

        public ViewHolder(View view) {
            textView = (TextView) view.findViewById(R.id.textView);
        }
    }
}
