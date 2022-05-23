package com.Sofiane.Projetreseau.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Sofiane.Projetreseau.entity.enumeration.Role;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private DetailsUtilisateurService detailsUtilisateurService;

	public SecurityConfiguration(DetailsUtilisateurService detailsUtilisateurService) {
		this.detailsUtilisateurService = detailsUtilisateurService;
	}

//AuthenticationManagerBuilder effectue l'authentification dans notre systeme
//Managerbuilder cree un object managerbean
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.authenticationProvider(getAuthenticationProvider());
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider daoAutenticationProvider = new DaoAuthenticationProvider();
		daoAutenticationProvider.setUserDetailsService(detailsUtilisateurService);
		daoAutenticationProvider.setPasswordEncoder(getPasswordEncoder());
		return daoAutenticationProvider;
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilterBefore(new JwtFilter(),UsernamePasswordAuthenticationFilter.class)
		.authorizeRequests()
		.antMatchers("/api").authenticated()
		.antMatchers("/**/admin/**").hasRole(Role.ADMIN.toString());
	}

}
