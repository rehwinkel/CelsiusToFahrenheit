package com.temperatures.ian.celsiustofahrenheit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ian.celsiustofahrenheit.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    int op1 = 1;
    int op2 = 2;
    float input = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-2274559060341445~8228868412");

        AdView ad = (AdView) findViewById(R.id.adView);
        AdRequest request = new AdRequest.Builder().build();
        ad.loadAd(request);

        Spinner sp = (Spinner) findViewById(R.id.spinner2);
        sp.setSelection(1);

        EditText in = (EditText) findViewById(R.id.editText3);
        in.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString();

                if(str.equals("-") || str.equals(".") || str.equals("") || str.equals("+") || str.equals("+.") || str.equals("-.")){
                    input = 0;
                }else{
                    float i = Float.parseFloat(str);
                    input = i;
                }

                runCalcs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Spinner spinnerIn = (Spinner) findViewById(R.id.spinner3);
        spinnerIn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinnerIn = (Spinner) findViewById(R.id.spinner3);
                if(spinnerIn.getSelectedItem().toString().equals("°C")){
                    op1 = 1;
                }

                if(spinnerIn.getSelectedItem().toString().equals("°F")){
                    op1 = 2;
                }

                if(spinnerIn.getSelectedItem().toString().equals("K")){
                    op1 = 0;
                }

                runCalcs();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spinnerOut = (Spinner) findViewById(R.id.spinner2);
        spinnerOut.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinnerOut = (Spinner) findViewById(R.id.spinner2);
                if(spinnerOut.getSelectedItem().toString().equals("°C")){
                    op2 = 1;
                }

                if(spinnerOut.getSelectedItem().toString().equals("°F")){
                    op2 = 2;
                }

                if(spinnerOut.getSelectedItem().toString().equals("K")){
                    op2 = 0;
                }

                runCalcs();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void runCalcs(){
        TextView result = (TextView) findViewById(R.id.textView3);
        float out = 0;
        if(op1 == 0){
            if(op2 == 0){
                out = input;
            }

            if(op2 == 1){
                out = input - 273.15F;
            }

            if(op2 == 2){
                out = input * 9 / 5 - 459.67F;
            }
        }

        if(op1 == 1){
            if(op2 == 0){
                out = input + 273.15F;
            }

            if(op2 == 1){
                out = input;
            }

            if(op2 == 2){
                out = input * 9 / 5 + 32F;
            }
        }

        if(op1 == 2){
            if(op2 == 0){
                out = (input + 459.67F) * 5/9;
            }

            if(op2 == 1){
                out = (input - 32) / 1.8F;
            }

            if(op2 == 2){
                out = input;
            }
        }

        out = (float) Math.round(out * 10) / 10;
        String end = out + "";
        Log.d("k", end);
        if(end.contains(".0")){
            end = end.substring(0, end.length()-2);
        }
        result.setText(end);
    }
}