package com.mafv.notas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mafv.notas.models.Permiso;


@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Integer>{
    
}
