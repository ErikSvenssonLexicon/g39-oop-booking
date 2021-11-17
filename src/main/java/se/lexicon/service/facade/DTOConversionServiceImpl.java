package se.lexicon.service.facade;

import se.lexicon.model.Booking;
import se.lexicon.model.Patient;
import se.lexicon.model.Premises;
import se.lexicon.model.UserCredentials;
import se.lexicon.model.dto.views.BookingDTO;
import se.lexicon.model.dto.views.PatientDTO;
import se.lexicon.model.dto.views.PremisesDTO;
import se.lexicon.model.dto.views.UserCredentialsDTO;

import java.util.List;
import java.util.stream.Collectors;

public class DTOConversionServiceImpl implements DTOConversionService{

    @Override
    public UserCredentialsDTO toFullDTO(UserCredentials userCredentials) {
        if(userCredentials == null) return null;

        UserCredentialsDTO dto = new UserCredentialsDTO();
        dto.setId(userCredentials.getId());
        dto.setUsername(userCredentials.getUsername());
        dto.setRole(userCredentials.getRole());

        return dto;
    }

    @Override
    public PatientDTO toFullDTO(Patient patient, List<Booking> bookings) {
        PatientDTO patientDTO = null;
        if(patient != null){
            patientDTO = toMinifiedDTO(patient);
            patientDTO.setCredentials(toFullDTO(patient.getCredentials()));
            patientDTO.setContactInfo(patient.getContactInfo());
            patientDTO.setBookings(
                    bookings.stream()
                            .map(this::toMinifiedDTO)
                            .collect(Collectors.toList())
            );
        }

        return patientDTO;
    }

    @Override
    public PatientDTO toMinifiedDTO(Patient patient) {
        PatientDTO patientDTO = null;
        if(patient != null){
            patientDTO = new PatientDTO();
            patientDTO.setId(patient.getId());
            patientDTO.setFirstName(patient.getFirstName());
            patientDTO.setLastName(patient.getLastName());
            patientDTO.setBirthDate(patient.getBirthDate());
            patientDTO.setSsn(patient.getSsn());
        }
        return patientDTO;
    }

    @Override
    public BookingDTO toFullDTO(Booking booking) {
        BookingDTO bookingDTO = null;
        if(booking != null){
            bookingDTO = toMinifiedDTO(booking);
            bookingDTO.setAdministrator(booking.getAdministrator());
            bookingDTO.setPremises(toMinifiedDTO(booking.getPremises()));
            bookingDTO.setPatient(toMinifiedDTO(booking.getPatient()));
        }
        return bookingDTO;
    }

    @Override
    public BookingDTO toMinifiedDTO(Booking booking) {
        BookingDTO bookingDTO = null;
        if(booking != null){
            bookingDTO = new BookingDTO();
            bookingDTO.setId(booking.getId());
            bookingDTO.setDateTime(booking.getDateTime());
            bookingDTO.setPrice(booking.getPrice());
            bookingDTO.setVaccineId(booking.getVaccineId());
            bookingDTO.setVacant(booking.isVacant());
        }
        return bookingDTO;
    }

    @Override
    public PremisesDTO toFullDTO(Premises premises, List<Booking> bookings) {
        PremisesDTO premisesDTO = null;
        if(premises != null){
            premisesDTO = toMinifiedDTO(premises);
            premisesDTO.setContactInfo(premises.getContactInfo());
            premisesDTO.setBookings(
                    bookings.stream()
                            .map(this::toMinifiedDTO)
                            .collect(Collectors.toList())
            );
        }

        return premisesDTO;
    }

    @Override
    public PremisesDTO toMinifiedDTO(Premises premises) {
        PremisesDTO premisesDTO = null;
        if(premises != null){
            premisesDTO = new PremisesDTO();
            premisesDTO.setId(premises.getId());
            premisesDTO.setName(premises.getName());
            premisesDTO.setAddress(premises.getAddress());
        }
        return premisesDTO;
    }


}
