package com.example.travel.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin()
                // 로그인 시
                .loginPage("/members/login")
                //성공 시
                .defaultSuccessUrl("/")
                //로그인 시 사용할 파라미터 이름
                .usernameParameter("email")
                // 실패 시
                .failureUrl("/members/login/error")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                // 로그아웃 성공 시
                .logoutSuccessUrl("/");

        http.authorizeRequests()
                .mvcMatchers("/css/**", "/js/**", "/img/**").permitAll()
                .mvcMatchers("/", "/members/**", "/item/**", "/images/**").permitAll()
                .mvcMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated();

        http.exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());


        return http.build();
    }

}
