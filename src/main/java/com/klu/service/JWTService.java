package com.klu.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.klu.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

	String key="hthdheutklopedcvhtlykdlfgkdfgkdfkgd;fkgkdfkg";
	SecretKey skey=Keys.hmacShaKeyFor(key.getBytes());
	public String generateJWT(User u1) {
		Map<String,Object> claim=new HashMap<>();
		claim.put("id", u1.getId());
		claim.put("username", u1.getUsername());
		claim.put("role", u1.getRole());
		return Jwts.builder()
				.claims(claim)
				.issuedAt(new Date())
				.setExpiration(new Date(new Date().getTime()+86400000))
				.signWith(skey)
				.compact();
	}
	public Map<String, Object> validateJWT(String token) throws Exception{
		Claims claim=Jwts.parser()
		.verifyWith(skey)
		.build()
		.parseSignedClaims(token)
		.getPayload();
		if(claim==null||claim.getExpiration().before(new Date())) {
			throw new Exception("Token Failed");
		}
		Map<String, Object> parsedJWT=new HashMap<>();
		parsedJWT.put("id", claim.get("id"));
		parsedJWT.put("username", claim.get("username"));
		parsedJWT.put("role", claim.get("role"));
		return parsedJWT;
	}
}
