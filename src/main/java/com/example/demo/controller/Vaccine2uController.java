package com.example.demo.controller;

import com.example.demo.model.dto.*;
import com.example.demo.model.entity.VaccineRegister;
import com.example.demo.service.IVaccineService;
import com.sun.istack.internal.NotNull;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(ResourcePath.APP_NAME)
public class Vaccine2uController {
    @Autowired
    private IVaccineService vaccineService;

    @Operation(summary = "List all the vaccines registered.")
    @GetMapping
    public ResponseEntity<List<FindAllVaccineResponseDTO>> findAllVaccine(){
        return ResponseEntity.ok(vaccineService.findAllVaccine());
    }

    @Operation(summary = "Retrieve vaccine locations from 3rd party API.")
    @GetMapping(ResourcePath.RETRIEVE_LOCATIONS)
    public ResponseEntity<List<RetrieveLocationResponseDTO>> retrieveLocations(){
        return ResponseEntity.ok(vaccineService.retrieveLocations());
    }

    @Operation(summary = "Vaccine registrations to 3rd party API.")
    @PostMapping
    public ResponseEntity<RegisterVaccineResponseDTO> registerVaccine(@Valid @RequestBody RegisterVaccineRequestDTO registerVaccineRequestDTO){
        return ResponseEntity.ok(vaccineService.registerVaccine(registerVaccineRequestDTO));
    }

    @Operation(summary = "Edit registration detail.")
    @PutMapping(ResourcePath.PATH_ID)
    @ResponseStatus(HttpStatus.OK)
    public void editVaccine(@NotNull @PathVariable("id") String id,
                                                              @RequestBody EditVaccineRequestDTO editVaccineRequestDTO){
        vaccineService.editVaccine(id, editVaccineRequestDTO);
    }

    @Operation(summary = "Delete registration detail.")
    @DeleteMapping(ResourcePath.PATH_ID)
    @ResponseStatus(HttpStatus.OK)
    public void deleteVaccine(@NotNull @PathVariable("id") String id){
        vaccineService.deleteVaccine(id);
    }
}
