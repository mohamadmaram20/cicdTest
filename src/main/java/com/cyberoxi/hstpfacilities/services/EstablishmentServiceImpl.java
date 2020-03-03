package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.Unit;
import com.cyberoxi.hstpfacilities.models.Establishment;
import com.cyberoxi.hstpfacilities.repositories.UnitRepository;
import com.cyberoxi.hstpfacilities.repositories.EstablishmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 1/28/2020
 */
@Service
public class EstablishmentServiceImpl implements EstablishmentService {

    private UnitRepository unitRepository;
    private EstablishmentRepository establishmentRepository;

    @Autowired
    public EstablishmentServiceImpl(UnitRepository unitRepository, EstablishmentRepository establishmentRepository) {
        this.unitRepository = unitRepository;
        this.establishmentRepository = establishmentRepository;
    }

    @Override
    public Iterable<Establishment> getEstablishments(long unitId) {
        return unitRepository.findById(unitId).get().getEstablishments();
    }

    @Override
    public Establishment getEstablishment(long id) {
        return establishmentRepository.findById(id).get();
    }

    @Override
    public Unit addEstablishment(long unitId, Establishment establishment) {
        Unit findUnit = unitRepository.findById(unitId).get();
        findUnit.getEstablishments().add(establishment);
        return unitRepository.save(findUnit);
    }

    @Override
    public Establishment updateEstablishment(long id, Establishment establishment) {
        Establishment findEstablishment = establishmentRepository.findById(id).get();
        // TODO: 1/28/2020 Update fields
        return establishmentRepository.save(findEstablishment);
    }
}
