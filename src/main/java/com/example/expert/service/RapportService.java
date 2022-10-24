package com.example.expert.service;


import com.example.expert.entity.Rapport;
import com.example.expert.entity.Rapport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RapportService {
    public Rapport addRapport(Rapport rapport);
    public List<Rapport> getRapports();
    public Rapport getRapport(Long rapportId);
    public void deleteRapport(Long rapportId);
    public Rapport editRapport( Rapport rapport);

}
