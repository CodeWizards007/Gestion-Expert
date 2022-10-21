package com.example.expert.controller;


import com.example.expert.entity.Expert;
import com.example.expert.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RequestMapping("/expert")
@CrossOrigin(origins = "*")
@RestController
public class ExpertRestController {


    @Autowired
    ExpertService expertService;


    // http://localhost:8081/retrieve-all-experts
    @GetMapping("/getall")
    @ResponseBody
    public List<Expert> getExperts() {
        return expertService.getExperts();
    }

    // http://localhost:8081/SpringMVC/servlet/add-expert
    @PostMapping("/add")
    @ResponseBody
    public Expert addExpert(@RequestBody Expert e){
        Expert expert = expertService.addExpert(e);
        return expert;
    }

    // http://localhost:8081/retrieve-expert/2
    @GetMapping("/get/{id}")
    @ResponseBody
    public Expert retrieveExpert(@PathVariable("id") Long expertId) {
        return expertService.getExpert(expertId);
    }


    // http://localhost:8081/SpringMVC/servlet/remove-expert/{expert-id}
    @DeleteMapping("/delete/{expert-id}")
    @ResponseBody
    public void removeExpert(@PathVariable("expert-id") Long expertId) {
        expertService.deleteExpert(expertId);
    }

    // http://localhost:8081/SpringMVC/servlet/modify-expert
    @PutMapping("/edit")
    @ResponseBody
    public Expert modifyExpert(@RequestBody Expert expert) {
        return expertService.editExpert(expert);
    }


}
