package com.alok.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.ldap.core.support.BaseLdapPathSource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

//    @Autowired
//    private LdapServiceAuthenticationProvider ldapAuthenticationProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .anyRequest().fullyAuthenticated()
                )
                //.authenticationProvider(ldapAuthenticationProvider)
                .httpBasic(Customizer.withDefaults())
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                ;
        return http.build();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .ldapAuthentication()
                .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=groups")
                .contextSource()
                .url("ldap://localhost:8389/dc=springframework,dc=org")
                .and()
                .passwordCompare()
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                //.passwordEncoder(new BCryptPasswordEncoder())
                .passwordAttribute("userPassword");
    }
//@Bean
//LdapAuthenticationProvider ldapAuthenticationProvider() {
//    return new LdapAuthenticationProvider(bindAuthenticator());
//}
//    @Bean
//    public BindAuthenticator bindAuthenticator(){
//        FilterBasedLdapUserSearch search = new FilterBasedLdapUserSearch ("ou=groups", "(uid={0})",
//                contextSource());
//        BindAuthenticator bindAuthenticator = new BindAuthenticator(contextSource());
//        bindAuthenticator.setUserSearch(search);
//       return bindAuthenticator;
//    }
//
//    @Bean
//    public DefaultSpringSecurityContextSource contextSource() {
//        DefaultSpringSecurityContextSource contextSource =
//                new DefaultSpringSecurityContextSource("ldap://localhost:8389/dc=springframework,dc=org");
//        contextSource.setUserDn("uid={0},ou=people");
//                return contextSource;
//    }


}
