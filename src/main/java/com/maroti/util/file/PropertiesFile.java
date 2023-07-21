package com.maroti.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

/*
 * @Author : Maroti Pawar
 * */
@FunctionalInterface
public interface PropertiesFile {
    Properties loadProps(String fileName);

    public static Properties load(String fileName) throws URISyntaxException, IOException {
        URL urlPth = PropertiesFile.class.getClassLoader().getResource(fileName);
        File file = null;
        if (urlPth != null) {
            file = new File(urlPth.toURI());
        }
        if (file != null && file.exists()) {
            FileInputStream stream = new FileInputStream(file);
            Properties prop = new Properties();
            prop.load(stream);
            return prop;
        }
        return null;
    }


}
