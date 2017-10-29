package com.czeng.tidyday.tabs;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.czeng.tidyday.R;

import java.util.ArrayList;

public class memo_class extends Fragment{

    RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_layout, container, false);

        // RECYCLER
        rv = (RecyclerView) rootView.findViewById(R.id.Cards_recycler);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }
}
