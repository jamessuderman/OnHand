/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 01/24/2021
 */

package com.sudee.gcu.onhand.repositories;

import com.sudee.gcu.onhand.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String name);
}
