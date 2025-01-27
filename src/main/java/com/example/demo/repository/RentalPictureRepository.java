package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.RentalPicture;

public interface RentalPictureRepository extends JpaRepository<RentalPicture, Long> {
	
}