package com.nhnacademy.project.config;

import com.nhnacademy.project.auth.LoginSuccessHandler;
import com.nhnacademy.project.service.impl.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(HttpMethod.GET, "/projects/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MEMBER")
            .antMatchers(HttpMethod.GET, "/tasks/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MEMBER")
            .antMatchers(HttpMethod.GET, "/milestones/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MEMBER")
            .antMatchers(HttpMethod.GET, "/tags/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MEMBER")
            .antMatchers(HttpMethod.GET, "/comments/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MEMBER")
            .anyRequest().permitAll()
            .and()
                .formLogin()
                .usernameParameter("id")
                .passwordParameter("pwd")
                .loginPage("/auth/login")
                .loginProcessingUrl("/login")
                .successHandler(loginSuccessHandler(null))
                .and()
            .logout()
            .and()
            .csrf()
            .disable()
            .sessionManagement()
            .sessionFixation()
            .none();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider(null));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler(RedisTemplate<String, String> redisTemplate) {
        return new LoginSuccessHandler(redisTemplate);
    }
}
