package br.java.appfirebasecrudandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.java.appfirebasecrudandroid.modelo.Produto;

public class FirebaseDBCreateActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;

    private Button btEnviar;
    private EditText etNome;
    private EditText etMarca;
    private EditText etPreco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_dbcreate);

        etNome = (EditText) findViewById(R.id.et_nomeproduto);
        etMarca = (EditText) findViewById(R.id.et_marca_produto);
        etPreco = (EditText) findViewById(R.id.et_precoproduto);
        btEnviar = (Button) findViewById(R.id.bt_enviar);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        final Produto produto = (Produto) getIntent().getSerializableExtra("dado");

        if (produto != null) {
            etNome.setText(produto.getNome());
            etMarca.setText(produto.getMarca());
            etPreco.setText(produto.getPreco());

            btEnviar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    produto.setNome(etNome.getText().toString());
                    produto.setMarca(etMarca.getText().toString());
                    produto.setPreco(etPreco.getText().toString());

                    atualizarProduto(produto);
                }
            });
        } else {
            btEnviar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!estaVazio(etNome.getText().toString()) && !estaVazio(etMarca.getText().toString()) && !estaVazio(etPreco.getText().toString()))
                        enviarProduto(new Produto(etNome.getText().toString(), etMarca.getText().toString(), etPreco.getText().toString()));

                    else
                        Snackbar.make(findViewById(R.id.bt_enviar), "Os dados do item não podem estar vazios", Snackbar.LENGTH_LONG).show();

                    InputMethodManager inputMethodManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(etNome.getWindowToken(), 0);
                }
            });
        }
    }

    private boolean estaVazio(String s) {
        return TextUtils.isEmpty(s);
    }

    private void atualizarProduto(Produto produto) {
        databaseReference.child("produto")
                .child(produto.getKey())
                .setValue(produto)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Snackbar.make(findViewById(R.id.bt_enviar), "Dados atualizados com sucesso", Snackbar.LENGTH_LONG).setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        }).show();
                    }
                });
    }

    private void enviarProduto(Produto produto) {
        databaseReference.child("produto").push().setValue(produto).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                etNome.setText("");
                etMarca.setText("");
                etPreco.setText("");

                Snackbar.make(findViewById(R.id.bt_enviar), "Dados adicionado com sucesso", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, FirebaseDBCreateActivity.class);
    }
}