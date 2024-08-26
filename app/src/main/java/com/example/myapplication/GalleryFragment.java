package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;


public class GalleryFragment extends Fragment {

 // EventsRecyclerView = findViewById(R.id.MainGalleryRecyclerView);


    public GalleryFragment() {
        // Required empty public constructor
   }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gallery, container, false);

        GamerDatabase


    }
    private void SetupGameEventModels(){

    }
}