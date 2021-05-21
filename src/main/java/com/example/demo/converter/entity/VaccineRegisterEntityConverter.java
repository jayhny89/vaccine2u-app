package com.example.demo.converter.entity;

import com.example.demo.model.dto.VaccineRegisterDTO;
import com.example.demo.model.entity.VaccineRegister;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VaccineRegisterEntityConverter implements Converter<VaccineRegister, VaccineRegisterDTO> {
    @Qualifier("converterModelMapper")
    private ModelMapper mapper;

    @Override
    public VaccineRegisterDTO convert(VaccineRegister vaccineRegister) {
        return mapper.map(vaccineRegister, VaccineRegisterDTO.class);
    }
}
