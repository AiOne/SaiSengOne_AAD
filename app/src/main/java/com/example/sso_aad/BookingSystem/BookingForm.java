package com.example.sso_aad.BookingSystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sso_aad.BusItemFragment;
import com.example.sso_aad.BusLine;
import com.example.sso_aad.R;

import java.util.Date;

public class BookingForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private int buslinePosition;
    private TextView titleTextView;
    private Spinner destinationSpinner;
    private Spinner sourceSpinner;
    private RadioGroup radioGroupTime;
    private Button nextButton;
    private DatePicker datePicker;
    private String town;
    private String[] townList;


    public static final String BUS_LINE = "BUS_LINE";
    public static final String SOURCE = "SOURCE";
    public static final String DESTINATION = "DESTINATION";
    public static final String DATE_KEY = "DATE";
    public static final String ROUTINE = "ROUTINE";


    private Intent previousIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_form);

        previousIntent  = getIntent();
        buslinePosition = previousIntent.getIntExtra(BusItemFragment.POSITION, 0);
        titleTextView = (TextView) findViewById(R.id.placePickerTitle);
        titleTextView.setText("Booking form for " + BusLine.busLineNames[buslinePosition]);


        destinationSpinner = (Spinner) findViewById(R.id.spinnerDestination);
        sourceSpinner = (Spinner) findViewById(R.id.spinnerSource);
        radioGroupTime = (RadioGroup) findViewById(R.id.routeRadioGroup);
        nextButton = (Button) findViewById(R.id.nextButton);
        townList = getResources().getStringArray(R.array.Town);

        datePicker = (DatePicker) findViewById(R.id.dateBooking);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Town, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sourceSpinner.setAdapter(adapter);
        destinationSpinner.setAdapter(adapter);
        sourceSpinner.setOnItemSelectedListener(this);
        destinationSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        town = townList[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        town = "No Town Selected";
    }

    public void next(View v){
        Intent nextIntent = new Intent(this, Personalinformation.class);
        Bundle bookingBundle = new Bundle();

        bookingBundle.putInt(BUS_LINE, buslinePosition);
        bookingBundle.putString(SOURCE, sourceSpinner.getSelectedItem().toString());
        bookingBundle.putString(DESTINATION, destinationSpinner.getSelectedItem().toString());
        bookingBundle.putString(DATE_KEY, datePicker.getDayOfMonth() + "-" + datePicker.getMonth() + "-" + datePicker.getYear());

        RadioButton radioText = (RadioButton) findViewById(radioGroupTime.getCheckedRadioButtonId());
        bookingBundle.putString(ROUTINE, (String) radioText.getText());

        nextIntent.putExtras(bookingBundle);

        startActivity(nextIntent);
    }
}
