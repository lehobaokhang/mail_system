package com.miroslav.mailsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.miroslav.mailsystem.service.UserService;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder);
         
        return authProvider;
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/css/**", "/icons/**", "/images/**", "/js/**", "/pages/**", "/plugins/**", "/uploads/**").permitAll()
			.antMatchers("/register", "/registerProcessing").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/login")
					.loginProcessingUrl("/authenticate")
					.defaultSuccessUrl("/")
						.usernameParameter("username")
						.passwordParameter("password")
						.permitAll()
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.invalidateHttpSession(true)
			.clearAuthentication(true)
			.permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
	}
}
