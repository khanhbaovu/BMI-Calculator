package com.example.bmiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edt1, edt2;
    private Button btn;
    private TextView tv;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        btn.setOnClickListener(this);
    }

    private void init() {
        edt1 = (EditText) findViewById(R.id.edt1);
        edt2 = (EditText) findViewById(R.id.edt2);

        btn = (Button) findViewById(R.id.btn);

        tv = (TextView) findViewById(R.id.tv);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onClick(View v) {
        if(TextUtils.isEmpty(edt1.getText()) || TextUtils.isEmpty(edt2.getText())) {
            showToast("Enter your height and weight");
        }
        else {
            float height = Float.parseFloat(edt1.getText().toString());
            float weight = Float.parseFloat(edt2.getText().toString());
            String record1 = null, record2 = null, record3 = null, record4 = null;
            float bmi = calculateBMI(weight, height);
            float bestWeight = (height%100)*9/10;

            record1 = String.format("Chỉ số BMI của bạn: %.2f\n", bmi) ;
            record2 = convertBMI(bmi) + "\n";
            record3 = String.format("Cân nặng lý tưởng của bạn là: %.0f\n", bestWeight);
            record4 = getAdvice(bestWeight, weight);

            text = record1 +  record2 +  record3 + record4;

            tv.setText(text);
        }
    }

    public String getAdvice(float bestWeight, float weight) {
        if(bestWeight > weight) {
            return String.format("Bạn còn thiếu %.0f\n", bestWeight - weight);
        }
        else if(bestWeight < weight) {
            return String.format("Bạn bị thừa %.0f", weight - bestWeight);
        }
        return null;
    }

    public String convertBMI(float bmi) {
        if(bmi < 18.5) {
            return "Bạn bị thiếu cân";
        }
        else if(bmi < 25) {
            return "Thân hình siêu mẫu";
        }
        else if(bmi < 30) {
            return "Bạn béo phì cấp độ 1";
        }
        else if(bmi < 40) {
            return "Bạn béo phì cấp độ 2";
        }
        else {
            return "Bạn béo phì cấp độ 3";
        }
    }

    public float calculateBMI(float weight, float height) {
        return (weight/(height/100 * height/100));
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
