package com.learn.SpareZone.Service;

import java.util.List;

import com.learn.SpareZone.Entities.Vehicle;
import com.learn.SpareZone.Entities.Vehicletype;

public interface VehicleService {

    List<Vehicle> getAllVehicles();

    List<Vehicle> getVehicleByType(Vehicletype vehicletype);

    Vehicle saveVehicle(Vehicle vehicle);
}
