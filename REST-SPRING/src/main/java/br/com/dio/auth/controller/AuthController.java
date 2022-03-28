//classe controladora do login
package br.com.dio.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dio.auth.service.UserService;
import br.com.dio.config.jwt.JwtRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody JwtRequest request) {
		return userService.signin(request);

		/*manda um JwtRequest com usuário e senha, 
		 *ver classe JwtRequest no pacote .config.jwt
		 *
		 *Se estiver tudo certo, retorna um userService
		 *que, por usa vez, faz a autenticação e o uso de tokens
		*/
	}

}