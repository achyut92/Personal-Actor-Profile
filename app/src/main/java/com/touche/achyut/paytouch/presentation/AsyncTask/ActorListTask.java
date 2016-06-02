package com.touche.achyut.paytouch.presentation.AsyncTask;

import android.os.AsyncTask;

import com.touche.achyut.paytouch.domain.model.Actor;
import com.touche.achyut.paytouch.domain.service.ActorService;

import java.io.InputStream;
import java.util.List;

public class ActorListTask extends AsyncTask<InputStream,Void,List<Actor>>{

    private ActorService actorService;

    public ActorListTask() {
        this.actorService = new ActorService();
    }

    @Override
    protected List<Actor> doInBackground(InputStream... params) {

        List<Actor> result = actorService.getActorList(params[0]);
        return result;
    }

    @Override
    protected void onPostExecute(List<Actor> actors) {
        super.onPostExecute(actors);
    }
}
