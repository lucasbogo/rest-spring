package br.com.dio.config;

/* 
 * Basic Security
 * 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter { 


    @Override
    protected void configure(HttpSecurity http) throws Exception { // meptodo de configuração que recebe um http
    	 http.csrf().disable() // desabilitamos ataque csrf.
         .authorizeRequests().anyRequest().authenticated() // Qualquer requisição deve ser autenticada.
         .and()
         .httpBasic();
    }
    
    //Autorização configurada para poder receber a autenticação.
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication() //faz um autenticação em memória com o usuário fulanoDeTal...
		.withUser("lucas")
		.password("{noop}123456") //noop = não codificar a senha, apeans salvar em memória. Se tirar, será necessário configuração de criptografia para assegurar a senha.
		.roles("USER"); //Roles = permissões do usuário. Aqui, o mesmo terá permissão apenas de usuário; 
	} 
	
	End of basic security
	
	*/




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.dio.auth.service.UserService;
import br.com.dio.config.jwt.JwtAuthenticationEntryPoint;
import br.com.dio.config.jwt.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public UserService userService;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	//criptografia da senha
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.csrf().disable()
				.authorizeRequests().antMatchers("/auth/signin").permitAll() //Permite autenticações para todos 
				.antMatchers("/actuator/**").permitAll() // Permite realizar os teste com o Actuator
				.antMatchers("/produto/**").hasAnyRole("ADMIN") //Só o ADMIN poderá acessar o endpoint /produto
				.antMatchers("/pedido/**").authenticated()
				.anyRequest().authenticated().and()
				.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //stateless não registra estado de sessão

		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}


}
