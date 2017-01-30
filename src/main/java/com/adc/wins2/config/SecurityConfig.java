package com.adc.wins2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("wins2LogoutHandler")
	private LogoutHandler logoutHandler;

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
		web
				.ignoring()
					.antMatchers("/lib/**", "/css/**", "/js/**")
					.antMatchers("/temp/**")
		;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
					.antMatchers(
							"/index", "/", "/login", "/logout"
							, "/js/**", "/lib/**", "/css/**"
					).permitAll()
					.anyRequest().authenticated()
				.and()
				.csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.and()
				//.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
				.logout()
					.logoutSuccessUrl("/")
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.addLogoutHandler(logoutHandler)
				.and()
		;
	}

}
