package dto;

import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Objects;

@NodeEntity
public class Student {
    private String name;
    private String matriculationNumber;

    public Student(String name, String matriculationNumber) {
        this.name = name;
        this.matriculationNumber = matriculationNumber;
    }

    public String getName() {
        return name;
    }

    public String getMatriculationNumber() {
        return matriculationNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) && Objects.equals(matriculationNumber, student.matriculationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, matriculationNumber);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", matriculationNumber='" + matriculationNumber + '\'' +
                '}';
    }
}
