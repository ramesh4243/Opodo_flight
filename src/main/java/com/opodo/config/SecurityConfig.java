package com.opodo.config;

import com.opodo.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration  //
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // Other security-related configurations can be added here

    @Override
    protected void configure(HttpSecurity http) throws Exception{   //httpSecurity object auto created.
//        http.csrf().disable()
//                .authorizeRequests()   //any request coming should be authorize.
//                .antMatchers(HttpMethod.GET,"/api/**").permitAll()
//                .antMatchers("/api/auth/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().disable()
//                .httpBasic();
//    }

            http.csrf().disable()
                    .authorizeRequests()
                    .anyRequest().permitAll()  // Allow all requests without authentication
                    .and()
                    .formLogin().disable()
                    .httpBasic().disable();   // Disable basic authentication
        }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{      //
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    }



