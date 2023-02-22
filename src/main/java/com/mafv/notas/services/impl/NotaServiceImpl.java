package com.mafv.notas.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafv.notas.models.Nota;
import com.mafv.notas.repositories.NotaRepository;
import com.mafv.notas.services.NotaService;

@Service
public class NotaServiceImpl implements NotaService{

    @Autowired
    NotaRepository notaRepository;

    @Override
    public List<Nota> findAll() {
        
        return notaRepository.findAll();
    }

    @Override
    public Nota findById(int id) {
        Optional<Nota> findById = notaRepository.findById(id);
        if(findById != null){
            return findById.get();
        }
        return null;
    }

    @Override
    public Nota save(Nota nota) {
        return notaRepository.save(nota);
    }

    @Override
    public void update(int id, Nota nota) {
        this.findById(id);
        nota.setId(id);
        notaRepository.save(nota);
    }

    @Override
    public void deleteById(int id) {
        notaRepository.deleteById(id);
        
    }

    @Override
    public void deleteAll() {
        notaRepository.deleteAll();
    }

    @Override
    public List<Nota> findCriteria(String titulo, Date fecha) {
        List<Nota> notasCriteria = notaRepository.findByTituloContainingAndFechaGreaterThan(titulo, fecha);

        return notasCriteria;
    }
    
}
