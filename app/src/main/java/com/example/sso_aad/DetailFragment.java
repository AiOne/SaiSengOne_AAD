package com.example.sso_aad;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    public static final String POSITION = "position";

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null){
            int position = args.getInt(POSITION);
            ImageView buslineLogo = (ImageView) getActivity().findViewById(R.id.logoImageView);
            buslineLogo.setImageResource(BusLine.busLineLogo[position]);
            TextView busLineDetail = (TextView) getActivity().findViewById(R.id.busDetailTextView);
            busLineDetail.setText(BusLine.busLineDetail[position]);
        }else {
            TextView detail = (TextView) getActivity().findViewById(R.id.busDetailTextView);
            detail.setText("Please Click the left Size to view its detail");
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }


}
