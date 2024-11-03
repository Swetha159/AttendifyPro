package com.example.attendifypro2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterPage extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextPassword;

    Button signUp ;

    TextView signIn;

    FirebaseAuth firebaseAuth  = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_page);

        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        signUp = findViewById(R.id.register);
        signIn = findViewById(R.id.login);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterPage.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                email = String .valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(RegisterPage.this , "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(RegisterPage.this , "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(RegisterPage.this , "Registration successful",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterPage.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Toast.makeText(RegisterPage.this,"Registration Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });

    }
}