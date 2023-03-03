package com.mafv.notas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mafv.notas.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    
    Usuario findByNombre(String username);
}
