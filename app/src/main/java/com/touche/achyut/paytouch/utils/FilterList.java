package com.touche.achyut.paytouch.utils;

import com.touche.achyut.paytouch.domain.model.Actor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FilterList {

    public  static List<Actor> filterByName(String name, List<Actor> original){

        List<Actor> filtered = new ArrayList<>();

        Iterator i = original.iterator();
        while (i.hasNext()){
            Actor actor = (Actor) i.next();
            if (actor.getName().toLowerCase().contains(name.toLowerCase()))
                filtered.add(actor);
        }


        return filtered;
    }


    public static List<Actor> filterByTrue(boolean bool, List<Actor> actorList) {
        List<Actor> filtered = new ArrayList<>();

        Iterator i = actorList.iterator();
        while (i.hasNext()){
            Actor actor = (Actor) i.next();
            if (actor.isTop()==bool)
                filtered.add(actor);
        }
        return filtered;

    }

    public static List<Actor> filterByPopularity(int value, List<Actor> actorList) {

        List<Actor> filtered = new ArrayList<>();

        Iterator i = actorList.iterator();
        while (i.hasNext()){
            Actor actor = (Actor) i.next();
            if (actor.getPopularity() > 0 && actor.getPopularity()<value)
                filtered.add(actor);
        }


        return filtered;

    }

    public static List<Actor> filterByLocation(String loc, List<Actor> actorList) {
        List<Actor> filtered = new ArrayList<>();

        Iterator i = actorList.iterator();
        while (i.hasNext()){
            Actor actor = (Actor) i.next();
            if (actor.getLocation().toLowerCase().contains(loc.toLowerCase()))
                filtered.add(actor);
        }


        return filtered;

    }

    public static List<Actor> filterByFalse(boolean b, List<Actor> actorList) {
        List<Actor> filtered = new ArrayList<>();

        Iterator i = actorList.iterator();
        while (i.hasNext()){
            Actor actor = (Actor) i.next();
            if (actor.isTop()==b)
                filtered.add(actor);
        }
        return filtered;

    }
}
