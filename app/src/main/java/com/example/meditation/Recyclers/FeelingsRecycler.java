package com.example.meditation.Recyclers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.meditation.Network.Feelings;
import com.example.meditation.R;

public class FeelingsRecycler extends RecyclerView.Adapter<FeelingsRecycler.MyVH> {
    Context context;
    ImageView imageView;
    TextView title;
    Feelings list;

    public FeelingsRecycler(Context context, Feelings list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FeelingsRecycler.MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.emotion_adapter,parent,false);
        return new MyVH(root);
    }

    @Override
    public void onBindViewHolder(@NonNull FeelingsRecycler.MyVH holder, int position) {
        Glide.with(context).load(list.getData().get(position).getImage()).into(imageView);
        title.setText(list.getData().get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.getData().size();
    }

    public class MyVH extends RecyclerView.ViewHolder {
        public MyVH(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.emotionimage);
            title = itemView.findViewById(R.id.nameemotion);
        }
    }
}
