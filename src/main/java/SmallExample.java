import org.neo4j.driver.*;
import org.neo4j.driver.Record;

import static org.neo4j.driver.Values.parameters;

public class SmallExample {
    public static final SessionConfig DB_CONFIG = SessionConfig.forDatabase("neo4j");
    Driver driver;

    public SmallExample(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    private void addPerson(String name) {
        // Sessions are lightweight and disposable connection wrappers.
        try (Session session = driver.session(DB_CONFIG)) {
            // Wrapping a Cypher Query in a Managed Transaction provides atomicity
            // and makes handling errors much easier.
            // Use `session.writeTransaction` for writes and `session.readTransaction` for reading data.
            // These methods are also able to handle connection problems and transient errors using an automatic retry mechanism.
            session.writeTransaction(tx -> tx.run("MERGE (a:Person {name: $x})", parameters("x", name)));
        }
    }

    private void printPeople(String initial) {
        try (Session session = driver.session()) {
            // A Managed transaction is a quick and easy way to wrap a Cypher Query.
            // The `session.run` method will run the specified Query.
            // This simpler method does not use any automatic retry mechanism.
            Result result = session.run(
                    "MATCH (a:Person) WHERE a.name STARTS WITH $x RETURN a.name AS name",
                    parameters("x", initial));
            // Each Cypher execution returns a stream of records.
            while (result.hasNext()) {
                Record record = result.next();
                // Values can be extracted from a record by index or name.
                System.out.println(record.get("name").asString());
            }
        }
    }

    public void close() {
        // Closing a driver immediately shuts down all open connections.
        driver.close();
    }

    public static void main(String... args) {
        SmallExample example = new SmallExample("bolt://localhost:7687", "neo4j", "password");
        example.addPerson("Ada");
        example.addPerson("Alice");
        example.addPerson("Bob");
        example.printPeople("A");
        example.close();
    }
}
