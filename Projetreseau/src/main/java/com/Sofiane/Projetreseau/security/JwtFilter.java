package com.Sofiane.Projetreseau.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jeton = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (jeton != null && !jeton.isEmpty()) {
			jeton = jeton.replace("Bearer ", "");
			try {
				DecodedJWT jetonDecoder = JWT.require(Algorithm.HMAC512(SecurityProperties.SECRET)).build()
						.verify(jeton);
				String nom = jetonDecoder.getSubject();
				String role = jetonDecoder.getClaim("role").asString();
				GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						nom, null, Collections.singletonList(grantedAuthority));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("jeton invalide");
			}
		}
		filterChain.doFilter(request, response);
	}

}
