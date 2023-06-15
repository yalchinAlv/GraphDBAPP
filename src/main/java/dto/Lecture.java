package dto;

import dto.relations.Teaches;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;
import java.util.Objects;

/**
 * This class represents the "Lecture" node in the graph with its properties, and simple relations.
 */
@NodeEntity
public class Lecture {

    private String topic;
    @Id
    private String id;
    private int ects;
    @Relationship(type = "TEACHES", direction = Relationship.Direction.INCOMING)
    private List<Teaches> professors;
    @Relationship(type = "HAS_EXAM", direction = Relationship.Direction.OUTGOING)
    private List<Exam> exams;
    @Relationship(type = "HEARS", direction = Relationship.Direction.INCOMING)
    private List<Student> students;

    public Lecture() {
        // default constructor for neo4j.ogm
    }

    public Lecture(String topic, String id, int ects, List<Teaches> professors, List<Exam> exams, List<Student> students) {
        this.topic = topic;
        this.id = id;
        this.ects = ects;
        this.professors = professors;
        this.exams = exams;
        this.students = students;
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

    public List<Teaches> getProfessors() {
        return professors;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecture lecture = (Lecture) o;
        return ects == lecture.ects && Objects.equals(topic, lecture.topic) && Objects.equals(id, lecture.id) && Objects.equals(professors, lecture.professors) && Objects.equals(exams, lecture.exams) && Objects.equals(students, lecture.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topic, id, ects, professors, exams, students);
    }

    @Override
    public String toString() {
        return id + ", " + topic;
    }

    public String toDetailedString() {
        return "topic: " + topic + "\n" +
                "id: " + id + "\n" +
                "ects: " + ects + "\n";
    }
}
