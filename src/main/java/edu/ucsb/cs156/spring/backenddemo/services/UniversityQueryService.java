package edu.ucsb.cs156.spring.backenddemo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class UniversityQueryService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public UniversityQueryService(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
    }

    public static final String ENDPOINT = "http://universities.hipolabs.com/search?name={name}";

    public String getJSON(String name) throws HttpClientErrorException {
        String url = ENDPOINT.replace("{name}", name);
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (HttpClientErrorException e) {
            log.error("Error while fetching university data: {}", e.getMessage());
            throw e;
        }
    }
}
