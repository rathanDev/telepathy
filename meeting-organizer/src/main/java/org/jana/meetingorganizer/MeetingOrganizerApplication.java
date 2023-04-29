package org.jana.meetingorganizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeetingOrganizerApplication {

    public static final int OCCUPATION_SIZE = 24 * 60;
    public static List<Integer> hallIds = new ArrayList<>();
    public static Map<Integer, boolean[]> hallMap = new HashMap<>();

    public static void main(String[] args) {
        String filePath = args[0];
        List<String> lines = FileReaderUtil.readFile(filePath);

        List<Slot> slots = MeetingHallUtil.convertToSlots(lines);

        for (Slot slot : slots) {
            int hallId = findHall(slot);
            bookHall(hallId, slot);
        }
        int count = findCount();
        System.out.println(count);
    }


    private static int findHall(Slot slot) {
        if (hallIds.isEmpty()) {
            int hallId = 0;
            boolean[] occupations = new boolean[OCCUPATION_SIZE];
            hallIds.add(hallId);
            hallMap.put(hallId, occupations);
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
        boolean[] occupations = new boolean[OCCUPATION_SIZE];
        hallIds.add(hallId);
        hallMap.put(hallId, occupations);
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

    private static int findCount() {
        return hallMap.keySet().size();
    }

}
