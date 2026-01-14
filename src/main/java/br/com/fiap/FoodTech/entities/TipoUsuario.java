package br.com.fiap.FoodTech.entities;

public enum TipoUsuario {

    CLIENTE("CLIENTE"), DONO("DONO"),;

    private String descricao;

    TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}