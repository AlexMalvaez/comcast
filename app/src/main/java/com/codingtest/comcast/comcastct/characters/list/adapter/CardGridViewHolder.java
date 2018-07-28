package com.codingtest.comcast.comcastct.characters.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codingtest.comcast.comcastct.R;

/**
 * Created by alexlm on 7/26/18.
 */

public class CardGridViewHolder extends RecyclerView.ViewHolder{

    public ImageView ivCharacterImage;
    public TextView tvTitle;

    public CardGridViewHolder(View itemView) {
        super(itemView);

        ivCharacterImage = (ImageView) itemView.findViewById(R.id.iv_card_character);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_card_name);
    }

}
