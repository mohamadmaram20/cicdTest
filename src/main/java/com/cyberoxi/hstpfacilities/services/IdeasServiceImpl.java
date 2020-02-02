package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.Unit;
import com.cyberoxi.hstpfacilities.models.Idea;
import com.cyberoxi.hstpfacilities.repositories.UnitsRepository;
import com.cyberoxi.hstpfacilities.repositories.IdeasRepository;
import org.springframework.stereotype.Service;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 1/28/2020
 */
@Service
public class IdeasServiceImpl implements IdeasService {

    private UnitsRepository unitsRepository;
    private IdeasRepository ideasRepository;

    public IdeasServiceImpl(UnitsRepository unitsRepository, IdeasRepository ideasRepository) {
        this.unitsRepository = unitsRepository;
        this.ideasRepository = ideasRepository;
    }

    @Override
    public Iterable<Idea> getIdeas(long unitId) {
        return unitsRepository.findById(unitId).get().getIdeas();
    }

    @Override
    public Idea getIdea(long id) {
        return ideasRepository.findById(id).get();
    }

    @Override
    public Unit addIdea(long unitId, Idea idea) {
        Unit findUnit = unitsRepository.findById(unitId).get();
        findUnit.getIdeas().add(idea);
        return unitsRepository.save(findUnit);
    }

    @Override
    public Idea updateIdea(long id, Idea idea) {
        Idea findIdea = ideasRepository.findById(id).get();
        // TODO: 1/28/2020 Update fields
        return ideasRepository.save(findIdea);
    }
}
