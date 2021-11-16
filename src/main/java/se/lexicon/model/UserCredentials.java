package se.lexicon.model;

import java.io.Serializable;
import java.util.UUID;

public class UserCredentials implements Serializable {

    private String id;
    private String username;
    private String password;
    private String role;

    /**
     *
     * @param id String <b>Not null</b>
     * @param username String <b>Not null</b>
     * @param password String <b>Not null</b>
     * @param role String <b>Not null</b>
     * @throws RuntimeException when anything is null
     */
    public UserCredentials(String id, String username, String password, String role) throws RuntimeException {
        if(id == null){
            throw new RuntimeException("id was null");
        }
        this.id = id;
        setUsername(username);
        setPassword(password);
        setRole(role);
    }

    public UserCredentials(String username, String password, String role) {
        this(UUID.randomUUID().toString(), username, password, role);
    }

    UserCredentials(){}

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username String <b>Not null</b>
     * @throws RuntimeException when username is null
     */
    public void setUsername(String username) throws RuntimeException {
        if(username == null){
            throw new RuntimeException("Username was null");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password == null) {
            throw new RuntimeException("Password was null");
        }
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if(role == null){
            throw new RuntimeException("Role was null");
        }
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserCredentials{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
