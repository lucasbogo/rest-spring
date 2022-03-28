package br.com.dio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dio.entity.Produto;
import br.com.dio.exception.ProductNewException;
import br.com.dio.exception.ProductPriceException;
import br.com.dio.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	public Produto save(Produto produto) throws Exception {
		
		//tratando exceão, não deixar mostar nome nulo;
		if(produto.getNome() == null || produto.getPreco() == null)//se o nome e o preço do produto for nulo,
			throw new ProductNewException(); // lançar mensagem de erro.
		
		if(produto.getPreco()<= 0) // se o preço for menor ou igual a zero,
			throw new ProductPriceException(); // lançar mensagem de erro.
			
			return repository.save(produto);
	}

	public Produto findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public List<Produto> findAll() {
		return repository.findAll();
	}
}
