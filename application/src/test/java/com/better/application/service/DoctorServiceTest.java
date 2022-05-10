package com.better.application.service;

import com.better.application.exception.DoctorNotFoundException;
import com.better.application.mapper.DoctorMapper;
import com.better.application.mock.DoctorGenerator;
import com.better.application.mock.PatientGenerator;
import com.better.application.repository.IDoctorRepository;
import com.better.application.service.impl.DoctorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {

    @InjectMocks
    private DoctorService doctorService;

    @Mock
    private IDoctorRepository doctorRepository;

    @Mock
    private IPatientService patientService;

    @Mock
    private DoctorMapper doctorMapper;

    @Test
    void saveDoctor_shouldSaveDoctor_whenDoctorIdNotExist() {
        final var doctorModel = DoctorGenerator.doctorModel();
        final var doctor = DoctorGenerator.doctor();

        when(doctorMapper.toDoctor(doctorModel)).thenReturn(doctor);
        when(doctorRepository.existsById(doctor.getId())).thenReturn(false);

        doctorService.save(doctorModel);

        verify(doctorRepository).save(doctor);
        verify(patientService).saveAll(List.of(), 1);
    }

    @Test
    void saveDoctor_shouldSavePatientsToDoctor_whenDoctorIdExists() {
        final var doctorModel = DoctorGenerator.doctorModel();
        final var doctor = DoctorGenerator.doctor();

        when(doctorMapper.toDoctor(doctorModel)).thenReturn(doctor);
        when(doctorRepository.existsById(doctor.getId())).thenReturn(true);

        doctorService.save(doctorModel);

        verify(patientService).saveAll(List.of(), 1);
    }

    @Test
    void getOne_shouldReturnDoctorResponse_whenDoctorIdExists() {
        final var doctor = DoctorGenerator.doctor();
        final var expected = DoctorGenerator.doctorResponse();

        when(doctorRepository.findById(1)).thenReturn(Optional.of(doctor));
        when(doctorMapper.toDoctorResponse(doctor)).thenReturn(expected);

        final var actual = doctorService.getOne(1);

        assertEquals(expected, actual);
    }

    @Test
    void getOne_shouldThrowDoctorNotFoundException_whenDoctorIdNotExist() {
        when(doctorRepository.findById(2)).thenReturn(Optional.empty());

        assertThrows(DoctorNotFoundException.class, () -> {
            doctorService.getOne(2);
        });
    }

    @Test
    void getPatients_shouldReturnPatientsResponse_whenDoctorIdExists() {
        final var doctor = DoctorGenerator.doctor();
        final var doctorResponse = DoctorGenerator.doctorResponse();
        final var expected = PatientGenerator.patientsResponse();

        when(doctorRepository.findById(1)).thenReturn(Optional.of(doctor));
        when(doctorMapper.toDoctorResponse(doctor)).thenReturn(doctorResponse);
        when(patientService.getAll(1)).thenReturn(expected);

        final var actual = doctorService.getPatients(1);
        assertEquals(expected, actual);
    }

    @Test
    void getPatients_shouldThrowDoctorNotFoundException_whenDoctorIdNotExist() {
        when(doctorRepository.findById(2)).thenReturn(Optional.empty());

        assertThrows(DoctorNotFoundException.class, () -> {
            doctorService.getOne(2);
        });
    }

    @Test
    void getAll_shouldReturnDoctorsResponse() {
        final var doctors = DoctorGenerator.doctors();
        final var expected = DoctorGenerator.doctorsResponse();

        when(doctorRepository.findAll()).thenReturn(doctors);
        when(doctorMapper.toDoctorsResponse(doctors)).thenReturn(expected);

        final var actual = doctorService.getAll();

        assertEquals(expected, actual);
    }
}
