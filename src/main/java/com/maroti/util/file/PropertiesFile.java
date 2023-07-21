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

        String path = System.getProperty("user.dir")+"/src/main/resources/"+fileName;
        URL urlPth = PropertiesFile.class.getClassLoader().getResource(fileName);
        File file = null;
        if (path != null) {
            file = new File(path);
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
