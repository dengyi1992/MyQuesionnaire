package com.example.deng.myquesionnaire.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.LinearLayout;

import com.example.deng.myquesionnaire.DetailActivity;
import com.example.deng.myquesionnaire.R;
import com.example.deng.myquesionnaire.bean.Detail;

public class QuestionItemAdapter extends BaseAdapter {


    private Context context;
    private LayoutInflater layoutInflater;

    public QuestionItemAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return DetailActivity.mList.size();
    }

    public int getType(int i) {
        return DetailActivity.mList.get(i).getType();
    }

    @Override
    public Detail.QuestionsBean getItem(int position) {
        return DetailActivity.mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.question_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((Detail.QuestionsBean) getItem(position), (ViewHolder) convertView.getTag(), position);
        return convertView;
    }

    private void initializeViews(final Detail.QuestionsBean object, ViewHolder holder, int position) {
        //TODO implement
//        TextView tv = new TextView(holder.llContent.getContext());
//        tv.setText("Hello World");
        holder.llContent.removeAllViews();
//        holder.llContent.addView(tv,0);
        int i = position + 1;
        holder.tvheader.setText("第" + i + "题");
        List<Detail.QuestionsBean.Option> options = object.getOptions();

        switch (object.getType()) {
            case 0:
                break;
            case 1:
                RadioGroup radioGroup = new RadioGroup(holder.llContent.getContext());
                radioGroup.removeAllViews();
                for (int j = 0; j < options.size(); j++) {
                    RadioButton child = new RadioButton(holder.llContent.getContext());
                    final Detail.QuestionsBean.Option option = options.get(j);
                    child.setText(option.getContent());
                    child.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    child.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked){
                                object.setAnswer(option.get_id());
                            }
                        }
                    });
                    radioGroup.addView(child);
                }
                holder.llContent.addView(radioGroup);
                break;
            case 2:
                holder.tvheader.setText("第" + i + "题（可多选）");
                for (int j = 0; j < options.size(); j++) {
                    CheckBox child = new CheckBox(holder.llContent.getContext());
                    final Detail.QuestionsBean.Option option = options.get(j);
                    child.setText(option.getContent());
                    child.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    child.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            String answer = object.getAnswer();
                            if (isChecked){
                                if (!answer.contains(option.get_id()+",")){
                                    object.setAnswer(answer+option.get_id()+",");
                                }
                            }else {
                                object.setAnswer(answer.replace(option.get_id()+",",""));
                            }
                        }
                    });
                    holder.llContent.addView(child);
                }
                break;
            case 3:
                EditText child = new EditText(holder.llContent.getContext());
                child.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        object.setAnswer(s.toString());
                    }
                });
                holder.llContent.addView(child);
                break;
            default:
                break;
        }
        holder.tvQuestionName.setText(object.getContent());


    }

    protected class ViewHolder {
        private TextView tvheader;
        private TextView tvQuestionName;
        private LinearLayout llContent;

        public ViewHolder(View view) {
            tvheader = (TextView) view.findViewById(R.id.tvheader);
            tvQuestionName = (TextView) view.findViewById(R.id.question_name);
            llContent = (LinearLayout) view.findViewById(R.id.ll_content);
        }
    }
}
