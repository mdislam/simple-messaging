package com.dreambroker.assignment.configuration;

import com.dreambroker.assignment.authentication.DBAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
					.antMatchers("/register", "/authenticate", "/js/**", "/css/**", "/login/**")
						.permitAll()
					.anyRequest().authenticated()
					.and()
				.formLogin()
					.loginPage("/login")
					.loginProcessingUrl("/authenticate")
					.usernameParameter("username")
					.passwordParameter("password")
					.defaultSuccessUrl("/")
					.permitAll()
					.and()
				.logout()
					.logoutSuccessUrl("/login")
					.logoutUrl("/logout")
					.invalidateHttpSession(true)
					.permitAll();
	}

	@Configuration
	protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
		@Autowired
		DBAuthenticationProvider provider;

		@Override
		public void init(AuthenticationManagerBuilder builder) throws Exception {
			builder.authenticationProvider(provider);
		}

	}
}
