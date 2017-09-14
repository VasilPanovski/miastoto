package com.bgmiastoto.utils;

import com.bgmiastoto.messages.Constants;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by mm on 4.5.2017 г..
 */
public class FilesIO {

    public static String readFile(String absolutePath) {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(absolutePath)));
            String line;
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static String writeFile(byte[] fileFrom, String fileName) {
        String file = Constants.FILE_PATH + fileName;
        try (
                FileOutputStream outputStream = new FileOutputStream(file);
        ) {
            outputStream.write(fileFrom);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }
}
