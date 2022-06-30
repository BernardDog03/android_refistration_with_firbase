package com.example.project_akhir;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class admin_login extends AppCompatActivity {
    Button b1,b2;
    EditText ed1,ed2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        b1 = (Button)findViewById(R.id.button1);
        ed1 = (EditText)findViewById(R.id.editText1);
        ed2 = (EditText)findViewById(R.id.editText2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usernameKey = ed1.getText().toString();
                String passwordKey = ed2.getText().toString();

                if
                (usernameKey.equals("bernard") && passwordKey.equals("12345")) {
                    Intent intent = new Intent(admin_login.this, DisplayActivity.class);
                    admin_login.this.startActivity(intent);
                    finish();
                }else {
                    ed2.setText("");
                    AlertDialog.Builder builder = new AlertDialog.Builder(admin_login.this);
                    builder.setMessage("Insert correct username & password").setNegativeButton("Retry", null).create().show();
                }
            }
        });

    }
}
