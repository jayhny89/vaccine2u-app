package com.example.demo.service.impl;

import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.integration.VaccineLocationIntegration;
import com.example.demo.model.dto.*;
import com.example.demo.model.entity.VaccineRegister;
import com.example.demo.repository.VaccineRegisterRepository;
import com.example.demo.service.IVaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VaccineServiceImpl implements IVaccineService {

    @Autowired
    private Converter<VaccineRegisterDTO, VaccineRegister> vaccineRegisterDTOConverter;
    @Autowired
    private Converter<VaccineRegister, VaccineRegisterDTO> vaccineRegisterEntityConverter;
    @Autowired
    private VaccineRegisterRepository vaccineRegisterRepository;
    @Autowired
    private VaccineLocationIntegration vaccineLocationIntegration;

    @Override
    public List<FindAllVaccineResponseDTO> findAllVaccine(){
        List<VaccineRegisterDTO> vaccineRegisterDTOList = vaccineRegisterRepository.findAll().stream().map(vaccineRegister ->
                vaccineRegisterEntityConverter.convert(vaccineRegister)).collect(Collectors.toList());
        return vaccineRegisterDTOList.stream().map(vaccineRegisterDTO -> FindAllVaccineResponseDTO.builder()
                .icNumber(vaccineRegisterDTO.getId())
                .firstName(vaccineRegisterDTO.getFirstName())
                .lastName(vaccineRegisterDTO.getLastName())
                .contactNumber(vaccineRegisterDTO.getContactNumber())
                .location(vaccineRegisterDTO.getLocation())
                .bookingDate(vaccineRegisterDTO.getBookingDate())
                .bookingTime(vaccineRegisterDTO.getBookingTime()).build())
                .collect(Collectors.toList());
    }

    @Override
    public List<RetrieveLocationResponseDTO> retrieveLocations() {
        //GET locations from 3rd Party API
        List<VaccineLocationDTO> vaccineLocationDTOList = vaccineLocationIntegration.getVaccineLocations();
        return vaccineLocationDTOList.stream().map(vaccineLocationDTO -> RetrieveLocationResponseDTO.builder()
                .location(vaccineLocationDTO.getLocation())
                .capacity(vaccineLocationDTO.getCapacity())
                .availableDate(vaccineLocationDTO.getAvailableDate())
                .build()).collect(Collectors.toList());
    }

    @Override
    public RegisterVaccineResponseDTO registerVaccine(RegisterVaccineRequestDTO registerVaccineRequestDTO){
        VaccineRegisterDTO vaccineRegisterDTO = VaccineRegisterDTO.builder()
                .id(registerVaccineRequestDTO.getIcNumber())
                .firstName(registerVaccineRequestDTO.getFirstName())
                .lastName(registerVaccineRequestDTO.getLastName())
                .contactNumber(registerVaccineRequestDTO.getContactNumber())
                .location(registerVaccineRequestDTO.getLocation())
                .bookingDate(registerVaccineRequestDTO.getBookingDate())
                .bookingTime(registerVaccineRequestDTO.getBookingTime())
                .createDate(LocalDateTime.now())
                .build();
        //POST to 3rd Party API
        String registerStatus = vaccineLocationIntegration.registerVaccine(vaccineRegisterDTO);
        //SAVE data into database
        vaccineRegisterRepository.saveAndFlush(vaccineRegisterDTOConverter.convert(vaccineRegisterDTO));

        return RegisterVaccineResponseDTO.builder()
                .status(registerStatus)
                .build();
    }

    @Override
    public void editVaccine(String id, EditVaccineRequestDTO editVaccineRequestDTO) {
        VaccineRegisterDTO vaccineRegisterDTO= searchVaccineRegisterById(id);
        vaccineRegisterDTO.setLocation(editVaccineRequestDTO.getLocation());
        vaccineRegisterDTO.setContactNumber(editVaccineRequestDTO.getContactNumber());
        vaccineRegisterDTO.setBookingDate(editVaccineRequestDTO.getBookingDate());
        vaccineRegisterDTO.setBookingTime(editVaccineRequestDTO.getBookingTime());
        vaccineRegisterDTO.setUpdateDate(LocalDateTime.now());

        vaccineRegisterRepository.saveAndFlush(vaccineRegisterDTOConverter.convert(vaccineRegisterDTO));
    }

    @Override
    public void deleteVaccine(String icno) {
        VaccineRegisterDTO vaccineRegisterDTO= searchVaccineRegisterById(icno);
        vaccineRegisterRepository.delete(vaccineRegisterDTOConverter.convert(vaccineRegisterDTO));
    }

    private VaccineRegisterDTO searchVaccineRegisterById(String id){
        return vaccineRegisterEntityConverter.convert(vaccineRegisterRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
