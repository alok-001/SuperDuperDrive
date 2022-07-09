package com.udacity.jwdnd.course1.cloudstorage.config;

import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class Configsecurity extends WebSecurityConfigurerAdapter{


        private AuthenticationService authenticationService;

        public Configsecurity(AuthenticationService authenticationService){
            this.authenticationService = authenticationService;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder){
            authenticationManagerBuilder.authenticationProvider(this.authenticationService);
        }

        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.authorizeRequests().antMatchers(
                    "/login",
                    "/signup",
                    "/css/**",
                    "/js/**",
                    "/h2-console/**"
            ).permitAll().anyRequest().authenticated();

            httpSecurity.formLogin().loginPage("/login").permitAll();

            httpSecurity.csrf().ignoringAntMatchers("/h2-console/**");

            httpSecurity.formLogin().defaultSuccessUrl("/home", true);

            httpSecurity.logout().logoutSuccessUrl("/login?logout").permitAll();
        }
    }

