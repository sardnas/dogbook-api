package com.animal.doginfo.repositories;
import java.util.Optional;

import com.animal.doginfo.models.ERole;
import com.animal.doginfo.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}