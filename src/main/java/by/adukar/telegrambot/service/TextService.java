package by.adukar.telegrambot.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class TextService {
    String result = "";
    InputStream inputStream;

    public String getPropValues(String propertiesPath, String propertyName) throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = propertiesPath;

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            result = prop.getProperty(propertyName);

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }
}

