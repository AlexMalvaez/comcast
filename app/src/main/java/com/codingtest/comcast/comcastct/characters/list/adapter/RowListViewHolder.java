package com.codingtest.comcast.comcastct.characters.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.codingtest.comcast.comcastct.data.model.Character;

/**
 * Created by alexlm on 7/26/18.
 */

public class RowListViewHolder extends RecyclerView.ViewHolder {

    public TextView tvTitle;

    public RowListViewHolder(View itemView) {
        super(itemView);

        tvTitle = (TextView) itemView.findViewById(android.R.id.text1);
    }

}
