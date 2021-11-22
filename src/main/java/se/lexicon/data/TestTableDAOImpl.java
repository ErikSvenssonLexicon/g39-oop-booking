package se.lexicon.data;

import se.lexicon.data.interfaces.TestTableDAO;
import se.lexicon.model.TestTableEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestTableDAOImpl extends AbstractDAO implements TestTableDAO {

    /*
        INSERT INTO table_name (column1, column2, column3, ...)
        VALUES (value1, value2, value3, ...);
     */
    @Override
    public TestTableEntity create(TestTableEntity testTableEntity) {
        if(testTableEntity == null) throw new IllegalArgumentException("Entity was null");
        if(testTableEntity.getId() != 0) throw new IllegalArgumentException("id wasn't 0");

        //Pre declaring the Connection object
        Connection connection = null;
        //Pre declaring the Prepared statement object
        PreparedStatement statement = null;
        //Pre declaring the ResultSet that is needed to fetch generated key (auto incremented)
        ResultSet keySet = null;
        //Pre declaring the return object
        TestTableEntity result = null;

        try{
            // 1 - We need to create our connection. Here we are using a utility method in AbstractDAO parent
            connection = getConnection();
            /* 2 - We create prepared statement from connection and define the SQL. Since we choose auto increment we need
                   to add the optional Statement.RETURN_GENERATED_KEY
             */
            statement = connection.prepareStatement("INSERT INTO test_table (description, number) " +
                    "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            // 3 - Setting the first parameter
            statement.setString(1, testTableEntity.getDescription());
            // 4 - Setting the second parameter
            statement.setInt(2, testTableEntity.getNumber());
            // 5 - Executing the statement.
            statement.execute();
            // 6 - Getting the generated key back in the result set. Would crash if we did not specify Statement.RETURN_GENERATED_KEY
            keySet = statement.getGeneratedKeys();
            // 7 - Iterating through the result set row by row (only one row in this use case)
            while (keySet.next()){
                result = new TestTableEntity(
                        keySet.getInt(1),
                        testTableEntity.getDescription(),
                        testTableEntity.getNumber()
                );
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(keySet, statement, connection);
        }
        return result;
    }

    @Override
    public List<TestTableEntity> findAll() {
        List<TestTableEntity> result = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM test_table");
            while (resultSet.next()){
                result.add(
                        convertFrom(resultSet)
                );
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(resultSet, statement, connection);
        }
        return result;
    }

    @Override
    public Optional<TestTableEntity> findById(Integer integer) {
        TestTableEntity entity = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try{
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM test_table WHERE id = ?");
            statement.setInt(1, integer);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                entity = convertFrom(resultSet);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(resultSet, statement, connection);
        }

        return Optional.ofNullable(entity);
    }

    /*
    DELETE FROM table_name WHERE condition;
     */
    @Override
    public boolean delete(Integer integer) {
        Connection connection = null;
        PreparedStatement statement = null;
        int rows = 0;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("DELETE from test_table WHERE id = ?");
            statement.setInt(1, integer);
            rows = statement.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(statement, connection);
        }
        return rows > 0;
    }

    /*
    UPDATE table_name
    SET column1 = value1, column2 = value2, ...
    WHERE condition;
     */
    @Override
    public TestTableEntity update(TestTableEntity testTableEntity) {
        if(testTableEntity == null) throw new IllegalArgumentException("Entity was null");
        if(testTableEntity.getId() == 0) throw new IllegalStateException("It seems entity is not yet persisted");
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("UPDATE test_table SET description = ?, number = ? WHERE id = ?");
            statement.setString(1, testTableEntity.getDescription());
            statement.setInt(2, testTableEntity.getNumber());
            statement.setInt(3,testTableEntity.getId());
            statement.execute();

        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(statement, connection);
        }

        return testTableEntity;
    }
}
