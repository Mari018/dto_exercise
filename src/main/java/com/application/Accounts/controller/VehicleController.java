package com.application.Accounts.controller;

import com.application.Accounts.dto.VehicleDTO;
import com.application.Accounts.entity.Vehicle;
import com.application.Accounts.exception.UserNotFoundException;
import com.application.Accounts.exception.VehicleNotfoundException;
import com.application.Accounts.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<?> createVehicle(@Valid @RequestBody VehicleDTO vehicleDTO, BindingResult bindingResult){
       if(bindingResult.hasErrors()){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }

        return ResponseEntity.ok(vehicleService.createVehicle(vehicleDTO));
    }

    @PatchMapping("/active/{id}")
    public ResponseEntity<?> activateAccount (@PathVariable Long id){
        try {
            return ResponseEntity.ok(vehicleService.activateVehicle(id));
        } catch (VehicleNotfoundException e) {
            Error error = new Error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<?> deactivateAccount (@PathVariable Long id){
        try {
            return ResponseEntity.ok(vehicleService.deactivateVehicle(id));
        } catch (VehicleNotfoundException e) {
            Error error = new Error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PatchMapping("/{id}/account/{accountId}")
    public ResponseEntity<?> associateAccount (@PathVariable Long id, @PathVariable("accountId")Long accountId){
        try {
            return ResponseEntity.ok(vehicleService.associateAccount(id,accountId));
        } catch (VehicleNotfoundException | UserNotFoundException e){
            Error error = new Error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getDeactivatedAccountsWithActiveVehicles(){
        try {
            return ResponseEntity.ok(vehicleService.getDeactivatedAccountsWithActiveVehicles());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getPlateFromDeactivatedAccounts(){
        try {
            return ResponseEntity.ok(vehicleService.getPlateFromDeactivatedAccounts());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
