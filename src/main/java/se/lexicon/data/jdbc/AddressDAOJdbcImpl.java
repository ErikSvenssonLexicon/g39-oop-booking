package se.lexicon.data.jdbc;

import se.lexicon.data.AbstractDAO;
import se.lexicon.data.interfaces.AddressDAO;
import se.lexicon.model.Address;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddressDAOJdbcImpl extends AbstractDAO implements AddressDAO {
    @Override
    public List<Address> findByStreetAddress(String streetAddress) {
        List<Address> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM address WHERE UPPER(street) = UPPER(?)");
            statement.setString(1, streetAddress);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                result.add(mapAddress(resultSet));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(resultSet, statement, connection);
        }
        return result;
    }

    @Override
    public List<Address> findByZipCode(String zipCode) {
        List<Address> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM address WHERE zip_code = ?");
            statement.setString(1, zipCode);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                result.add(mapAddress(resultSet));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(resultSet, statement, connection);
        }
        return result;
    }

    @Override
    public List<Address> findByCity(String city) {
        List<Address> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM address WHERE UPPER(city) = UPPER(?)");
            statement.setString(1, city);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                result.add(mapAddress(resultSet));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(resultSet, statement, connection);
        }
        return result;
    }

    @Override
    public long countReferencesToAddressId(String id) {
        long count = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("SELECT COUNT(fk_address) as count FROM premises WHERE fk_address = ?");
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                count = resultSet.getLong("count");
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(resultSet, statement, connection);
        }
        return count;
    }

    @Override
    public Optional<Address> findByStreetZipCodeAndCity(String street, String zipCode, String city) {
        Address result = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM address WHERE UPPER(street) = UPPER(?) AND zip_code = UPPER(?) AND UPPER(city) = UPPER(?)");
            statement.setString(1, street);
            statement.setString(2, zipCode);
            statement.setString(3, city);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                result = mapAddress(resultSet);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(resultSet, statement, connection);
        }
        return Optional.ofNullable(result);
    }

    @Override
    public Address create(Address address) {
        if(address == null) throw new IllegalArgumentException("Entity was null");
        if(address.getId() == null) throw new IllegalArgumentException("Entity id was null");
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("INSERT INTO address (id, street, zip_code, city) VALUES (?,?,?,?)");
            statement.setString(1, address.getId());
            statement.setString(2, address.getStreetAddress());
            statement.setString(3, address.getZipCode());
            statement.setString(4, address.getCity());
            statement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(statement, connection);
        }
        return address;
    }

    @Override
    public List<Address> findAll() {
        List<Address> result = new ArrayList<>();
        try(
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM address")
        ){
            while(resultSet.next()){
                result.add(mapAddress(resultSet));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public Optional<Address> findById(String id) {
        Address result = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM address WHERE id = ?");
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                result = mapAddress(resultSet);
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
        int rowsDeleted = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("DELETE FROM address WHERE id = ?");
            statement.setString(1, id);
            rowsDeleted = statement.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(statement, connection);
        }
        return rowsDeleted > 0;
    }
}
