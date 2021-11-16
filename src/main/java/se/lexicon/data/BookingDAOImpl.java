package se.lexicon.data;

import se.lexicon.data.interfaces.BookingDAO;
import se.lexicon.io.JSONManager;
import se.lexicon.model.Booking;

import java.io.File;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static se.lexicon.io.URLConstants.BOOKINGS_JSON;

public class BookingDAOImpl implements BookingDAO {

    private static BookingDAOImpl INSTANCE;

    public static BookingDAO getInstance(){
        if(INSTANCE == null) INSTANCE = new BookingDAOImpl(null);
        return INSTANCE;
    }

    static BookingDAOImpl getTestInstance(Collection<Booking> bookings){
        if(bookings == null) bookings = new ArrayList<>();
        return new BookingDAOImpl(bookings);
    }

    private BookingDAOImpl(Collection<Booking> bookings){
        this.bookings = bookings == null ? new HashSet<>(JSONManager.getInstance().deserializeFromJSON(new File(BOOKINGS_JSON), Booking.class)) : new HashSet<>(bookings);
    }

    private final Set<Booking> bookings;

    @Override
    public List<Booking> findByDateBetween(final LocalDate start, final LocalDate end) {
        List<Booking> bookings = new ArrayList<>();
        for(Booking booking : this.bookings){
            LocalDate date = booking.getDateTime().toLocalDate();

            if((date.isAfter(start) || date.isEqual(start)) && (date.isBefore(end) || date.isEqual(end))){
                bookings.add(booking);
            }
        }
        return bookings;
    }

    @Override
    public List<Booking> findByDateAfter(final LocalDate start) {
        return bookings.stream()
                .filter(booking -> booking.getDateTime().toLocalDate().equals(start) || booking.getDateTime().toLocalDate().isAfter(start))
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findByDateBefore(final LocalDate end) {
        return bookings.stream()
                .filter(booking -> booking.getDateTime().toLocalDate().equals(end) || booking.getDateTime().toLocalDate().isBefore(end))
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findByVaccineId(final String vaccineId) {
        return bookings.stream()
                .filter(booking -> booking.getVaccineId().equals(vaccineId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findByVacantStatus(final boolean vacantStatus) {
        return bookings.stream()
                .filter(booking -> booking.isVacant() == vacantStatus)
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findByPremisesId(final String premisesId) {
        return bookings.stream()
                .filter(booking -> booking.getPremises().getId().equals(premisesId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findByPatientId(final String patientId) {
        return bookings.stream()
                .filter(booking -> booking.getPatient() != null && booking.getPatient().getId().equals(patientId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findByCity(final String city) {
        return bookings.stream()
                .filter(booking -> booking.getPremises().getAddress().getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    @Override
    public Booking create(Booking booking) {
        if(booking == null) throw new IllegalArgumentException("Booking was null");
        if(booking.getId() == null) throw new IllegalArgumentException("Booking.id was null");
        bookings.add(booking);
        return booking;
    }

    @Override
    public List<Booking> findAll() {
        return new ArrayList<>(bookings);
    }

    @Override
    public Optional<Booking> findById(final String id) {
        return bookings.stream()
                .filter(booking -> booking.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean delete(String id) {
       return findById(id).map(bookings::remove).orElse(false);
    }
}
