package se.lexicon.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class DatabaseCredentials {

    private static DatabaseCredentials INSTANCE;

    private final String user;
    private final String password;
    private final String url;

    public static void initialize(String url) throws IOException {
        INSTANCE = new DatabaseCredentials(Paths.get(url));
    }

    public static DatabaseCredentials getInstance(){
        if(INSTANCE == null) throw new IllegalStateException("Please initialize object first");
        return INSTANCE;
    }

    private DatabaseCredentials(Path path) throws IOException {
        Properties properties = new Properties();
        properties.load(Files.newBufferedReader(path));
        user = properties.getProperty("username");
        password = properties.getProperty("password");
        url = properties.getProperty("url");
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

}
