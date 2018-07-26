package com.codingtest.comcast.comcastct.characters.detail;

import com.codingtest.comcast.comcastct.data.model.Character;

/**
 * Created by alexlm on 7/25/18.
 */

public interface CharacterDetailFragmentContract {

    public interface View{

        void setPresenter(CharacterDetailFragmentContract.Presenter presenter);
        void updateCharacterDetails(Character character);

    }

    public interface Presenter{

    }
}
