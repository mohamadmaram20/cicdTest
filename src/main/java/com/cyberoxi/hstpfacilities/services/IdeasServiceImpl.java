package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.Company;
import com.cyberoxi.hstpfacilities.models.Idea;
import com.cyberoxi.hstpfacilities.repositories.CompaniesRepository;
import com.cyberoxi.hstpfacilities.repositories.FacilitiesRepository;
import com.cyberoxi.hstpfacilities.repositories.IdeasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 1/28/2020
 */
@Service
public class IdeasServiceImpl implements IdeasService {

    private CompaniesRepository companiesRepository;
    private IdeasRepository ideasRepository;

    public IdeasServiceImpl(CompaniesRepository companiesRepository, IdeasRepository ideasRepository) {
        this.companiesRepository = companiesRepository;
        this.ideasRepository = ideasRepository;
    }

    @Override
    public Iterable<Idea> getIdeas(long companyId) {
        return companiesRepository.findById(companyId).get().getIdeas();
    }

    @Override
    public Idea getIdea(long id) {
        return ideasRepository.findById(id).get();
    }

    @Override
    public Company addIdea(long companyId, Idea idea) {
        Company findCompany = companiesRepository.findById(companyId).get();
        findCompany.getIdeas().add(idea);
        return companiesRepository.save(findCompany);
    }

    @Override
    public Idea updateIdea(long id, Idea idea) {
        Idea findIdea = ideasRepository.findById(id).get();
        // TODO: 1/28/2020 Update fields
        return ideasRepository.save(findIdea);
    }
}
