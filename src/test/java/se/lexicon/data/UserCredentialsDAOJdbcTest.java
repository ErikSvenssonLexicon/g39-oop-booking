package se.lexicon.data;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.model.Patient;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserCredentialsDAOJdbcTest {
    @BeforeAll
    static void beforeAll() throws IOException {
        DatabaseCredentials.initialize("credentials/h2.properties");

    }

    public void dropAndCreateTables() throws Exception{
        Connection connection = null;
        Statement statement = null;
        try(BufferedReader reader = Files.newBufferedReader(Paths.get("credentials/g39-booking.sql"))){
            String sql = reader.lines()
                    .collect(Collectors.joining());

            System.err.println(sql);

            connection = DriverManager.getConnection(DatabaseCredentials.getInstance().getUrl(), DatabaseCredentials.getInstance().getUser(), DatabaseCredentials.getInstance().getPassword());
            statement = connection.createStatement();
            statement.execute(sql);
        }finally {
            if(statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
        }
    }

    private PatientDAOJdbcImpl testObject;

    @BeforeEach
    void setUp() throws Exception{
        dropAndCreateTables();
        testObject = new PatientDAOJdbcImpl();
    }

    @Test
    void create() {
        Patient patient = new Patient(
                "123",
                "198001011234",
                "Nils",
                "Svensson",
                LocalDate.parse("1980-01-01")
        );

        Patient result = testObject.create(patient);

        assertNotNull(result);
        assertEquals("123", patient.getId());

    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void findByUserName() {
    }

    @Test
    void findByRole() {
    }
}