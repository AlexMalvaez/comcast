package com.codingtest.comcast.comcastct.characters;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.codingtest.comcast.comcastct.R;
import com.codingtest.comcast.comcastct.characters.detail.CharacterDetailActivity;
import com.codingtest.comcast.comcastct.characters.detail.CharacterDetailFragment;
import com.codingtest.comcast.comcastct.characters.list.CharacterListFragment;
import com.codingtest.comcast.comcastct.data.model.Character;


public class CharacterActivity extends AppCompatActivity implements CharacterListFragment.OnCharacterTitleSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onCharacterTitleSelected(Character character) {

        int screenOrientation = getResources().getConfiguration().orientation;

        if(screenOrientation == Configuration.ORIENTATION_PORTRAIT) {
            Intent intent = new Intent(this, CharacterDetailActivity.class);
            intent.putExtra(CharacterListFragment.CHARACTER_SELECTED_KEY, character);
            startActivity(intent);
        } else {
            CharacterDetailFragment characterDetailFragment = (CharacterDetailFragment) getSupportFragmentManager().findFragmentById(R.id.two_pane_character_detail_fragment);
            characterDetailFragment.updateCharacterDetails(character);
        }
    }
}
