package com.example.demo.dto;

import java.util.List;
import com.example.demo.entities.Rental;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

public class RentalsResponseDTO {
    private List<RentalViewModel> rentals;

    public RentalsResponseDTO(List<Rental> rentals) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        this.rentals = rentals.stream()
            .map(rental -> new RentalViewModel(
                rental.getId(),
                rental.getName(),
                rental.getSurface(),
                rental.getPrice(),
                rental.getPictureUrl(),
                rental.getDescription(),
                rental.getOwnerId(),
                dateFormat.format(rental.getCreatedAt()),
                dateFormat.format(rental.getUpdatedAt())
            ))
            .collect(Collectors.toList());
    }

    public List<RentalViewModel> getRentals() {
        return rentals;
    }
} 