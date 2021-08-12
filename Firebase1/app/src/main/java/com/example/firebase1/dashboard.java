package com.example.firebase1;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class dashboard extends AppCompatActivity {
    Button logout;
    private EditText employeeNameEdt, employeeQualEdt, employeeAddressEdt;
    private Button sendDatabtn,btnview;
    TextView phnoview;
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database Reference for Firebase

    DatabaseReference databaseReference;

    // creating a variable for our object class

    EmployeeInfo employeeInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        logout=findViewById(R.id.logout);
        phnoview=findViewById(R.id.phnoview);
        employeeNameEdt = findViewById(R.id.idEdtEmployeeName);
        employeeQualEdt = findViewById(R.id.idEdtEmployeeQualification);
        employeeAddressEdt = findViewById(R.id.idEdtEmployeeAddress);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("EmployeeInfo");
        employeeInfo = new EmployeeInfo();
        sendDatabtn = findViewById(R.id.idBtnSendData);
        btnview=findViewById(R.id.btnview);

        String phno = getIntent().getStringExtra("keyph");
        phnoview.setText(phno);

        sendDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = employeeNameEdt.getText().toString();
                String qua = employeeQualEdt.getText().toString();
                String address = employeeAddressEdt.getText().toString();
                String phno = phnoview.getText().toString();

                if (phno.isEmpty() && name.isEmpty() && qua.isEmpty() && address.isEmpty()) {
                    Toast.makeText(dashboard.this, "Please add some data", Toast.LENGTH_SHORT).show();
                } else {
                    addDatatoFirebase(phno,name,qua,address);
                }
            }
        });
    }

    private void addDatatoFirebase(String phno ,String name, String qua, String address) {
        employeeInfo.setPhno(phno);
        employeeInfo.setEmployeeName(name);
        employeeInfo.setEmployeeQualification(qua);
        employeeInfo.setEmployeeAddress(address);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.setValue(employeeInfo);
                Toast.makeText(dashboard.this, "Data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(dashboard.this, "Fail to add data" + error, Toast.LENGTH_SHORT).show();
            }

        });

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phno=phnoview.getText().toString();
                String name=employeeNameEdt.getText().toString();
                String qual=employeeQualEdt.getText().toString();
                String place=employeeAddressEdt.getText().toString();

                Intent intent = new Intent(dashboard.this, viewdetail.class);
                intent.putExtra("keyphno",phno);
                intent.putExtra("keyname",name);
                intent.putExtra("keyqual",qual);
                intent.putExtra("keyplace",place);
                startActivity(intent);
            }
        });

    }
}












