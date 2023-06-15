package dto.relations;

import dto.Exam;
import dto.Student;
import org.neo4j.ogm.annotation.*;

import java.util.Objects;

/**
 * This class represents the "HAS_GRADE" relationship between a student and an exam in the graph.
 * This class is needed because this relationship is a complex relation that has a property.
 */
@RelationshipEntity(type = "HAS_GRADE")
public class HasGrade {
    @Id
    @GeneratedValue
    private Long id;
    private int grade;
    @StartNode
    private Student student;
    @EndNode
    private Exam exam;

    public HasGrade() {
        // default constructor for neo4j.ogm
    }

    public HasGrade(int grade, Student student, Exam exam) {
        this.grade = grade;
        this.student = student;
        this.exam = exam;
    }

    public int getGrade() {
        return grade;
    }

    public Student getStudent() {
        return student;
    }

    public Exam getExam() {
        return exam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HasGrade hasGrade = (HasGrade) o;
        return grade == hasGrade.grade && Objects.equals(id, hasGrade.id) && Objects.equals(student, hasGrade.student) && Objects.equals(exam, hasGrade.exam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, grade, student, exam);
    }

    @Override
    public String toString() {
        return "HasGrade{" +
                "id=" + id +
                ", grade=" + grade +
//                ", student=" + student +
//                ", exam=" + exam +
                '}';
    }
}
