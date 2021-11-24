package se.lexicon.data.jdbc;

import se.lexicon.data.AbstractDAO;
import se.lexicon.data.interfaces.PatientDAO;
import se.lexicon.model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientDAOJdbcImpl extends AbstractDAO implements PatientDAO {
    @Override
    public Patient create(Patient patient) {
        if(patient == null) throw new IllegalArgumentException("Entity was null");
        if(patient.getId() == null ) throw new IllegalArgumentException("Entity was null");
        if(patient.getCredentials() == null || patient.getCredentials().getId() == null) throw new IllegalArgumentException("Entity patient.userDetails or it's id was null");
        String contactId = null;
        if(patient.getContactInfo() != null){
            contactId = patient.getContactInfo().getId();
        }


        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("INSERT INTO patient (id, ssn, first_name, last_name, birth_date, fk_user_credentials, fk_contact_info)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, patient.getId());
            statement.setString(2, patient.getSsn());
            statement.setString(3, patient.getFirstName());
            statement.setString(4, patient.getLastName());
            statement.setObject(5, patient.getBirthDate());
            statement.setString(6, patient.getCredentials().getId());
            statement.setString(7, contactId);

            statement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(statement, connection);
        }
        return patient;
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> result = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT id, ssn, first_name, last_name, birth_date FROM patient");
            while (resultSet.next()){
                result.add(
                        mapPatient(resultSet)
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
    public Optional<Patient> findById(String id) {
        Patient result = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("SELECT id, ssn, first_name, last_name, birth_date FROM patient WHERE id = ?");
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                result = mapPatient(resultSet);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(resultSet, statement, connection);
        }
        return Optional.ofNullable(result);
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public Optional<Patient> findBySSN(String ssn) {
        return Optional.empty();
    }

    @Override
    public List<Patient> findByName(String name) {
        return null;
    }

    @Override
    public Optional<Patient> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<Patient> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Patient update(Patient patient) {
        return null;
    }
}
