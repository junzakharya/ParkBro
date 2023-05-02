package com.example.ParkBro.dto;

import com.example.ParkBro.model.Spot;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "parking_lot")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLotResponseDto {
    String name;

    String address;

    List<SpotResponseDto> spotList;
}
