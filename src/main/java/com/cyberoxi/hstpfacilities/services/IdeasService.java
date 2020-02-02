package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.Unit;
import com.cyberoxi.hstpfacilities.models.Idea;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 1/28/2020
 */
public interface IdeasService {

    Iterable<Idea> getIdeas(long unitId);

    Idea getIdea(long id);

    Unit addIdea(long unitId, Idea idea);

    Idea updateIdea(long id, Idea idea);
}
