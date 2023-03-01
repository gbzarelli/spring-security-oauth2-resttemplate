package br.com.helpdev.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

/**
 * Configuration classes to manage the Oauth2
 */
@Configuration
public class OAuthClientConfiguration {

    @Bean
    ClientRegistration myClientRegistration(
            @Value("${spring.security.oauth2.client.provider.my-client.token-uri}") String token_uri,
            @Value("${spring.security.oauth2.client.registration.my-client.client-id}") String client_id,
            @Value("${spring.security.oauth2.client.registration.my-client.client-secret}") String client_secret,
            @Value("${spring.security.oauth2.client.registration.my-client.authorization-grant-type}") String authorizationGrantType
    ) {
        return ClientRegistration
                .withRegistrationId("my-client")
                .tokenUri(token_uri)
                .clientId(client_id)
                .clientSecret(client_secret)
                .authorizationGrantType(new AuthorizationGrantType(authorizationGrantType))
                .build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(ClientRegistration oktaClientRegistration) {
        return new InMemoryClientRegistrationRepository(oktaClientRegistration);
    }

    @Bean
    public OAuth2AuthorizedClientService auth2AuthorizedClientService(ClientRegistrationRepository clientRegistrationRepository) {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
    }

    @Bean
    public AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientServiceAndManager (
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientService authorizedClientService) {

        OAuth2AuthorizedClientProvider authorizedClientProvider =
                OAuth2AuthorizedClientProviderBuilder.builder()
                        .clientCredentials()
                        .build();

        AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientManager =
                new AuthorizedClientServiceOAuth2AuthorizedClientManager(
                        clientRegistrationRepository, authorizedClientService);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

        return authorizedClientManager;
    }

}
