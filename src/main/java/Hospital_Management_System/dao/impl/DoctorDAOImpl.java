package Hospital_Management_System.dao.impl;

import Hospital_Management_System.dao.DoctorDAO;
import Hospital_Management_System.model.Doctor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Repository
@Transactional
public class DoctorDAOImpl implements DoctorDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Doctor doctor) {
        entityManager.persist(doctor);
    }

    @Override
    public Optional<Doctor> findById(Long id) {
        Doctor doctor = entityManager.find(Doctor.class, id);
        return Optional.ofNullable(doctor);
    }

    @Override
    public ArrayList<Doctor> findAll() {
        TypedQuery<Doctor> query = entityManager.createQuery(
            "SELECT d FROM Doctor d", Doctor.class);
        return new ArrayList<>(query.getResultList());
    }

    @Override
    public ArrayList<Doctor> findBySpecialization(String specialization) {
        TypedQuery<Doctor> query = entityManager.createQuery(
            "SELECT d FROM Doctor d WHERE d.specialization LIKE :spec", Doctor.class);
        query.setParameter("spec", "%" + specialization + "%");
        return new ArrayList<>(query.getResultList());
    }

    @Override
    public void update(Doctor doctor) {
        entityManager.merge(doctor);
    }

    @Override
    public void delete(Long id) {
        Doctor doctor = entityManager.find(Doctor.class, id);
        if (doctor != null) {
            entityManager.remove(doctor);
        }
    }
}
