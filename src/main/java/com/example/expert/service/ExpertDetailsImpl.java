package com.example.expert.service;


import com.example.expert.entity.Expert;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ToString
public class ExpertDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String nom;

    private String prenom;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private Long cin;

    private Long telephone;

    private String adresse;

    private Date dateNaissance;

    private Date createdAt;

    private Date updatedAt;

    private Collection<? extends GrantedAuthority> authorities;

    public ExpertDetailsImpl(Long id, String nom, String prenom, String username, String email, String password, Long cin, Long telephone, String adresse, Date dateNaissance, Date createdAt, Date updatedAt,
                             Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.password = password;
        this.cin=cin;
        this.telephone = telephone;
        this.adresse=adresse;
        this.dateNaissance=dateNaissance;
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
        this.authorities = authorities;

    }

    public static ExpertDetailsImpl build(Expert expert) {
        List<GrantedAuthority> authorities = expert.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new ExpertDetailsImpl(
                expert.getId(),
                expert.getNom(),
                expert.getPrenom(),
                expert.getUsername(),
                expert.getEmail(),
                expert.getPassword(),
                expert.getCin(),
                expert.getTelephone(),
                expert.getAdresse(),
                expert.getDateNaissance(),
                expert.getCreatedAt(),
                expert.getUpdatedAt(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public Long getCin() {
        return cin;
    }

    public Long getTelephone() {
        return telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ExpertDetailsImpl expert = (ExpertDetailsImpl) o;
        return Objects.equals(id, expert.id);
    }
}
