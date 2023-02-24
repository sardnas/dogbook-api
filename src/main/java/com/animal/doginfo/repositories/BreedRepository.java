package com.animal.doginfo.repositories;

import com.animal.doginfo.models.Breed;
import com.animal.doginfo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BreedRepository  extends JpaRepository<Breed, Long> {
}
