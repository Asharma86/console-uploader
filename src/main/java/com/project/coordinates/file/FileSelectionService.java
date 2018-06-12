package com.project.coordinates.file;

import static java.lang.System.exit;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

import org.springframework.stereotype.Service;

@Service
public class FileSelectionService {

    private static final String DIALOG_TITLE = "Please select a CSV file";
    private static final String FILE_DESC = "Comma Separated File (*.csv)";
    private static final String FILE_EXTENSION = "csv";

    public File select() {

        final JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle(DIALOG_TITLE);
        fileChooser.setAcceptAllFileFilterUsed(false);

        final FileNameExtensionFilter filter = new FileNameExtensionFilter(FILE_DESC, FILE_EXTENSION);
        fileChooser.addChoosableFileFilter(filter);

        final int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.CANCEL_OPTION) {
            exit(0);
        }

        return fileChooser.getSelectedFile();

    }

}