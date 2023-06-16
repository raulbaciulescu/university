package com.example.marire.domain;

import java.time.LocalDateTime;

public class Nevoie extends Entity<Long>{
    private String nevoie;
    private String descriere;
    private LocalDateTime deadline;
    private long omInNevoie;
    private long omSalvator;
    private String status;
}
