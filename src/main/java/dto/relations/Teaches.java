package dto.relations;

import dto.Lecture;
import dto.Professor;
import org.neo4j.ogm.annotation.*;

import java.util.Objects;

/**
 * This class represents the "TEACHES" relationship between a professor and a lecture in the graph.
 * This class is needed because this relationship is a complex relation that has a property.
 */
@RelationshipEntity("TEACHES")
public class Teaches {
    @Id
    @GeneratedValue
    private Long id;
    private int order;
    @StartNode
    private Professor professor;
    @EndNode
    private Lecture lecture;

    public Teaches() {
        // default constructor for neo4j.ogm
    }

    public Teaches(int order, Professor professor, Lecture lecture) {
        this.order = order;
        this.professor = professor;
        this.lecture = lecture;
    }

    public int getOrder() {
        return order;
    }

    public Professor getProfessor() {
        return professor;
    }

    public Lecture getLecture() {
        return lecture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teaches teaches = (Teaches) o;
        return order == teaches.order && Objects.equals(id, teaches.id) && Objects.equals(professor, teaches.professor) && Objects.equals(lecture, teaches.lecture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, professor, lecture);
    }

    @Override
    public String toString() {
        return "Teaches{" +
                "id=" + id +
                ", order=" + order +
//                ", professor=" + professor +
//                ", lecture=" + lecture +
                '}';
    }
}
