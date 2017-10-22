package com.czeng.tidyday.MemoDataObject;

import java.util.ArrayList;

public class MemoCardsCollection {
    public static ArrayList<MemoCard> getMemoCards(){
        ArrayList<MemoCard> memoCards = new ArrayList<>();

        MemoCard memo = new MemoCard("New Car", "It's been 123 days!");
        memoCards.add(memo);

        memo = new MemoCard("What I Need", "Serotonin");
        memoCards.add(memo);

        memo = new MemoCard("Parents Come to Visit", "5 days to go!");
        memoCards.add(memo);

        return memoCards;
    }
}
