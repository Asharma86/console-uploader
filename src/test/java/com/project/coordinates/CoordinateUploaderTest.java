package com.project.coordinates;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class CoordinateUploaderTest {

    @InjectMocks
    private CoordinatesUploader target;
    @Mock
    private Environment environment;
    @Mock
    private RestTemplate restTemplate;
    @Captor
    private ArgumentCaptor<HttpEntity<List<Coordinate>>> argumentCaptor;

    @Test
    public void shouldUploadCoordinate() {

        final List<Coordinate> coordinates = singletonList(new Coordinate());

        final String url = "url";
        when(environment.getProperty("application.cloud.url")).thenReturn(url);

        target.upload(coordinates);

        verify(restTemplate).exchange(eq(url), eq(HttpMethod.POST), argumentCaptor.capture(), eq(Void.class));
        assertThat(argumentCaptor.getValue().getBody(), is(coordinates));

    }

}