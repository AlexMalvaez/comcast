package com.codingtest.comcast.comcastct.utils;

import android.content.Context;
import android.content.res.Configuration;

import com.codingtest.comcast.comcastct.data.remote.RetrofitClient;
import com.codingtest.comcast.comcastct.data.remote.services.CharacterService;

/**
 * Created by alexlm on 7/23/18.
 */

public class APIUtils {
    public static final String BASE_URL = "http://api.duckduckgo.com/";

    public static CharacterService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(CharacterService.class);
    }

}
