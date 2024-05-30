package com.example.registerplayer.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.registerplayer.R;

public class InitialPageFragment extends Fragment {
    private View view;
    private TextView textViewAppTitle;
    private TextView textViewAppDescription;

    public InitialPageFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_initial_page, container, false);

        textViewAppTitle = view.findViewById(R.id.textViewAppTitle);
        textViewAppDescription = view.findViewById(R.id.textViewAppDescription);

        return view;
    }
}