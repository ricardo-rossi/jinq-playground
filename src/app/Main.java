package app;

import models.Cities;
import models.Names;
import org.jinq.jpa.JPAQueryLogger;
import org.jinq.jpa.JinqJPAStreamProvider;
import org.jinq.orm.stream.JinqStream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.PrintStream;
import java.util.Map;

/**
 * JINQ test
 */
public class Main {

    private static EntityManagerFactory entityManagerFactory;
    private static JinqJPAStreamProvider streams;
    private EntityManager em;

    // Models
    private JinqStream<Cities> cities() {
        return streams.streamAll(em, Cities.class);
    }

    private JinqStream<Names> names() {
        return streams.streamAll(em, Names.class);
    }

    public static void main(String[] args) throws Exception {

        // Configure Jinq for the given JPA database connection
        entityManagerFactory = Persistence.createEntityManagerFactory("MyDBModels");
        streams = new JinqJPAStreamProvider(entityManagerFactory);

        // Configure Jinq to output the queries it executes
        streams.setHint("queryLogger", new JPAQueryLogger() {
            @Override
            public void logQuery(String query, Map<Integer, Object> positionParameters,
                                 Map<String, Object> namedParameters) {
                System.out.println("  " + query);
            }
        });

        // Start running some queries
        new Main().runSampleQueries();

    }

    private void runSampleQueries() {

        PrintStream out = System.out;
        em = entityManagerFactory.createEntityManager();

        out.println("LIST OF CITIES");
        cities().forEach(c -> out.println(c.getName()));
        out.println();

        out.println("LIST OF NAMES");
        names().forEach(n -> out.println(n.getFirstName() + " " + n.getLastName()));
        out.println();
    }
}
