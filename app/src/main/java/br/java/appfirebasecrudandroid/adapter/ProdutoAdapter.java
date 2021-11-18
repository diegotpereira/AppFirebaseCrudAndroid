package br.java.appfirebasecrudandroid.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.java.appfirebasecrudandroid.FirebaseDBCreateActivity;
import br.java.appfirebasecrudandroid.FirebaseDBReadActivity;
import br.java.appfirebasecrudandroid.FirebaseDBReadSingleActivity;
import br.java.appfirebasecrudandroid.R;
import br.java.appfirebasecrudandroid.modelo.Produto;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ViewHolder> {

    private ArrayList<Produto> listaItens;
    private Context context;
    FirebaseDataListener listener;

    public ProdutoAdapter(ArrayList<Produto> produtos, Context ctx) {
        listaItens = produtos;
        context = ctx;
        listener = (FirebaseDBReadActivity) ctx;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitulo;
        CardView cvMain;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = (TextView) itemView.findViewById(R.id.tv_nomeproduto);
            cvMain = (CardView) itemView.findViewById(R.id.cv_principal);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final String nome = listaItens.get(position).getNome();
        System.out.println("Dados Produto um por um" + position + listaItens.size());
        holder.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(FirebaseDBReadSingleActivity.getActIntent((Activity) context).putExtra("dado", listaItens.get(position)));

            }
        });
        holder.cvMain.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialogo_view);
                dialog.setTitle("Escolher ação");
                dialog.show();

                Button editButton = (Button) dialog.findViewById(R.id.bt_edit_dado);
                Button delButton = (Button) dialog.findViewById(R.id.bt_deletar_dado);

                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        context.startActivity(FirebaseDBCreateActivity.getActIntent((Activity) context).putExtra("dado", listaItens.get(position)));
                    }
                });

                delButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        listener.onDeleteData(listaItens.get(position), position);
                    }
                });
                return true;
            }
        });
        holder.tvTitulo.setText(nome);
    }

    @Override
    public int getItemCount() {
        return listaItens.size();
    }

    public interface FirebaseDataListener {
        void onDeleteData(Produto produto, int position);
    }
}
