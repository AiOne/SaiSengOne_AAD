package com.example.sso_aad.BookingSystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.sso_aad.BusItemFragment;
import com.example.sso_aad.R;

public class Personalinformation extends AppCompatActivity {
    private EditText nameEditText;
    private EditText nrcEditText;
    private EditText phoneEditText;
    private EditText addressEditText;
    private EditText emailEditText;

    public static final String NAME_KEY = "NAME_KEY";
    public static final String NRC_KEY = "NRC_KEY";
    public static final String PHONE_KEY = "PHONE_KEY";
    public static final String ADDRESS_KEY = "ADDRESS_KEY";
    public static final String EMAIL_KEY = "EMAIL_KEY";

    private Intent previousIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalinformation);



        nameEditText = (EditText) findViewById(R.id.nameEditText);
        nrcEditText = (EditText) findViewById(R.id.nrcEditText);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);
        addressEditText = (EditText) findViewById(R.id.addressEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditTex);

    }


    public void submitBooking(View v){
        previousIntent = getIntent();
        Bundle personalBundle = previousIntent.getExtras();

        personalBundle.putString(NAME_KEY, nameEditText.getText().toString());
        personalBundle.putString(NRC_KEY, nrcEditText.getText().toString());
        personalBundle.putString(PHONE_KEY, phoneEditText.getText().toString());
        personalBundle.putString(ADDRESS_KEY, addressEditText.getText().toString());
        personalBundle.putString(EMAIL_KEY, emailEditText.getText().toString());

        Intent nextIntent = new Intent(this, SuccessActivity.class);

        nextIntent.putExtras(personalBundle);

        startActivity(nextIntent);
    }


}
