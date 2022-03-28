package br.com.dio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dio.entity.Produto;
import br.com.dio.service.ProdutoService;

@RestController // Anotação REST.
@RequestMapping(value = "/produto") // qualquer barra produto, seja para salvar, editar e excluir.
public class ProdutoController {

	@Autowired
	private ProdutoService service; // injeção do ProdutoService, é necessário a anotação Autowired

	@PostMapping(value = "/save") // A anoteção ja sabe que que deve 
	public ResponseEntity<Produto> salvaProduto(@RequestBody Produto produto) throws Exception { // @RquestBody é necessário para passar a requisição ao corpo

		produto = service.save(produto); //chamar o service e salvar o produto
		
		return ResponseEntity.ok().body(produto);	

	

	}

	//buscar produto por id
	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> buscaProduto(@PathVariable Long id) { // response entity serve para retornar o produto

		Produto produto = service.findById(id); // produto recebe produto 

		return ResponseEntity.ok().body(produto); // volta produto no corpo da requisição

	}

	//buscar todos os produtos; retorna uma lista
	@GetMapping(value = "/busca-todos") //mesmo método que o busca por id mas com endpoints diferentes
	public ResponseEntity<List<Produto>> buscaTodosProdutos() {

		List<Produto> produtos = service.findAll();

		return ResponseEntity.ok().body(produtos);

	}

}
