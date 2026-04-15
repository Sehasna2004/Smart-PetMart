
package com.pawmart.petcare_backend.repository;

import com.pawmart.petcare_backend.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    // This interface allows the Controller to use .save() and .findAll()
}