package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class LoginFragment extends Fragment {

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_login, container, false);

        View page_view = inflater.inflate(R.layout.fragment_login, container, false);

        page_view.findViewById(R.id.sms_permission_notitication_reject_btn).setOnClickListener(page_view1 -> Navigation.findNavController(page_view1).navigate(R.id.action_loginFragment_to_galleryFragment));
        return page_view;
    }
}