package Hospital_Management_System.controller;

import Hospital_Management_System.model.Appointment;
import Hospital_Management_System.model.Bill;
import Hospital_Management_System.model.Patient;
import Hospital_Management_System.service.AppointmentService;
import Hospital_Management_System.service.BillingService;
import Hospital_Management_System.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/billing")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public String listBills(Model model) {
        model.addAttribute("bills", billingService.getAllBills());
        return "billing/list";
    }

    @GetMapping("/add")
    public String showAddForm(
            @RequestParam(required = false) Long patientId,
            Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        if (patientId != null) {
            patientService.getPatientById(patientId).ifPresent(p -> {
                model.addAttribute("selectedPatient", p);
                model.addAttribute("appointments",
                    appointmentService.getAppointmentsByPatient(patientId));
            });
        } else {
            model.addAttribute("appointments", appointmentService.getAllAppointments());
        }
        return "billing/add";
    }

    @PostMapping("/add")
    public String addBill(
            @RequestParam Long patientId,
            @RequestParam(required = false) Long appointmentId,
            @RequestParam double amount,
            @RequestParam(required = false) String description) {

        Patient patient = patientService.getPatientById(patientId).orElse(null);

        Appointment appointment = null;
        if (appointmentId != null) {
            appointment = appointmentService.getAppointmentById(appointmentId).orElse(null);
        }

        Bill bill = new Bill();
        bill.setPatient(patient);
        bill.setAppointment(appointment);
        bill.setAmount(amount);
        bill.setDescription(description);
        bill.setPaid(false);

        billingService.createBill(bill);
        return "redirect:/billing";
    }

    @GetMapping("/pay/{id}")
    public String markAsPaid(@PathVariable Long id) {
        billingService.markAsPaid(id);
        return "redirect:/billing";
    }

    @GetMapping("/delete/{id}")
    public String deleteBill(@PathVariable Long id) {
        billingService.deleteBill(id);
        return "redirect:/billing";
    }

    @GetMapping("/unpaid")
    public String unpaidBills(Model model) {
        model.addAttribute("bills", billingService.getUnpaidBills());
        return "billing/list";
    }

    @GetMapping("/print/{id}")
    public String printBill(@PathVariable Long id, Model model) {
        billingService.getBillById(id).ifPresent(b -> model.addAttribute("bill", b));
        return "billing/print";
    }
}