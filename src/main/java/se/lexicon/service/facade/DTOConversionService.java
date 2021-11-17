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

public interface DTOConversionService {
    UserCredentialsDTO toFullDTO(UserCredentials userCredentials);
    PatientDTO toFullDTO(Patient patient, List<Booking> bookings);
    PatientDTO toMinifiedDTO(Patient patient);
    BookingDTO toFullDTO(Booking booking);
    BookingDTO toMinifiedDTO(Booking booking);
    PremisesDTO toFullDTO(Premises premises, List<Booking> bookings);
    PremisesDTO toMinifiedDTO(Premises premises);


}
