package com.codingtest.comcast.comcastct.characters.list.adapter;

import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.codingtest.comcast.comcastct.R;
import com.codingtest.comcast.comcastct.data.model.Character;

import java.util.List;

/**
 * Created by alexlm on 7/26/18.
 */

public class CharacterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ROW_TYPE = 1;
    public static final int CARD_TYPE = 2;

    private List<Character> mCharacterList;
    private PostCharacterListener mPostCharacterListener;
    private int mType = ROW_TYPE;

    public CharacterAdapter(List<Character> characterList, PostCharacterListener postCharacterListener){
        mCharacterList = characterList;
        mPostCharacterListener = postCharacterListener;
    }

    public CharacterAdapter(List<Character> characterList, PostCharacterListener postCharacterListener, int viewType) {
        mCharacterList = characterList;
        mPostCharacterListener = postCharacterListener;
        if(viewType != 1 && viewType !=2) {
            mType = ROW_TYPE;
        } else {
            mType = viewType;
        }
    }

    public void updateCharacters(List<Character> characterList) {
        mCharacterList = characterList;
        notifyDataSetChanged();
    }

    public Character getCharacter(int adapterPosition){
        return mCharacterList.get(adapterPosition);
    }

    public List<Character> getCharacterList() {
        return mCharacterList;
    }

    public int getmType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch(mType){
            case ROW_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
                return new RowListViewHolder(view);
            case CARD_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_grid_card_view, null);
                return new CardGridViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Character character = getCharacter(position);
                mPostCharacterListener.onPostClick(character);
            }
        });

        switch(mType){
            case ROW_TYPE:
                RowListViewHolder rlViewHolder = (RowListViewHolder) holder;
                rlViewHolder.tvTitle.setText(mCharacterList.get(position).getTitle());
                break;
            case CARD_TYPE:
                CardGridViewHolder cgViewHolder = (CardGridViewHolder) holder;

                Character chr = mCharacterList.get(position);

                cgViewHolder.tvTitle.setText(chr.getTitle());

                CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(cgViewHolder.tvTitle.getContext());
                circularProgressDrawable.setStrokeWidth(5f);
                circularProgressDrawable.setCenterRadius(40f);
                circularProgressDrawable.start();

                Glide.with(cgViewHolder.ivCharacterImage.getContext())
                        .load(chr.getUlr())
                        .placeholder(circularProgressDrawable)
                        .error(R.drawable.ic_not_found)
                        .fitCenter()
                        .into(cgViewHolder.ivCharacterImage);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mCharacterList.size();
    }

    //Interface for sending the Character Info once it has been clicked on an item
    public interface PostCharacterListener{
        void onPostClick(Character character);
    }
}
