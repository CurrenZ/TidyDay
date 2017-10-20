package com.czeng.tidyday.tabs;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.czeng.tidyday.tools.MyAdapter;
import com.czeng.tidyday.R;

import java.util.Arrays;
import java.util.List;

public class memo_class extends Fragment{

    List<String> Title;
    List<String> Detail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab_layout, container, false);
        Title = Arrays.asList(getResources().getStringArray(R.array.memos_title));
        Detail = Arrays.asList(getResources().getStringArray(R.array.memos_detial));

        RecyclerView cards_list = (RecyclerView) rootView.findViewById(R.id.Cards_recycler);
        cards_list.setHasFixedSize(true);
        MyAdapter adapter = new MyAdapter("memo_card", Title, Detail);
        cards_list.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        cards_list.setLayoutManager(llm);

        return rootView;
    }
}
