package se.lexicon;

import se.lexicon.data.DatabaseCredentials;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

public class H2Util {

    private static H2Util INSTANCE;
    private String sql;

    static {
        BufferedReader reader = null;
        try {
            DatabaseCredentials.initialize("credentials/h2.properties");
            reader = Files.newBufferedReader(Paths.get("credentials/g39-booking.sql"));
            String sql = reader.lines().collect(Collectors.joining());
            INSTANCE = new H2Util(sql);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static H2Util getInstance(){
        return INSTANCE;
    }

    private H2Util (String sql){
        this.sql = sql;
    }

    public void dropAndCreateTables() throws SQLException {
        try(Connection connection = DriverManager.getConnection(DatabaseCredentials.getInstance().getUrl(), DatabaseCredentials.getInstance().getUser(), DatabaseCredentials.getInstance().getPassword());
            Statement statement = connection.createStatement()){
            statement.execute(sql);
        }
    }


}
