package com.example.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "vaccine_reg")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VaccineRegister {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "contact_num")
    private String contactNumber;

    @Column(name = "location")
    private String location;

    @Column(name = "book_date")
    private LocalDate bookingDate;

    @Column(name = "book_time")
    private LocalTime bookingTime;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;
}
