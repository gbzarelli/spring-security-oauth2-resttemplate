# Sample RestTemplate Client with Spring Security Oauth2 with client_credentials flow

This sample works with `Spring Security Oauth2 5` integrated in Spring Boot RestTemplate to make
client requests with Oauth2 client credentials flow. The goal is manage request tokens and expirations time.

# Caution!!

This lib inject the Spring Security context configurations, but, you can remove-it just 
add the exclusions in `spring-boot-starter-oauth2-client` like that:

```xml 
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
```

After that, just remove the [SecurityConfiguration.java](/src/main/java/br/com/helpdev/security/SecurityConfiguration.java).