package com.application.Accounts.service;

import com.application.Accounts.dto.AccountDTO;
import com.application.Accounts.dto.VehicleDTO;
import com.application.Accounts.dto.VehiclePlateDto;
import com.application.Accounts.entity.Account;
import com.application.Accounts.entity.Vehicle;
import com.application.Accounts.exception.PlateNotFoudException;
import com.application.Accounts.exception.UserNotFoundException;
import com.application.Accounts.exception.VehicleNotfoundException;
import com.application.Accounts.mapper.AccountMapper;
import com.application.Accounts.mapper.VehicleMapper;
import com.application.Accounts.repository.AccountRepository;
import com.application.Accounts.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

   private final VehicleRepository vehicleRepository;
   private final AccountRepository accountRepository;

    public VehicleService(VehicleRepository vehicleRepository, AccountRepository accountRepository) {
        this.vehicleRepository = vehicleRepository;
        this.accountRepository = accountRepository;
    }

    public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = VehicleMapper.INSTANCE.vehicleDTOToVehicle(vehicleDTO);

        if(vehicleRepository.findByPlate(vehicleDTO.getPlate()) != null){
            throw new PlateNotFoudException("this car already exists");
        }
        return VehicleMapper.INSTANCE.vehicleToVehicleDTO( vehicleRepository.save(vehicle));
    }

    public VehicleDTO activateVehicle (Long id){
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(()-> new VehicleNotfoundException("This vehicle do not exist"));
        vehicle.setActive(true);
        return VehicleMapper.INSTANCE.vehicleToVehicleDTO(vehicleRepository.save(vehicle));
    }

    public VehicleDTO deactivateVehicle (Long id){
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(()-> new VehicleNotfoundException("This vehicle do not exist"));
        vehicle.setActive(false);
        return VehicleMapper.INSTANCE.vehicleToVehicleDTO(vehicleRepository.save(vehicle));
    }

    public VehicleDTO associateAccount ( Long vehicleId, Long accountId){
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(()-> new VehicleNotfoundException("this vehicle do not exist"));
        Account account = accountRepository.findById(accountId).orElseThrow(()-> new UserNotFoundException("user do not exist"));
        vehicle.setAccount(account);
        return VehicleMapper.INSTANCE.vehicleToVehicleDTO(vehicleRepository.save(vehicle));
    }

    public List<AccountDTO> getDeactivatedAccountsWithActiveVehicles(){
        List<Account> accounts = accountRepository.findAll().stream().filter( account -> !account.isActive()).toList();
        List<Vehicle> vehicle = vehicleRepository.findByAccountIn(accounts).stream().filter(Vehicle::isActive).toList();
        return AccountMapper.INSTANCE.listAccountToListAccountDTO(VehicleMapper.INSTANCE.listVehicleToVehicleDTO(vehicle).stream().map(VehicleDTO :: getAccount).toList());
    }

    public List<VehiclePlateDto> getPlateFromDeactivatedAccounts (){
        List<Account> accounts = accountRepository.findAll().stream().filter( account -> !account.isActive()).toList();
        return VehicleMapper.INSTANCE.listVehicleToPlateDto(vehicleRepository.findByAccountIn(accounts).stream().filter(Vehicle::isActive).toList());
    }
}
