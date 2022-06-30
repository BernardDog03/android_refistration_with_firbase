package com.example.project_akhir;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;
    Button sign_out, HomeSide;
    TextView nameTV, emailTV, namahome;
    ImageView photoIV;
    private LinearLayout layoutside = null;
    private LinearLayout layoutKelas = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        sign_out = findViewById(R.id.log_out);
        nameTV = findViewById(R.id.name);
        emailTV = findViewById(R.id.email);
        namahome = findViewById(R.id.hello);
        photoIV = findViewById(R.id.photo);
        layoutside = (LinearLayout) findViewById(R.id.sidedrawwer);
        layoutKelas = (LinearLayout) findViewById(R.id.kelasopsi);
        HomeSide = (Button) findViewById(R.id.homeside);

        HomeSide.setBackgroundColor(getColor(R.color.biru3));
        HomeSide.setTextColor(getColor(R.color.colorPrimaryDark));

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
        findViewById(R.id.basicbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), basic.class));
            }
        });
        findViewById(R.id.child).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), basic.class));
            }
        });
        findViewById(R.id.teen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), greeting.class));
            }
        });
        findViewById(R.id.greetbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), greeting.class));
            }
        });
        findViewById(R.id.adult).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), expert.class));
            }
        });
        findViewById(R.id.expertbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), expert.class));
            }
        });
        findViewById(R.id.convbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), conver.class));
            }
        });
        findViewById(R.id.conversation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), conver.class));
            }
        });
        findViewById(R.id.aboutUs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), about.class));
            }
        });
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(MainActivity2.this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String persongivenname = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            nameTV.setText(personName);
            emailTV.setText(personEmail);
            namahome.setText(persongivenname);
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
                        Toast.makeText(MainActivity2.this,"Successfully signed out",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity2.this, MainActivity.class));
                        finish();
                    }
                });
    }

}