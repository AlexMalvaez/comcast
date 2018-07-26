package com.codingtest.comcast.comcastct.characters.list;

import android.util.Log;

import com.codingtest.comcast.comcastct.data.model.Character;
import com.codingtest.comcast.comcastct.data.remote.services.CharacterService;
import com.codingtest.comcast.comcastct.data.remote.services.response.CharacterResponse;
import com.codingtest.comcast.comcastct.data.remote.services.response.RelatedTopic;
import com.codingtest.comcast.comcastct.utils.APIUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by alexlm on 7/23/18.
 */

public class CharacterListFragmentPresenter implements CharacterListFragmentContract.Presenter {

    //View instance
    private final CharacterListFragmentContract.View mView;

    private CharacterService mService;

    CharacterListFragmentPresenter(CharacterListFragmentContract.View view){
        this.mView = view;
        mService = APIUtils.getSOService();
        mView.setPresenter(this);
    }

    public void getCharacters(String characterGroupName){

        final List<Character> characterList = new ArrayList<Character>();

        mService.getAnswers(characterGroupName, "json")
                .subscribeOn(Schedulers.io())
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CharacterResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.d("ON_COMPLETE", "ON_COMPLETE");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("ON_ERROR", e.getMessage());
                    }

                    @Override
                    public void onNext(CharacterResponse characterResponse) {

                        List<RelatedTopic> relatedTopicsList = characterResponse.getRelatedTopics();

                        for(RelatedTopic relatedTopic : relatedTopicsList){

                            Character character = new Character();

                            if(relatedTopic.getText() != null && !relatedTopic.getText().isEmpty()) {
                                String sections[] = relatedTopic.getText().split("-", 2);
                                if(sections.length == 2){
                                    character.setTitle(sections[0].trim());
                                    character.setDescription(sections[1].trim());
                                } else{
                                    character.setTitle(relatedTopic.getText());
                                    character.setDescription(relatedTopic.getText());
                                }
                            } else{
                                character.setTitle("No Title");
                                character.setDescription("No description by the moment.");
                            }

                            if(relatedTopic.getIcon().getURL() != null && !relatedTopic.getIcon().getURL().isEmpty()) {
                                character.setUlr(relatedTopic.getIcon().getURL());
                            } else {
                                character.setUlr("No URL");
                            }

                            characterList.add(character);

                        }

                        mView.updateCharacters(characterList);

                    }
                });
    }


}
