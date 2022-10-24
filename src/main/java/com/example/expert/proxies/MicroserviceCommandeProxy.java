package com.example.expert.proxies;

import com.example.expert.entity.Constats;
import com.example.expert.entity.Devis;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "ASSUREUR-SERVICE", url = "localhost:8888")
public interface MicroserviceCommandeProxy {

    @GetMapping(value = "/api/constat/find/{id}")
    Constats recupererUnConstat(@PathVariable("id") String id);

    @GetMapping(value = "/api/constat/findByExpert/{id}")
    List<Constats> recupererConstatsByExpert(@PathVariable("id") String id);

    @GetMapping(value = "/api/devis/findOne/{id}")
    Devis recupererUnDevis(@PathVariable("id") String id);

    @PutMapping(value = "/api/constat/update")
    void updateConstat(@RequestBody Constats c);

    @PutMapping(value = "/api/devis/update/{id}")
    Devis updateDevis(@RequestBody Devis d,@PathVariable("id") String id);
}
