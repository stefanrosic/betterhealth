package com.better.application.service;

import com.better.application.dto.response.DiseaseResponse;
import com.better.application.exception.PatientNotFoundException;
import com.better.application.mapper.PatientMapper;
import com.better.application.mock.DiseaseGenerator;
import com.better.application.mock.DoctorGenerator;
import com.better.application.mock.PatientGenerator;
import com.better.application.repository.IDoctorRepository;
import com.better.application.repository.IPatientRepository;
import com.better.application.service.impl.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    private IPatientRepository patientRepository;

    @Mock
    private IDoctorRepository doctorRepository;

    @Mock
    private PatientMapper patientMapper;

    @Test
    void saveAll_shouldSaveAllNonExistent() {
        final var doctor = DoctorGenerator.doctor();

        final var patient = PatientGenerator.patient();
        final var patients = PatientGenerator.patients();
        final var patientModel = PatientGenerator.patientModel();
        final var patientsModel = PatientGenerator.patientsModel();

        when(doctorRepository.getById(doctor.getId())).thenReturn(doctor);
        when(patientRepository.findByIdAndDoctorId(patientModel.getId(), doctor.getId())).thenReturn(Optional.empty());
        when(patientMapper.toPatient(patientModel, doctor)).thenReturn(patient);

        patientService.saveAll(patientsModel, doctor.getId());

        verify(patientRepository).saveAll(patients);
    }

    @Test
    void saveAll_shouldNotSaveExistentPatient() {
        final var doctor = DoctorGenerator.doctor();

        final var patient = PatientGenerator.patient();
        final var patients = PatientGenerator.patients();
        final var patientModel = PatientGenerator.patientModel();
        final var patientsModel = PatientGenerator.patientsModel();

        when(doctorRepository.getById(doctor.getId())).thenReturn(doctor);
        when(patientRepository.findByIdAndDoctorId(patientModel.getId(), doctor.getId())).thenReturn(Optional.of(patient));

        patientService.saveAll(patientsModel, doctor.getId());

        verify(patientRepository, never()).saveAll(patients);
    }

    @Test
    void getOne_shouldReturnPatientResponse_whenPatientIdExists() {
        final var patient = PatientGenerator.patient();
        final var expected = PatientGenerator.patientResponse();

        when(patientRepository.findById(1)).thenReturn(Optional.of(patient));
        when(patientMapper.toPatientResponse(patient)).thenReturn(expected);

        final var actual = patientService.getOne(1);

        assertEquals(expected, actual);
    }

    @Test
    void getOne_shouldThrowPatientNotFoundException_whenPatientIdNotExist() {
        when(patientRepository.findById(2)).thenReturn(Optional.empty());

        assertThrows(PatientNotFoundException.class, () -> {
            patientService.getOne(2);
        });
    }

    @Test
    void getDiseases_shouldReturnDiseaseResponse() {
        final var patient = PatientGenerator.patient();
        final var patientResponse = PatientGenerator.patientResponse();
        final var expected = DiseaseGenerator.diseaseResponse();

        when(patientRepository.findById(1)).thenReturn(Optional.of(patient));
        when(patientMapper.toPatientResponse(patient)).thenReturn(patientResponse);

        final var actual = DiseaseResponse.builder().diseases(patientService.getOne(1).getDiseases()).build();

        assertEquals(expected, actual);
    }
}
