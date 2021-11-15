package se.lexicon.data;

import se.lexicon.data.interfaces.AddressDAO;
import se.lexicon.model.Address;

import java.util.*;
import java.util.stream.Collectors;

public class AddressDAOImpl implements AddressDAO {

    private static final AddressDAOImpl INSTANCE;

    static {
        INSTANCE = new AddressDAOImpl(null);
    }

    private AddressDAOImpl(Collection<Address> addresses){
        this.addresses = addresses == null ? new HashSet<>() : new HashSet<>(addresses);
    }

    public static AddressDAO getInstance(){
        return INSTANCE;
    }

    static AddressDAOImpl getTestInstance(Collection<Address> addresses){
        return new AddressDAOImpl(addresses);
    }

    private final Set<Address> addresses;

    @Override
    public List<Address> findByStreetAddress(final String streetAddress) {
        return addresses.stream()
                .filter(address -> address.getStreetAddress().toLowerCase().startsWith(streetAddress.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Address> findByZipCode(final String zipCode) {
        return addresses.stream()
                .filter(address -> address.getZipCode().replaceAll(" ", "").equals(zipCode.replaceAll(" ", "")))
                .collect(Collectors.toList());
    }

    @Override
    public List<Address> findByCity(final String city) {
        return addresses.stream()
                .filter(address -> address.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    @Override
    public Address create(Address address) {
        if(address == null) throw new IllegalArgumentException("Address was null");
        if(address.getId() == null) throw new IllegalArgumentException("Address.id was null");
        addresses.add(address);
        return address;
    }

    @Override
    public List<Address> findAll() {
        return new ArrayList<>(addresses);
    }

    @Override
    public Optional<Address> findById(final String id) {
        return addresses.stream()
                .filter(address -> address.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean delete(String id) {
        return findById(id).map(addresses::remove).orElse(false);
    }
}
