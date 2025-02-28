package com.application.Accounts.mapper;

import com.application.Accounts.dto.VehicleDTO;
import com.application.Accounts.dto.VehiclePlateDto;
import com.application.Accounts.entity.Vehicle;

import java.util.List;

public class VehicleMapperImpl implements VehicleMapper{
    @Override
    public Vehicle vehicleDTOToVehicle(VehicleDTO vehicleDTO) {
        return null;
    }

    @Override
    public VehicleDTO vehicleToVehicleDTO(Vehicle vehicle) {
        return null;
    }

    @Override
    public List<VehicleDTO> listVehicleToVehicleDTO(List<Vehicle> vehicles) {
        return List.of();
    }

    @Override
    public List<VehiclePlateDto> listVehicleToPlateDto(List<Vehicle> vehicles) {
        return List.of();
    }
}
