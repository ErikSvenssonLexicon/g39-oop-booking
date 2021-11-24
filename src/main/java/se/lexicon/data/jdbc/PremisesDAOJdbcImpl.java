package se.lexicon.data.jdbc;

import se.lexicon.data.AbstractDAO;
import se.lexicon.data.interfaces.PremisesDAO;
import se.lexicon.model.Premises;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PremisesDAOJdbcImpl extends AbstractDAO implements PremisesDAO {

    @Override
    public Premises create(Premises premises) {
        if(premises == null) throw new IllegalArgumentException("Entity was null");
        if(premises.getId() == null) throw new IllegalArgumentException("Entity id was null");
        if(premises.getContactInfo() != null && premises.getContactInfo().getId() == null) throw new IllegalArgumentException("premises.contactInfo.id was null");
        if(premises.getAddress() != null && premises.getAddress().getId() == null) throw new IllegalArgumentException("premises.address.id was null");
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("INSERT INTO premises (id, name, fk_contact_info, fk_address) VALUES (?, ?, ?, ?)");
            statement.setString(1, premises.getId());
            statement.setString(2, premises.getName());
            statement.setString(3, premises.getContactInfo() == null ? null : premises.getContactInfo().getId());
            statement.setString(4, premises.getAddress() == null ? null : premises.getAddress().getId());
            statement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(statement, connection);
        }
        return premises;
    }

    @Override
    public List<Premises> findAll() {
        List<Premises> result = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT id, name FROM premises");
            while(resultSet.next()){
                result.add(mapPremises(resultSet));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(resultSet, statement, connection);
        }
        return result;
    }

    @Override
    public Optional<Premises> findById(String id) {
        Premises result = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("SELECT id, name FROM premises WHERE id = ?");
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                result = mapPremises(resultSet);
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
            statement = connection.prepareStatement("DELETE FROM premises WHERE id = ?");
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
    public List<Premises> findByName(String name) {
        List<Premises> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("SELECT id, name FROM premises WHERE UPPER(name) = UPPER(?)");
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                result.add(mapPremises(resultSet));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(resultSet, statement, connection);
        }
        return result;
    }

    @Override
    public List<Premises> findByCity(String city) {
        List<Premises> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("SELECT premises.id AS id, name FROM premises " +
                    "JOIN address a ON a.id = premises.fk_address " +
                    "WHERE UPPER(city) = UPPER(?)");
            statement.setString(1, city);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                result.add(mapPremises(resultSet));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(resultSet, statement, connection);
        }
        return result;
    }

    @Override
    public List<Premises> findByZipCode(String zipCode) {
        List<Premises> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT premises.id AS id, name FROM premises " +
                    "JOIN address a ON premises.fk_address = a.id " +
                    "WHERE zip_code = ?");
            statement.setString(1, zipCode);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                result.add(mapPremises(resultSet));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(resultSet, statement, connection);
        }
        return result;
    }

    @Override
    public Premises update(Premises premises) {
        if(premises == null) throw new IllegalArgumentException("Entity was null");
        if(premises.getId() == null) throw new IllegalArgumentException("Entity id was null");
        if(premises.getAddress() != null && premises.getAddress().getId() == null) throw new IllegalArgumentException("premises.address.id was null");
        if(premises.getContactInfo() != null && premises.getContactInfo().getId() == null) throw new IllegalArgumentException("premises.contactInfo.id was null");
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("UPDATE premises SET name = ?, fk_contact_info = ?, fk_address = ? WHERE id = ?");
            statement.setString(1, premises.getName());
            statement.setString(2, premises.getContactInfo() == null ? null : premises.getContactInfo().getId());
            statement.setString(3, premises.getAddress() == null ? null : premises.getAddress().getId());
            statement.setString(4, premises.getId());
            statement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(statement, connection);
        }
        return premises;
    }

}
