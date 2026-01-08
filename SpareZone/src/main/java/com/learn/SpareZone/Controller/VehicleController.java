package com.learn.SpareZone.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.SpareZone.Entities.Vehicle;
import com.learn.SpareZone.Entities.Vehicletype;
import com.learn.SpareZone.Service.VehicleService;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
	@Autowired 
	private VehicleService vehicleService; 
	
	@GetMapping
	 public ResponseEntity<List<Vehicle>> getAllVehicle(){
		 List<Vehicle> allVehicles = vehicleService.getAllVehicles();
		 return new ResponseEntity<List<Vehicle>>(allVehicles,HttpStatus.OK);
	 }
	@GetMapping("/type/{vehicletype}")
 public ResponseEntity<List<Vehicle>> getVehicleByType(@PathVariable Vehicletype vehicletype){  
	 List<Vehicle> allVehicletype = vehicleService.getVehicleByType(vehicletype);
	 return new ResponseEntity<List<Vehicle>>(allVehicletype,HttpStatus.FOUND);
 }
	
	@PostMapping
	public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle){
		Vehicle savedVehicle = vehicleService.saveVehicle(vehicle);
		return new ResponseEntity<Vehicle>(savedVehicle,HttpStatus.CREATED); 
		
	}
}
