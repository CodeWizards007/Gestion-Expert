package com.example.expert.service;


import com.example.expert.entity.Expert;
import com.example.expert.repository.ExpertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ExpertServiceImpl implements ExpertService {
    @Autowired
    ExpertRepository expertRepository;


    @Override
    public Expert addExpert(Expert expert) {


        return expertRepository.save(expert);

    }

    @Override
    public List<Expert> getExperts() {

        List<Expert> experts = StreamSupport
                .stream(expertRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return experts;

    }


    @Override
    public Expert getExpert(Long expertId) {
        if(expertRepository.findById(expertId).isPresent()) {
            return expertRepository.findById(expertId).get();
        }else {
            return null;
        }
    }

    @Override
    public void deleteExpert(Long expertId) {
        Expert expert = getExpert(expertId);
        expertRepository.delete(expert);
    }

@Transactional
    @Override
    public Expert editExpert( Expert expert) {

    return expertRepository.save(expert);
}
    }

