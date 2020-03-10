package com.cyberoxi.hstpfacilities.repositories;

import com.cyberoxi.hstpfacilities.models.Credential;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 3/9/2020
 */
@Repository
public interface CredentialRepository extends CrudRepository<Credential, Long> {

    Optional<Credential> findByUsername(String username);
}
