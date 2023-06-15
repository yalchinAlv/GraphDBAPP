import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.SessionFactory;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // setup DB connection
        Configuration config = new Configuration.Builder()
                .uri(args[0])
                .credentials(args[1], args[2])
                .build();
        SessionFactory sessionFactory = new SessionFactory(config, "dto", "dto.relations");

        // Create and show the Lecture details
        SwingUtilities.invokeLater(() -> {
            LectureScreen lectureScreen = new LectureScreen(sessionFactory);
            lectureScreen.setVisible(true);
        });
    }
}

