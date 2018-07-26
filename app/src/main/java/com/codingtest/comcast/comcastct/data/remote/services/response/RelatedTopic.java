package com.codingtest.comcast.comcastct.data.remote.services.response;

/**
 * Created by alexlm on 7/23/18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelatedTopic {

    @SerializedName("Text")
    @Expose
    private String text;

    @SerializedName("Icon")
    @Expose
    private Icon icon;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

}
