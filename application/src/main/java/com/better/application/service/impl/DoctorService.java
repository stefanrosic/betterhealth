package com.better.application.service.impl;

import com.better.application.dto.response.DoctorResponse;
import com.better.application.dto.response.PatientResponse;
import com.better.application.exception.DoctorNotFoundException;
import com.better.application.mapper.DoctorMapper;
import com.better.application.model.DoctorModel;
import com.better.application.repository.IDoctorRepository;
import com.better.application.service.IDoctorService;
import com.better.application.service.IPatientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorService implements IDoctorService {

    private final IDoctorRepository doctorRepository;

    private final IPatientService patientService;

    private final DoctorMapper doctorMapper;

    public DoctorService(IDoctorRepository doctorRepository, IPatientService patientService, DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.patientService = patientService;
        this.doctorMapper = doctorMapper;
    }

    @Transactional
    @Override
    public void save(DoctorModel doctorModel) {
        final var doctor = doctorMapper.toDoctor(doctorModel);

        if (!doctorRepository.existsById(doctor.getId())) {
            doctorRepository.save(doctor);
        }
        patientService.saveAll(doctorModel.getPatients(), doctorModel.getId());
    }

    @Override
    public DoctorResponse getOne(Integer id) {
        final var doctor = doctorRepository.findById(id);

        if (doctor.isEmpty()) {
            throw new DoctorNotFoundException(id);
        }
        return doctorMapper.toDoctorResponse(doctor.get());
    }

    @Override
    public List<PatientResponse> getPatients(Integer doctorId) {
        final var doctor = getOne(doctorId);
        return patientService.getAll(doctor.getId());
    }

    @Override
    public List<DoctorResponse> getAll() {
        final var doctors = doctorRepository.findAll();
        return doctorMapper.toDoctorsResponse(doctors);
    }
}
