package com.example.sso_aad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.sso_aad.ui.home.ImageSliderAdapter;

public class BusLineInfo extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_line_info);


        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentLeft, new BusItemFragment()).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentRight, new DetailFragment()).commit();
        }

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(getBaseContext());
        viewPager.setAdapter(imageSliderAdapter);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(DetailFragment.POSITION, position);
        detailFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentRight, detailFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }


    public void close(View view){
        finish();
    }
}
