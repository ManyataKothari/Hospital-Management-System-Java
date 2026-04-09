package Hospital_Management_System.service;

import Hospital_Management_System.dao.BillingDAO;
import Hospital_Management_System.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class BillingService {

    @Autowired
    private BillingDAO billingDAO;

    public void createBill(Bill bill) {
        billingDAO.save(bill);
    }

    public ArrayList<Bill> getAllBills() {
        return billingDAO.findAll();
    }

    public Optional<Bill> getBillById(Long id) {
        return billingDAO.findById(id);
    }

    public ArrayList<Bill> getBillsByPatient(Long patientId) {
        return billingDAO.findByPatientId(patientId);
    }

    public ArrayList<Bill> getUnpaidBills() {
        return billingDAO.findUnpaidBills();
    }

    public void markAsPaid(Long id) {
        Optional<Bill> bill = billingDAO.findById(id);
        bill.ifPresent(b -> {
            b.setPaid(true);
            billingDAO.update(b);
        });
    }

    public void updateBill(Bill bill) {
        billingDAO.update(bill);
    }

    public void deleteBill(Long id) {
        billingDAO.delete(id);
    }
}