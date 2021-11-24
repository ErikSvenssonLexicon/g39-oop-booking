package se.lexicon.data.jdbc;

import se.lexicon.data.AbstractDAO;
import se.lexicon.data.interfaces.UserCredentialsDAO;
import se.lexicon.model.UserCredentials;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserCredentialsDAOJdbcImpl extends AbstractDAO implements UserCredentialsDAO {


    /*
        INSERT INTO table_name (column1, column2, column3, ...)
        VALUES (value1, value2, value3, ...);
     */
    @Override
    public UserCredentials create(UserCredentials userCredentials) {
        if(userCredentials == null) throw new IllegalArgumentException("Entity was null");
        if(userCredentials.getId() == null) throw new IllegalArgumentException("Entity id was null");

        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("INSERT INTO user_credentials (id, username, password, role) VALUES (?, ?, ?, ?)");
            statement.setString(1, userCredentials.getId());
            statement.setString(2, userCredentials.getUsername());
            statement.setString(3, userCredentials.getPassword());
            statement.setString(4, userCredentials.getRole());
            statement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(statement, connection);
        }
        return userCredentials;
    }

    @Override
    public List<UserCredentials> findAll() {
        List<UserCredentials> userCredentials = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM user_credentials");
            while (resultSet.next()){
                userCredentials.add(
                        mapUserCredentials(resultSet)
                );
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(resultSet, statement, connection);
        }
        return userCredentials;
    }

    @Override
    public Optional<UserCredentials> findById(String id) {
        UserCredentials userCredentials = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM user_credentials WHERE id = ?");
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                userCredentials = mapUserCredentials(resultSet);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(resultSet, statement, connection);
        }
        return Optional.ofNullable(userCredentials);
    }

    @Override
    public boolean delete(String id) {
        Connection connection = null;
        PreparedStatement statement = null;
        int rowsDeleted = 0;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("DELETE FROM user_credentials WHERE id = ?");
            statement.setString(1, id);
            rowsDeleted = statement.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(statement, connection);
        }
        return rowsDeleted > 0;
    }

    @Override
    public UserCredentials update(UserCredentials userCredentials) {
        if(userCredentials == null) throw new IllegalArgumentException("Entity was null");
        if(userCredentials.getId() == null) throw new IllegalArgumentException("Entity id was null");
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("UPDATE user_credentials SET username = ?, password = ?, role = ? WHERE id = ?");
            statement.setString(1, userCredentials.getUsername());
            statement.setString(2, userCredentials.getPassword());
            statement.setString(3, userCredentials.getRole());
            statement.setString(4, userCredentials.getId());
            statement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(statement, connection);
        }
        return userCredentials;
    }

    @Override
    public Optional<UserCredentials> findByUserName(String userName) {
        UserCredentials result = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM user_credentials WHERE UPPER(username) = UPPER(?)");
            statement.setString(1, userName);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                result = mapUserCredentials(resultSet);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(resultSet, statement, connection);
        }
        return Optional.ofNullable(result);
    }

    @Override
    public List<UserCredentials> findByRole(String role) {
        List<UserCredentials> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM user_credentials WHERE role = ?");
            statement.setString(1, role);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                result.add(mapUserCredentials(resultSet));
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(resultSet, statement, connection);
        }
        return result;
    }
}
