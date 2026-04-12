package Hospital_Management_System.service;

import Hospital_Management_System.dao.PatientDAO;
import Hospital_Management_System.dao.AppointmentDAO;
import Hospital_Management_System.dao.BillingDAO;
import Hospital_Management_System.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientDAO patientDAO;

    @Autowired
    private AppointmentDAO appointmentDAO;

    @Autowired
    private BillingDAO billingDAO;

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
        // First delete all bills for this patient
        billingDAO.findByPatientId(id).forEach(bill -> billingDAO.delete(bill.getId()));

        // Then delete all appointments for this patient
        appointmentDAO.findByPatientId(id).forEach(apt -> appointmentDAO.delete(apt.getId()));

        // Now safe to delete the patient
        patientDAO.delete(id);
    }
}