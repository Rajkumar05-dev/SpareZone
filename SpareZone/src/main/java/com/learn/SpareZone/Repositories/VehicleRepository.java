package com.learn.SpareZone.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.learn.SpareZone.Entities.Vehicle;
import com.learn.SpareZone.Entities.Vehicletype;
import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>  {
    List<Vehicle> findByVehicleType(Vehicletype vehicleType);
}
