package com.mvuchevski.ilibrary.repositories;

import com.mvuchevski.ilibrary.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Iterable<Role> findAllByName(String role);
}
