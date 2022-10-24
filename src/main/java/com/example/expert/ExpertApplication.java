package com.example.expert;

import com.example.expert.entity.ERole;
import com.example.expert.entity.Role;
import com.example.expert.repository.RoleRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients("com.example.expert")
public class ExpertApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(ExpertApplication.class, args);

		RoleRepository roleRepository = context.getBean(RoleRepository.class);



		for (ERole d : ERole.values()) {


			if (roleRepository.findByName(d).isEmpty()) {
				Role role = new Role();
				role.setName(d);
				roleRepository.save(role);


			}


		}


	}

}
