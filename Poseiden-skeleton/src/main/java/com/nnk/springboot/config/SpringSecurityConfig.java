package com.nnk.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nnk.springboot.service.MyUserDetailsService;

/**
 * Class to configure Spring Security for for Trading App UI (Poseidon inc)
 *
 * @author PUYJALON Pierre
 * @since 25/06/2023
 */
@Configuration @EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return new MyUserDetailsService();
  }

  @Override
  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
      throws Exception {
    authenticationManagerBuilder.userDetailsService(userDetailsService())
        .passwordEncoder(passwordEncoder());
  }

  // ************************************************************************

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf().disable().authorizeRequests().antMatchers("/css/**", "/", "error/**").permitAll()
        .antMatchers("app/error").permitAll().antMatchers("/user/**").hasAuthority("ADMIN")
        .antMatchers("/bidList/**", "/rating/**", "/ruleName/**", "/trade/**", "/curvePoint/**")
        .hasAnyAuthority("ADMIN", "USER", "ROLE_USER").anyRequest().authenticated().and()
        .oauth2Login().defaultSuccessUrl("/bidList/list", true).and().formLogin()
        .defaultSuccessUrl("/bidList/list", true).and().logout().logoutUrl("/app-logout")
        .invalidateHttpSession(true).deleteCookies("JSESSIONID");

  }

}
