package com.example.expert.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Constats{

	private String _id;
	private String fautif;
	private Date dateAccidents;


	private Emplacement emplacement;


	private String statusRembourssement;
	private String agence;
	private List<String> images;
	private Long expertId;
	private Long responsableId;
	private Long clientId;
	private List<String> devis;


	private Long rapport;






}
