package com.example.ParkBro.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "parking_lot")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    String name;

    String phoneNumber;
}
