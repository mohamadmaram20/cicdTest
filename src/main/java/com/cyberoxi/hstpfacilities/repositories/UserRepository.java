package com.cyberoxi.hstpfacilities.repositories;

import com.cyberoxi.hstpfacilities.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 1/21/20
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByCredential_UsernameAndCredential_Password(String username, String password);

    Optional<User> findByCredential_Username(String username);
}
