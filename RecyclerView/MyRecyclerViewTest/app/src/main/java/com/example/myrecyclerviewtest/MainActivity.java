package com.example.myrecyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Actor> actorList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActors();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ActorAdapter adapter = new ActorAdapter(actorList);
        recyclerView.setAdapter(adapter);

    }

    private void initActors() {
        for (int i = 0; i < 2; i++) {
            Actor geyou = new Actor("葛优", R.drawable.geyou_pic);
            actorList.add(geyou);
            Actor jiangwen = new Actor("姜文", R.drawable.jiangwen_pic);
            actorList.add(jiangwen);
            Actor zhourunfa = new Actor("周润发", R.drawable.zhourunfa_pic);
            actorList.add(zhourunfa);
            Actor liujialing = new Actor("刘嘉玲", R.drawable.liujialing_pic);
            actorList.add(liujialing);
            Actor liaofan = new Actor("廖凡", R.drawable.liaofan_pic);
            actorList.add(liaofan);
            Actor zhouyun = new Actor("周韵", R.drawable.zhouyun_pic);
            actorList.add(zhouyun);
            Actor chenkun = new Actor("陈坤", R.drawable.chenkun_pic);
            actorList.add(chenkun);
            Actor shaobing = new Actor("邵兵", R.drawable.shaobing_pic);
            actorList.add(shaobing);
            Actor zhangmo = new Actor("张默", R.drawable.zhangmo_pic);
            actorList.add(zhangmo);
        }
    }
}