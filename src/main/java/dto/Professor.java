package dto;

import dto.relations.Teaches;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;
import java.util.Objects;

/**
 * This class represents the "Professor" node in the graph with its properties, and simple relations.
 */
@NodeEntity
public class Professor {
    private String name;
    @Id
    private String employeeNumber;
    @Relationship(type = "TEACHES", direction = Relationship.Direction.OUTGOING)
    private List<Teaches> lectures;
    @Relationship(type = "EXAMINES", direction = Relationship.Direction.OUTGOING)
    private List<Exam> exams;

    public Professor() {
        // default constructor for neo4j.ogm
    }

    public Professor(String name, String employeeNumber, List<Teaches> lectures, List<Exam> exams) {
        this.name = name;
        this.employeeNumber = employeeNumber;
        this.lectures = lectures;
        this.exams = exams;
    }

    public String getName() {
        return name;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public List<Teaches> getLectures() {
        return lectures;
    }

    public List<Exam> getExams() {
        return exams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return Objects.equals(name, professor.name) && Objects.equals(employeeNumber, professor.employeeNumber) && Objects.equals(lectures, professor.lectures) && Objects.equals(exams, professor.exams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, employeeNumber, lectures, exams);
    }

    @Override
    public String toString() {
        return name + ", " + employeeNumber;
    }
}
