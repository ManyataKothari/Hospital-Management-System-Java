package Hospital_Management_System.controller;

import Hospital_Management_System.model.Patient;
import Hospital_Management_System.service.AppointmentService;
import Hospital_Management_System.service.BillingService;
import Hospital_Management_System.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private BillingService billingService;

    @GetMapping
    public String listPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patients/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patients/add";
    }

    @PostMapping("/add")
    public String addPatient(@ModelAttribute Patient patient) {
        patientService.addPatient(patient);
        return "redirect:/patients";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        patientService.getPatientById(id).ifPresent(p ->
            model.addAttribute("patient", p));
        return "patients/edit";
    }

    @PostMapping("/edit/{id}")
    public String updatePatient(@PathVariable Long id,
                                @ModelAttribute Patient patient) {
        patient.setId(id);
        patientService.updatePatient(patient);
        return "redirect:/patients";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }

    @GetMapping("/search")
    public String searchPatients(@RequestParam String name, Model model) {
        model.addAttribute("patients", patientService.searchPatients(name));
        return "patients/list";
    }

    @GetMapping("/history/{id}")
    public String patientHistory(@PathVariable Long id, Model model) {
        patientService.getPatientById(id).ifPresent(p -> {
            model.addAttribute("patient", p);
            model.addAttribute("appointments",
                appointmentService.getAppointmentsByPatient(id));
            model.addAttribute("bills",
                billingService.getBillsByPatient(id));

            double totalBilled = billingService.getBillsByPatient(id)
                .stream().mapToDouble(b -> b.getAmount()).sum();
            double totalPaid = billingService.getBillsByPatient(id)
                .stream().filter(b -> b.isPaid())
                .mapToDouble(b -> b.getAmount()).sum();

            model.addAttribute("totalBilled", totalBilled);
            model.addAttribute("totalPaid", totalPaid);
            model.addAttribute("totalDue", totalBilled - totalPaid);
        });
        return "patients/history";
    }
}