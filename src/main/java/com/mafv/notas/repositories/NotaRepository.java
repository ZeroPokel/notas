package com.mafv.notas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mafv.notas.models.Nota;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Integer>{
    
}
