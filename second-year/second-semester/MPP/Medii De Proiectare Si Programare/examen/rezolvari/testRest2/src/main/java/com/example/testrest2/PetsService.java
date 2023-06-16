package com.example.testrest2;

// PetsService.java
//

import com.example.testrest2.entities.Pets;
import org.springframework.stereotype.Service;
//
@Service
public class PetsService {
    //
    private final PetsRepository petsRepository;
    //
    public PetsService(PetsRepository petsRepository) {
        this.petsRepository = petsRepository;
    }
    //
    public Pets save(Pets pet) {
        return petsRepository.save(pet);
    }
}
