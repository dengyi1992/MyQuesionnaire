package com.example.deng.myquesionnaire;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.deng.myquesionnaire.adapter.ListItemAdapter;
import com.example.deng.myquesionnaire.bean.Qs;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int SUCCESS = 0;
    private ListView qListView;
    private ListItemAdapter adapter;
    private List<Qs.QuestionnairesBean> mList;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        qListView = (ListView) findViewById(R.id.lv_q);
        mList = new ArrayList<Qs.QuestionnairesBean>();
        adapter = new ListItemAdapter(this, mList);
        qListView.setAdapter(adapter);
        qListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("id",mList.get(i).get_id());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getQList();
    }

    private void getQList() {
        new Thread() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(CONSTANT.QUEURL +"/data/questionnairesList")
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "79bffb57-760d-2eb5-9d16-130c1dca31f6")
                        .build();

                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    Gson gson = new Gson();
                    Qs qs = gson.fromJson(response.body().string(), Qs.class);
                    if (qs.isSuccess()) {
                        mList.clear();
                        mList.addAll(qs.getQuestionnaires());
                        handler.sendEmptyMessage(SUCCESS);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ;

            }
        }.start();

    }
}
