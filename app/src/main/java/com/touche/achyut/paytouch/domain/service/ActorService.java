package com.touche.achyut.paytouch.domain.service;

import com.touche.achyut.paytouch.domain.delegate.ActorDelegate;
import com.touche.achyut.paytouch.domain.model.Actor;

import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ActorService {


    public List<Actor> getActorList(InputStream inputStream) {
        ActorDelegate actorDelegate = new ActorDelegate();
        return actorDelegate.getActorList(inputStream);
    }

    public List<String> getLocationList(InputStream is) {
        ActorDelegate actorDelegate = new ActorDelegate();
        List<String> locations = actorDelegate.getLocations(is);

        Set<String> set = new HashSet<>();
        set.addAll(locations);
        locations.clear();
        locations.addAll(set);
        return locations;
    }
}
