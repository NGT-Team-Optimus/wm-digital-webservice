package com.cg.teamoptimus.WealthManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cg.teamoptimus.WealthManagement.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter { 

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtAuthenticationFilter jwtFilter;
    @Autowired
    private JwtAuthenticationEntryPoint entryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
            .disable()
            .cors()
            .disable()
            .authorizeRequests()
            .antMatchers("/user/signin",
            		"/user/signup",
            		"/goals/get","/addGoals",
            		"/updateGoalDetails/{userGoalId}/{goalId}",
            		"/getGoalByUserIdAndGoalId/{userId}/{goalId}",
            		"/getUserGoalByUserId/{userId}",
            		"/getGoalByUserGoalIdAndGoalId/{userGoalId}/{goalId}",
            		"/getGoalDetails/{userId}/{goalId}",
            		"/user/api/forget_password/{email}",
            		"/user/api/confirm_password",
            		"/{userId}/{goalId}/transactions",
            		"/{userId}/{goalId}/{fundId}/transactions/add",
                    "/getNumberOfGoalsForUser/{userId}",
                    "/getGoals/{userId}/shortTerm",
                    "/getGoals/{userId}/midTerm",
                    "/getGoals/{userId}/longTerm",
                    "/notifications/user/{userId}/latest",
                    "/getPercentage/shortTermGoals/{userId}",
                    "/user-funds",
                    "/user-funds/{userId}",
                    "/user-funds/{userId}/{fundId}",
                    "/user-funds/{fundId}",
                    "/getAllAvailableFunds",
                    "/user-funds/getTotalBalance/{userId}",
                    "/user-funds/filteruserfunds/{userId}").permitAll()
            .anyRequest().authenticated()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .exceptionHandling().authenticationEntryPoint(entryPoint);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}

