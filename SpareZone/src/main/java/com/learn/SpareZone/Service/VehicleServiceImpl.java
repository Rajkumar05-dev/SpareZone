package com.learn.SpareZone.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.SpareZone.Entities.Vehicle;
import com.learn.SpareZone.Entities.Vehicletype;
import com.learn.SpareZone.Repositories.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {

	
	@Autowired
	private VehicleRepository vehicleRepository;

	@Override
	public List<Vehicle> getVehicleByType(Vehicletype vehicletype) {
	
	
		return  vehicleRepository.findByVehicleType(vehicletype);
	}

	@Override
	public List<Vehicle> getAllVehicles() {
	
		return vehicleRepository.findAll();
	}

	@Override
	public Vehicle saveVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return  vehicleRepository.save(vehicle);
	}

}
