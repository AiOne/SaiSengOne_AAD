package com.example.sso_aad;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sso_aad.BookingSystem.BookingForm;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusItemFragment extends ListFragment implements AdapterView.OnItemClickListener{
    public static final String POSITION = "position";
    public BusItemFragment() {
        // Required empty public constructor
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        TextView textView = new TextView(getActivity());
//        textView.setText(R.string.hello_blank_fragment);
//        return textView;
//    }
//
    @Override
    public void onStart(){
        super.onStart();

        if (getActivity() instanceof BusLineInfo) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            getListView().setSelector(android.R.color.holo_blue_bright);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, BusLine.busLineNames));

        if(getActivity() instanceof MainActivity){
            getListView().setOnItemClickListener(this);
        }
        if(getActivity() instanceof BusLineInfo){
            getListView().setOnItemClickListener((BusLineInfo) getActivity());
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent bookingItent = new Intent(getActivity(), BookingForm.class);
        bookingItent.putExtra(POSITION, position);
        startActivity(bookingItent);
        Toast.makeText(getActivity(), BusLine.busLineNames[position], Toast.LENGTH_SHORT).show();
    }
}
