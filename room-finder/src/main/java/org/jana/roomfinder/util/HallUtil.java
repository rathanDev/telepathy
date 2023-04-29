package org.jana.roomfinder.util;

import org.jana.roomfinder.Slot;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class HallUtil {

    public static List<Slot> convertToSlots(List<String> lines) {
        return lines.stream().map(HallUtil::convertToSlot).collect(Collectors.toList());
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
        int duration = (int) ChronoUnit.MINUTES.between(from, to) - 1; // bcz of the examples

        int start = (from.getHour() * 60) + from.getMinute();

        return new Slot(start, duration);
    }

}
