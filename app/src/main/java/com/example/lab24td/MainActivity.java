package com.example.lab24td;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etHeight, etWeight;
    private RadioGroup rgGender;
    private CheckBox cbSaveData;
    private Button btnCalculate;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);
        rgGender = findViewById(R.id.rgGender);
        cbSaveData = findViewById(R.id.cbSaveData);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String heightStr = etHeight.getText().toString().trim();
        String weightStr = etWeight.getText().toString().trim();

        if (heightStr.isEmpty()) {
            etHeight.setError("Pole wzrostu nie może być puste!");
            return;
        }

        if (weightStr.isEmpty()) {
            etWeight.setError("Pole wagi nie może być puste!");
            return;
        }

        float heightCm = Float.parseFloat(heightStr);
        float weightKg = Float.parseFloat(weightStr);

        if (heightCm <= 0) {
            etHeight.setError("Wzrost musi być większy od 0!");
            return;
        }
        if (weightKg <= 0) {
            etWeight.setError("Waga musi być większy od 0!");
            return;
        }

        float heightM = heightCm / 100;
        float bmi = weightKg / (heightM * heightM);

        String category;
        if (bmi < 18.5) {
            category = "Niedowaga";
        } else if (bmi >= 18.5 && bmi < 25) {
            category = "Waga prawidłowa";
        } else if (bmi >= 25 && bmi < 30) {
            category = "Nadwaga";
        } else {
            category = "Otyłość";
        }

        int selectedGenderId = rgGender.getCheckedRadioButtonId();
        String gender = (selectedGenderId == R.id.rbFemale) ? "Kobieta" : "Mężczyzna";

        if (cbSaveData.isChecked()) {
            Toast.makeText(MainActivity.this, "Dane zostały tymczasowo zapisane!", Toast.LENGTH_SHORT).show();
        }

        String resultText = String.format("Twoje BMI: %.2f\nInterpretacja: %s\nPłeć: %s", bmi, category, gender);
        tvResult.setText(resultText);
    }
}
