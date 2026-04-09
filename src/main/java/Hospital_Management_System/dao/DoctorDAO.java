package Hospital_Management_System.dao;

import Hospital_Management_System.model.Doctor;
import java.util.ArrayList;
import java.util.Optional;

public interface DoctorDAO {
    void save(Doctor doctor);
    Optional<Doctor> findById(Long id);
    ArrayList<Doctor> findAll();
    ArrayList<Doctor> findBySpecialization(String specialization);
    void update(Doctor doctor);
    void delete(Long id);
}
