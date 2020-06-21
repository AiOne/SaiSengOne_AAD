package com.example.sso_aad.BookingSystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sso_aad.BusLine;
import com.example.sso_aad.R;

import org.w3c.dom.Text;

public class SuccessActivity extends AppCompatActivity {


    private TextView nameText;
    private TextView nrcText;
    private TextView phoneText;
    private TextView emailText;
    private TextView travelText;
    private TextView busLineText;
    private TextView timeText;
    private TextView dateText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);


        nameText = (TextView) findViewById(R.id.nameText);
        nrcText = (TextView) findViewById(R.id.NRCText);
        phoneText = (TextView) findViewById(R.id.phoneText);
        emailText = (TextView) findViewById(R.id.emailText);
        travelText = (TextView) findViewById(R.id.travelText);
        busLineText = (TextView) findViewById(R.id.busLineText);
        dateText = (TextView) findViewById(R.id.dateText);

        Intent intent = getIntent();
        Bundle allData = intent.getExtras();

        nameText.setText("Name : " + allData.getString(Personalinformation.NAME_KEY, ""));
        nrcText.setText("NRC : " + allData.getString(Personalinformation.NRC_KEY, ""));
        phoneText.setText("Phone : " + allData.getString(Personalinformation.PHONE_KEY, ""));
        emailText.setText("Email : " + allData.getString(Personalinformation.EMAIL_KEY, ""));
        busLineText.setText("Bus Line : " + BusLine.busLineNames[allData.getInt(BookingForm.BUS_LINE, 0)]);
        travelText.setText("From " + allData.getString(BookingForm.SOURCE, "") + " To " + allData.getString(BookingForm.DESTINATION, ""));
        dateText.setText("Date: " + allData.getString(BookingForm.DATE_KEY, "") + " at " + allData.getString(BookingForm.ROUTINE, ""));

        getSupportActionBar().hide();
    }


    public void finish(View v){
        finish();

    }


}
