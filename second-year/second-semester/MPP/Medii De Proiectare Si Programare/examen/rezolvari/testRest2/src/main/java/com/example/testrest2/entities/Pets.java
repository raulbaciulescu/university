package com.example.testrest2.entities;


//
import lombok.Data;
//
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;
//
@Entity(name = "PETS")
@Data
public class Pets {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String type;
    private Boolean indoor = true;
}
