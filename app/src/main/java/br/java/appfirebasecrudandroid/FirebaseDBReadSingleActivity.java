package br.java.appfirebasecrudandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.java.appfirebasecrudandroid.modelo.Produto;

public class FirebaseDBReadSingleActivity extends AppCompatActivity {

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

        etNome.setEnabled(false);
        etMarca.setEnabled(false);
        etPreco.setEnabled(false);
        btEnviar.setVisibility(View.GONE);

        Produto produto = (Produto) getIntent().getSerializableExtra("dado");

        if (produto != null) {
            etNome.setText(produto.getNome());
            etMarca.setText(produto.getMarca());
            etPreco.setText(produto.getPreco());
        }
    }

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, FirebaseDBReadSingleActivity.class);
    }
}