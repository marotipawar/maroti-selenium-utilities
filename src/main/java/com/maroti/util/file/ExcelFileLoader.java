package com.maroti.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

@FunctionalInterface
public interface ExcelFileLoader {
    public FileInputStream get(String fileName);

    public static FileInputStream load(String fileName){
      String pathUrl = System.getProperty("user.dir")+"\\src\\test\\resources\\"+fileName;
        //  URL pathUrl = ExcelFile.class.getClassLoader().getResource(fileName);
        File file = null;
        if (pathUrl != null) {
            file = new File(pathUrl);
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
