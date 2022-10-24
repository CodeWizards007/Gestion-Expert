package com.example.expert.controller;


import com.example.expert.entity.Constats;
import com.example.expert.entity.Rapport;
import com.example.expert.proxies.MicroserviceCommandeProxy;
import com.example.expert.service.RapportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/expert/rapport")

@RestController
public class RapportRestController {


    @Autowired
    RapportService rapportService;

    @Autowired
    MicroserviceCommandeProxy microserviceCommandeProxy;

    // http://localhost:8081/retrieve-all-experts
    @GetMapping("/getall")
    @ResponseBody
    public List<Rapport> getRapports() {
        return rapportService.getRapports();
    }

    @GetMapping("/getconstat/{id}")
    @ResponseBody
    public Object retrieveConstat(@PathVariable("id") String rapportId) {
    Constats cons = microserviceCommandeProxy.recupererUnConstat(rapportId);

        return cons;
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
    @PutMapping("/edit")
    @ResponseBody
    public Rapport modifyRapport(@RequestBody Rapport rapport) {
        return rapportService.editRapport(rapport);
    }


}
