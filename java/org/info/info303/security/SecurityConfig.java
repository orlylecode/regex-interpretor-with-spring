/*package org.info.info303.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication().withUser("admin").password("123").roles("ADMIN","USER");
		auth.inMemoryAuthentication().withUser("user").password("123").roles("USER");
		
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select username as principal,pass as credentials,active from users where username=?  ")
		.authoritiesByUsernameQuery("select users_username as principal, roles_role as role from users_roles where users_username=?")
		.rolePrefix("ROLE_")
		.passwordEncoder(new  Md5PasswordEncoder() );
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.formLogin().loginPage("/login");
		http.authorizeRequests().antMatchers("/cites","/batiments","/chambres").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/formEtudiantFormAjout").hasRole("ETUDIANT");
		http.exceptionHandling().accessDeniedPage("/403");
	//	http.exceptionHandling().accessDeniedPage("/404");
	}
	

}
*/