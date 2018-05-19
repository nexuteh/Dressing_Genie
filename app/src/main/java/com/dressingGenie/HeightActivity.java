package com.dressingGenie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HeightActivity extends AppCompatActivity {

    private Button btnSubmit ;
    private TextView txtHeight;

    @Override
    @SuppressWarnings("ConstantConditions")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height);
        btnSubmit = findViewById(R.id.btnSubmit);
        txtHeight = findViewById(R.id.edtHeight);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Step 1: Please Insert Your Height(cm)");
        setSupportActionBar(toolbar);

    }

    public void fnSubmit(View vw){
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(validationSuccess()){
                    Intent myIntent = new Intent(HeightActivity.this, FirstActivityCamera.class);
                    String height = txtHeight.getText().toString();
                    myIntent.putExtra("Height", height);
                    HeightActivity.this.startActivity(myIntent);
                }
            }
        });
    }

    private Boolean validationSuccess() {
        if (txtHeight.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(getApplicationContext(), "Please Enter Your Height", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
