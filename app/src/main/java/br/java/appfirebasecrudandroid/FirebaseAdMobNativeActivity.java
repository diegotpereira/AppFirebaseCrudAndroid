package br.java.appfirebasecrudandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class FirebaseAdMobNativeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_ad_mob_native);
    }
    public static Intent getActIntent(Activity activity) {

        return new Intent(activity, FirebaseAdMobNativeActivity.class);
    }
}