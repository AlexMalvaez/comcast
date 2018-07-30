package com.codingtest.comcast.comcastct.characters.list;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codingtest.comcast.comcastct.BuildConfig;
import com.codingtest.comcast.comcastct.R;
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

    private boolean isCharacterList = true;

    private RecyclerView mRecyclerViewCharacter;
    private RecyclerView.ItemDecoration mItemDecoration;
    private CharacterGridAdapter mCharacterGridAdapter;
    private CharacterListAdapter mCharacterListAdapter;


    public static final String CHARACTER_SELECTED_KEY = "CHARACTER_SELECTED";

    public boolean isCharacterList() {
        return isCharacterList;
    }

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
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerViewCharacter = (RecyclerView) view.findViewById(R.id.rvCharacterList);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCharacterListAdapter = new CharacterListAdapter(new ArrayList<Character>(), new CharacterListAdapter.PostCharacterListener() {
            @Override
            public void onPostClick(Character character) {
                mOnCharacterTitleSelectedListener.onCharacterTitleSelected(character);
            }
        });

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mItemDecoration = new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL);
        mRecyclerViewCharacter.setLayoutManager(linearLayoutManager);
        mRecyclerViewCharacter.addItemDecoration(mItemDecoration);
        mRecyclerViewCharacter.setAdapter(mCharacterListAdapter);
        showCharacters();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        setPresenter(null);
        mOnCharacterTitleSelectedListener = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void showCharacters() {
        mPresenter.getCharacters(BuildConfig.Q_PARAMETER_VALUE);
    }

    @Override
    public void updateCharacters(List<Character> characterList){
        if(isCharacterList) {
            mCharacterListAdapter.updateCharacters(characterList);
            mCharacterListAdapter.notifyDataSetChanged();
        } else {
            mCharacterGridAdapter.updateCharacters(characterList);
            mCharacterGridAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setPresenter(CharacterListFragmentContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    public void toggleToGrid(){

        List<Character> characterList = mCharacterListAdapter.getCharacterList();
        mCharacterGridAdapter = new CharacterGridAdapter(characterList, new CharacterGridAdapter.PostCharacterListener() {
            @Override
            public void onPostClick(Character character) {
                mOnCharacterTitleSelectedListener.onCharacterTitleSelected(character);
            }
        });

        mRecyclerViewCharacter.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 3));
        mRecyclerViewCharacter.removeItemDecoration(mItemDecoration);
        mRecyclerViewCharacter.setAdapter(mCharacterGridAdapter);
        mCharacterGridAdapter.notifyDataSetChanged();

        isCharacterList = false;
    }

    public void toggleToList(){

        List<Character> characterList = mCharacterGridAdapter.getCharacterList();
        mCharacterListAdapter = new CharacterListAdapter(characterList, new CharacterListAdapter.PostCharacterListener() {
            @Override
            public void onPostClick(Character character) {
                mOnCharacterTitleSelectedListener.onCharacterTitleSelected(character);
            }
        });

        mRecyclerViewCharacter.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mItemDecoration = new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL);
        mRecyclerViewCharacter.addItemDecoration(mItemDecoration);
        mRecyclerViewCharacter.setAdapter(mCharacterListAdapter);
        mCharacterListAdapter.notifyDataSetChanged();

        isCharacterList = true;
    }

    public interface OnCharacterTitleSelectedListener{
        public void onCharacterTitleSelected(Character character);
    }
}
