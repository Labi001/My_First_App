package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.R;

import com.example.myapplication.databinding.FragmentSecondBinding;
import com.example.myapplication.helper.Constants;

public class SecondFragment extends Fragment {

    private TextView mTitle;
    private TextView mDetail;
    private int mValue = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){

            if(getArguments() != null)
                mValue = getArguments().getInt(Constants.FRAGMENT_SEND_STRING,0);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_second,container,false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTitle = view.findViewById(R.id.mTitle);
        mDetail = view.findViewById(R.id.mDetail);

        mTitle.setText(Constants.newsMenu[mValue]);
        mDetail.setText(Constants.newsDetail[mValue]);

    }


}