package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.Unit;
import com.cyberoxi.hstpfacilities.models.Idea;
import com.cyberoxi.hstpfacilities.repositories.UnitRepository;
import com.cyberoxi.hstpfacilities.repositories.IdeaRepository;
import org.springframework.stereotype.Service;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 1/28/2020
 */
@Service
public class IdeaServiceImpl implements IdeaService {

    private UnitRepository unitRepository;
    private IdeaRepository ideaRepository;

    public IdeaServiceImpl(UnitRepository unitRepository, IdeaRepository ideaRepository) {
        this.unitRepository = unitRepository;
        this.ideaRepository = ideaRepository;
    }

    @Override
    public Iterable<Idea> getIdeas(long unitId) {
        return unitRepository.findById(unitId).get().getIdeas();
    }

    @Override
    public Idea getIdea(long id) {
        return ideaRepository.findById(id).get();
    }

    @Override
    public Unit addIdea(long unitId, Idea idea) {
        Unit findUnit = unitRepository.findById(unitId).get();
        findUnit.getIdeas().add(idea);
        return unitRepository.save(findUnit);
    }

    @Override
    public Idea updateIdea(long id, Idea idea) {
        Idea findIdea = ideaRepository.findById(id).get();
        // TODO: 1/28/2020 Update fields
        return ideaRepository.save(findIdea);
    }
}
