package com.example.meditation.Recyclers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.meditation.MoreState;
import com.example.meditation.Network.Emot;
import com.example.meditation.R;

public class EmtionRecycler extends RecyclerView.Adapter<EmtionRecycler.MyVH> {
    Context context;
    ImageView imageView;
    TextView textView,descr;
    Button button;
    Emot list;

    public EmtionRecycler(Context context, Emot list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override

    public EmtionRecycler.MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View root = LayoutInflater.from(context).inflate(R.layout.state_adapter,parent,false);
       return new MyVH(root);
    }

    @Override
    public void onBindViewHolder(@NonNull EmtionRecycler.MyVH holder, int position) {
        Glide.with(context).load(list.getData().get(position).getImage()).into(imageView);
        textView.setText(list.getData().get(position).getTitle());
        descr.setText(list.getData().get(position).getDescription());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MoreState.class);
                intent.putExtra("id",list.getData().get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.getData().size();
    }

    public class MyVH extends RecyclerView.ViewHolder {
        public MyVH(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.stateimage);
            textView = itemView.findViewById(R.id.titlestate);
            descr = itemView.findViewById(R.id.descr);
            button = itemView.findViewById(R.id.button2);

        }
    }
}
