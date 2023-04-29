package org.jana.meetingorganizer;

public class Slot {

    private int start;
    private int duration;

    public Slot(int start, int duration) {
        this.start = start;
        this.duration = duration;
    }

    public int getStart() {
        return start;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "start=" + start +
                ", duration=" + duration +
                '}';
    }
}
