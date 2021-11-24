package se.lexicon.data.jdbc;

import se.lexicon.data.AbstractDAO;
import se.lexicon.data.interfaces.ContactInfoDAO;
import se.lexicon.model.ContactInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactInfoDAOJdbcImpl extends AbstractDAO implements ContactInfoDAO {

    @Override
    public Optional<ContactInfo> findByEmail(String email) {
        ContactInfo contactInfo = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM contact_info WHERE UPPER(email) = UPPER(?)");
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                contactInfo = mapContactInfo(resultSet);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(resultSet, statement, connection);
        }

        return Optional.ofNullable(contactInfo);
    }

    @Override
    public ContactInfo create(ContactInfo contactInfo) {
        if(contactInfo == null) throw new IllegalArgumentException("Entity was null");
        if(contactInfo.getId() == null) throw new IllegalArgumentException("Entity id was null");

        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("INSERT INTO contact_info (id, email, phone) VALUES (?,?,?)");
            statement.setString(1, contactInfo.getId());
            statement.setString(2, contactInfo.getEmail());
            statement.setString(3, contactInfo.getPhone());
            statement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(statement, connection);
        }
        return contactInfo;
    }

    @Override
    public List<ContactInfo> findAll() {
        List<ContactInfo> result = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM contact_info");
            while(resultSet.next()){
                result.add(mapContactInfo(resultSet));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(resultSet, statement, connection);
        }
        return result;
    }

    @Override
    public Optional<ContactInfo> findById(String id) {
        ContactInfo result = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM contact_info WHERE id = ?");
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                result = mapContactInfo(resultSet);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(resultSet, statement, connection);
        }
        return Optional.ofNullable(result);
    }

    @Override
    public boolean delete(String id) {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("DELETE FROM contact_info WHERE id = ?");
            statement.setString(1, id);
            rowsAffected = statement.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(statement, connection);
        }
        return rowsAffected > 0;
    }

    @Override
    public ContactInfo update(ContactInfo contactInfo) {
        if(contactInfo == null) throw new IllegalArgumentException("Entity was null");
        if(contactInfo.getId() == null) throw new IllegalArgumentException("Entity id was null");
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("UPDATE contact_info SET email = ?, phone = ? WHERE id = ?");
            statement.setString(1, contactInfo.getEmail());
            statement.setString(2, contactInfo.getPhone());
            statement.setString(3, contactInfo.getId());
            statement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(statement, connection);
        }
        return contactInfo;
    }
}
