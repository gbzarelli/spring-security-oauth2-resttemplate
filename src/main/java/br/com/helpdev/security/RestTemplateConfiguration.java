package br.com.helpdev.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Objects;

/**
 * Configure a specific RestTemplate to use the AuthorizedClientServiceOAuth2AuthorizedClientManager
 * to manage oauth2 tokens. The AuthorizedClientServiceOAuth2AuthorizedClientManager was configured in:
 * {@link OAuthClientConfiguration}
 */
@Configuration
public class RestTemplateConfiguration {

    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";

    @Bean("my-client")
    RestTemplate rest(final AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientServiceAndManager) {
        final var rest = new RestTemplate();
        rest.getInterceptors().add((request, body, execution) -> {
            OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId("my-client")
                    .principal("my-client RestTemplate Oauth2")
                    .build();
            OAuth2AuthorizedClient authorizedClient = authorizedClientServiceAndManager.authorize(authorizeRequest);
            final var token = Objects.requireNonNull(authorizedClient).getAccessToken().getTokenValue();
            request.getHeaders().put(AUTHORIZATION, Collections.singletonList(BEARER.concat(token)));
            return execution.execute(request, body);
        });
        return rest;
    }

}
