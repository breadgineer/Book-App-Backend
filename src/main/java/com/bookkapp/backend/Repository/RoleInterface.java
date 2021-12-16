package com.bookkapp.backend.Repository;

import com.bookkapp.backend.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleInterface extends MongoRepository<Role, String> {

    Role findByRole(String role);
}
