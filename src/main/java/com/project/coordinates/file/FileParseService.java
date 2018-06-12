package com.project.coordinates.file;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.project.coordinates.Coordinate;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

@Service
public class FileParseService {

    static final String ID = "#ID";
    static final String X_COORDINATE = "X_COORDINATE";
    static final String Y_COORDINATE = "Y_COORDINATE";
    static final String Z_COORDINATE = "Z_COORDINATE";

    public List<Coordinate> parse(final File selectedFile) throws IOException {

        final List<Coordinate> coordinates = new ArrayList<>();

        try (final Reader reader = new FileReader(selectedFile)) {

            final List<CSVRecord> csvRecords = getCsvParser(reader);

            for (final CSVRecord csvRecord : csvRecords) {

                final Coordinate coordinate = new Coordinate();
                coordinate.setId(new Long(csvRecord.get(ID)));
                coordinate.setX(csvRecord.get(X_COORDINATE));
                coordinate.setY(csvRecord.get(Y_COORDINATE));
                coordinate.setZ(csvRecord.get(Z_COORDINATE));

                coordinates.add(coordinate);
            }

        }

        return coordinates;

    }

    List<CSVRecord> getCsvParser(final Reader reader) throws IOException {
        //@formatter:off
        return new CSVParser(reader,
                        CSVFormat.DEFAULT
                                .withHeader(ID, X_COORDINATE, Y_COORDINATE, Z_COORDINATE)
                                .withSkipHeaderRecord(true)
                                .withIgnoreHeaderCase()
                                .withTrim())
                        .getRecords();
        //@formatter:on
    }
}
