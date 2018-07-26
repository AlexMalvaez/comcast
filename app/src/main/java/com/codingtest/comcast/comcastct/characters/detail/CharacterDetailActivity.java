package com.codingtest.comcast.comcastct.characters.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.codingtest.comcast.comcastct.R;
import com.codingtest.comcast.comcastct.characters.list.CharacterListFragment;
import com.codingtest.comcast.comcastct.data.model.Character;

/**
 * Created by alexlm on 7/25/18.
 */

public class CharacterDetailActivity extends AppCompatActivity {

    private CharacterDetailFragment mCharacterDetailFragment;

    private Character mCharacterSelected;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);

        mCharacterDetailFragment = (CharacterDetailFragment) getSupportFragmentManager().findFragmentById(R.id.char_detail_fragment);

        if(getIntent() != null){
            mCharacterSelected = (Character) getIntent().getParcelableExtra(CharacterListFragment.CHARACTER_SELECTED_KEY);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mCharacterDetailFragment.updateCharacterDetails(mCharacterSelected);
    }
}
