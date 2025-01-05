package com.jwt;
 
import java.util.List;
 
import com.model.Role;
 
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
 
public class TokenGenerator {
	private String token;
	private static final String SECRETKEY="##XXXAACCCR#123###AAA";
	public TokenGenerator() {
	}
 
public void generateToken(String username, String password, String roles) {
		this.token=Jwts.builder()
				.claim("username", username)
				.claim("password", password)
				.claim("roles", roles)
				.signWith(SignatureAlgorithm.HS256, SECRETKEY)
				.compact();
	}
 
	public String getToken() {
		return token;
	}
 
	public void setToken(String token) {
		this.token = token;
	}
 
	public static String getSecretkey() {
		return SECRETKEY;
	}
 
	public static boolean validate(String token) {
		  try {
		Jws<Claims> claims=Jwts.parser().setSigningKey(SECRETKEY).parseClaimsJws(token);
	  return true;
		  }
		  catch(JwtException e) {
			   return false;
		  }
	}
 
 
}
 