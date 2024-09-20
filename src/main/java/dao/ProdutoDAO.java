package dao;

import java.sql.*;
import model.Produto;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private final String url = "jdbc:postgresql://localhost:5432/estoque";
    private final String user = "usuario";
    private final String password = "senha";

    public void inserirProduto(String descricao, String preco, int quantidade, String dataFabricacao, String dataValidade) throws Exception {
        Connection conn = DriverManager.getConnection(url, user, password);
        String sql = "INSERT INTO produtos (descricao, preco, quantidade, data_fabricacao, data_validade) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, descricao);
        statement.setString(2, preco);
        statement.setInt(3, quantidade);
        statement.setTimestamp(4, Timestamp.valueOf(dataFabricacao.replace("T", " ") + ":00"));
        statement.setDate(5, Date.valueOf(dataValidade));
        statement.executeUpdate();
        conn.close();
    }


    public Produto detalharProduto(int id) throws Exception {
        Connection conn = DriverManager.getConnection(url, user, password);
        String sql = "SELECT * FROM produtos WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        Produto produto = null;
        
        if (rs.next()) {
            produto = new Produto(rs.getInt("id"), 
                                  rs.getString("descricao"), 
                                  rs.getString("preco"), 
                                  rs.getInt("quantidade"), 
                                  rs.getTimestamp("data_fabricacao"), 
                                  rs.getDate("data_validade"));
        }
        conn.close();
        return produto;
    }


    public boolean removerProduto(int id) throws Exception {
        Connection conn = DriverManager.getConnection(url, user, password);
        String sql = "DELETE FROM produtos WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        int rowsDeleted = statement.executeUpdate();
        conn.close();
        return rowsDeleted > 0;
    }

    public List<Produto> listarProdutos() throws Exception {
        Connection conn = DriverManager.getConnection(url, user, password);
        String sql = "SELECT * FROM produtos";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        List<Produto> produtos = new ArrayList<>();
        while (rs.next()) {
            Produto produto = new Produto(rs.getInt("id"), rs.getString("descricao"), rs.getString("preco"), rs.getInt("quantidade"), rs.getTimestamp("data_fabricacao"), rs.getDate("data_validade"));
            produtos.add(produto);
        }
        conn.close();
        return produtos;
    }
}
