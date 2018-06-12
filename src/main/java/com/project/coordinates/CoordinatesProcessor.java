package com.project.coordinates;

import static java.util.Collections.sort;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.project.coordinates.file.FileParseService;
import com.project.coordinates.file.FileSelectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoordinatesProcessor {

    @Autowired
    private FileSelectionService fileSelectionService;
    @Autowired
    private FileParseService fileParseService;
    @Autowired
    private CoordinatesUploader coordinatesUploader;

    public void process() throws IOException {

        final File selectedFile = fileSelectionService.select();

        final List<Coordinate> coordinates = fileParseService.parse(selectedFile);

        if (isNotEmpty(coordinates)) {
            sort(coordinates);
            coordinatesUploader.upload(coordinates);
        }

    }

}
