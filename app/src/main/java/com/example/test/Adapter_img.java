package com.example.test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Adapter_img extends RecyclerView.Adapter<Adapter_img.cardViewholder>{

    List<img> list_img;


    public Adapter_img(List<img> list_img ) {
        this.list_img = list_img;
    }

    @NonNull
    @Override
    public cardViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid,parent,false);

        return new cardViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cardViewholder holder, int position) {
        holder.change_img(list_img.get(position));
    }

    @Override
    public int getItemCount() {
        if (list_img!=null){
            return list_img.size();
        }
        return 0;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    public class cardViewholder extends RecyclerView.ViewHolder{
        ImageView imageView;
        ImageView img_card_back;
        ConstraintLayout constraint_img;
        public cardViewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img_card);
            img_card_back=itemView.findViewById(R.id.img_card_back);
            constraint_img=itemView.findViewById(R.id.constraint_img);
        }
        public void change_img(img img){
            imageView.setImageResource(img.getResourceImg());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    img_card_back.setVisibility(View.GONE);

                }
            });
        }
    }
}
