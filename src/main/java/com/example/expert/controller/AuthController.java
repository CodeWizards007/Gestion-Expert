package com.example.expert.controller;

import com.example.expert.entity.ERole;
import com.example.expert.entity.Expert;
import com.example.expert.entity.Role;
import com.example.expert.repository.ExpertRepository;
import com.example.expert.repository.RoleRepository;
import com.example.expert.service.ExpertDetailsImpl;

import com.example.expert.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController

@RequestMapping("/expert/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  ExpertRepository expertRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody Expert loginRequest) {

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    ExpertDetailsImpl expertDetails = (ExpertDetailsImpl) authentication.getPrincipal();

    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(expertDetails);

    List<String> roles = expertDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());


      return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
              .body(new Expert(expertDetails.getId(),
                      expertDetails.getNom(),
                      expertDetails.getPrenom(),
                      expertDetails.getUsername(),
                      expertDetails.getEmail(),
                      expertDetails.getCin(),
                      expertDetails.getTelephone(),
                      expertDetails.getAdresse(),
                      expertDetails.getDateNaissance(),
                      expertDetails.getCreatedAt(),
                      expertDetails.getUpdatedAt(),

                      roles));

  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody Expert signUpRequest) {
    if (expertRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest().body("Error: Username is already taken!");
    }

    if (expertRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest().body("Error: Email is already in use!");
    }
    if (signUpRequest.getPassword()==null || signUpRequest.getUsername()==null) {
      return ResponseEntity.badRequest().body("Password ou Username Vide!");
    }

    // Create new admin's account
    Expert expert = new Expert();
    expert.setUsername( signUpRequest.getUsername());
    expert.setEmail( signUpRequest.getEmail());
    expert.setPassword( encoder.encode(signUpRequest.getPassword()));
    expert.setTelephone( signUpRequest.getTelephone());
    expert.setPrenom( signUpRequest.getPrenom());
    expert.setNom( signUpRequest.getNom());
    expert.setAdresse( signUpRequest.getAdresse());
    expert.setDateNaissance( signUpRequest.getDateNaissance());
    expert.setCin( signUpRequest.getCin());


    List<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      throw new ResponseStatusException(
              HttpStatus.NOT_ACCEPTABLE, "Empty");


    } else {
      strRoles.forEach(role -> {
        if(role.equals("ROLE_EXPERT"))
            {
               Role expertRole = roleRepository.findByName(ERole.ROLE_EXPERT)
                     .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
               roles.add(expertRole);
            }
        else{
          throw new ResponseStatusException(
                  HttpStatus.NOT_ACCEPTABLE, "Erreur : Vous n'avez pas l'authorisation pour accéder à cette page");
        }


      });
    }

    expert.setRoles(roles);
    expertRepository.save(expert);

    return ResponseEntity.ok("User registered successfully!");
  }


  @PostMapping("/signout")
  public ResponseEntity<?> logoutUtilisateur() {
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body("You've been signed out!");
  }
}
