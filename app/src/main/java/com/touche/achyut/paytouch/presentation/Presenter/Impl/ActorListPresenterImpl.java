package com.touche.achyut.paytouch.presentation.Presenter.Impl;

import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;

import com.touche.achyut.paytouch.R;
import com.touche.achyut.paytouch.presentation.Activity.MainActivity;
import com.touche.achyut.paytouch.presentation.Presenter.ActorListPresenter;
import com.touche.achyut.paytouch.presentation.View.ActorListView;

public class ActorListPresenterImpl implements ActorListPresenter {

    ActorListView view;
    public ActorListPresenterImpl(ActorListView view) {
        this.view = view;
    }

    @Override
    public void onPopMenuClick(final MainActivity mainActivity,final View v) {

        if (v.getId() == R.id.orderBy){
            PopupMenu popup = new PopupMenu(mainActivity,v);
            //Inflating the Popup using xml file
            popup.getMenuInflater().inflate(R.menu.order_by, popup.getMenu());
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {

                    v.setSelected(false);
                    v.setPressed(false);

                    switch (item.getItemId()) {
                        case R.id.by_name:
                            view.sortByName();
                            break;
                        case R.id.by_popularity:
                            view.sortByPopularity();
                            break;
                    }
                    return true;
                }
            });

            popup.show();
        }
        if (v.getId() == R.id.search){
            view.openDrawer();
        }

    }
}
