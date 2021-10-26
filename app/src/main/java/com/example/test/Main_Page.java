package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main_Page extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter_img adapter_img;
    GridLayoutManager gridLayoutManager;
    List<img> mlist;
    Button btn_suffle,btn_back_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        recyclerView=findViewById(R.id.recycler_card);
        btn_suffle=findViewById(R.id.btn_suffle);
       btn_back_login=findViewById(R.id.btn_back_login);

        gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        mlist=imgs();
        Collections.shuffle(mlist);
        adapter_img=new Adapter_img(mlist);
        recyclerView.setAdapter(adapter_img);
        adapter_img.notifyDataSetChanged();

       btn_suffle.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             Collections.shuffle(mlist);
             adapter_img=new Adapter_img(mlist);
             recyclerView.setAdapter(adapter_img);
          }
       });
        btn_back_login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              Intent intent=new Intent(Main_Page.this,Login.class);
              startActivity(intent);
              finish();
           }
        });
    }



   private List<img> imgs(){
     List<img> list=new ArrayList<>();
        list.add(new img(R.drawable.c01));
        list.add(new img(R.drawable.c02));
        list.add(new img(R.drawable.c03));
        list.add(new img(R.drawable.c04));
        list.add(new img(R.drawable.c05));
        list.add(new img(R.drawable.c06));
        list.add(new img(R.drawable.c07));
        list.add(new img(R.drawable.c08));
        list.add(new img(R.drawable.c09));
        list.add(new img(R.drawable.c10));
        list.add(new img(R.drawable.c11));
        list.add(new img(R.drawable.c12));
        list.add(new img(R.drawable.c13));
        list.add(new img(R.drawable.c14));
        list.add(new img(R.drawable.m00));
        list.add(new img(R.drawable.m01));
        list.add(new img(R.drawable.m02));
        list.add(new img(R.drawable.m03));
        list.add(new img(R.drawable.m04));
        list.add(new img(R.drawable.m05));
        list.add(new img(R.drawable.m06));
        list.add(new img(R.drawable.m07));
        list.add(new img(R.drawable.m08));
        list.add(new img(R.drawable.m09));
        list.add(new img(R.drawable.m10));
        list.add(new img(R.drawable.m11));
        list.add(new img(R.drawable.m12));
        list.add(new img(R.drawable.m13));
        list.add(new img(R.drawable.m14));
        list.add(new img(R.drawable.m15));
        list.add(new img(R.drawable.m16));
        list.add(new img(R.drawable.m17));
        list.add(new img(R.drawable.m18));
        list.add(new img(R.drawable.m19));
        list.add(new img(R.drawable.m20));
        list.add(new img(R.drawable.m21));
        list.add(new img(R.drawable.p01));
        list.add(new img(R.drawable.p02));
        list.add(new img(R.drawable.p03));
        list.add(new img(R.drawable.p04));
        list.add(new img(R.drawable.p05));
        list.add(new img(R.drawable.p06));
        list.add(new img(R.drawable.p07));
        list.add(new img(R.drawable.p08));
        list.add(new img(R.drawable.p09));
        list.add(new img(R.drawable.p10));
        list.add(new img(R.drawable.p11));
        list.add(new img(R.drawable.p12));
        list.add(new img(R.drawable.p13));
        list.add(new img(R.drawable.p14));
        list.add(new img(R.drawable.s01));
        list.add(new img(R.drawable.s02));
        list.add(new img(R.drawable.s03));
        list.add(new img(R.drawable.s04));
        list.add(new img(R.drawable.s05));
        list.add(new img(R.drawable.s06));
        list.add(new img(R.drawable.s07));
        list.add(new img(R.drawable.s08));
        list.add(new img(R.drawable.s09));
        list.add(new img(R.drawable.s10));
        list.add(new img(R.drawable.s11));
        list.add(new img(R.drawable.s12));
        list.add(new img(R.drawable.s13));
        list.add(new img(R.drawable.s14));
        list.add(new img(R.drawable.w01));
        list.add(new img(R.drawable.w02));
        list.add(new img(R.drawable.w03));
        list.add(new img(R.drawable.w04));
        list.add(new img(R.drawable.w05));
        list.add(new img(R.drawable.w06));
        list.add(new img(R.drawable.w07));
        list.add(new img(R.drawable.w08));
        list.add(new img(R.drawable.w09));
        list.add(new img(R.drawable.w10));
        list.add(new img(R.drawable.w11));
        list.add(new img(R.drawable.w12));
        list.add(new img(R.drawable.w13));
        list.add(new img(R.drawable.w14));
        return list;
    }

}