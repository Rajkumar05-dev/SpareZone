package com.learn.SpareZone.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vehicle {

	  @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long vehicleId;

	  @Column(nullable = false)
	    private String manufacturer;   // Tata, Ashok Leyland
	  @Column(nullable = false)
	    private String model;           // Prima, Dost
	  @Column(nullable = false)
	    private Integer year;
	  @Column(nullable = false)
	  @Enumerated(EnumType.STRING)
	    private Vehicletype vehicleType; 
}
