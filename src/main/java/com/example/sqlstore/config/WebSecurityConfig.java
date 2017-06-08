package com.example.sqlstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{ //jakie adresy mają podlegać logowaniu, co może być wykonane
        http.authorizeRequests()
                .antMatchers("/","/public").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated() //restricted na tej podstawie
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
//                .and()
//                .httpBasic()
                .and()
                .exceptionHandling().accessDeniedPage("/403") //jeśli nie mamy uprawnień do wyświetlania strony wyświetla 403 zdefiniowanym w MVCConfig
        ;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Autowired
    void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{ // role, użytkownicy, hasła
        /* - - W postaci jawnej BEZ KLASY USERDETAILSSERVICE - -

        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER")
                .and()
                .withUser("admin").password("admin").roles("ADMIN"); */

       auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
