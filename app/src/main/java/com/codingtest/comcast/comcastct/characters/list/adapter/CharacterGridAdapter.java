package com.codingtest.comcast.comcastct.characters.list.adapter;

import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codingtest.comcast.comcastct.R;
import com.codingtest.comcast.comcastct.data.model.Character;

import java.util.List;

/**
 * Created by alexlm on 7/25/18.
 */

public class CharacterGridAdapter extends RecyclerView.Adapter<CharacterGridAdapter.ViewHolder> {

    private List<Character> mCharacterList;
    private PostCharacterListener mCharacterListener;

    public CharacterGridAdapter(List<Character> characterList, PostCharacterListener characterListener) {
        mCharacterList = characterList;
        mCharacterListener = characterListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        Log.i("Creating", "Grid Card");

        View characterView = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_grid_card_view, null);
        ViewHolder viewHolder = new ViewHolder(characterView, this.mCharacterListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Character chr = mCharacterList.get(position);

        holder.tvTitle.setText(chr.getTitle());

        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(holder.tvTitle.getContext());
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(40f);
        circularProgressDrawable.start();

        Glide.with(holder.ivCharacterImage.getContext())
                .load(chr.getUlr())
                .placeholder(circularProgressDrawable)
                .error(R.drawable.ic_not_found)
                //.override(120, 160)
                .fitCenter()
                .into(holder.ivCharacterImage);

        //holder.ivCharacterImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    @Override
    public int getItemCount() {
        return mCharacterList.size();
    }

    public void updateCharacters(List<Character> characterList) {
        mCharacterList = characterList;
        notifyDataSetChanged();
    }

    public Character getCharacter(int adapterPosition){
        Log.i("Position: ", Integer.toString(adapterPosition));
        Log.i("CharacterListSize: ", Integer.toString(mCharacterList.size()));

        return mCharacterList.get(adapterPosition);
    }

    //Interface for sending the Character Info once it has been clicked on a character list's title
    public interface PostCharacterListener{
        void onPostClick(Character character);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView ivCharacterImage;
        public TextView tvTitle;

        CharacterGridAdapter.PostCharacterListener mCharacterListener;

        public ViewHolder(View itemView, CharacterGridAdapter.PostCharacterListener postCharacterListener) {
            super(itemView);

            ivCharacterImage = (ImageView) itemView.findViewById(R.id.iv_card_character);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_card_name);

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
