package com.codingtest.comcast.comcastct.characters.list;

import com.codingtest.comcast.comcastct.data.model.Character;

import java.util.List;

/**
 * Created by alexlm on 7/23/18.
 */

public interface CharacterListFragmentContract {

    interface View{

        void setPresenter(CharacterListFragmentContract.Presenter presenter);
        void updateCharacters(List<Character> characterList);

    }

    interface Presenter{

        void getCharacters(String characterGroupName);

    }

}
