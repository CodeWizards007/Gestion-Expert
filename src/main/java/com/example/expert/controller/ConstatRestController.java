package com.example.expert.controller;


import com.example.expert.entity.Constats;
import com.example.expert.entity.Devis;
import com.example.expert.entity.Rapport;
import com.example.expert.proxies.MicroserviceCommandeProxy;
import com.example.expert.service.RapportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@RequestMapping("/expert/constat")

@RestController
public class ConstatRestController {


    @Autowired
    RapportService rapportService;

    @Autowired
    MicroserviceCommandeProxy microserviceCommandeProxy;

    // http://localhost:8081/retrieve-all-experts
    @GetMapping("/getall/{id}")
    @ResponseBody
    public List<Constats> getConstats(@PathVariable("id") String id) {
        return microserviceCommandeProxy.recupererConstatsByExpert(id);
    }

    @GetMapping("/getdevis/{id}")
    @ResponseBody
    public Devis retrieveDevis(@PathVariable("id") String devisId) {
    Devis devis = microserviceCommandeProxy.recupererUnDevis(devisId);

        return devis;
    }



    // http://localhost:8081/SpringMVC/servlet/add-expert
    @PostMapping("/add/{idconstat}")
    @ResponseBody
    public Constats addRapport(@RequestBody Rapport r,@PathVariable("idconstat") String idconstat){

        Rapport rapport = rapportService.addRapport(r);
        Constats constat = microserviceCommandeProxy.recupererUnConstat(idconstat);
        constat.setRapport(rapport.getId());
        microserviceCommandeProxy.updateConstat(constat);
        return constat;
    }

    // http://localhost:8081/retrieve-expert/2
    @GetMapping("/get/{id}")
    @ResponseBody
    public Rapport retrieveRapport(@PathVariable("id") Long rapportId) {
        return rapportService.getRapport(rapportId);
    }


    // http://localhost:8081/SpringMVC/servlet/remove-expert/{expert-id}
    @DeleteMapping("/delete/{rapport-id}")
    @ResponseBody
    public void removeRapport(@PathVariable("rapport-id") Long rapportId) {
        rapportService.deleteRapport(rapportId);
    }

    // http://localhost:8081/SpringMVC/servlet/modify-expert
    @PutMapping("/editdevis")
    @ResponseBody
    public Devis modifyRapport(@RequestBody Devis devis) {
        return microserviceCommandeProxy.updateDevis(devis,devis.get_id());
    }


}
