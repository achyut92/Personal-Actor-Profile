package com.touche.achyut.paytouch.presentation.Activity;

import android.app.ListActivity;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.touche.achyut.paytouch.R;
import com.touche.achyut.paytouch.domain.model.Actor;
import com.touche.achyut.paytouch.presentation.Adapter.ProfileListAdapter;
import com.touche.achyut.paytouch.presentation.AsyncTask.ImageStreamDownloadTask;
import com.touche.achyut.paytouch.utils.BitmapHelper;

import java.util.concurrent.ExecutionException;

public class ProfileDetails extends ListActivity implements View.OnClickListener {

    private Actor actor;
    private ImageButton back;
    private ImageView profile;
    private TextView name,location,description;
    private ProfileListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);

        actor = (Actor)getIntent().getExtras().get("profileDetail");

        initialize();

        //TODO Uncomment to load bitmap
//        Bitmap bitmap = BitmapHelper.getBitmap(actor.getProfile_pic());

        name.setText(actor.getName());
        location.setText(actor.getLocation());
        description.setText(actor.getDescription());

        //TODO Uncomment to set bitmap
//        profile.setImageBitmap(bitmap);

        setListAdapter(adapter);

        back.setOnClickListener(this);
    }

    private void initialize() {
        back = (ImageButton)findViewById(R.id.back_btn);
        profile = (ImageView)findViewById(R.id.profile_pic);
        name = (TextView)findViewById(R.id.name);
        description = (TextView)findViewById(R.id.description_text);
        location = (TextView)findViewById(R.id.location);
        adapter = new ProfileListAdapter(this,android.R.layout.simple_list_item_1,actor.getFilms());
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.back_btn:
                finish();
        }
    }
}
