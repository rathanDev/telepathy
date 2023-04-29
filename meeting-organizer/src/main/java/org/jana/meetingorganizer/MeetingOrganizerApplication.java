package org.jana.meetingorganizer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeetingOrganizerApplication {

    public static final int SIZE = 24 * 60;
    public static List<Integer> hallIds = new ArrayList<>();
    public static Map<Integer, boolean[]> hallMap = new HashMap<>();

    public static void main(String[] args) {

        String filePath = "C:\\VProjects\\telepathy\\meeting-organizer\\src\\main\\resources\\input2.txt";
        List<String> lines = readFile(filePath);

        for (String str : lines) {
            Slot slot = convertToSlot(str);
            int hallId = findHall(slot);
            bookHall(hallId, slot);
        }
        int count = findCount();
        System.out.println("count = " + count);
    }

    private static List<String> readFile(String filePath) {
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

        }
        return lines;
    }

    private static int findCount() {
        return hallMap.keySet().size();
    }

    private static Slot convertToSlot(String str) {
        String[] splits = str.split("-");
        String fromTimeStr = splits[0];
        String toTimeStr = splits[1];

        String[] fromTimeStrSplits = fromTimeStr.split(":");
        int fromTimeHour = Integer.parseInt(fromTimeStrSplits[0]);
        int fromTimeMinute = Integer.parseInt(fromTimeStrSplits[1]);

        String[] toTimeStrSplits = toTimeStr.split(":");
        int toTimeHour = Integer.parseInt(toTimeStrSplits[0]);
        int toTimeMinute = Integer.parseInt(toTimeStrSplits[1]);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime from = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), fromTimeHour, fromTimeMinute);
        LocalDateTime to = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), toTimeHour, toTimeMinute);
        int duration = (int) ChronoUnit.MINUTES.between(from, to) - 1;

        int start = (from.getHour() * 60) + from.getMinute();

        return new Slot(start, duration);
    }

    private static int findHall(Slot slot) {
        if (hallIds.isEmpty()) {
            int hallId = 0;
            boolean[] occupations = new boolean[SIZE];
            hallMap.put(hallId, occupations);
            hallIds.add(hallId);
            return hallId;
        }

        for (int hallId : hallIds) {
            boolean[] occupations = hallMap.get(hallId);
            int start = slot.getStart();
            int end = start + slot.getDuration();
            boolean occupied = false;
            for (int i = slot.getStart(); i <= end; i++) {
                if (occupations[i]) {
                    occupied = true;
                    break;
                }
            }
            if (!occupied) {
                return hallId;
            }
        }

        int hallId = hallIds.size();
        boolean[] occupations = new boolean[SIZE];
        hallMap.put(hallId, occupations);
        hallIds.add(hallId);
        return hallId;
    }

    private static void bookHall(int hallId, Slot slot) {
        boolean[] availability = hallMap.get(hallId);
        int start = slot.getStart();
        int end = start + slot.getDuration();
        for (int i = slot.getStart(); i <= end; i++) {
            availability[i] = true;
        }
        hallMap.put(hallId, availability);
    }

}
