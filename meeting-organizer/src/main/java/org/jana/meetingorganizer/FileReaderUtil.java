package org.jana.meetingorganizer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtil {

    public static List<String> readFile(String filePath) {
        // String filePath = "C:\\VProjects\\telepathy\\meeting-organizer\\src\\main\\resources\\input2.txt";
        List<String> lines = new ArrayList<>();
        try {
            String content = Files.readString(Paths.get(filePath));
            String[] splits = content.split("\n");
            for (String line : splits) {
                String trimmed = line.replaceAll("\r", "").trim();
                if (!trimmed.isEmpty()) {
                    lines.add(trimmed);
                }
            }
        } catch (IOException e) {
            System.out.println("Err at reading file; filePath:" + filePath);
            System.out.println(e);
        }
        return lines;
    }

}
