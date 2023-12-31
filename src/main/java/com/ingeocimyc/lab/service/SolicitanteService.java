package com.ingeocimyc.lab.service;

import com.ingeocimyc.lab.persistence.entity.SolicitanteEntity;
import com.ingeocimyc.lab.persistence.repository.SolicitantePageSortRepository;
import com.ingeocimyc.lab.persistence.repository.SolicitanteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitanteService {
    public final SolicitanteRepository solicitanteRepository;
    public final SolicitantePageSortRepository solicitantePageSortRepository;
    @Autowired
    public SolicitanteService(SolicitanteRepository solicitanteRepository, SolicitantePageSortRepository solicitantePageSortRepository) {
        this.solicitanteRepository = solicitanteRepository;
        this.solicitantePageSortRepository = solicitantePageSortRepository;
    }

    public Page<SolicitanteEntity> getAll(int page, int elements){
        return solicitantePageSortRepository.findAll(PageRequest.of(page,elements));
    }
    public List<SolicitanteEntity> getByName(String nombre){
        return solicitanteRepository.findByName(nombre);
    }
    public Optional<SolicitanteEntity> getById(String id){
        return solicitanteRepository.findById(id);
    }
    public SolicitanteEntity create(SolicitanteEntity solicitante) {
       return solicitanteRepository.save(solicitante);
    }
    public SolicitanteEntity update(SolicitanteEntity newSolicitante) {
        String id =newSolicitante.getId();
        SolicitanteEntity oldSolicitante = solicitanteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("not found with ID: " + id));
        oldSolicitante.setBusiness(newSolicitante.getBusiness());
        oldSolicitante.setName(newSolicitante.getName());
        oldSolicitante.setEmail(newSolicitante.getEmail());
        oldSolicitante.setPhone(newSolicitante.getPhone());
        oldSolicitante.setLastname(newSolicitante.getLastname());
        return solicitanteRepository.save(oldSolicitante);
    }
    public void delete(String id){
        solicitanteRepository.deleteById(id);
    }
}
