package br.com.dio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.dio.entity.Produto;
import br.com.dio.exception.ProductPriceException;
import br.com.dio.service.ProdutoService;

@SpringBootTest
public class ProdutoTest {
	
	@Autowired
	private ProdutoService produtoService;
	
	@Test //nome deve ser longo paraa explicar bem o teste.
	public void verificaValorNegativoNoProduto() throws Exception { 
		Produto produto = new Produto();
		
		produto.setNome("teste");
		produto.setPreco(-10.0);
		
	assertThrows(ProductPriceException.class,() -> produtoService.save(produto)); // () -> serve p/ indicar que o código deve ser executado.	

		assertNull(produto.getId());
		assertEquals(produto.getId(), 1);
	}

	/* exemplo de método para teste.
	@Test
	public void TesteFalso() {
		
		assertTrue(true);
	}*/
}
