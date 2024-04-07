package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService);
		//test1
	    // 設置角色定義
		auth.inMemoryAuthentication().withUser("admin")
//                .password("{noop}123456")
                .password("{noop}123456")//{MD5}MD5的編碼結果
                .roles("ADMIN", "USER") // 擁有ADMIN 與 USER角色
                .and()
                .withUser("user")
                .password("{noop}123")
                .roles("USER");// 擁有USER角色
	}
//test2
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		  http
//          .authorizeRequests() // 定義哪些url需要被保護
////          .antMatchers(HttpMethod.GET,"/app/index").permitAll()  // 定義匹配到"/app/index" 不需要驗證
//          .antMatchers(HttpMethod.GET,"/app/index").permitAll()  // 定義匹配到"/" 不需要驗證
//          .antMatchers("/swagger-ui/**").permitAll() // 匹配到"/swagger-ui.html", 不需要身份驗證
//          .anyRequest().authenticated() // 其他尚未匹配到的url都需要身份驗證
//          .and()
//          .formLogin()
//          .and()
//          .httpBasic();	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests() // 定義哪些url需要被保護 
		.antMatchers("/open/**").permitAll() 
		.antMatchers("/web/**").authenticated()
		.antMatchers("/app/**").authenticated()
//				.antMatchers("/api/**").authenticated()
//		.antMatchers("/app/**").permitAll()
		.antMatchers("/register").permitAll()
		.antMatchers(HttpMethod.GET).permitAll()  // 定義匹配到"/" 不需要驗證
		.antMatchers("/h2-console/**").permitAll() 
		.and().csrf().ignoringAntMatchers("/h2-console/**")
		.and().headers().frameOptions().sameOrigin()
		.and()
		.csrf().disable()
		.formLogin();	
		}

		@Bean
		public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
		}

}
