package Hospital_Management_System.service;

import Hospital_Management_System.dao.DoctorDAO;
import Hospital_Management_System.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorDAO doctorDAO;

    public void addDoctor(Doctor doctor) {
        doctorDAO.save(doctor);
    }

    public ArrayList<Doctor> getAllDoctors() {
        return doctorDAO.findAll();
    }

    public Optional<Doctor> getDoctorById(Long id) {
        return doctorDAO.findById(id);
    }

    public ArrayList<Doctor> searchBySpecialization(String specialization) {
        return doctorDAO.findBySpecialization(specialization);
    }

    public void updateDoctor(Doctor doctor) {
        doctorDAO.update(doctor);
    }

    public void deleteDoctor(Long id) {
        doctorDAO.delete(id);
    }
}