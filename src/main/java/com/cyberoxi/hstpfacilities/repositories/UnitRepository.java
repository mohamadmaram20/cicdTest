package com.cyberoxi.hstpfacilities.repositories;

import com.cyberoxi.hstpfacilities.models.Unit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 1/22/2020
 */
@Repository
public interface UnitRepository extends CrudRepository<Unit, Long> {

    Optional<Unit> findByUsernameAndPassword(String username, String password);
}
