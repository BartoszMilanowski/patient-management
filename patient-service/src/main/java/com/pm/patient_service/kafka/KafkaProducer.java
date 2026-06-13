package com.pm.patient_service.kafka;

import com.pm.patient_service.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class KafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendEvent(Patient patient){
        PatientEvent event = new PatientEvent(
                patient.getId().toString(),
                patient.getName(),
                patient.getEmail(),
                "PATIENT_CREATED"
        );

        try {
            kafkaTemplate.send("patient", objectMapper.writeValueAsString(event));
            log.info("Sent PatientCreated event for patient: {}", patient.getId());
        } catch (Exception e){
            log.error("Error sending PatientCreated event for patient: {}", patient.getId(), e);
        }
    }
}
