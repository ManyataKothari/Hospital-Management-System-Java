package Hospital_Management_System.controller;

import Hospital_Management_System.model.Bill;
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
    public String showAddForm(Model model) {
        model.addAttribute("bill", new Bill());
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        return "billing/add";
    }

    @PostMapping("/add")
    public String addBill(@ModelAttribute Bill bill) {
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
}
