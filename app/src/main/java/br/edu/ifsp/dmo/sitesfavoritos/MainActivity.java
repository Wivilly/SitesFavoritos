package br.edu.ifsp.dmo.sitesfavoritos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SiteItemClick{

    private List<Site> dataSource;
    private FloatingActionButton actionButton;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSource = new ArrayList<>();

        actionButton = findViewById(R.id.btn_adicionar);
        actionButton.setOnClickListener(v -> novoSite());

        recyclerView = findViewById(R.id.recycleview);
        SiteAdapter adapter = new SiteAdapter(this, dataSource, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void novoSite(){
        LayoutInflater inflater = getLayoutInflater();
        View tela = inflater.inflate(R.layout.novo_site,null);
        EditText apelidoEditText = tela.findViewById(R.id.editText_apelido);
        EditText urlEditText = tela.findViewById(R.id.editText_url);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(tela).setTitle(R.string.novo_site).setPositiveButton(R.string.salvar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dataSource.add(new Site(apelidoEditText.getText().toString(), urlEditText.getText().toString()));
                dialog.dismiss();
            }
        }).setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void clickSiteItem(int position){
        Site site = dataSource.get(position);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        startActivity(intent);
    }

    @Override
    public void clickCoracaoSiteItem(int position){
        Site site = dataSource.get(position);
        site.setFavorito(!site.isFavorito());
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void clickExcluirSiteItem(int position){
        dataSource.remove(position);
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}