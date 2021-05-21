package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VaccineRegisterDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private String location;
    private LocalDate bookingDate;
    private LocalTime bookingTime;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
