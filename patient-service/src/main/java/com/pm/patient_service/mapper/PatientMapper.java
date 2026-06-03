package com.pm.patient_service.mapper;

import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.model.Patient;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PatientMapper {

    public PatientResponseDTO toDTO(Patient patient) {
        return new PatientResponseDTO(
                patient.getId().toString(),
                patient.getName(),
                patient.getEmail(),
                patient.getAddress(),
                patient.getDateOfBirth().toString()
        );
    }

    public Patient toEntity(PatientRequestDTO patientRequestDTO) {
        return new Patient(
                patientRequestDTO.getName(),
                patientRequestDTO.getEmail(),
                patientRequestDTO.getAddress(),
                LocalDate.parse(patientRequestDTO.getDateOfBirth()),
                LocalDate.parse(patientRequestDTO.getRegisteredDate())
        );
    }
}
