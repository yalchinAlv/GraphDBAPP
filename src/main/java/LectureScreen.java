import dto.Exam;
import dto.Lecture;
import dto.Professor;
import dto.Student;
import dto.relations.Teaches;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;

public class LectureScreen extends JFrame {
    private JList<Lecture> lecturesList;
    private JEditorPane lectureDetailsTextArea;

    public LectureScreen(SessionFactory sessionFactory) {
        // Create the frame
        setTitle("Lecture Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel lecturesPanel = new JPanel(new BorderLayout());
        lecturesPanel.setPreferredSize(new Dimension(250, getHeight()));

        // Create the lectures list
        DefaultListModel<Lecture> lecturesModel = new DefaultListModel<>();
        Session session = sessionFactory.openSession();
        lecturesModel.addAll(session.loadAll(Lecture.class));

        lecturesList = new JList<>(lecturesModel);
        lecturesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Add the lectures list to a scroll pane
        JScrollPane lecturesScrollPane = new JScrollPane(lecturesList);
        lecturesPanel.add(lecturesScrollPane, BorderLayout.CENTER);

        // Create the details panel
        JPanel detailsPanel = new JPanel(new BorderLayout());
        lectureDetailsTextArea = new JEditorPane("text/html", "");
        lectureDetailsTextArea.setEditable(true);

        // Add the lecture details text area to a scroll pane
        JScrollPane detailsScrollPane = new JScrollPane(lectureDetailsTextArea);
        detailsPanel.add(detailsScrollPane, BorderLayout.CENTER);

        // Add the panels to the main panel
        mainPanel.add(lecturesPanel, BorderLayout.WEST);
        mainPanel.add(detailsPanel, BorderLayout.CENTER);

        // Add a selection listener to the lectures list
        lecturesList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Lecture selectedLecture = lecturesList.getSelectedValue();
                showLectureDetails(selectedLecture);
            }
        });

        // Add the main panel to the frame
        add(mainPanel);
    }

    private void showLectureDetails(Lecture lecture) {
        if (lecture != null) {
            // Set the lecture details in the text area
            StringBuilder text = new StringBuilder("<b>Details</b>:<br>" +
                    "<b>topic</b>: " + lecture.getTopic() + "<br>" +
                    "<b>id</b>: " + lecture.getId() + "<br>" +
                    "<b>ects</b>: " + lecture.getEcts() + "<br>" +
                    "<br><br>");
            addProfessors(lecture, text);
            text.append("<br><br>");
            addStudents(lecture, text);
            text.append("<br><br>");
            addExams(lecture, text);
            lectureDetailsTextArea.setText(text.toString());


        } else {
            // Clear the lecture details
            lectureDetailsTextArea.setText("");
        }
    }

    private static void addExams(Lecture lecture, StringBuilder text) {
        text.append("<b>Exams</b>:<br><p>");
        for (int i = 0; i < lecture.getExams().size(); i++) {
            Exam exam = lecture.getExams().get(i);
            text.append("    Exam ").append(i + 1).append(": ").append(exam.toString()).append("<br>");
        }
        text.append("</p>");
    }

    private static void addStudents(Lecture lecture, StringBuilder text) {
        text.append("<b>Students</b>:<br><p>");
        for (int i = 0; i < lecture.getStudents().size(); i++) {
            Student student = lecture.getStudents().get(i);
            text.append(student.toString()).append("<br>");
        }
        text.append("</p>");
    }

    private static void addProfessors(Lecture lecture, StringBuilder text) {
        text.append("<b>Professors</b>:<br><p>");
        lecture.getProfessors().sort(Comparator.comparing(Teaches::getOrder));
        for (int i = 0; i < lecture.getProfessors().size(); i++) {
            Professor professor = lecture.getProfessors().get(i).getProfessor();
            text.append(i).append(": ").append(professor.toString()).append("<br>");
        }
        text.append("</p>");
    }
}