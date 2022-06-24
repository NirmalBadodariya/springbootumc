package com.example.springbootumc.configurations;

//import com.example.springbootumc.jwt.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;


@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
//    @Autowired
//    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/token").permitAll()
                .antMatchers("/","/index","/ForgotPass","/ChangePass","/forgotpass","/setSession").permitAll()
                .antMatchers("/css/**")
                .permitAll()
                .antMatchers("/adminHome").hasRole("ADMIN")
                .antMatchers("/userHome").hasRole("USER")
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin()
                .loginPage("/index")
                .loginProcessingUrl("/login")
                .usernameParameter("email")
                .defaultSuccessUrl("/setSession")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/Logout")
                .logoutSuccessUrl("/index")
                .permitAll()



        ;
//        http
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public DaoAuthenticationProvider authProvider(){

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder())    ;
                return provider;
    }
    protected  void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(authProvider());
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


}
