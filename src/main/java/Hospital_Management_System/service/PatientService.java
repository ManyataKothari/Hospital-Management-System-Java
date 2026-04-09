package Hospital_Management_System.service;

import Hospital_Management_System.dao.PatientDAO;
import Hospital_Management_System.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientDAO patientDAO;

    public void addPatient(Patient patient) {
        patientDAO.save(patient);
    }

    public ArrayList<Patient> getAllPatients() {
        return patientDAO.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientDAO.findById(id);
    }

    public ArrayList<Patient> searchPatients(String name) {
        return patientDAO.findByName(name);
    }

    public void updatePatient(Patient patient) {
        patientDAO.update(patient);
    }

    public void deletePatient(Long id) {
        patientDAO.delete(id);
    }
}