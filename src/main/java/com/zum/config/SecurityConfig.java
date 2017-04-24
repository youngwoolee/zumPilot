package com.zum.config;

import com.zum.util.BcryptPasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by joeylee on 2017-03-20.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // 메소드별 권한 설정에 필요  ex) @PreAuthorize()
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring()
                .antMatchers("/resources/**", "/css/**",
                "/fonts/**", "/js/**", "/images/**", "/webjars/**");
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //내가 만든 데이터베이스 연결 로직을 사용
//        auth.userDetailsService(userDetailsService);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//        auth.inMemoryAuthentication().withUser("super").password("super").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/board/**").access("hasRole('ROLE_USER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/","/registerForm","/login","/register").permitAll()
                .and()
                    .formLogin()
                    .loginPage("/loginPage")
                    .loginProcessingUrl("/login")
                    .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return BcryptPasswordUtil.bCryptPasswordEncoder;
    }


}
