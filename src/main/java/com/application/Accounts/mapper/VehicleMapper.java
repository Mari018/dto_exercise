package com.application.Accounts.mapper;

import com.application.Accounts.dto.VehicleDTO;
import com.application.Accounts.dto.VehiclePlateDto;
import com.application.Accounts.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    Vehicle vehicleDTOToVehicle (VehicleDTO vehicleDTO);

    VehicleDTO vehicleToVehicleDTO (Vehicle vehicle);

    List<VehicleDTO> listVehicleToVehicleDTO (List<Vehicle> vehicles);

    List<VehiclePlateDto> listVehicleToPlateDto (List<Vehicle> vehicles);

}
