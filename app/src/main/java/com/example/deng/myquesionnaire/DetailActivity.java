package com.example.deng.myquesionnaire;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.deng.myquesionnaire.adapter.QuestionItemAdapter;
import com.example.deng.myquesionnaire.bean.Detail;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String body = data.getString("body");
            Gson gson = new Gson();
            Detail detail = gson.fromJson(body, Detail.class);
            mList.clear();
            mList.addAll(detail.getQuestions());
            adapter.notifyDataSetChanged();
        }
    };
    private ListView listView;
    public static List<Detail.QuestionsBean> mList;
    private QuestionItemAdapter adapter;
    private Button submitBtn;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.lv_detail);
        adapter = new QuestionItemAdapter(this);
        listView.setAdapter(adapter);
        LayoutInflater inflater = getLayoutInflater();
        View inflate = inflater.inflate(R.layout.lv_footer, null);
        submitBtn = (Button) inflate.findViewById(R.id.submit_btn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFinshed()) {
                    Toast.makeText(DetailActivity.this, "提交", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(DetailActivity.this, "还有未填写问题", Toast.LENGTH_LONG).show();
                }
            }
        });
        listView.addFooterView(inflate);
    }

    private boolean isFinshed() {
        JsonObject jsonObject = new JsonObject();
        for (Detail.QuestionsBean qb :
                mList) {
            System.out.println(qb.getAnswer());
            if (qb.getAnswer().equals("")) {
                return false;
            } else {
                jsonObject.addProperty(qb.get_id(), qb.getAnswer());
                if (qb.getType() == 2) {

                    String value = String.valueOf(qb.getAnswer());
                    jsonObject.addProperty(qb.get_id(), value.substring(0,value.length()-1));
                }
            }
        }
        senData(jsonObject.toString());

        return true;
    }

    private void senData(String s) {
        final OkHttpClient client = new OkHttpClient();
        System.out.println("answer=" + s);
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "answer=" + s);
        final Request request = new Request.Builder()
                .url(CONSTANT.QUEURL + "/questionnaire/m/" +id)
                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("cache-control", "no-cache")
                .build();

        new Thread() {
            @Override
            public void run() {
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }

    @Override
    protected void onResume() {
        super.onResume();
        new Thread() {
            @Override
            public void run() {
                getDetail();
            }
        }.start();
    }

    public void getDetail() {
        OkHttpClient client = new OkHttpClient();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        Request request = new Request.Builder()
                .url(CONSTANT.QUEURL +"/user/questionnaire/" + id)
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();
            Message msg = new Message();
            Bundle bundle = new Bundle();
            bundle.putString("body", response.body().string());
            msg.setData(bundle);
            handler.sendMessage(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
