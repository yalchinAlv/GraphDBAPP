package dto;

import java.util.Objects;

public class Professor {

    private String name;
    private String matriculationNumber;

    public Professor(String name, String matriculationNumber) {
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
        Professor professor = (Professor) o;
        return Objects.equals(name, professor.name) && Objects.equals(matriculationNumber, professor.matriculationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, matriculationNumber);
    }

    @Override
    public String toString() {
        return "Professor{" +
                "name='" + name + '\'' +
                ", matriculationNumber='" + matriculationNumber + '\'' +
                '}';
    }
}
