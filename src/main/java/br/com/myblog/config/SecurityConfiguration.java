package br.com.myblog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.myblog.service.UsuarioService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	  @Override protected void configure(HttpSecurity http) throws Exception {
		  http.csrf().disable().authorizeRequests()
		  .antMatchers(HttpMethod.GET, "/").permitAll()
	  .antMatchers(HttpMethod.GET, "/posts").permitAll()
	  .antMatchers("/loginForm").permitAll()
	  .antMatchers(HttpMethod.GET, "/posts/{id}").permitAll()
	  .anyRequest().authenticated().and()
	  .formLogin().loginPage("/login").permitAll()
		.and().logout().invalidateHttpSession(true)
		.clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/logout-success").permitAll();
	  
	  }
	 
	@Autowired
	private UsuarioService service;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(service)
		.passwordEncoder(new BCryptPasswordEncoder());
	}

	
	  @Override 
	  public void configure(WebSecurity web) throws Exception {
		 
	  web.ignoring().antMatchers("/bootstrap/**"); }
	  

}
