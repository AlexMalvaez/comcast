package com.codingtest.comcast.comcastct.characters.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codingtest.comcast.comcastct.R;
import com.codingtest.comcast.comcastct.characters.detail.CharacterDetailActivity;
import com.codingtest.comcast.comcastct.characters.list.adapter.CharacterGridAdapter;
import com.codingtest.comcast.comcastct.characters.list.adapter.CharacterListAdapter;
import com.codingtest.comcast.comcastct.data.model.Character;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexlm on 7/24/18.
 */

public class CharacterListFragment extends Fragment implements CharacterListFragmentContract.View {

    private CharacterListFragmentContract.Presenter mPresenter;

    OnCharacterTitleSelectedListener mOnCharacterTitleSelectedListener;

    private CharacterGridAdapter mCharacterGridAdapter;
    private CharacterListAdapter mCharacterListAdapter;
    private RecyclerView mRVCharacterList;

    public static final String CHARACTER_SELECTED_KEY = "CHARACTER_SELECTED";

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mOnCharacterTitleSelectedListener = (OnCharacterTitleSelectedListener) activity;
        setPresenter(new CharacterListFragmentPresenter(this));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_character_list_panel, container, false);
        Log.i("List Fragment", "List Fragment");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRVCharacterList = (RecyclerView) view.findViewById(R.id.rvCharacterList);

        mCharacterListAdapter = new CharacterListAdapter(new ArrayList<Character>(0), new CharacterListAdapter.PostCharacterListener() {
            @Override
            public void onPostClick(Character character) {
                mOnCharacterTitleSelectedListener.onCharacterTitleSelected(character);
            }
        });

        mCharacterGridAdapter = new CharacterGridAdapter(new ArrayList<Character>(0), new CharacterGridAdapter.PostCharacterListener() {
            @Override
            public void onPostClick(Character character) {
                mOnCharacterTitleSelectedListener.onCharacterTitleSelected(character);
            }
        });

        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);

        mRVCharacterList.setLayoutManager(layoutManager);
        mRVCharacterList.setAdapter(mCharacterGridAdapter);

        //mRVCharacterList.setAdapter(mCharacterListAdapter);
        //mRVCharacterList.setHasFixedSize(true);
        //RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        //mRVCharacterList.addItemDecoration(itemDecoration);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showCharacters();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void showCharacters() {
        mPresenter.getCharacters("simpsons characters");
    }

    @Override
    public void updateCharacters(List<Character> characterList){
        mCharacterGridAdapter.updateCharacters(characterList);
        mCharacterGridAdapter.notifyDataSetChanged();

        //mCharacterListAdapter.updateCharacters(characterList);

        //mRVCharacterList.swapAdapter(mCharacterListAdapter,false);
        //mRVCharacterList.setLayoutManager(new LinearLayoutManager(getContext()));
        //mCharacterListAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(CharacterListFragmentContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    public interface OnCharacterTitleSelectedListener{
        public void onCharacterTitleSelected(Character character);
    }
}
