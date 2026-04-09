package Hospital_Management_System.dao;

import Hospital_Management_System.model.Bill;
import java.util.ArrayList;
import java.util.Optional;

public interface BillingDAO {
    void save(Bill bill);
    Optional<Bill> findById(Long id);
    ArrayList<Bill> findAll();
    ArrayList<Bill> findByPatientId(Long patientId);
    void update(Bill bill);
    void delete(Long id);
    ArrayList<Bill> findUnpaidBills();
}
