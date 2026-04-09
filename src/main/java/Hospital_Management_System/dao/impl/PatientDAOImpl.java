package Hospital_Management_System.dao.impl;

import Hospital_Management_System.dao.PatientDAO;
import Hospital_Management_System.model.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Repository
@Transactional
public class PatientDAOImpl implements PatientDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Patient patient) {
        entityManager.persist(patient);
    }

    @Override
    public Optional<Patient> findById(Long id) {
        Patient patient = entityManager.find(Patient.class, id);
        return Optional.ofNullable(patient);
    }

    @Override
    public ArrayList<Patient> findAll() {
        TypedQuery<Patient> query = entityManager.createQuery(
            "SELECT p FROM Patient p", Patient.class);
        return new ArrayList<>(query.getResultList());
    }

    @Override
    public ArrayList<Patient> findByName(String name) {
        TypedQuery<Patient> query = entityManager.createQuery(
            "SELECT p FROM Patient p WHERE p.name LIKE :name", Patient.class);
        query.setParameter("name", "%" + name + "%");
        return new ArrayList<>(query.getResultList());
    }

    @Override
    public void update(Patient patient) {
        entityManager.merge(patient);
    }

    @Override
    public void delete(Long id) {
        Patient patient = entityManager.find(Patient.class, id);
        if (patient != null) {
            entityManager.remove(patient);
        }
    }
}