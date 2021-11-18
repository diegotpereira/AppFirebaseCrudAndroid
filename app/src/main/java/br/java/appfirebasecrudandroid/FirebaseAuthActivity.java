package br.java.appfirebasecrudandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthActivity extends AppCompatActivity {

    private Button btCadastrar;
    private Button btEntrar;
    private Button btSair;

    private EditText etEmail;
    private EditText etSenha;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener fStateListener;

    private static final String TAG = FirebaseAuthActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_auth);

        mAuth = FirebaseAuth.getInstance();

        fStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser usuario = firebaseAuth.getCurrentUser();

                if (usuario != null) {
                    Log.v(TAG, "onAuthStateChanged:signed_in:" + usuario.getUid());
                }
            }
        }
    }
}