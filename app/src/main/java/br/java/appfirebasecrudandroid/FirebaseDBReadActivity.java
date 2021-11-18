package br.java.appfirebasecrudandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import br.java.appfirebasecrudandroid.adapter.ProdutoAdapter;
import br.java.appfirebasecrudandroid.modelo.Produto;

public class FirebaseDBReadActivity extends AppCompatActivity implements ProdutoAdapter.FirebaseDataListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_dbread);
    }

    @Override
    public void onDeleteData(Produto produto, int position) {

    }

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, FirebaseDBReadSingleActivity.class);
    }
}