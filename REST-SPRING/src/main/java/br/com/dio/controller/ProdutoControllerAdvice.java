//Classe que serve para segurar os erros do produto;
//Ela é uma controller de erros;
package br.com.dio.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.dio.exception.ProductNewException;
import br.com.dio.exception.ProductPriceException;

@ControllerAdvice // Anotação paara indicar que essa classe irá tratar os erros da aplicação universalmente.
public class ProdutoControllerAdvice extends ResponseEntityExceptionHandler {
	
	//Anotação que indica que esse método serve para segurar uim erro específico
	@ExceptionHandler(ProductNewException.class)//Quando jogar essa excessão(ProductNewException), quem pega é a controller 
	public ResponseEntity<Object> capturaErroNull(){
		
		Map<String, Object>	body = new HashMap<String, Object>();//chamar um hash map para criar o body
		
		body.put("message", "verifique os campos do produto"); //aqui voce especifica a mensagem que aprecerá no body
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
		
	}
	
	@ExceptionHandler(ProductPriceException.class)
	public ResponseEntity<Object> capturaErroPreco(){
		
		Map<String, Object>	body = new HashMap<String, Object>();
		
		body.put("message", "verifique o preço do produto"); 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
		
	}
}
