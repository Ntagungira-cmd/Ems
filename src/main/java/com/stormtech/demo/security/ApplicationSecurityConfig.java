package com.stormtech.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder encoder;

	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder encoder) {
		this.encoder = encoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http

				//.csrf().disable()
				.authorizeRequests()
				.antMatchers("/","/hello", "/index", "/css/*", "/js/*")
				.permitAll()
				.antMatchers("/api/**")
				.hasRole(ApplicationUserRoles.STUDENT.name())
//				.antMatchers(HttpMethod.POST, "/management/api/**")
//				.hasAuthority(ApplicationUserPermissions.COURSE_WRITE.getPermission())
//				.antMatchers(HttpMethod.PUT, "/management/api/**")
//				.hasAuthority(ApplicationUserPermissions.COURSE_WRITE.getPermission())
//				.antMatchers(HttpMethod.DELETE, "/management/api/**")
//				.hasAuthority(ApplicationUserPermissions.COURSE_WRITE.getPermission())
//				.antMatchers(HttpMethod.GET, "/management/api/**")
//				.hasAnyRole(ApplicationUserRoles.ADMIN.name(),
//						ApplicationUserRoles.ADMINTRAINEE.name())
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
//				.roles(ApplicationUserRoles.STUDENT.name())
				.authorities(ApplicationUserRoles.STUDENT.getGrantedAuthorities())
				.build();

		UserDetails adminUser = User.builder()
				.username("ali")
				.password(encoder.encode("pswd"))
				//.roles(ApplicationUserRoles.ADMIN.name())
				.authorities(ApplicationUserRoles.ADMIN.getGrantedAuthorities())
				.build();

		UserDetails trainee = User.builder()
				.username("tom")
				.password(encoder.encode("pswd"))
				//.roles(ApplicationUserRoles.ADMINTRAINEE.name())
				.authorities(ApplicationUserRoles.ADMINTRAINEE.getGrantedAuthorities())
				.build();

		return new InMemoryUserDetailsManager(user, adminUser, trainee);
	}

}
