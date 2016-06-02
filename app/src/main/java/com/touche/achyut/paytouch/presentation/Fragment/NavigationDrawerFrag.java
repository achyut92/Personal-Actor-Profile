package com.touche.achyut.paytouch.presentation.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.touche.achyut.paytouch.R;
import com.touche.achyut.paytouch.domain.service.ActorService;
import com.touche.achyut.paytouch.presentation.View.DrawerView;

import java.io.InputStream;
import java.util.List;

public class NavigationDrawerFrag extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {

    private DrawerLayout mDrawerLayout;
    private View containerView;
    private ImageButton cross;
    private EditText name_et;
    private DrawerView drawerView;
    private Spinner location;
    private RadioGroup yes_no;
    private SeekBar popularity;
    private Button search;
    private RadioButton radioSelected;
    private int progressValue;
    private List<String> list;
    private TextView seekbarValue;

    public NavigationDrawerFrag() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer,container,false);

        InputStream is = null;
        try {
             is = getActivity().getAssets().open("sampleActors.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ActorService service = new ActorService();
        list = service.getLocationList(is);
        list.add(0,"select location");

        initializeWidgets(layout);


        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, list); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location.setAdapter(spinnerArrayAdapter);

        search.setOnClickListener(this);
        cross.setOnClickListener(this);
        yes_no.setOnCheckedChangeListener(this);
        popularity.setOnSeekBarChangeListener(this);

        return layout;
    }

    private void initializeWidgets(View layout) {
        cross = (ImageButton)layout.findViewById(R.id.close);
        name_et = (EditText)layout.findViewById(R.id.name_et);
        location = (Spinner)layout.findViewById(R.id.location_spinner);
        yes_no = (RadioGroup)layout.findViewById(R.id.top_rg);
        popularity = (SeekBar)layout.findViewById(R.id.seekBar);
        search = (Button)layout.findViewById(R.id.search_button);
        seekbarValue =(TextView)layout.findViewById(R.id.seekBar_val);
    }


    public void setUp(int fragment_id, DrawerLayout drawerLayout, DrawerView drView) {

        containerView = getActivity().findViewById(fragment_id);
        mDrawerLayout =drawerLayout;
        this.drawerView = drView;

    }


    public void open(){
        mDrawerLayout.openDrawer(Gravity.RIGHT);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.close:
                mDrawerLayout.closeDrawers();
                break;
            case R.id.search_button:
                String name = name_et.getText().toString();
                String loc = location.getSelectedItem().toString();
                String radioText="";
                if (radioSelected!=null)
                     radioText = radioSelected.getText().toString();
                name_et.setText("");
                if (!name.isEmpty())
                    drawerView.searchByName(name);
                if (!loc.contains("select"))
                    drawerView.searchByLocation(loc);
                if (!radioText.isEmpty())
                    drawerView.searchByTop(radioText);
                if (progressValue > 0)
                    drawerView.searchByPopularity(progressValue);
                mDrawerLayout.closeDrawers();
                break;

        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        radioSelected = (RadioButton)group.findViewById(checkedId);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        progressValue = progress;
        seekbarValue.setText(String.valueOf(progress));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
