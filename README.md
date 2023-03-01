# Sample RestTemplate Client with Spring Security Oauth2 with client_credentials flow

This sample works with `Spring Security Oauth2 5` integrated in Spring Boot RestTemplate to make
client requests with Oauth2 client credentials flow. The goal is manage request tokens and expirations time.

## Caution!!

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

## Oh no! I can't use Spring Security Oaut2 5, just 2.x versions, how to implement the client credentials flow?

If you can't upgrade, you can use the **DEPRECATED** way:
(its implementation is easier to build a RestTemplate class)
```
// Build o OAuth2RestTemplate:
final ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
resourceDetails.setAccessTokenUri("https://your.api.url.token.com/....");
resourceDetails.setClientId("clientID");
resourceDetails.setClientSecret("clientSecret");
resourceDetails.setGrantType("client_credentials");

final RestTemplate oauthClient = new OAuth2RestTemplate(resourceDetails);
```

## My project uses Spring Boot version previous **2.4.x** version

Change the `spring-boot-starter-oauth2-client` dependency to:

```xml
<dependency>
	<groupId>org.springframework.security</groupId>
	<artifactId>spring-security-oauth2-client</artifactId>
	<version>5.7.7</version>
</dependency>
<dependency>
	<groupId>org.springframework.security</groupId>
	<artifactId>spring-security-oauth2-core</artifactId>
	<version>5.7.7</version>
</dependency>
```

# References

- https://docs.spring.io/spring-security/site/docs/5.2.0.RELEASE/reference/html/oauth2.html
- https://github.com/spring-projects/spring-security/wiki/OAuth-2.0-Migration-Guide
- https://github.com/oktadev/okta-spring-boot-client-credentials-example/tree/main/client-webclient
