package com.tis2.AppRh.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;



@Entity
public class Rh extends User {
    
    @OneToMany(mappedBy = "responsavelRh")
    private List<VagasCriadas> vagasCriadas;
}
