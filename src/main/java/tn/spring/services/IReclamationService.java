package tn.spring.services;

import tn.spring.entities.Reclamation;

import java.util.List;

public interface IReclamationService {

    public Reclamation addReclamation(Reclamation reclamation,Long idClient);
    public void deleteReclamation(Long reclamationId);
    public List<Reclamation>getAllReclamations();
    public List<Reclamation>getAllReclamationsByIdClient(Long idClient);
}
