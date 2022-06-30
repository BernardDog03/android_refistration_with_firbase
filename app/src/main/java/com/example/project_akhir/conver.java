package com.example.project_akhir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class conver extends AppCompatActivity {
    GoogleSignInClient mGoogleSignInClient;
    EditText fn, ln, prov, ttl, dob, add, tel, email, note;
    Button saveBtn, Childbtn, sign_out;
    Spinner gender, lokasi, hari, time;
    ImageView photoIV;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private static final String TAG = "basic";
    private LinearLayout layoutside = null;
    private LinearLayout layoutKelas = null;

    DatabaseReference databaseChild;
    TextView nameTV, emailTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conver);

        databaseChild = FirebaseDatabase.getInstance().getReference("conver");
        layoutside = (LinearLayout) findViewById(R.id.sidedrawwer);
        layoutKelas = (LinearLayout) findViewById(R.id.kelasopsi);
        Childbtn = (Button) findViewById(R.id.conversation);
        nameTV = findViewById(R.id.name);
        emailTV = findViewById(R.id.email);
        photoIV = findViewById(R.id.photo);
        sign_out = findViewById(R.id.log_out);

        fn = (EditText) findViewById(R.id.firstnamaedt); ln = (EditText) findViewById(R.id.lastnamaedt);
        prov = (EditText) findViewById(R.id.provedt); ttl = (EditText) findViewById(R.id.tempatlhredt);
        dob = (EditText) findViewById(R.id.dateOBedt); add = (EditText) findViewById(R.id.alamatedt);
        tel = (EditText) findViewById(R.id.telpedt); email = (EditText) findViewById(R.id.emailedt);
        note = (EditText) findViewById(R.id.notedt);

        saveBtn = (Button) findViewById(R.id.savebtn);

        gender = (Spinner) findViewById(R.id.genderedt);  hari = (Spinner) findViewById(R.id.dayedt);
        lokasi = (Spinner) findViewById(R.id.locationedt);  time = (Spinner) findViewById(R.id.jamedt);

        Childbtn.setBackgroundColor(getColor(R.color.biru3));
        Childbtn.setTextColor(getColor(R.color.colorPrimaryDark));

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addChild();
            }
        });

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        conver.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.d(TAG, "onDateSet: yyyy-mm-dd: " +year+"-"+month+"-"+dayOfMonth);

                String date = year +"-"+month+"-"+dayOfMonth;
                dob.setText(date);
            }
        };

        findViewById(R.id.homeside).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), MainActivity2.class));
            }
        });
        findViewById(R.id.teen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), greeting.class));
            }
        });
        findViewById(R.id.child).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), basic.class));
            }
        });
        findViewById(R.id.adult).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), expert.class));
            }
        });
        findViewById(R.id.aboutUs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), about.class));
            }
        });
        findViewById(R.id.drawwer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutside.getVisibility() == View.GONE) {
                    layoutside.setVisibility(View.VISIBLE);
                } else {
                    layoutside.setVisibility(View.GONE);
                }
            }
        });
        findViewById(R.id.exitdrawwer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutside.getVisibility() == View.GONE) {
                    layoutside.setVisibility(View.VISIBLE);
                } else {
                    layoutside.setVisibility(View.GONE);
                }
            }
        });
        findViewById(R.id.kelas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutKelas.getVisibility() == View.GONE) {
                    layoutKelas.setVisibility(View.VISIBLE);
                } else {
                    layoutKelas.setVisibility(View.GONE);
                }
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(conver.this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String persongivenname = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            nameTV.setText(personName);
            emailTV.setText(personEmail);
            Glide.with(this).load(personPhoto).into(photoIV);
        }
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(conver.this,"Successfully signed out",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(conver.this, MainActivity.class));
                        finish();
                    }
                });
    }
    private void addChild(){
        String edt1 = fn.getText().toString().trim();
        String edt2 = ln.getText().toString().trim();
        String edt3 = prov.getText().toString().trim();
        String edt4 = ttl.getText().toString().trim();
        String edt5 = dob.getText().toString().trim();
        String edt6 = add.getText().toString().trim();
        String edt7 = tel.getText().toString().trim();
        String edt8 = email.getText().toString().trim();
        String edt9 = note.getText().toString().trim();

        String spin1 = gender.getSelectedItem().toString();
        String spin2 = lokasi.getSelectedItem().toString();
        String spin3 = hari.getSelectedItem().toString();
        String spin4 = time.getSelectedItem().toString();
        if (!TextUtils.isEmpty(edt1) && !TextUtils.isEmpty(edt2) && !TextUtils.isEmpty(edt3) && !TextUtils.isEmpty(edt4)
                && !TextUtils.isEmpty(edt5) && !TextUtils.isEmpty(edt6) && !TextUtils.isEmpty(edt7) && !TextUtils.isEmpty(edt8)
                && !TextUtils.isEmpty(edt9)){

            String id = databaseChild.push().getKey();
            Requests requests = new Requests(id,edt1,edt2,edt4,edt5,edt6,edt7,edt8,edt3,spin1,spin4,spin3,spin2,edt9);
            databaseChild.child(id).setValue(requests);
            Toast.makeText(this, "done",Toast.LENGTH_SHORT).show();
            fn.setText("");ln.setText("");prov.setText("");ttl.setText("");dob.setText("");add.setText("");tel.setText("");email.setText("");note.setText("");
        }else {
            Toast.makeText(this, "You Should Enter Your Name", Toast.LENGTH_LONG).show();
        }
    }
}