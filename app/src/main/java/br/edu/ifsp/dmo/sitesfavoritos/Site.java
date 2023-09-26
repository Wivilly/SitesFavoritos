package br.edu.ifsp.dmo.sitesfavoritos;

public class Site {

    private String apelido;
    private String url;
    private boolean favorito;

    public Site(String apelido, String url){
        this.apelido = apelido;
        this.url = url;
    }

    public String getApelido(){
        return apelido;
    }

    public void setApelido(String apelido){
        this.apelido = apelido;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }
}
