package com.codingtest.comcast.comcastct.characters.detail;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.widget.CircularProgressDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.codingtest.comcast.comcastct.R;
import com.codingtest.comcast.comcastct.data.model.Character;

/**
 * Created by alexlm on 7/24/18.
 */

public class CharacterDetailFragment extends Fragment implements CharacterDetailFragmentContract.View{

    private CharacterDetailFragmentContract.Presenter mPresenter;

    private ImageView mIVCharacterImage;
    private TextView mTVNAme;
    private TextView mTVDescription;


    @Override
    public void onAttach(Activity activity) {
        Log.i("CharacterDetailFragment", "onAttach");
        super.onAttach(activity);
        setPresenter(new CharacterDetailFragmentPresenter(this));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("CharacterDetailFragment", "onCreateView");
        View view = inflater.inflate(R.layout.fragment_character_detail_panel, container, false);
        Log.i("Detail Fragment", "Detail Fragment");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.i("CharacterDetailFragment", "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        mIVCharacterImage = (ImageView) view.findViewById(R.id.iv_character_image);
        mTVNAme =(TextView) view.findViewById(R.id.tv_name);
        mTVDescription = (TextView) view.findViewById(R.id.tv_description);
    }

    @Override
    public void setPresenter(CharacterDetailFragmentContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    public void updateCharacterDetails(Character character){
        mTVNAme.setText(character.getTitle());
        mTVDescription.setText(character.getDescription());

        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(getActivity().getApplicationContext());
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(40f);
        circularProgressDrawable.start();

        Glide.with(this)
                .load(character.getUlr())
                .placeholder(circularProgressDrawable)
                .error(R.drawable.ic_not_found)
                .into(mIVCharacterImage);

    }

}

