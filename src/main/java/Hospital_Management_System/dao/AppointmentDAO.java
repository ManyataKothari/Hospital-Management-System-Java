package Hospital_Management_System.dao;

import Hospital_Management_System.model.Appointment;
import java.util.ArrayList;
import java.util.Optional;

public interface AppointmentDAO {
    void save(Appointment appointment);
    Optional<Appointment> findById(Long id);
    ArrayList<Appointment> findAll();
    ArrayList<Appointment> findByPatientId(Long patientId);
    ArrayList<Appointment> findByDoctorId(Long doctorId);
    void update(Appointment appointment);
    void delete(Long id);
}
