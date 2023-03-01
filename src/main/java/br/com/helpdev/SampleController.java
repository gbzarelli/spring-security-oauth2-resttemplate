package br.com.helpdev;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SampleController {

    private final RestTemplate restTemplate;
    private final String sampleUrl;

    public SampleController(@Qualifier("my-client") RestTemplate clientRestTemplate,
                            @Value("${sample.url}") String sampleUrl) {
        this.restTemplate = clientRestTemplate;
        this.sampleUrl = sampleUrl;
    }

    @GetMapping("/retrieve")
    public String get() {
        //Sample to use the specific rest template configured in br.com.helpdev.security.RestTemplateConfiguration
        final var responseEntity = restTemplate.exchange(sampleUrl,
                HttpMethod.GET, HttpEntity.EMPTY, String.class);

        return responseEntity.getBody();
    }
}
