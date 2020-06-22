package com.epam.jdi.bookstore.configuration.security;

import com.epam.jdi.bookstore.security.BasicAuthEntryPoint;
import com.epam.jdi.bookstore.utils.constants.Roles;
import com.epam.jdi.bookstore.security.BasicAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Order(2)
public class SecurityConfigurationBasicAuth extends WebSecurityConfigurerAdapter {

    @Autowired
    private BasicAuthEntryPoint authenticationEntryPoint;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin@epam.com").password(new BCryptPasswordEncoder().encode("1234"))
                .authorities(Roles.USER, Roles.ADMIN);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(
                        "/securityNone",
                        "/**/api-docs",
                        "/swagger**",
                        "/**/docs",
                        "/favicon.ico",
                        "/status",
                        "/actuator/shutdown",
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/v2/api-docs/**",
                        "/v3/swagger-ui.html",
                        "/v3/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/",
                        "/h2-console/**",
                        "/**/webjars/**",
                        "/swagger-resources/**",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint);

        http.addFilterAfter(new BasicAuthFilter(),
                BasicAuthenticationFilter.class);
    }

}
