package br.com.helpdev;

import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Objects;

public class MyClientRestTemplate extends RestTemplate {
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public MyClientRestTemplate(final AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientServiceAndManager) {
        this.getInterceptors().add((request, body, execution) -> {
            OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId("my-client")
                    .principal("my-client RestTemplate Oauth2")
                    .build();
            OAuth2AuthorizedClient authorizedClient = authorizedClientServiceAndManager.authorize(authorizeRequest);
            final var token = Objects.requireNonNull(authorizedClient).getAccessToken().getTokenValue();
            request.getHeaders().put(AUTHORIZATION, Collections.singletonList(BEARER.concat(token)));
            return execution.execute(request, body);
        });
    }
}
