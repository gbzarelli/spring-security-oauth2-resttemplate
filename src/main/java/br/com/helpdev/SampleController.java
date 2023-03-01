package br.com.helpdev;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    private final MyClientRestTemplate restTemplate;
    private final String sampleUrl;

    public SampleController(MyClientRestTemplate clientRestTemplate,
                            @Value("${sample.url}") String sampleUrl) {
        this.restTemplate = clientRestTemplate;
        this.sampleUrl = sampleUrl;
    }

    @GetMapping("/retrieve")
    public String get() {
        final var responseEntity = restTemplate.exchange(sampleUrl,
                HttpMethod.GET, HttpEntity.EMPTY, String.class);

        return responseEntity.getBody();
    }
}
