package com.czeng.tidyday.MemoDataObject;

import java.util.ArrayList;

public class MemoCardsCollection {
    public static ArrayList<MemoCard> getMemoCards(){
        ArrayList<MemoCard> memoCards = new ArrayList<>();

        MemoCard memo = new MemoCard("Memo 1", "Memo 1");
        memoCards.add(memo);

        memo = new MemoCard("Memo 1", "Memo 1");
        memoCards.add(memo);

        memo = new MemoCard("Memo 2", "Memo 2");
        memoCards.add(memo);

        memo = new MemoCard("Memo 4", "Memo 4");
        memoCards.add(memo);

        return memoCards;
    }
}
