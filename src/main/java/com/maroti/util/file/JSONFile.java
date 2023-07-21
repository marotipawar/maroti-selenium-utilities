package com.maroti.util.file;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/*
 * @Author : Maroti Pawar
 * */
public interface JSONFile<T> {

    T read(String file);


    public static File load(String fileName) {
        String urlPath = System.getProperty("user.dir")+"/src/test/resources/"+fileName;
        //URL urlPath = JSONFile.class.getClassLoader().getResource(fileName);
        File file = null;
        if (urlPath != null) {
            file = new File(urlPath);
        }
        if (file != null && file.exists()) {
            return file;
        }
        return null;
    }

    public static Map toMap(String fileName) throws IOException {
        File file = load(fileName);
        if (file != null && file.exists()) {
            ObjectMapper mapper = new ObjectMapper();
            Map<?, ?> map = mapper.readValue(file, Map.class);
            return map;
        }

        return null;
    }

    public static List<Map> toList(String fileName) throws IOException {
        File file = load(fileName);
        if (file != null && file.exists()) {
            ObjectMapper mapper = new ObjectMapper();
            List<Map> list = Arrays.asList(mapper.readValue(file, Map[].class));
            return list;
        }

        return null;
    }


}
