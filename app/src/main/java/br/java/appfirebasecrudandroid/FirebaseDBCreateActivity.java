package br.java.appfirebasecrudandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class FirebaseDBCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_dbcreate);
    }

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, FirebaseDBReadSingleActivity.class);
    }
}