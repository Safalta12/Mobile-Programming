package com.example.contact;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText email_input= findViewById(R.id.emailInput);
        EditText password_input= findViewById(R.id.passwordInput);
        Button login_button= findViewById(R.id.LoginButton);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=email_input.getText().toString();
                String password=password_input.getText().toString();
                if(email.isEmpty()|| password.isEmpty()){
                    Toast.makeText(MainActivity.this,"Email and password is required",Toast.LENGTH_SHORT).show();
                }
                if (email.equals("admin")&& password.equals("admin")){
                    //login successful,navigate to the next activity called Homepage
                    Intent intent=new Intent(MainActivity.this, HomePage.class);
                    //pass email to next activity
                    intent.putExtra("email",email);
                    startActivity(intent);

                    Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}