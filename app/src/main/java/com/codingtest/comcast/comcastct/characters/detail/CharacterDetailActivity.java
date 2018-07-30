package com.codingtest.comcast.comcastct.characters.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.character_toolbar);
        setSupportActionBar(toolbar);

        if(getIntent() != null){
            mCharacterSelected = (Character) getIntent().getParcelableExtra(CharacterListFragment.CHARACTER_SELECTED_KEY);
            setTitle(mCharacterSelected.getTitle());
        } else {
            setTitle(getString(R.string.unknown_character_name));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mCharacterDetailFragment = (CharacterDetailFragment) getFragmentManager().findFragmentById(R.id.char_detail_fragment);
        mCharacterDetailFragment.updateCharacterDetails(mCharacterSelected);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_character_detail, menu);
        return true;
    }
}
