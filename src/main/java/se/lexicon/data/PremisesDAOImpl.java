package se.lexicon.data;

import se.lexicon.data.interfaces.PremisesDAO;
import se.lexicon.io.JSONManager;
import se.lexicon.model.Premises;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import static se.lexicon.io.URLConstants.PREMISES_JSON;

public class PremisesDAOImpl implements PremisesDAO {

    private static PremisesDAOImpl INSTANCE;

    public static PremisesDAO getInstance(){
        if(INSTANCE == null) INSTANCE = new PremisesDAOImpl(null);
        return INSTANCE;
    }

    static PremisesDAOImpl getTestInstance(Collection<Premises> premises){
        if(premises == null) premises = new ArrayList<>();
        return new PremisesDAOImpl(premises);
    }

    private PremisesDAOImpl(Collection<Premises> premises){
        this.premisesSet = premises == null ? new HashSet<>(JSONManager.getInstance().deserializeFromJSON(new File(PREMISES_JSON), Premises.class)) : new HashSet<>(premises);
    }

    private final Set<Premises> premisesSet;

    @Override
    public Premises create(Premises premises) {
        if(premises == null) throw new IllegalArgumentException("Premises was null");
        if(premises.getId() == null) throw new IllegalArgumentException("Premises.id was null");
        premisesSet.add(premises);
        return premises;
    }

    @Override
    public List<Premises> findAll() {
        return new ArrayList<>(premisesSet);
    }

    @Override
    public Optional<Premises> findById(final String id) {
        return premisesSet.stream()
                .filter(premises -> premises.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean delete(String id) {
        return findById(id).map(premisesSet::remove).orElse(false);
    }

    @Override
    public List<Premises> findByName(final String name) {
        return premisesSet.stream()
                .filter(premises -> premises.getName().toLowerCase().startsWith(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Premises> findByCity(final String city) {
        return premisesSet.stream()
                .filter(premises -> premises.getAddress().getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    @Override
    public List<Premises> findByZipCode(String zipCode) {
        return premisesSet.stream()
                .filter(premises -> premises.getAddress().getZipCode().replaceAll(" ", "").equals(zipCode.replaceAll(" ","")))
                .collect(Collectors.toList());
    }
}
