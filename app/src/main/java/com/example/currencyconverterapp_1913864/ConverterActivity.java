package com.example.currencyconverterapp_1913864;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class ConverterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edText;
    TextView txtResult;
    Button btnConvert;
    String[] currency;
    ArrayAdapter arrayAdapter;
    AutoCompleteTextView autoCompleteTextView;
    TextInputLayout dropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        initialize();
    }

    private void initialize() {

        edText = findViewById(R.id.edText);
        txtResult = findViewById(R.id.txtResult);
        btnConvert = findViewById(R.id.btnConvert);
        btnConvert.setOnClickListener(this);
        currency = getResources().getStringArray(R.array.currency);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        arrayAdapter = new ArrayAdapter<>(this,R.layout.dropdownlist,currency);
        autoCompleteTextView.setAdapter(arrayAdapter);
        dropdown = findViewById(R.id.dropdown);
        ((AutoCompleteTextView)dropdown.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                txtResult.setText(null);
            }
        });
        ResetScreenComponents();
    }

    private void ResetScreenComponents() {
        edText.setText(null);
        txtResult.setText(null);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnConvert) {
            String inputEuro = edText.getText().toString();
            if (inputEuro.isEmpty()){
                Toast.makeText(this, "Empty Input!", Toast.LENGTH_LONG).show();
            }
            else if (!onlyNumber(inputEuro)) {
                Toast.makeText(this, "Invalid input! Input can only be Number", Toast.LENGTH_LONG).show();
            }
            else {
                String selectedVal = ((AutoCompleteTextView)dropdown.getEditText()).getText().toString();
                switch (selectedVal) {
                    case "USD": {
                        double usd =(Double.valueOf(inputEuro) / 0.92);
                        txtResult.setText("$" + usd + " " + selectedVal);
                        break;
                    }
                    case "BR": {
                        double br =(Double.valueOf(inputEuro) / 0.18);
                        txtResult.setText("$" + br + " " + selectedVal);
                        break;
                    }
                }

            }
        }

    }

    private boolean onlyNumber(String str) {
        try {
            double v = Double.parseDouble(str);
            return true;
        }

        catch (NumberFormatException nfe) {

        }

        return false;
    }
}
