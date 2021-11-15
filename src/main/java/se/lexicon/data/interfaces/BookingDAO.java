package se.lexicon.data.interfaces;

import se.lexicon.model.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingDAO extends GenericCRUD<Booking, String>{

    List<Booking> findByDateBetween(LocalDate start, LocalDate end);
    List<Booking> findByDateAfter(LocalDate start);
    List<Booking> findByDateBefore(LocalDate end);
    List<Booking> findByVaccineId(String vaccineId);
    List<Booking> findByVacantStatus(boolean vacantStatus);
    List<Booking> findByPremisesId(String premisesId);
    List<Booking> findByPatientId(String patientId);
    List<Booking> findByCity(String city);

}
