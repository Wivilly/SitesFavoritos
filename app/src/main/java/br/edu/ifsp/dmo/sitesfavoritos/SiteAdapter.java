package br.edu.ifsp.dmo.sitesfavoritos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class SiteAdapter extends RecyclerView.Adapter<SiteAdapter.ViewHolder> {
    private Context context;
    private List<Site> dataset;
    private SiteItemClick listener;

    public SiteAdapter(Context context, List<Site> dataset, SiteItemClick listener){
        this.context = context;
        this.dataset = dataset;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.site_item, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        Site site = dataset.get(position);
        holder.apelido.setText(site.getApelido());
        holder.url.setText(site.getUrl());
        if(site.isFavorito()){
            holder.coracao.setColorFilter(context.getColor(R.color.red));
        }else {
            holder.coracao.setColorFilter(context.getColor(R.color.gray));
        }

        holder.coracao.setOnClickListener(v -> listener.clickCoracaoSiteItem(position));
        holder.itemView.setOnClickListener(v -> listener.clickSiteItem(position));
        holder.excluir.setOnClickListener(v -> listener.clickExcluirSiteItem(position));
    }

    @Override
    public int getItemCount(){
        return dataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView apelido;
        public TextView url;
        public ImageView coracao;
        public ImageView excluir;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            apelido = itemView.findViewById(R.id.textview_apelido);
            url = itemView.findViewById(R.id.textview_url);
            coracao = itemView.findViewById(R.id.img_coracao);
            excluir = itemView.findViewById(R.id.img_excluir);
        }
    }
}
