package service;

import dao.ProdutoDAO;
import model.Produto;
import spark.Request;
import spark.Response;

public class ProdutoService {

    private ProdutoDAO produtoDAO;

    public ProdutoService() {
        this.produtoDAO = new ProdutoDAO();
    }

    public String insert(Request req, Response res) {
        String descricao = req.queryParams("descricao");
        String precoStr = req.queryParams("preco");
        String quantidadeStr = req.queryParams("quantidade");
        String dataFabricacao = req.queryParams("data_fabricacao");
        String dataValidade = req.queryParams("data_validade");

        try {
        	String preco = precoStr;

            int quantidade = Integer.parseInt(quantidadeStr);

            produtoDAO.inserirProduto(descricao, preco, quantidade, dataFabricacao, dataValidade);

        } catch (Exception e) {
            e.printStackTrace();
            res.status(500);
            return "Erro ao cadastrar produto.";
        }

        return "Produto cadastrado com sucesso!";
    }

    public String get(Request req, Response res) {
        String id = req.params(":id");
        Produto produto;

        try {
            produto = produtoDAO.detalharProduto(Integer.parseInt(id));

            if (produto != null) {
                return produto.toString();
            } else {
                res.status(404);
                return "Produto não encontrado.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            res.status(500);
            return "Erro ao buscar produto.";
        }
    }
}
