package com.example.demo.converter.dto;

import com.example.demo.model.dto.VaccineRegisterDTO;
import com.example.demo.model.entity.VaccineRegister;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VaccineRegisterDTOConverter implements Converter<VaccineRegisterDTO, VaccineRegister> {
    @Qualifier("converterModelMapper")
    private ModelMapper mapper;

    @Override
    public VaccineRegister convert(VaccineRegisterDTO vaccineRegisterDTO) {
        return mapper.map(vaccineRegisterDTO, VaccineRegister.class);
    }
}
