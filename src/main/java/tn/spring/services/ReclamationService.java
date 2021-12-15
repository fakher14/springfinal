package tn.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tn.spring.entities.Client;
import tn.spring.entities.Reclamation;
import tn.spring.repositories.ClientRepository;
import tn.spring.repositories.ReclamationRepository;

import java.util.List;
@Repository
public class ReclamationService implements IReclamationService{
    @Autowired
    ReclamationRepository reclamationRepository;

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Reclamation addReclamation(Reclamation reclamation,Long idClient) {
        Client client = clientRepository.findById(idClient).get();
        reclamation.setClient(client);
        return reclamationRepository.save(reclamation);
    }

    @Override
    public void deleteReclamation(Long reclamationId) {
        reclamationRepository.deleteById(reclamationId);
    }

    @Override
    public List<Reclamation> getAllReclamations() {
        return reclamationRepository.findAll();
    }

    @Override
    public List<Reclamation> getAllReclamationsByIdClient(Long idClient) {
        return reclamationRepository.findReclamationsByClientId(idClient);
    }
}
