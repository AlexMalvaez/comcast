package com.codingtest.comcast.comcastct.data.remote.services.response;

/**
 * Created by alexlm on 7/23/18.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Icon {

    @SerializedName("URL")
    @Expose
    private String uRL;

    public String getURL() {
        return uRL;
    }

    public void setURL(String uRL) {
        this.uRL = uRL;
    }

}