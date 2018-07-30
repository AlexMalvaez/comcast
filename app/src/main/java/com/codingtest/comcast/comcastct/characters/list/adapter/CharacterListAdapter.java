package com.codingtest.comcast.comcastct.characters.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codingtest.comcast.comcastct.data.model.Character;

import java.util.List;

/**
 * Created by alexlm on 7/25/18.
 */

public class CharacterListAdapter extends RecyclerView.Adapter<CharacterListAdapter.ViewHolder> {

    private List<Character> mCharacterList;
    private PostCharacterListener mCharacterListener;

    public CharacterListAdapter(List<Character> characterList, PostCharacterListener characterListener) {
        mCharacterList = characterList;
        mCharacterListener = characterListener;
    }

    public void updateCharacters(List<Character> characterList) {
        mCharacterList = characterList;
        notifyDataSetChanged();
    }

    public List<Character> getCharacterList() {
        return mCharacterList;
    }

    public Character getCharacter(int adapterPosition){
        return mCharacterList.get(adapterPosition);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View characterView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        ViewHolder viewHolder = new ViewHolder(characterView, this.mCharacterListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(mCharacterList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mCharacterList.size();
    }

    //Interface for sending the Character Info once it has been clicked on a character list's title
    public interface PostCharacterListener{
        void onPostClick(Character character);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvTitle;
        PostCharacterListener mCharacterListener;

        public ViewHolder(View itemView, PostCharacterListener postCharacterListener) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(android.R.id.text1);
            this.mCharacterListener = postCharacterListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Character character = getCharacter(getAdapterPosition());
            this.mCharacterListener.onPostClick(character);
        }
    }

}
