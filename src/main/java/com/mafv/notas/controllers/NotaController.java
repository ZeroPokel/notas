
package com.mafv.notas.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mafv.notas.models.Nota;
import com.mafv.notas.services.NotaService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class NotaController {
    
    @Autowired
    NotaService notaService;

    @GetMapping("/notas")
    List<Nota> all(){
        return notaService.findAll();
    }
    
    @GetMapping("/notas/{id}")
    Nota findNota(@PathVariable int id){
        return notaService.findById(id);
    }

    @GetMapping(value="/notas/buscar/{texto}")
    public List<Nota> findByTitulo(@PathVariable String texto) {
        List<Nota> notas = notaService.findAll();
        List<Nota> notasEncontradas = new ArrayList<Nota>();

        for (Nota nota : notas){
            if (nota.getTitulo().contains(texto)){
                notasEncontradas.add(nota);
            }
        }

        return notasEncontradas;
    }

    @DeleteMapping("/notas/{id}")
    void deleteNota(@PathVariable int id){
        notaService.deleteById(id);
    }

    @PostMapping("/notas")
    Nota crearNota(@RequestBody Nota nota){
        notaService.save(nota);
        return nota;
    }

    @PutMapping("/notas/{id}")
    void modificarNota(@PathVariable int id, @RequestBody Nota nota){
        notaService.update(id, nota);
    }

    
}
