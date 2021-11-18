package br.java.appfirebasecrudandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirebaseAdMobActivity extends AppCompatActivity {

    Button btBanner;
    Button btIntersticiais;
    Button btNativo;
    Button btVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_ad_mob);

        btBanner = findViewById(R.id.bt_adbanner);
        btBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(FirebaseAdMobBannerActivity.getActIntent(FirebaseAdMobActivity.this));
            }
        });

        btIntersticiais = findViewById(R.id.bt_adinterstitial);
        btIntersticiais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(FirebaseAdMobInterstitialActivity.getActIntent(FirebaseAdMobActivity.this));
            }
        });

        btNativo = findViewById(R.id.bt_adnative);
        btNativo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(FirebaseAdMobNativeActivity.getActIntent(FirebaseAdMobActivity.this));
            }
        });

        btVideo = findViewById(R.id.bt_advideo);
        btVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(FirebaseAdMobVideoActivity.getActIntent(FirebaseAdMobActivity.this));
            }
        });
    }
}