package Hospital_Management_System.dao;

import Hospital_Management_System.model.Patient;
import java.util.ArrayList;
import java.util.Optional;

public interface PatientDAO {
    void save(Patient patient);
    Optional<Patient> findById(Long id);
    ArrayList<Patient> findAll();
    ArrayList<Patient> findByName(String name);
    void update(Patient patient);
    void delete(Long id);
}
