package com.czeng.tidyday.tabs;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.czeng.tidyday.MemoDataObject.MemoCard;
import com.czeng.tidyday.MemoDataObject.MemoCardsCollection;
import com.czeng.tidyday.MemoRecycler.MemoAdapter;
import com.czeng.tidyday.R;
import com.czeng.tidyday.Swiper.MemoSwipeHelper;

import java.util.ArrayList;

public class memo_class extends Fragment{

    RecyclerView rv;
    MemoAdapter adapter;
    ArrayList<MemoCard> memoCards;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_layout, container, false);

        // RECYCLER
        rv = (RecyclerView) rootView.findViewById(R.id.Cards_recycler);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        // DATA
        memoCards = MemoCardsCollection.getMemoCards();

        // ADAPTER
        adapter = new MemoAdapter(getActivity(), memoCards);
        rv.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new MemoSwipeHelper(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(rv);

        return rootView;
    }
}
