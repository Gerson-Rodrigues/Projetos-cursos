package com.projetos.cursos.security;
 
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/*Método utilizado para gerar o Token */
public class TokenSecurity {
	
	public static String generateToken(String login) {
		//Chave secreta para geração do Token
		//(Evitar Falsificações)
		String secretKey = JwtSecurity.SECRET;
		
		List<GrantedAuthority> grantedAuthorityList = 
				AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		
	/*GERSON_JWT -> nome da aplicação de gerou pro token!!!!
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+ 6000000))
	*/
		String token = Jwts.builder()
				.setId("GERSON_JWT")
				.setSubject(login)
				.claim("authorities", grantedAuthorityList.stream()
						.map(GrantedAuthority::getAuthority)
						.collect(Collectors.toList()))
				.signWith(SignatureAlgorithm.HS512,secretKey.getBytes()).compact();
		
		return token;
	}
	
	//Método ppara ler o login do usuario (ADM ou cliente) gravado no Token 
	public static String getLoginFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	private static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		String secretKey = JwtSecurity.SECRET;
		final Claims claims = Jwts.parser().setSigningKey(secretKey.getBytes())
				.parseClaimsJws(token).getBody();
		return claimsResolver.apply(claims);
	}

}