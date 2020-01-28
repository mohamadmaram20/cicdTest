package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.Company;
import com.cyberoxi.hstpfacilities.models.Idea;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 1/28/2020
 */
public interface IdeasService {

    Iterable<Idea> getIdeas(long companyId);

    Idea getIdea(long id);

    Company addIdea(long companyId, Idea idea);

    Idea updateIdea(long id, Idea idea);
}
