package com.animal.doginfo.repositories;

import com.animal.doginfo.models.Breed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreedRepository  extends JpaRepository<Breed, Long> {
}
