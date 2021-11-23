package se.lexicon.data;

import se.lexicon.model.TestTableEntity;
import se.lexicon.model.UserCredentials;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDAO {
    public void closeAll(AutoCloseable...closeables){
        if(closeables != null){
            for(AutoCloseable closeable : closeables){
                if(closeable != null){
                    try{
                        closeable.close();
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DatabaseCredentials.getInstance().getUrl(), DatabaseCredentials.getInstance().getUser(), DatabaseCredentials.getInstance().getPassword());
    }

    public TestTableEntity convertFrom(ResultSet resultSet) throws SQLException{
        return new TestTableEntity(
                resultSet.getInt("id"),
                resultSet.getString("description"),
                resultSet.getInt("number")
        );
    }

    public UserCredentials mapUserCredentials(ResultSet resultSet) throws SQLException {
        return new UserCredentials(
                resultSet.getString("id"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getString("role")
        );
    }
}
