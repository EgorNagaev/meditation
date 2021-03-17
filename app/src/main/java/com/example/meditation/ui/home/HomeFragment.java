package com.example.meditation.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.meditation.Network.Emot;
import com.example.meditation.Network.Feelings;
import com.example.meditation.Network.MyRetrofit;
import com.example.meditation.Network.RetrofitCalls;
import com.example.meditation.R;
import com.example.meditation.Recyclers.EmtionRecycler;
import com.example.meditation.Recyclers.FeelingsRecycler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {
String avatar,name,token;
ImageView av;
TextView user;
RecyclerView emotions,state;
SharedPreferences sharedPreferences;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        state = root.findViewById(R.id.log);
        av = root.findViewById(R.id.imageavatar);
        emotions = root.findViewById(R.id.emotion);
        user = root.findViewById(R.id.textView5);
        Retrofit ret = MyRetrofit.getretofit();
        sharedPreferences = this.getActivity().getSharedPreferences("main", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token","");
        avatar = sharedPreferences.getString("avatar","");
        name = sharedPreferences.getString("name","Loh");
        user.setText("С возвращением," +name +"!");
        Glide.with(root).load(avatar).circleCrop().into(av);
        RetrofitCalls rcall = ret.create(RetrofitCalls.class);
        Call<Feelings> emotio = rcall.feelings();
        emotio.enqueue(new Callback<Feelings>() {
            @Override
            public void onResponse(Call<Feelings> call, Response<Feelings> response) {
                FeelingsRecycler emotion = new FeelingsRecycler(getContext(),response.body());
                emotions.setAdapter(emotion);

            }

            @Override
            public void onFailure(Call<Feelings> call, Throwable t) {

            }


        });
        Call<Emot> em =  rcall.quotes();
        em.enqueue(new Callback<Emot>() {
            @Override
            public void onResponse(Call<Emot> call, Response<Emot> response) {
                EmtionRecycler emt = new EmtionRecycler(getContext(),response.body());
                state.setAdapter(emt);
            }

            @Override
            public void onFailure(Call<Emot> call, Throwable t) {

            }
        });





        return root;


    }
}