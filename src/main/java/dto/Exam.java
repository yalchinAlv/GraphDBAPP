package dto;

import java.util.Objects;

public class Exam {
    private String date;
    private String note;
    private String room;

    public Exam(String date, String note, String room) {
        this.date = date;
        this.note = note;
        this.room = room;
    }

    public String getDate() {
        return date;
    }

    public String getNote() {
        return note;
    }

    public String getRoom() {
        return room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exam exam = (Exam) o;
        return Objects.equals(date, exam.date) && Objects.equals(note, exam.note) && Objects.equals(room, exam.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, note, room);
    }

    @Override
    public String toString() {
        return "Exam{" +
                "date='" + date + '\'' +
                ", note='" + note + '\'' +
                ", room='" + room + '\'' +
                '}';
    }
}
