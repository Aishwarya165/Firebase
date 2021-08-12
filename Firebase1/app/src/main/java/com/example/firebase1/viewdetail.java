package com.example.firebase1;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class viewdetail extends AppCompatActivity {
    TextView phno,name,qual,place;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdetail);
        phno=findViewById(R.id.phno);
        logout=findViewById(R.id.logout);
        name=findViewById(R.id.name);
        qual=findViewById(R.id.qual);
        place=findViewById(R.id.place);
        String phnok =getIntent().getStringExtra("keyphno");
        String namek =getIntent().getStringExtra("keyname");
        String qualk =getIntent().getStringExtra("keyqual");
        String placek =getIntent().getStringExtra("keyplace");
        phno.setText(phnok);
        name.setText(namek);
        qual.setText(qualk);
        place.setText(placek);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(viewdetail.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

}