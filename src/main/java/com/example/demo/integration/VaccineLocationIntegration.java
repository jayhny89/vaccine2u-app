package com.example.demo.integration;

import com.example.demo.config.AppProperties;
import com.example.demo.helper.VaccineConstant;
import com.example.demo.model.dto.VaccineLocationDTO;
import com.example.demo.model.dto.VaccineRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class VaccineLocationIntegration {
    @Autowired
    private RestTemplate restTemplateClient;

    @Autowired
    private AppProperties appProperties;

    public List<VaccineLocationDTO> getVaccineLocations(){
        try{
            ResponseEntity<VaccineLocationDTO[]> response
                    = restTemplateClient.getForEntity(appProperties.getLocationURL(), VaccineLocationDTO[].class);
            if(response.getStatusCode() == HttpStatus.OK){
                return Arrays.asList(response.getBody().clone());
            }else {
                return new ArrayList<>();
            }
        }catch (Exception ex){
            return new ArrayList<>();
        }
    }

    public String registerVaccine(VaccineRegisterDTO vaccineRegisterDTO){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<VaccineRegisterDTO> request =
                    new HttpEntity<>(vaccineRegisterDTO, headers);
            ResponseEntity<VaccineRegisterDTO> response
                    = restTemplateClient.postForEntity(appProperties.getRegisterURL(), request, VaccineRegisterDTO.class);
            if(response.getStatusCode() == HttpStatus.CREATED){
                return VaccineConstant.STATUS_SUCCESS;
            }else {
                return VaccineConstant.STATUS_FAILED;
            }
        }catch (Exception ex){
            return VaccineConstant.STATUS_FAILED;
        }
    }

}
