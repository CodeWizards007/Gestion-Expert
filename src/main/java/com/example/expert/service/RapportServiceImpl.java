package com.example.expert.service;


import com.example.expert.entity.Rapport;
import com.example.expert.entity.Rapport;
import com.example.expert.repository.ExpertRepository;
import com.example.expert.repository.RapportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RapportServiceImpl implements RapportService {
    @Autowired
    RapportRepository rapportRepository;


    @Override
    public Rapport addRapport(Rapport rapport) {


        return rapportRepository.save(rapport);

    }

    @Override
    public List<Rapport> getRapports() {

        List<Rapport> experts = StreamSupport
                .stream(rapportRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return experts;

    }


    @Override
    public Rapport getRapport(Long rapportId) {
        if(rapportRepository.findById(rapportId).isPresent()) {
            return rapportRepository.findById(rapportId).get();
        }else {
            return null;
        }
    }

    @Override
    public void deleteRapport(Long rapportId) {
        Rapport rapport = getRapport(rapportId);
        rapportRepository.delete(rapport);
    }

@Transactional
    @Override
    public Rapport editRapport( Rapport rapport) {

    return rapportRepository.save(rapport);
}
    }

