package com.project.coordinates;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.project.coordinates.file.FileParseService;
import com.project.coordinates.file.FileSelectionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CoordinateProcessorTest {

    @InjectMocks
    private CoordinatesProcessor sut;
    @Mock
    private FileParseService fileParseService;
    @Mock
    private FileSelectionService fileSelectionService;
    @Mock
    private CoordinatesUploader coordinatesUploader;

    @Test
    public void shouldProcess() throws IOException {

        final File file = new File("somefile");
        when(fileSelectionService.select()).thenReturn(file);

        final Coordinate coordinate = new Coordinate();
        coordinate.setId(5);
        final List<Coordinate> coordinateTooBeAdded = singletonList(coordinate);

        when(fileParseService.parse(file)).thenReturn(coordinateTooBeAdded);

        sut.process();

        verify(coordinatesUploader).upload(coordinateTooBeAdded);
    }

    @Test
    public void shouldNotUploadWhenNoRecordsAreParsed() throws IOException {

        final File file = new File("somefile");
        when(fileSelectionService.select()).thenReturn(file);

        when(fileParseService.parse(file)).thenReturn(emptyList());

        sut.process();

        verifyZeroInteractions(coordinatesUploader);
    }

}