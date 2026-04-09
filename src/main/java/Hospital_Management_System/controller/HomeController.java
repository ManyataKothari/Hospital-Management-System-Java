package Hospital_Management_System.controller;

import Hospital_Management_System.service.AppointmentService;
import Hospital_Management_System.service.BillingService;
import Hospital_Management_System.service.DoctorService;
import Hospital_Management_System.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private BillingService billingService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("totalPatients", patientService.getAllPatients().size());
        model.addAttribute("totalDoctors", doctorService.getAllDoctors().size());
        model.addAttribute("totalAppointments", appointmentService.getAllAppointments().size());
        model.addAttribute("unpaidBills", billingService.getUnpaidBills().size());
        return "index";
    }
}
