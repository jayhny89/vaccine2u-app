package com.example.demo.service;

import com.example.demo.model.dto.*;

import java.util.List;

public interface IVaccineService {
    List<FindAllVaccineResponseDTO> findAllVaccine();
    List<RetrieveLocationResponseDTO> retrieveLocations();
    RegisterVaccineResponseDTO registerVaccine(RegisterVaccineRequestDTO registerVaccineRequestDTO);
    void editVaccine(String id, EditVaccineRequestDTO editVaccineRequestDTO);
    void deleteVaccine(String id);
}
