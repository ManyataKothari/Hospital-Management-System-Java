package Hospital_Management_System.service;

import Hospital_Management_System.dao.AppointmentDAO;
import Hospital_Management_System.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentDAO appointmentDAO;

    public void bookAppointment(Appointment appointment) {
        appointmentDAO.save(appointment);
    }

    public ArrayList<Appointment> getAllAppointments() {
        return appointmentDAO.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentDAO.findById(id);
    }

    public ArrayList<Appointment> getAppointmentsByPatient(Long patientId) {
        return appointmentDAO.findByPatientId(patientId);
    }

    public ArrayList<Appointment> getAppointmentsByDoctor(Long doctorId) {
        return appointmentDAO.findByDoctorId(doctorId);
    }

    public void updateAppointment(Appointment appointment) {
        appointmentDAO.update(appointment);
    }

    public void cancelAppointment(Long id) {
        Optional<Appointment> appointment = appointmentDAO.findById(id);
        appointment.ifPresent(a -> {
            a.setStatus("CANCELLED");
            appointmentDAO.update(a);
        });
    }

    public void deleteAppointment(Long id) {
        appointmentDAO.delete(id);
    }
}