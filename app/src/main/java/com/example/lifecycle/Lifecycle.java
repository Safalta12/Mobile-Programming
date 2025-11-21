package com.example.lifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Lifecycle extends AppCompatActivity {

    private static final int REQUEST_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lifecycle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btn_name=findViewById(R.id.LoginButton);
        btn_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent=new Intent(Lifecycle.this,MainActivity.class);
              intent.putExtra("message","Welcome to next page");
//              startActivity(intent);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==REQUEST_CODE && resultCode== Activity.RESULT_OK){
            if (data!=null && data.hasExtra("reply")){
                String reply= data.getStringExtra("reply");
                Toast.makeText(this,"Reply from SeconActivity: " + reply, Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d("Lifecycle Test","Activity Started");
        Toast.makeText(this,"onStart",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d("Lifecycle Test","Activity Resumed");
        Toast.makeText(this,"onResume",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d("Lifecycle Test","Activity Paused");
        Toast.makeText(this,"onPause",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d("Lifecycle Test","Activity Stoped");
        Toast.makeText(this,"onStop",Toast.LENGTH_SHORT).show();
    }

}