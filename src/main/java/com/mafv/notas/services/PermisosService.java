package com.mafv.notas.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafv.notas.models.Permiso;
import com.mafv.notas.repositories.PermisoRepository;


@Service
public class PermisosService {

    @Autowired
    private PermisoRepository permissionRepository;

    public Permiso createPermiso(Permiso permission) {
        return permissionRepository.save(permission);
    }

    public Permiso updatePermiso(Permiso permission) {
        return permissionRepository.save(permission);
    }

    public void deletePermiso(Integer permissionId) {
        permissionRepository.deleteById(permissionId);
    }

    public Permiso getPermiso(Integer permissionId) {
        return permissionRepository.findById(permissionId).orElse(null);
    }

    public List<Permiso> getAllPermisos() {
        return permissionRepository.findAll();
    }
}
