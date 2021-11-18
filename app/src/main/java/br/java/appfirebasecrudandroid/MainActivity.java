package br.java.appfirebasecrudandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btAnaliticos;
    private Button btAuth;
    private Button btFBDatabase;
    private Button btAdmob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btAnaliticos = findViewById(R.id.bt_fbanalytics);
        btAnaliticos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FirebaseAnalyticsActivity.class));
            }
        });

        btFBDatabase = findViewById(R.id.bt_fbdatabase);
        btFBDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FirebaseDBActivity.class));
            }
        });

        btAuth = findViewById(R.id.bt_fbauth);
        btAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FirebaseAuthActivity.class));
            }
        });

        btAdmob = findViewById(R.id.bt_fbadmob);
        btAdmob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FirebaseAdMobActivity.class));
            }
        });
    }
}