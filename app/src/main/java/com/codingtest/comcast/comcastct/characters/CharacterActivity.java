package com.codingtest.comcast.comcastct.characters;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.codingtest.comcast.comcastct.R;
import com.codingtest.comcast.comcastct.characters.detail.CharacterDetailActivity;
import com.codingtest.comcast.comcastct.characters.detail.CharacterDetailFragment;
import com.codingtest.comcast.comcastct.characters.list.CharacterListFragment;
import com.codingtest.comcast.comcastct.data.model.Character;
import com.codingtest.comcast.comcastct.utils.ConfigUtils;


public class CharacterActivity extends AppCompatActivity implements CharacterListFragment.OnCharacterTitleSelectedListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("CharacterActivity", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.character_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.app_name));
    }

    @Override
    protected void onDestroy() {
        Log.i("CharacterActivity", "onDestroy");
        //CharacterListFragment characterListFragment = getCharacterListFragment();
        //characterListFragment = null;
        super.onDestroy();
    }

    public int getScreenOrientation(){
        return getResources().getConfiguration().orientation;
    }

    private CharacterListFragment getCharacterListFragment(){
        return (CharacterListFragment) getFragmentManager().findFragmentById(
                (getScreenOrientation() == Configuration.ORIENTATION_PORTRAIT && !ConfigUtils.isTablet(getApplicationContext())) ?
                        R.id.one_pane_character_list_fragment : R.id.two_pane_character_list_fragment);
    }

    @Override
    public void onCharacterTitleSelected(Character character) {

        if(getScreenOrientation() == Configuration.ORIENTATION_PORTRAIT && !ConfigUtils.isTablet(getApplicationContext())) {
            Intent intent = new Intent(this, CharacterDetailActivity.class);
            intent.putExtra(CharacterListFragment.CHARACTER_SELECTED_KEY, character);
            startActivity(intent);
        } else {
            CharacterDetailFragment characterDetailFragment = (CharacterDetailFragment) getFragmentManager().findFragmentById(R.id.two_pane_character_detail_fragment);
            characterDetailFragment.updateCharacterDetails(character);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_character_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.character_type_list_item:

                CharacterListFragment characterListFragment = getCharacterListFragment();

                if(characterListFragment.isCharacterList()){
                    characterListFragment.toggleToGrid();
                    item.setIcon(R.drawable.ic_list);
                } else {
                    characterListFragment.toggleToList();
                    item.setIcon(R.drawable.ic_grid);
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
