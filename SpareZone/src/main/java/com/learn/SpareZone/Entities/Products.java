package com.learn.SpareZone.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Products {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long productId;

	    private String name;
	    private String partNumber;
	    private Double price;
	    private Integer stock;

	    @Column(length = 1000)
	    private String description;

	    

	    
}
