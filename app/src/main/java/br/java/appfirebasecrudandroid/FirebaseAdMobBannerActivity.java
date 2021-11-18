package br.java.appfirebasecrudandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class FirebaseAdMobBannerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_ad_mob_banner);


    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, FirebaseAdMobInterstitialActivity.class);
    }
}