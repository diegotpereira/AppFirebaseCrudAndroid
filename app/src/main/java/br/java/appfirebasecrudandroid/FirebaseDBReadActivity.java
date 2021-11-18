package br.java.appfirebasecrudandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.java.appfirebasecrudandroid.adapter.ProdutoAdapter;
import br.java.appfirebasecrudandroid.modelo.Produto;

public class FirebaseDBReadActivity extends AppCompatActivity implements ProdutoAdapter.FirebaseDataListener {

    private DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Produto> listaProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_dbread);

        recyclerView = (RecyclerView) findViewById(R.id.rv_principal);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("produto").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaProdutos = new ArrayList<>();

                for(DataSnapshot noteDataSnapshot : snapshot.getChildren()) {
                    Produto produto = noteDataSnapshot.getValue(Produto.class);
                    produto.setKey(noteDataSnapshot.getKey());

                    listaProdutos.add(produto);
                }

                adapter = new ProdutoAdapter(listaProdutos, FirebaseDBReadActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error.getDetails() + " " + error.getMessage());
            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, FirebaseDBReadActivity.class);
    }

    @Override
    public void onDeleteData(Produto produto, int position) {

        if (databaseReference != null) {
            databaseReference.child("produto").child(produto.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                    Toast.makeText(FirebaseDBReadActivity.this, "sucesso ao deletar", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}