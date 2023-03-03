package com.mafv.notas.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Nota {
    
    @Id
    @GeneratedValue
    private int id;

    @Column(length = 100, nullable = false)
    private String titulo;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-mm-dd", shape = JsonFormat.Shape.STRING, timezone = JsonFormat.DEFAULT_TIMEZONE,  locale="es_ES")
    private Date fecha;

    @Column(length = 1000, nullable = false)
    private String descripcion;

    public Nota() {
    }

    public Nota(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Nota other = (Nota) obj;
        if (id != other.id)
            return false;
        return true;
    }

    
}
