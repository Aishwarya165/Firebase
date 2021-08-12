package com.example.firebase1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {
    EditText emailid;
    EditText password;
    Button btnsignup;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mFirebaseAuth=FirebaseAuth.getInstance();
        emailid=findViewById(R.id.emailid);
        password=findViewById(R.id.password);
        btnsignup=findViewById(R.id.btnsignup);

        btnsignup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = emailid.getText().toString();
                String pws = password.getText().toString();
                if (pws.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_LONG).show();
                    return;
                }
                else if(pws.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_LONG).show();
                    return;
                }

                else if(!(pws.isEmpty() && pws.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pws).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(signup.this, "Mail already exist", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(signup.this, "Sign up Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(signup.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                }

            }
        });
    }
}