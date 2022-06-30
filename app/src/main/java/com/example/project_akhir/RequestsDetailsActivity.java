package com.example.project_akhir;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class RequestsDetailsActivity extends AppCompatActivity {
    private Button update;
    private Button delete;
    private String key;
    private String fn, ln, gender, tob, dob, add, tel, email, locate, day, time, note, major;
    private EditText afn, aln, amajor, atob, adob, aadd, atel, aemail, anote;
    private Spinner agender, alokasi, ahari, atime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests_details);
        key = getIntent().getStringExtra("key");
        fn = getIntent().getStringExtra("vfn");
        ln = getIntent().getStringExtra("vln");
        gender = getIntent().getStringExtra("vgende");
        tob = getIntent().getStringExtra("vtob");
        dob = getIntent().getStringExtra("vdob");
        add = getIntent().getStringExtra("vadd");
        tel = getIntent().getStringExtra("vtel");
        email = getIntent().getStringExtra("vemail");
        locate = getIntent().getStringExtra("vlocate");
        day = getIntent().getStringExtra("vday");
        time = getIntent().getStringExtra("vtime");
        note = getIntent().getStringExtra("vnote");
        major = getIntent().getStringExtra("vmajor");
        update = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);

        afn = (EditText) findViewById(R.id.firstnamaedt);
        afn.setText(fn);
        aln = (EditText) findViewById(R.id.lastnamaedt);
        aln.setText(ln);
        amajor = (EditText) findViewById(R.id.provedt);
        amajor.setText(major);
        atob = (EditText) findViewById(R.id.tempatlhredt);
        atob.setText(tob);
        adob = (EditText) findViewById(R.id.dateOBedt);
        adob.setText(dob);
        aadd = (EditText) findViewById(R.id.alamatedt);
        aadd.setText(add);
        atel = (EditText) findViewById(R.id.telpedt);
        atel.setText(tel);
        aemail = (EditText) findViewById(R.id.emailedt);
        aemail.setText(email);
        anote = (EditText) findViewById(R.id.notedt);
        anote.setText(note);
        agender = (Spinner) findViewById(R.id.genderedt);
        agender.setSelected(Boolean.parseBoolean(gender));
        ahari = (Spinner) findViewById(R.id.dayedt);
        ahari.setSelected(Boolean.parseBoolean(day));
        alokasi = (Spinner) findViewById(R.id.locationedt);
        alokasi.setSelected(Boolean.parseBoolean(locate));
        atime = (Spinner) findViewById(R.id.jamedt);
        atime.setSelected(Boolean.parseBoolean(time));

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Requests requests = new Requests();
                requests.setFirstname(afn.getText().toString());
                requests.setLastname(aln.getText().toString());
                requests.setMajor(amajor.getText().toString());
                requests.setTempatlahir(atob.getText().toString());
                requests.setDateOB(adob.getText().toString());
                requests.setAddress(aadd.getText().toString());
                requests.setPhoneno(atel.getText().toString());
                requests.setEmail(aemail.getText().toString());
                requests.setNote(anote.getText().toString());
                requests.setGender(agender.getSelectedItem().toString());
                requests.setDay(ahari.getSelectedItem().toString());
                requests.setLocate(alokasi.getSelectedItem().toString());
                requests.setTime(atime.getSelectedItem().toString());

                new FirebaseDatabaseHelper().updateBook(key, requests, new
                        FirebaseDatabaseHelper.DataStatus() {
                            @Override
                            public void dataIsLoad(List<Requests> requests, List<String> keys) {
                            }
                            @Override
                            public void dataIsUpdate() {
                                Toast.makeText(RequestsDetailsActivity.this,"Data Update",Toast.LENGTH_LONG).show();
                            }
                            @Override
                            public void dataIsDeleted() {
                            }
                        });
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RequestsDetailsActivity.this,"Data Delete",Toast.LENGTH_LONG).show();
                new FirebaseDatabaseHelper().deleteBook(key, new
                        FirebaseDatabaseHelper.DataStatus() {
                            @Override
                            public void dataIsLoad(List<Requests> requests, List<String> keys) {
                            }
                            @Override
                            public void dataIsUpdate() {
                            }
                            @Override
                            public void dataIsDeleted() {
                                Toast.makeText(RequestsDetailsActivity.this,"Data Delete",Toast.LENGTH_LONG).show();
                                        finish(); return;
                            }
                        });
            }
        });
    }
}
