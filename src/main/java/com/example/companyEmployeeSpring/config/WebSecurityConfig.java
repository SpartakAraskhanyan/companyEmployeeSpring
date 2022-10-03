package com.example.companyEmployeeSpring.config;


import com.example.companyEmployeeSpring.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin()
                .loginPage("/loginPage")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login")
                .failureUrl("/loginPage?error=true")
                .defaultSuccessUrl("/loginSuccess")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET,"/user/register").permitAll()
                .antMatchers(HttpMethod.POST,"/user/register").permitAll()
                .antMatchers(HttpMethod.GET,"/company/add").hasAuthority(Role.ADMIN.name())
                .antMatchers(HttpMethod.POST,"/company/add").hasAuthority(Role.ADMIN.name())
                .antMatchers("/employee").hasAuthority(Role.ADMIN.name())
                .antMatchers("/company/delete").hasAuthority(Role.ADMIN.name())
                .anyRequest()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied");

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder);
    }


}
