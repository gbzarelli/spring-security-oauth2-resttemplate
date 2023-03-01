package br.com.helpdev.security;

import br.com.helpdev.MyClientRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;

/**
 * Configure a specific RestTemplate to use the AuthorizedClientServiceOAuth2AuthorizedClientManager
 * to manage oauth2 tokens. The AuthorizedClientServiceOAuth2AuthorizedClientManager was configured in:
 * {@link OAuthClientConfiguration}
 */
@Configuration
public class RestTemplateConfiguration {

    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";

    @Bean
    MyClientRestTemplate rest(final AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientServiceAndManager) {
        return new MyClientRestTemplate(authorizedClientServiceAndManager);
    }

}
