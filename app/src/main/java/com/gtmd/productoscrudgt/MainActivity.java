package com.gtmd.productoscrudgt;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText user, pass;
    private Button btnlo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        btnlo = (Button) findViewById(R.id.btnCrear);

        btnlo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if(user.getText().toString().equals("admin") && pass.getText().toString().equals("admin")){
                        Intent intent = new Intent(getApplicationContext(), Menusl.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(MainActivity.this, "Credenciasles incorrectas", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "err -> "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}