package Hospital_Management_System.dao.impl;

import Hospital_Management_System.dao.BillingDAO;
import Hospital_Management_System.model.Bill;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Repository
@Transactional
public class BillingDAOImpl implements BillingDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Bill bill) {
        entityManager.persist(bill);
    }

    @Override
    public Optional<Bill> findById(Long id) {
        Bill bill = entityManager.find(Bill.class, id);
        return Optional.ofNullable(bill);
    }

    @Override
    public ArrayList<Bill> findAll() {
        TypedQuery<Bill> query = entityManager.createQuery(
            "SELECT b FROM Bill b", Bill.class);
        return new ArrayList<>(query.getResultList());
    }

    @Override
    public ArrayList<Bill> findByPatientId(Long patientId) {
        TypedQuery<Bill> query = entityManager.createQuery(
            "SELECT b FROM Bill b WHERE b.patient.id = :patientId", Bill.class);
        query.setParameter("patientId", patientId);
        return new ArrayList<>(query.getResultList());
    }

    @Override
    public void update(Bill bill) {
        entityManager.merge(bill);
    }

    @Override
    public void delete(Long id) {
        Bill bill = entityManager.find(Bill.class, id);
        if (bill != null) {
            entityManager.remove(bill);
        }
    }

    @Override
    public ArrayList<Bill> findUnpaidBills() {
        TypedQuery<Bill> query = entityManager.createQuery(
            "SELECT b FROM Bill b WHERE b.paid = false", Bill.class);
        return new ArrayList<>(query.getResultList());
    }
}
