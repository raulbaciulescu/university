package com.example.marire.service;

import com.example.marire.domain.Persoana;
import com.example.marire.repo.PersoanaRepo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

public class PersoanaService {
    PersoanaRepo persoanaRepo;
    private static long id = 10000;
    public PersoanaService(PersoanaRepo repo) {
        persoanaRepo = repo;
    }

    public Optional<Persoana> add(String nume, String prenume, String username, String parola,
                                  String oras, String strada, String numarStrada, String telefon) {
        Persoana pers = new Persoana(id,
                nume, prenume, username, parola, oras, strada, numarStrada, telefon
        );
        persoanaRepo.save(pers);
        return Optional.of(pers);
    }

    public ArrayList<Persoana> getAll() {
        return (ArrayList<Persoana>) persoanaRepo.findAll();
    }
}
