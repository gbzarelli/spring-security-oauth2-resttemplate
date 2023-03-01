package br.com.helpdev.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /*
     * If you don't need the resources about Spring Security web configs,
     * just remove it in your spring-boot-starter-oauth2-client dependency:
     *
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-oauth2-client</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>org.springframework.security</groupId>
                    <artifactId>spring-security-config</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
     *
     * and remove that class.
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // That config is only to test, you need adapt to your case
        http.authorizeRequests()
                .antMatchers("/**").permitAll();
    }

}