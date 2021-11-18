package br.java.appfirebasecrudandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirebaseDBActivity extends AppCompatActivity {

    private Button btCriarDB;
    private Button btExibirDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_dbactivity);

        btCriarDB = (Button) findViewById(R.id.bt_createdata);
        btExibirDB = (Button) findViewById(R.id.bt_viewdata);

        btCriarDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(FirebaseDBCreateActivity.getActIntent(FirebaseDBActivity.this));
            }
        });

        btExibirDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(FirebaseDBReadActivity.getActIntent(FirebaseDBActivity.this));
            }
        });
    }
}