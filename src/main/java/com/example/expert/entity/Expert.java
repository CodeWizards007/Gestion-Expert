package com.example.expert.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Expert implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	private String nom;
	private String prenom;
	private String username;
	private String email;
	private String password;
	private Long cin;
	private Long telephone;
	private String adresse;
	private Date dateNaissance;
	@CreationTimestamp
	private Date createdAt;
	@UpdateTimestamp
	private Date updatedAt;


	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "utilisateur_roles",
			joinColumns = @JoinColumn(name = "utilisateur_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();


	@Transient
	private List<String> role;

	public Expert(Long id, String nom, String prenom, String username, String email, Long cin, Long telephone, String adresse, Date dateNaissance, Date createdAt, Date updatedAt, List<String> role) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.username = username;
		this.email = email;
		this.cin = cin;
		this.telephone = telephone;
		this.adresse = adresse;
		this.dateNaissance = dateNaissance;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.role = role;
	}












}
