package com.codingtest.comcast.comcastct.data.remote.services.response;

/**
 * Created by alexlm on 7/23/18.
 */

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CharacterResponse {

    @SerializedName("RelatedTopics")
    @Expose
    private List<RelatedTopic> relatedTopics = null;

    public List<RelatedTopic> getRelatedTopics() {
        return relatedTopics;
    }

    public void setRelatedTopics(List<RelatedTopic> relatedTopics) {
        this.relatedTopics = relatedTopics;
    }

}