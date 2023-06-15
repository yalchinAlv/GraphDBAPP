package dto;

import dto.relations.HasGrade;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;
import java.util.Objects;

/**
 * This class represents the "Student" node in the graph with its properties, simple and complex relations.
 */
@NodeEntity
public class Student {
    private String name;
    @Id
    private String matriculationNumber;
    @Relationship(type = "HEARS", direction = Relationship.Direction.OUTGOING)
    private List<Lecture> lectures;
    @Relationship(type = "REGISTERS", direction = Relationship.Direction.OUTGOING)
    private List<Exam> exams;
    @Relationship(type = "HAS_GRADE", direction = Relationship.Direction.OUTGOING)
    private List<HasGrade> grades;

    public Student() {
        // default constructor for neo4j.ogm
    }

    public Student(String name, String matriculationNumber, List<Lecture> lectures, List<Exam> exams, List<HasGrade> grades) {
        this.name = name;
        this.matriculationNumber = matriculationNumber;
        this.lectures = lectures;
        this.exams = exams;
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public String getMatriculationNumber() {
        return matriculationNumber;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public List<HasGrade> getGrades() {
        return grades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) && Objects.equals(matriculationNumber, student.matriculationNumber) && Objects.equals(lectures, student.lectures) && Objects.equals(exams, student.exams) && Objects.equals(grades, student.grades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, matriculationNumber, lectures, exams, grades);
    }

    @Override
    public String toString() {
        return name + ", " + matriculationNumber;
    }
}
