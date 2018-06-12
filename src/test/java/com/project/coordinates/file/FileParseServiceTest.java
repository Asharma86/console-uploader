package com.project.coordinates.file;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.List;

import com.project.coordinates.Coordinate;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ CSVRecord.class })
public class FileParseServiceTest {

    @InjectMocks
    private FileParseService sut;
    @Mock
    private CSVRecord record;

    @Test
    public void shouldReturnEmptyListWhenCsvFileIsEmpty() throws IOException {
        final List<Coordinate> actual = sut.parse(new File(FileParseServiceTest.class.getResource("/test.csv").getFile()));

        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void shouldParseCsvFile() throws IOException {
        final FileParseService spyOnSut = Mockito.spy(sut);
        doReturn(Collections.singletonList(record)).when(spyOnSut).getCsvParser(any(Reader.class));
        final String id = "12";
        when(record.get(FileParseService.ID)).thenReturn(id);
        final String xCoordinate = "-12345";
        when(record.get(FileParseService.X_COORDINATE)).thenReturn(xCoordinate);
        final String yCoordinate = "98876";
        when(record.get(FileParseService.Y_COORDINATE)).thenReturn(yCoordinate);
        final String zCoordinate = "-5464654";
        when(record.get(FileParseService.Z_COORDINATE)).thenReturn(zCoordinate);
        final List<Coordinate> actual = spyOnSut.parse(new File(FileParseServiceTest.class.getResource("/test.csv").getFile()));

        assertThat(actual.isEmpty(), is(false));
        assertThat(actual.get(0).getId(), is(new Long(id)));
        assertThat(actual.get(0).getX(), is(xCoordinate));
        assertThat(actual.get(0).getY(), is(yCoordinate));
        assertThat(actual.get(0).getZ(), is(zCoordinate));
    }
}