package com.touche.achyut.paytouch.presentation.Activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.touche.achyut.paytouch.R;
import com.touche.achyut.paytouch.domain.model.Actor;
import com.touche.achyut.paytouch.presentation.Adapter.RecyclerViewAdapter;
import com.touche.achyut.paytouch.presentation.AsyncTask.ActorListTask;
import com.touche.achyut.paytouch.presentation.Fragment.NavigationDrawerFrag;
import com.touche.achyut.paytouch.presentation.Presenter.ActorListPresenter;
import com.touche.achyut.paytouch.presentation.Presenter.Impl.ActorListPresenterImpl;
import com.touche.achyut.paytouch.presentation.View.ActorListView;
import com.touche.achyut.paytouch.presentation.View.DrawerView;
import com.touche.achyut.paytouch.utils.FilterList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ActorListView,DrawerView {

    Toolbar toolbar;
    android.support.v7.app.ActionBar actionBar;
    ImageButton orderByBtn,searchBtn;
    private RecyclerView recyclerView;
    private List<Actor> actorList;
    private ActorListPresenter presenter;
    private RecyclerViewAdapter adapter;
    private NavigationDrawerFrag drawerFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeWidget();

        try {
            //TODO Comment 'is' InputStream variable to load JSON from URL
            InputStream is = getAssets().open("sampleActors.json");
            //TODO Remove 'is' from execute() to load JSON from URL
            actorList = new ActorListTask().execute(is).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        adapter = new RecyclerViewAdapter(this,actorList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        orderByBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
    }

    private void initializeWidget() {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        recyclerView = (RecyclerView)findViewById(R.id.actorList);
        orderByBtn = (ImageButton)toolbar.findViewById(R.id.orderBy);
        searchBtn = (ImageButton)toolbar.findViewById(R.id.search);
        drawerFrag = (NavigationDrawerFrag)getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        drawerFrag.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout),this);

        presenter = new ActorListPresenterImpl(this);
    }

    @Override
    public void onClick(View v) {

        orderByBtn.setSelected(true);
        orderByBtn.setPressed(true);

        presenter.onPopMenuClick(MainActivity.this, v);

    }


    @Override
    public void sortByName() {
        Collections.sort(actorList, new Comparator<Actor>() {
            @Override
            public int compare(Actor lhs, Actor rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        });
        adapter.notifyDataSetChanged();
    }

    @Override
    public void sortByPopularity() {
        Collections.sort(actorList, new Comparator<Actor>() {
            @Override
            public int compare(Actor lhs, Actor rhs) {
                if (lhs.getPopularity()>rhs.getPopularity())
                    return -1;
                else
                    return 1;
            }
        });
        adapter.notifyDataSetChanged();
    }

    @Override
    public void openDrawer() {
        drawerFrag.open();
    }

    @Override
    public void searchByName(String name) {

        List<Actor> filtered = FilterList.filterByName(name, actorList);
        this.actorList = filtered;
        adapter = new RecyclerViewAdapter(this,filtered);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void searchByTop(String result) {

        List<Actor> filtered = null;

        if (result.equals("yes"))
            filtered = FilterList.filterByTrue(true,actorList);
        else
            filtered = FilterList.filterByFalse(false,actorList);

        adapter = new RecyclerViewAdapter(this,filtered);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void searchByPopularity(int value) {

        List<Actor> filtered = FilterList.filterByPopularity(value,actorList);
        this.actorList = filtered;
        adapter = new RecyclerViewAdapter(this,filtered);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void searchByLocation(String loc) {
        List<Actor> filtered = FilterList.filterByLocation(loc, actorList);
        this.actorList = filtered;
        adapter = new RecyclerViewAdapter(this,filtered);
        recyclerView.setAdapter(adapter);
    }
}
