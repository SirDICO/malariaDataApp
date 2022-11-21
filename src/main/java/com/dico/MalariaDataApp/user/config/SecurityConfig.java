package com.dico.MalariaDataApp.user.config;

import com.dico.MalariaDataApp.user.service.CustomerUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

     @Bean
    public UserDetailsService userDetailsService(){
        return new CustomerUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder (){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider (){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain FilterChain(HttpSecurity http) throws Exception {
          authenticationProvider();
          http.csrf(auth-> auth.disable());
          http.authorizeRequests(auth->{
              auth.antMatchers("/","/resources/**", "/vendor/**", "/scss/**", "/css/**", "/fonts/**", "/img/**").permitAll();
                      auth.antMatchers("/resources/**", "/css/**", "/fonts/**", "/img/**", "/js/**").permitAll();
        //           auth.antMatchers("/user/**").hasAnyRole("Administrator");
        //           auth.antMatchers("/users").hasAnyRole("Administrator");
        //           auth.antMatchers("/post/**").hasAnyRole("Administrator","Editor");
        //           auth.antMatchers("/category/**").hasAnyRole("Administrator");
              auth.anyRequest().permitAll();
          });
        http.formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/deskofficers/")
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/login").permitAll();

        return http.build();
    }


}
