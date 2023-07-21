package com.maroti.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

@FunctionalInterface
public interface ExcelFileLoader {
    public FileInputStream get(String fileName);

    public static FileInputStream load(String fileName) throws URISyntaxException {
        URL pathUrl = ExcelFile.class.getClassLoader().getResource(fileName);
        File file = null;
        if (pathUrl != null) {
            file = new File(pathUrl.toURI());
        }
        if (file != null && file.exists()) {
            try {
                FileInputStream stream = new FileInputStream(file);
                return stream;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }


}
