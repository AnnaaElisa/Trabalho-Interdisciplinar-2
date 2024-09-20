package app;

import static spark.Spark.*;
import service.ProdutoService;

public class Aplicacao {

	private static ProdutoService produtoService = new ProdutoService();

    public static void main(String[] args) {
    	 port(4567);
         
         staticFiles.location("/public");
         
         get("/formulario", (req, res) -> {
             res.redirect("/formulario.html");
             return null;
         });
         
         post("/produto/insert", (request, response) -> produtoService.insert(request, response));

         get("/produto/:id", (request, response) -> produtoService.get(request, response));

    }
}
