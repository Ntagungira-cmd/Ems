package com.stormtech.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder encoder;

	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder encoder) {
		this.encoder = encoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/", "/index", "/css/*", "/js/*")
				.permitAll()
				.antMatchers("/api/**")
				.hasRole(ApplicationUserRoles.STUDENT.name())
				.anyRequest()
				.authenticated()
				.and()
				.httpBasic();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails user = User.builder()
				.username("ntagungira")
				.password(encoder.encode("pswd"))
				.roles(ApplicationUserRoles.STUDENT.name())
				.build();

		UserDetails adminUser = User.builder()
				.username("ali")
				.password(encoder.encode("pswd"))
				.roles(ApplicationUserRoles.ADMIN.name())
				.build();

		return new InMemoryUserDetailsManager(user, adminUser);
	}

}
