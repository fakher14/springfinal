package tn.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.spring.entities.Reclamation;
import tn.spring.entities.Stock;
import tn.spring.services.IReclamationService;
import tn.spring.services.IStockService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/reclamations")
public class ReclamationController {

    @Autowired
    IReclamationService reclamationService ;

    @GetMapping("/get-all")
    @ResponseBody
    public List<Reclamation> getReclamations(){
        return reclamationService.getAllReclamations();
    }

    @GetMapping("/getByIdClient/{id}")
    @ResponseBody
    public List<Reclamation> getReclamationsByClient(@PathVariable("id")Long idClient){
        return reclamationService.getAllReclamationsByIdClient(idClient);
    }

    @PostMapping("/add/{id}")
    @ResponseBody
    public Reclamation addReclamation(@RequestBody Reclamation reclamation,@PathVariable("id")Long idClient){
        return reclamationService.addReclamation(reclamation,idClient);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteReclamation(@PathVariable("id")Long id){
        reclamationService.deleteReclamation(id);
    }
}
