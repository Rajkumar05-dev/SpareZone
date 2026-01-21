package com.learn.SpareZone.Dtos;

import com.learn.SpareZone.Entities.Vehicletype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {
	
    private String manufacturer;   // Tata, Ashok Leyland
  
    private String model;          // Prima, Dost
 
    private Integer year;
  
  
    private Vehicletype vehicleType; 
}
