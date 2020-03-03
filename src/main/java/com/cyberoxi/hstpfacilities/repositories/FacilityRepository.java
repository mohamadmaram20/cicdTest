package com.cyberoxi.hstpfacilities.repositories;

import com.cyberoxi.hstpfacilities.models.Facility;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 1/23/2020
 */
@Repository
public interface FacilityRepository extends CrudRepository<Facility, Long> {
}
