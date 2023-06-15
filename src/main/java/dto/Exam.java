package dto;

import dto.relations.HasGrade;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;
import java.util.Objects;

/**
 * This class represents the "Exam" node in the graph with its properties, simple and complex relations.
 */
@NodeEntity
public class Exam {
    @Id
    @GeneratedValue
    private Long id;
    private String date;
    private String note;
    private String room;

    @Relationship(type = "EXAMINES", direction = Relationship.Direction.INCOMING)
    private List<Professor> professors;
    @Relationship(type = "HAS_EXAM", direction = Relationship.Direction.INCOMING)
    private Lecture lecture;
    @Relationship(type = "REGISTERS", direction = Relationship.Direction.INCOMING)
    private List<Student> students;
    @Relationship(type = "HAS_GRADE", direction = Relationship.Direction.INCOMING)
    private List<HasGrade> grades;

    public Exam() {
        // default constructor for neo4j.ogm
    }

    public Exam(String date, String note, String room, List<Professor> professors, Lecture lecture, List<Student> students, List<HasGrade> grades) {
        this.date = date;
        this.note = note;
        this.room = room;
        this.professors = professors;
        this.lecture = lecture;
        this.students = students;
        this.grades = grades;
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

    public List<Professor> getProfessors() {
        return professors;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<HasGrade> getGrades() {
        return grades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exam exam = (Exam) o;
        return Objects.equals(id, exam.id) && Objects.equals(date, exam.date) && Objects.equals(note, exam.note) && Objects.equals(room, exam.room) && Objects.equals(professors, exam.professors) && Objects.equals(lecture, exam.lecture) && Objects.equals(students, exam.students) && Objects.equals(grades, exam.grades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, note, room, professors, lecture, students, grades);
    }

    @Override
    public String toString() {
        return "date: " + date +
                ", note: " + note +
                ", room: " + room;
    }
}
