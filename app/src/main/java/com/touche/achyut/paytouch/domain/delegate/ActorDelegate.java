package com.touche.achyut.paytouch.domain.delegate;


import com.touche.achyut.paytouch.domain.model.Actor;
import com.touche.achyut.paytouch.domain.model.Film;
import com.touche.achyut.paytouch.utils.Constants;
import com.touche.achyut.paytouch.utils.WebServiceUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ActorDelegate {
    public List<Actor> getActorList(InputStream inputStream) {


        List<Actor> actorList = new ArrayList<>();

        //TODO uncomment to load JSON from URL and comment the following j_array variable
        //String j_array = WebServiceUtil.getJson(Constants.URL_ACTOR);

        String j_array = WebServiceUtil.readStream(inputStream);

        if(j_array == ""){
            return null;
        }

        try {
            JSONObject jsonObject = new JSONObject(j_array);
            JSONArray actors = jsonObject.getJSONArray(Constants.JSON_ACTORS);


            for (int i=0;i<actors.length();i++){

                Actor actor = new Actor();
                ArrayList<Film> filmList = new ArrayList<>();

                JSONObject actor_obj = actors.getJSONObject(i);
                JSONArray known_for = actor_obj.getJSONArray(Constants.JSON_KNOWN_FOR);

                for (int j=0;j<known_for.length();j++){
                    Film film = new Film();
                    JSONObject film_obj = known_for.getJSONObject(j);

                    film.setTitle(film_obj.getString(Constants.JSON_TITLE));
                    film.setRelease_date(film_obj.getString(Constants.JSON_RELEASE));
                    film.setPoster(film_obj.getString(Constants.JSON_POSTER));
                    film.setAvg_count(film_obj.getInt(Constants.JSON_COUNT));
                    film.setAvg_vote(film_obj.getInt(Constants.JSON_VOTE));

                    filmList.add(film);

                }
                actor.setName(actor_obj.getString(Constants.JSON_NAME));
                actor.setLocation(actor_obj.getString(Constants.JSON_LOCATION));
                actor.setDescription(actor_obj.getString(Constants.JSON_DESCRIPTION));
                actor.setPopularity(actor_obj.getDouble(Constants.JSON_POPULARITY));
                actor.setProfile_pic(actor_obj.getString(Constants.JSON_PROFILE_PIC));
                actor.setTop(actor_obj.getBoolean(Constants.JSON_TOP));
                actor.setFilms(filmList);

                actorList.add(actor);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
            return actorList;
    }

    public List<String> getLocations(InputStream inputStream){
        List<String> locList = new ArrayList<>();

        String j_array = WebServiceUtil.readStream(inputStream);
        if(j_array == ""){
            return null;
        }

        try {
            JSONObject jsonObject = new JSONObject(j_array);
            JSONArray actors = jsonObject.getJSONArray(Constants.JSON_ACTORS);


            for (int i = 0; i < actors.length(); i++) {

                Actor actor = new Actor();

                JSONObject actor_obj = actors.getJSONObject(i);
                locList.add(actor_obj.getString(Constants.JSON_LOCATION));

            }
        }catch (Exception e){

        }

        return locList;
    }
}
