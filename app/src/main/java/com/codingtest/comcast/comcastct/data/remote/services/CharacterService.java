package com.codingtest.comcast.comcastct.data.remote.services;

import com.codingtest.comcast.comcastct.data.remote.services.response.CharacterResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by alexlm on 7/23/18.
 */

public interface CharacterService {

    @GET("/")
    Observable<CharacterResponse> getAnswers(@Query("q") String q, @Query("format") String format);
}
