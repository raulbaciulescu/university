package com.example.testrest2;

// PetsController.java
//

import com.example.testrest2.entities.Pets;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
//
import static org.springframework.web.bind.annotation.RequestMethod.POST;
//
@RepositoryRestController
public class PetsController {
    //
    private final PetsService petsService;
    //
    public PetsController(PetsService petsService) {
        this.petsService = petsService;
    }
    //
    @RequestMapping(method = POST, value = "/pets")
    public @ResponseBody ResponseEntity<?> createPets(@RequestBody Pets pet, HttpServletRequest request) {
        Pets createdPet = petsService.save(pet);
//
        URI createdUri = URI.create(request.getRequestURL().toString() + "/" + createdPet.getId());
        return ResponseEntity.created(createdUri).body(createdPet);
    }
}