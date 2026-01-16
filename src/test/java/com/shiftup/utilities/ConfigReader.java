package com.shiftup.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

    public class ConfigReader {

        private static Properties properties;

        static {
            try {
                String path = "configuration.properties";
                FileInputStream file = new FileInputStream(path);
                properties = new Properties();
                properties.load(file);
                file.close();
            } catch (IOException e) {
                System.out.println("Configuration dosyası bulunamadı veya okunamadı.");
                e.printStackTrace();
            }
        }

        public static String getProperty(String key) {
            return properties.getProperty(key);
        }
    }

