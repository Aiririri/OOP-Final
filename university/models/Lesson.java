package university.model;

import university.enums.LessonType;

public class Lesson {
    private final LessonType type;
    private final String day, time, room;

    public Lesson(LessonType type, String day, String time, String room) {
        this.type = type; this.day = day; this.time = time; this.room = room;
    }

    public LessonType getType() { return type; }
    public String getDay()  { return day; }
    public String getTime() { return time; }
    public String getRoom() { return room; }

    @Override public String toString() {
        return String.format("  %s — %s %s (Room %s)", type, day, time, room);
    }
}
