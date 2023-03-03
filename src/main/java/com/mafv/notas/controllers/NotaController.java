
package com.mafv.notas.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

    @GetMapping(value="/notas/buscar")
    public List<Nota> findByCriteria(@RequestParam String titulo, 
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") Date fecha) {
        
        List<Nota> notas = notaService.findCriteria(titulo, fecha);

        return notas;
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
