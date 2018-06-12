package com.project;

import com.project.coordinates.CoordinatesProcessor;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ConsoleApplication {

    @Autowired
    private CoordinatesProcessor coordinatesProcessor;

    public static void main(final String[] args) {

        final SpringApplicationBuilder builder = new SpringApplicationBuilder(ConsoleApplication.class);
        builder.headless(false).run(args);

    }

    @Bean
    public CommandLineRunner commandLineRunner(final ApplicationContext ctx) {

        return args -> {
            coordinatesProcessor.process();
            System.exit(0);
        };
    }

    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient()));
    }

    private CloseableHttpClient httpClient() {
        final HttpClientBuilder builder = HttpClientBuilder.create();
        return builder.build();
    }

}
