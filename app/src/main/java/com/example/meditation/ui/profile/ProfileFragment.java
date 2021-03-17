package com.example.meditation.ui.profile;

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

import com.bumptech.glide.Glide;
import com.example.meditation.R;

public class ProfileFragment extends Fragment {
    ImageView imgprofile;
    TextView nameprofile;
    String iamge,name;
    SharedPreferences sharedPreferences;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        imgprofile = root.findViewById(R.id.imageprofile);
        nameprofile =root.findViewById(R.id.nameprofile);
        sharedPreferences = getActivity().getSharedPreferences("main", Context.MODE_PRIVATE);
        iamge = sharedPreferences.getString("avatar","");
        name = sharedPreferences.getString("name","");
        Glide.with(root).load(iamge).circleCrop().into(imgprofile);
        nameprofile.setText(name);







        return root;
    }
}