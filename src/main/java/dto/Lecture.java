package dto;

import java.util.Objects;

public class Lecture {

    private String topic;
    private String id;
    private int ects;

    public Lecture(String topic, String id, int ects) {
        this.topic = topic;
        this.id = id;
        this.ects = ects;
    }

    public String getTopic() {
        return topic;
    }

    public String getId() {
        return id;
    }

    public int getEcts() {
        return ects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecture lecture = (Lecture) o;
        return ects == lecture.ects && Objects.equals(topic, lecture.topic) && Objects.equals(id, lecture.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topic, id, ects);
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "topic='" + topic + '\'' +
                ", id='" + id + '\'' +
                ", ects=" + ects +
                '}';
    }
}
