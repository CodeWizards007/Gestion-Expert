package com.example.expert.service;


import com.example.expert.entity.Expert;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpertService {
    public Expert addExpert(Expert expert);
    public List<Expert> getExperts();
    public Expert getExpert(Long expertId);
    public void deleteExpert(Long expertId);
    public Expert editExpert( Expert expert);

}
