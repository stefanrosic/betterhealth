package com.better.application.service.impl;

import com.better.application.dto.response.DiseaseResponse;
import com.better.application.dto.response.PatientResponse;
import com.better.application.exception.PatientNotFoundException;
import com.better.application.mapper.PatientMapper;
import com.better.application.model.PatientModel;
import com.better.application.repository.IDoctorRepository;
import com.better.application.repository.IPatientRepository;
import com.better.application.service.IPatientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService implements IPatientService {

    private final IPatientRepository patientRepository;

    private final IDoctorRepository doctorRepository;

    private final PatientMapper patientMapper;

    public PatientService(IPatientRepository patientRepository, IDoctorRepository doctorRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.patientMapper = patientMapper;
    }

    @Override
    public void saveAll(List<PatientModel> patientsModel, Integer doctorId) {
        final var doctor = doctorRepository.getById(doctorId);

        final var patients = patientsModel.stream()
                .filter(patient -> patientRepository.findByIdAndDoctorId(patient.getId(), doctorId).isEmpty())
                .map(patient -> patientMapper.toPatient(patient, doctor))
                .collect(Collectors.toSet());

        if (patients.isEmpty()) {
            return;
        }

        patientRepository.saveAll(patients);
    }

    @Override
    public List<PatientResponse> getAll() {
        final var patients = patientRepository.findAll();
        return patientMapper.toPatientsResponse(patients);
    }

    @Override
    public List<PatientResponse> getAll(Integer doctorId) {
        final var patients = patientRepository.findByDoctorId(doctorId);
        return patientMapper.toPatientsResponse(patients);
    }

    @Override
    public PatientResponse getOne(Integer id) {
        final var patient = patientRepository.findById(id);

        if (patient.isEmpty()) {
            throw new PatientNotFoundException(id);
        }

        return patientMapper.toPatientResponse(patient.get());
    }

    @Override
    public DiseaseResponse getDiseases(Integer patientId) {
        final var patient = getOne(patientId);
        return DiseaseResponse.builder().diseases(patient.getDiseases()).build();
    }
}
