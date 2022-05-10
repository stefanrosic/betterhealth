package com.better.application.repository;

import com.better.application.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IPatientRepository extends JpaRepository<Patient, Integer> {

    Optional<Patient> findByIdAndDoctorId(Integer id, Integer doctorId);

    List<Patient> findByDoctorId(Integer doctorId);
}
