package Hospital_Management_System.dao.impl;

import Hospital_Management_System.dao.AppointmentDAO;
import Hospital_Management_System.model.Appointment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Repository
@Transactional
public class AppointmentDAOImpl implements AppointmentDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Appointment appointment) {
        entityManager.persist(appointment);
    }

    @Override
    public Optional<Appointment> findById(Long id) {
        Appointment appointment = entityManager.find(Appointment.class, id);
        return Optional.ofNullable(appointment);
    }

    @Override
    public ArrayList<Appointment> findAll() {
        TypedQuery<Appointment> query = entityManager.createQuery(
            "SELECT a FROM Appointment a", Appointment.class);
        return new ArrayList<>(query.getResultList());
    }

    @Override
    public ArrayList<Appointment> findByPatientId(Long patientId) {
        TypedQuery<Appointment> query = entityManager.createQuery(
            "SELECT a FROM Appointment a WHERE a.patient.id = :patientId", Appointment.class);
        query.setParameter("patientId", patientId);
        return new ArrayList<>(query.getResultList());
    }

    @Override
    public ArrayList<Appointment> findByDoctorId(Long doctorId) {
        TypedQuery<Appointment> query = entityManager.createQuery(
            "SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId", Appointment.class);
        query.setParameter("doctorId", doctorId);
        return new ArrayList<>(query.getResultList());
    }

    @Override
    public void update(Appointment appointment) {
        entityManager.merge(appointment);
    }

    @Override
    public void delete(Long id) {
        Appointment appointment = entityManager.find(Appointment.class, id);
        if (appointment != null) {
            entityManager.remove(appointment);
        }
    }
}
