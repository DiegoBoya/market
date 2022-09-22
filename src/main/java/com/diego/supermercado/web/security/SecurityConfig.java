package com.diego.supermercado.web.security;

import com.diego.supermercado.domain.service.MarketUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//todo: cambio de WebSecurityCOnfigurerAdapter por BiCrypt - vid 34
    @Autowired
    private MarketUserDetailsService marketUserDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        auth.userDetailsService(marketUserDetailsService);
    }

    //mantener acceso a swagger sin autorizacion opcion 1
  /*  @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs/**");
        web.ignoring().antMatchers("/swagger.json");
        web.ignoring().antMatchers("/swagger-ui.html");
        web.ignoring().antMatchers("/swagger-resources/**");
        web.ignoring().antMatchers("/webjars/**");
    }*/

    //mantener acceso a los endpoint que cumplan con los patrones detallados en el .antMatchers()
    @Override
    protected void configure(HttpSecurity http)throws Exception {
        http.authorizeRequests()
                .antMatchers("/swagger*",
                        "**/authenticate")
                .permitAll().anyRequest().authenticated();
    }

    //TODO: ver min 8
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean()throws Exception{
        return super.authenticationManagerBean();
    }

}
