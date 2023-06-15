import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LectureDetailsGUI extends JFrame {
    private JList<String> lecturesList;
    private JList<String> studentsList;
    private JTextArea detailsTextArea;
    private JComboBox<String> panelSelector;

    private JPanel detailsPanel;
    private JPanel firstPanel;

    public LectureDetailsGUI() {
        // Create the frame
        setTitle("Lecture/Student Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        // Create the main panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create the panel selector
        panelSelector = new JComboBox<>(new String[]{"Lectures", "Students"});
        panelSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPanel = (String) panelSelector.getSelectedItem();
                showSelectedPanel(selectedPanel);
            }
        });

        // Create the first panel
        firstPanel = new JPanel(new BorderLayout());
        firstPanel.setPreferredSize(new Dimension(200, getHeight()));

        // Create the lectures list
        DefaultListModel<String> lecturesModel = new DefaultListModel<>();
        lecturesModel.addElement("Lecture 1");
        lecturesModel.addElement("Lecture 2");
        lecturesModel.addElement("Lecture 3");
        lecturesModel.addElement("Lecture 4");
        lecturesModel.addElement("Lecture 5");

        lecturesList = new JList<>(lecturesModel);
        lecturesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Create the students list
        DefaultListModel<String> studentsModel = new DefaultListModel<>();
        studentsModel.addElement("Student 1");
        studentsModel.addElement("Student 2");
        studentsModel.addElement("Student 3");
        studentsModel.addElement("Student 4");
        studentsModel.addElement("Student 5");

        studentsList = new JList<>(studentsModel);
        studentsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Create the details panel
        detailsPanel = new JPanel(new BorderLayout());

        // Create the details text area
        detailsTextArea = new JTextArea();
        detailsTextArea.setEditable(false);

        // Add the details text area to a scroll pane
        JScrollPane detailsScrollPane = new JScrollPane(detailsTextArea);
        detailsPanel.add(detailsScrollPane, BorderLayout.CENTER);

        // Add the panel selector, first panel, and details panel to the main panel
        mainPanel.add(panelSelector, BorderLayout.NORTH);
        mainPanel.add(firstPanel, BorderLayout.WEST);
        mainPanel.add(detailsPanel, BorderLayout.CENTER);

        // Add action listeners to the lists
        lecturesList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedLecture = lecturesList.getSelectedValue();
                    showDetails(selectedLecture);
                }
            }
        });

        studentsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedStudent = studentsList.getSelectedValue();
                    showDetails(selectedStudent);
                }
            }
        });

        // Add the main panel to the frame
        add(mainPanel);
    }

    private void showSelectedPanel(String selectedPanel) {
        firstPanel.removeAll();

        if (selectedPanel.equals("Lectures")) {
            firstPanel.add(new JScrollPane(lecturesList), BorderLayout.CENTER);
        } else if (selectedPanel.equals("Students")) {
            firstPanel.add(new JScrollPane(studentsList), BorderLayout.CENTER);
        }

        firstPanel.revalidate();
        firstPanel.repaint();
    }

    private void showDetails(String selectedItem) {
        if (selectedItem != null) {
            detailsTextArea.setText("Details for " + selectedItem);
        } else {
            detailsTextArea.setText("");
        }
    }

    public static void main(String[] args) {
        // Create and show the GUI
        SwingUtilities.invokeLater(() -> {
            LectureDetailsGUI gui = new LectureDetailsGUI();
            gui.setVisible(true);
        });
    }
}
