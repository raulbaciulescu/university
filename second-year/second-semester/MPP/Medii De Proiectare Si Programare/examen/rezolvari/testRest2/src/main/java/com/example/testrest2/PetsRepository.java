package com.example.testrest2;
// PetsRepository.java
//

import com.example.testrest2.entities.Pets;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//
import java.util.List;
import java.util.UUID;
//
@RepositoryRestResource(collectionResourceRel = "pets", path = "pets")
public interface PetsRepository extends PagingAndSortingRepository<Pets, UUID> {
    //
    List findByName(@Param("name") String name);
}