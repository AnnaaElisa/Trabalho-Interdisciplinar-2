package model;

import java.sql.Timestamp;
import java.sql.Date;

public class Produto{
    private int id;
    private String descricao;
    private String preco;
    private int quantidade;
    private Timestamp dataFabricacao;
    private Date dataValidade;

    public Produto(int id, String descricao, String preco, int quantidade, Timestamp dataFabricacao, Date dataValidade) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.dataFabricacao = dataFabricacao;
        this.dataValidade = dataValidade;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Descrição: " + descricao + ", Preço: " + preco + ", Quantidade: " + quantidade + 
               ", Data de Fabricação: " + dataFabricacao + ", Data de Validade: " + dataValidade;
    }
}