package com.learn.SpareZone.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

	@ManyToOne
	@JoinColumn(name = "category_id")
	@JsonManagedReference
	private Category category;

	@OneToMany
	@JsonBackReference
	private List<CartItem> cartItems;
	@OneToMany
	private List<OrderItem> orderItems;

}
