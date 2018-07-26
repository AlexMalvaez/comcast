package com.codingtest.comcast.comcastct.characters.detail;

/**
 * Created by alexlm on 7/25/18.
 */

public class CharacterDetailFragmentPresenter implements CharacterDetailFragmentContract.Presenter {

    private final CharacterDetailFragmentContract.View mView;

    public CharacterDetailFragmentPresenter(CharacterDetailFragmentContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


}
