package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.Unit;
import com.cyberoxi.hstpfacilities.models.Establishment;
import com.cyberoxi.hstpfacilities.repositories.UnitsRepository;
import com.cyberoxi.hstpfacilities.repositories.EstablishmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 1/28/2020
 */
@Service
public class EstablishmentServiceImpl implements EstablishmentService {

    private UnitsRepository unitsRepository;
    private EstablishmentsRepository establishmentsRepository;

    @Autowired
    public EstablishmentServiceImpl(UnitsRepository unitsRepository, EstablishmentsRepository establishmentsRepository) {
        this.unitsRepository = unitsRepository;
        this.establishmentsRepository = establishmentsRepository;
    }

    @Override
    public Iterable<Establishment> getEstablishments(long unitId) {
        return unitsRepository.findById(unitId).get().getEstablishments();
    }

    @Override
    public Establishment getEstablishment(long id) {
        return establishmentsRepository.findById(id).get();
    }

    @Override
    public Unit addEstablishment(long unitId, Establishment establishment) {
        Unit findUnit = unitsRepository.findById(unitId).get();
        findUnit.getEstablishments().add(establishment);
        return unitsRepository.save(findUnit);
    }

    @Override
    public Establishment updateEstablishment(long id, Establishment establishment) {
        Establishment findEstablishment = establishmentsRepository.findById(id).get();
        // TODO: 1/28/2020 Update fields
        return establishmentsRepository.save(findEstablishment);
    }
}
