package com.sa3rha.android.sa3rha.Ui.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sa3rha.android.sa3rha.R;

/**
 * Created by Maged on 8/24/2017.
 */

public class Compare_SecondFragment  extends Fragment implements View.OnClickListener  {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_compare, container, false);
        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
