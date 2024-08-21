package rjr.studio.passgate.conf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;
	private CacheControlFilter cacheControlFilter;
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public SecurityConfig(UserDetailsService userDetailsService, CacheControlFilter cacheControlFilter,
			JwtAuthenticationFilter jwtAuthenticationFilter, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.cacheControlFilter = cacheControlFilter;
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		try {
		//@formatter:off
		http.csrf().disable()
			.authorizeRequests()
				.antMatchers("/test/check/all").permitAll()
				.antMatchers("/login/match").permitAll()
				.antMatchers("/test/check/login").authenticated()
				.antMatchers("/test/check/roles").hasAnyRole("SYSTEM", "ADMIN")
				.anyRequest().authenticated()
				.and()
			.addFilterBefore(cacheControlFilter, UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		//@formatter:on
		} catch (Exception e) {
			System.out.println("***************** ERRORE DA MODIFICARE *****************");
			System.out.println(e.getMessage());
		}
	}

}