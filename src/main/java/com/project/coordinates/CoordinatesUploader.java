package com.project.coordinates;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CoordinatesUploader {

    @Autowired
    private Environment environment;
    @Autowired
    private RestTemplate restTemplate;

    void upload(final List<Coordinate> coordinates) {

        final HttpEntity<List<Coordinate>> entity = new HttpEntity<>(coordinates);

        restTemplate.exchange(environment.getProperty("application.cloud.url"), HttpMethod.POST, entity, Void.class);

    }
}
