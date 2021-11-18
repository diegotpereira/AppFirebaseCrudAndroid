package br.java.appfirebasecrudandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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
                    
                } else {
                    Toast.makeText(FirebaseAuthActivity.this, "Usuário Saiu\n", Toast.LENGTH_SHORT).show();

                    btSair.setEnabled(false);
                    Log.v(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        btCadastrar = (Button) findViewById(R.id.bt_signup);
        btEntrar = (Button) findViewById(R.id.bt_signin);
        btSair = (Button) findViewById(R.id.bt_signout);

        etEmail = (EditText) findViewById(R.id.et_email);
        etSenha = (EditText) findViewById(R.id.et_password);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrarSe(etEmail.getText().toString(), etSenha.getText().toString());
            }
        });

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                entrar(etEmail.getText().toString(), etSenha.getText().toString());
            }
        });

        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sair();
            }
        });

        verificarLogin();
    }

    private void cadastrarSe(final String email, String senha) {
        mAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                if (!task.isSuccessful()) {
                    task.getException().printStackTrace();

                    Toast.makeText(FirebaseAuthActivity.this, "Falha no processo de registro", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(FirebaseAuthActivity.this, "Processo de registro bem-sucedido\n" + "Email " + email, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void entrar(final String email, String senha) {
        mAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                if (!task.isSuccessful()) {
                    Log.w(TAG, "signInWithEmail:failed", task.getException());
                    Toast.makeText(FirebaseAuthActivity.this, "Falha no processo de login\n",
                            Toast.LENGTH_SHORT).show();
                } else {
                    btSair.setEnabled(true);

                    Toast.makeText(FirebaseAuthActivity.this, "Login bem sucedido\n" +
                                    "Email "+email,
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void sair() {
        mAuth.signOut();
    }

    private void verificarLogin() {
        FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
        
        if (usuario != null) {
            String email = usuario.getEmail();
            String uid = usuario.getUid();

            Toast.makeText(FirebaseAuthActivity.this, "Entrar como\n" + email + " " + uid, Toast.LENGTH_SHORT).show();
            btSair.setEnabled(true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(fStateListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAuth.addAuthStateListener(fStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (fStateListener != null) {

            mAuth.removeAuthStateListener(fStateListener);
        }
    }
}